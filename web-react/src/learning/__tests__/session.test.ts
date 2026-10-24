import { describe, it, expect } from "vitest";
import { readFileSync } from "node:fs";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import {
  initSession,
  resumeSession,
  sessionReducer,
  currentQuestion,
  currentDrill,
  duePhrases,
  scheduleFromSession,
  scheduleReview,
  sessionSummary,
  normalizeAnswer,
  checkAccepted,
  STORY_STEPS,
} from "../session";
import { setStories, storyById } from "../stories";
import { revive, makeDefaults } from "../../domain/store";
import type { Story, StoryFile } from "../types";

const __dirname = dirname(fileURLToPath(import.meta.url));
const NOW = 1_700_000_000_000;
const DAY = 86_400_000;

/** L'histoire pilote réelle — le test valide le contenu publié, pas une copie. */
const file = JSON.parse(
  readFileSync(join(__dirname, "..", "..", "..", "public", "data", "stories.v1.json"), "utf-8")
) as StoryFile;
setStories(file);
const story = storyById("clinical-symptoms-001") as Story;

function runTo(state: Parameters<typeof sessionReducer>[0], actions: Parameters<typeof sessionReducer>[1][]) {
  let s = state;
  for (const a of actions) s = sessionReducer(s, a, story);
  return s;
}

describe("normalizeAnswer", () => {
  it("normalise casse, ponctuation et espaces", () => {
    expect(normalizeAnswer("  No,   I do NOT. ")).toBe("no i do not");
    expect(normalizeAnswer("Yes!")).toBe("yes");
  });

  it("accepte l'apostrophe typographique (doesn't)", () => {
    expect(normalizeAnswer("No, he doesn’t.")).toBe("no he doesn't");
  });

  it("ne supprime jamais une négation ni un nombre", () => {
    expect(normalizeAnswer("two days ago")).toBe("two days ago");
    expect(normalizeAnswer("does not")).not.toBe("does");
  });
});

describe("checkAccepted", () => {
  it("accepte une réponse courte correcte (un mot)", () => {
    expect(checkAccepted("No", ["no", "no he does not"])).toBe(true);
  });

  it("rejette une réponse qui inverse un fait (fièvre)", () => {
    expect(checkAccepted("Yes", ["no", "no he does not"])).toBe(false);
  });

  it("rejette la chaîne vide et les inclusions partielles", () => {
    expect(checkAccepted("", ["no"])).toBe(false);
    expect(checkAccepted("nooo", ["no"])).toBe(false);
  });
});

describe("initSession", () => {
  it("démarre sur l'écoute quand aucune expression n'est due", () => {
    const s = initSession(story);
    expect(s.step).toBe("LISTEN_DISCOVER");
    expect(s.reviewIds).toEqual([]);
  });

  it("démarre sur la révision avec les expressions dues de l'histoire", () => {
    const s = initSession(story, { duePhraseIds: ["en:no-fever:v1", "en:inconnu:v9"] });
    expect(s.step).toBe("REVIEW");
    expect(s.reviewIds).toEqual(["en:no-fever:v1"]); // id hors histoire écarté
  });
});

describe("parcours complet — chemin nominal", () => {
  it("traverse les 8 étapes et termine sans erreur", () => {
    let s = initSession(story);
    // 3 étapes d'écoute
    s = sessionReducer(s, { type: "ADVANCE" }, story);
    expect(s.step).toBe("LISTEN_WITH_HELP");
    s = sessionReducer(s, { type: "ADVANCE" }, story);
    s = sessionReducer(s, { type: "ADVANCE" }, story);
    expect(s.step).toBe("CIRCLING");
    // 5 questions de circling, réponses courtes correctes
    const answers = ["yes", "no", "a headache", "a headache", "lina"];
    for (const a of answers) {
      const q = currentQuestion(s, story);
      expect(q).toBeDefined();
      s = sessionReducer(s, { type: "SUBMIT_ANSWER", value: a }, story);
      expect(s.lastCorrect).toBe(true);
      s = sessionReducer(s, { type: "ADVANCE" }, story);
    }
    expect(s.step).toBe("PATTERN_PRACTICE");
    // 3 drills
    for (const d of ["has", "does", "have"]) {
      expect(currentDrill(s, story)).toBeDefined();
      s = sessionReducer(s, { type: "SUBMIT_DRILL", value: d }, story);
      expect(s.lastCorrect).toBe(true);
      s = sessionReducer(s, { type: "ADVANCE" }, story);
    }
    expect(s.step).toBe("RETELL");
    s = sessionReducer(s, { type: "RETELL_DONE", attempted: true }, story);
    s = sessionReducer(s, { type: "ADVANCE" }, story);
    expect(s.step).toBe("TRANSFER_AND_SUMMARY");
    // 2 questions de transfert
    for (const a of ["a sore throat", "no"]) {
      s = sessionReducer(s, { type: "SUBMIT_ANSWER", value: a }, story);
      expect(s.lastCorrect).toBe(true);
      s = sessionReducer(s, { type: "ADVANCE" }, story);
    }
    // tâche de production finale puis bilan
    expect(s.transferTaskPending).toBe(true);
    s = sessionReducer(s, { type: "TRANSFER_TASK_DONE", attempted: true }, story);
    s = sessionReducer(s, { type: "ADVANCE" }, story);
    expect(s.finished).toBe(true);

    const sum = sessionSummary(s, story);
    expect(sum.total).toBe(10); // 5 questions + 3 drills + 2 transfert
    expect(sum.firstTry).toBe(10);
    expect(sum.mistakes).toBe(0);
    expect(sum.helped).toBe(0);
    expect(sum.retellAttempted).toBe(true);
    expect(sum.transferAttempted).toBe(true);
  });
});

describe("erreurs et aide — sémantique des trois états", () => {
  it("une erreur sur la négation marque la phrase difficile et n'est pas effacée par une réussite", () => {
    let s = initSession(story);
    s = runTo(s, [{ type: "ADVANCE" }, { type: "ADVANCE" }, { type: "ADVANCE" }]);
    // Q2 : « Does he have a fever? » → oui = inversion du fait
    s = sessionReducer(s, { type: "ADVANCE" }, story); // q1
    s = sessionReducer(s, { type: "SUBMIT_ANSWER", value: "yes" }, story);
    s = sessionReducer(s, { type: "SUBMIT_ANSWER", value: "no" }, story); // 2e tentative
    expect(s.lastCorrect).toBe(true);
    expect(s.stats.mistakeIds).toContain("q2"); // l'erreur initiale reste
    expect(s.stats.firstTryIds).not.toContain("q2");
    expect(s.stats.difficultPhraseIds).toContain("en:no-fever:v1");
  });

  it("REVEAL (« je ne sais pas encore ») = aidé, ni réussite ni erreur", () => {
    let s = initSession(story);
    s = runTo(s, [{ type: "ADVANCE" }, { type: "ADVANCE" }, { type: "ADVANCE" }]);
    s = sessionReducer(s, { type: "REVEAL" }, story);
    expect(s.revealed).toBe(true);
    expect(s.stats.helpedIds).toContain("q1");
    expect(s.stats.firstTryIds).not.toContain("q1");
    expect(s.stats.mistakeIds).not.toContain("q1");
  });

  it("un second REVEAL sur la même question ne double pas l'aide", () => {
    let s = initSession(story);
    s = runTo(s, [{ type: "ADVANCE" }, { type: "ADVANCE" }, { type: "ADVANCE" }]);
    s = sessionReducer(s, { type: "REVEAL" }, story);
    s = sessionReducer(s, { type: "REVEAL" }, story);
    expect(s.stats.helpedIds.filter((x) => x === "q1").length).toBe(1);
  });

  it("l'aide après une réussite retire la réussite « sans aide »", () => {
    let s = initSession(story);
    s = runTo(s, [{ type: "ADVANCE" }, { type: "ADVANCE" }, { type: "ADVANCE" }]);
    s = sessionReducer(s, { type: "SUBMIT_ANSWER", value: "yes" }, story);
    expect(s.stats.firstTryIds).toContain("q1");
    s = sessionReducer(s, { type: "HELP" }, story);
    expect(s.stats.firstTryIds).not.toContain("q1");
    expect(s.stats.helpedIds).toContain("q1");
  });

  it("une réponse resoumise après validation est ignorée (pas de double comptage)", () => {
    let s = initSession(story);
    s = runTo(s, [{ type: "ADVANCE" }, { type: "ADVANCE" }, { type: "ADVANCE" }]);
    s = sessionReducer(s, { type: "SUBMIT_ANSWER", value: "yes" }, story);
    const before = s.stats.firstTryIds.length;
    s = sessionReducer(s, { type: "SUBMIT_ANSWER", value: "no" }, story);
    expect(s.stats.firstTryIds.length).toBe(before);
    expect(s.lastCorrect).toBe(true);
  });

  it("une action invalide pour l'étape courante est ignorée", () => {
    const s0 = initSession(story);
    const s1 = sessionReducer(s0, { type: "SUBMIT_ANSWER", value: "yes" }, story);
    expect(s1).toBe(s0); // pas de question pendant l'écoute → état inchangé
  });
});

describe("reprise après interruption (régressions)", () => {
  const atCircling = () =>
    runTo(initSession(story), [
      { type: "ADVANCE" }, { type: "ADVANCE" }, { type: "ADVANCE" },
    ]);

  it("REGRESS-1 : une question re-répondue après reprise ne double pas les stats", () => {
    // Avant correction : q1 ratée (mistakeIds), interruption, reprise, q1
    // réussie → le verdict était réenregistré (id en réussite ET en erreur).
    let s = atCircling();
    s = sessionReducer(s, { type: "SUBMIT_ANSWER", value: "no" }, story); // q1 ratée
    expect(s.stats.mistakeIds).toContain("q1");
    // Interruption puis reprise : le moteur repart avec attemptsOnCurrent = 0.
    const resumed = resumeSession(
      story,
      {
        contentVersion: story.contentVersion,
        step: "CIRCLING",
        qIndex: 0,
        drillIndex: 0,
        reviewIndex: 0,
        stats: s.stats,
        retellAttempted: false,
        transferTaskAttempted: false,
        transferTaskPending: false,
      },
      []
    );
    const after = sessionReducer(resumed, { type: "SUBMIT_ANSWER", value: "yes" }, story);
    expect(after.lastCorrect).toBe(true); // la réponse est validée…
    expect(after.stats.firstTryIds).not.toContain("q1"); // …mais pas comptée deux fois
    expect(after.stats.mistakeIds).toContain("q1"); // l'erreur initiale reste
  });

  it("REGRESS-2 : reviewIndex recalé si la liste d'expressions dues a changé", () => {
    // Sauvegardé à l'index 5 avec 3 expressions dues à l'époque ; aujourd'hui
    // une seule est due → l'index doit être borné, pas hors liste.
    const s = resumeSession(
      story,
      {
        contentVersion: story.contentVersion,
        step: "REVIEW",
        qIndex: 0,
        drillIndex: 0,
        reviewIndex: 5,
        stats: { firstTryIds: [], mistakeIds: [], helpedIds: [], difficultPhraseIds: [] },
        retellAttempted: false,
        transferTaskAttempted: false,
        transferTaskPending: false,
      },
      ["en:no-fever:v1"]
    );
    expect(s.step).toBe("REVIEW");
    expect(s.reviewIndex).toBe(0);
    expect(s.reviewIds).toEqual(["en:no-fever:v1"]);
  });

  it("REGRESS-2b : reprise de REVIEW sans expression due → écoute directe", () => {
    const s = resumeSession(
      story,
      {
        contentVersion: story.contentVersion,
        step: "REVIEW",
        qIndex: 0,
        drillIndex: 0,
        reviewIndex: 1,
        stats: { firstTryIds: [], mistakeIds: [], helpedIds: [], difficultPhraseIds: [] },
        retellAttempted: false,
        transferTaskAttempted: false,
        transferTaskPending: false,
      },
      []
    );
    expect(s.step).toBe("LISTEN_DISCOVER");
    expect(s.reviewIndex).toBe(0);
  });

  it("les curseurs de reprise sont bornés aux tableaux du contenu", () => {
    const s = resumeSession(
      story,
      {
        contentVersion: story.contentVersion,
        step: "CIRCLING",
        qIndex: 99,
        drillIndex: 99,
        reviewIndex: 0,
        stats: { firstTryIds: [], mistakeIds: [], helpedIds: [], difficultPhraseIds: [] },
        retellAttempted: false,
        transferTaskAttempted: false,
        transferTaskPending: false,
      },
      []
    );
    expect(s.qIndex).toBe(story.questions.length - 1);
    expect(s.drillIndex).toBe(story.drill.items.length - 1);
    expect(currentQuestion(s, story)).toBeDefined();
  });
});

describe("mémoire d'expressions", () => {
  it("une expression jamais vue n'est pas « due »", () => {
    expect(duePhrases(story, {}, NOW)).toEqual([]);
  });

  it("une expression échue est due ; une expression future ne l'est pas", () => {
    const sm2 = {
      "en:no-fever:v1": { rep: 1, ef: 2.5, iv: 1, due: NOW - 1 },
      "en:have-headache:v1": { rep: 1, ef: 2.5, iv: 1, due: NOW + 5 * DAY },
    };
    expect(duePhrases(story, sm2, NOW)).toEqual(["en:no-fever:v1"]);
  });

  it("scheduleFromSession : difficile → revue demain, réussie → intervalle normal", () => {
    const s = runTo(
      initSession(story),
      [
        { type: "ADVANCE" }, { type: "ADVANCE" }, { type: "ADVANCE" }, // → CIRCLING
        { type: "SUBMIT_ANSWER", value: "yes" }, // q1 correcte
        { type: "ADVANCE" },
      ]
    );
    const wrong = sessionReducer(s, { type: "SUBMIT_ANSWER", value: "yes" }, story); // q2 ratée
    expect(wrong.stats.difficultPhraseIds).toContain("en:no-fever:v1");
    const out = scheduleFromSession(wrong, story, {}, NOW);
    expect(out["en:no-fever:v1"]).toMatchObject({ rep: 0, iv: 1, due: NOW + DAY });
    expect(out["en:have-headache:v1"]).toMatchObject({ rep: 1, iv: 1, due: NOW + DAY });
  });

  it("scheduleReview : souvenu (q4) vs oublié (q2)", () => {
    const ok = scheduleReview("en:no-fever:v1", true, {}, NOW);
    const ko = scheduleReview("en:no-fever:v1", false, ok, NOW);
    expect(ok["en:no-fever:v1"].rep).toBe(1);
    expect(ko["en:no-fever:v1"].rep).toBe(0);
  });

  it("scheduleFromSession préserve les expressions d'autres histoires", () => {
    const prev = { "en:other:v1": { rep: 5, ef: 2.2, iv: 30, due: NOW } };
    const out = scheduleFromSession(initSession(story), story, prev, NOW);
    expect(out["en:other:v1"]).toEqual(prev["en:other:v1"]);
  });
});

describe("chargeur d'histoires", () => {
  it("ne sert jamais un contenu draft", () => {
    expect(storyById("clinical-symptoms-001")).toBeDefined();
    const draft = { ...file, stories: [{ ...story, storyId: "x", publicationStatus: "draft" as const }] };
    setStories(draft);
    expect(storyById("x")).toBeUndefined();
    setStories(file); // restaurer
  });

  it("REGRESS-3 : un échec de chargement est distingué de « pas encore chargé »", async () => {
    const { loadStories, storiesFailed, storiesSettled } = await import("../stories");
    const orig = global.fetch;
    global.fetch = (() => Promise.reject(new Error("offline"))) as typeof fetch;
    await expect(loadStories()).rejects.toThrow("offline");
    expect(storiesFailed()).toBe(true);
    expect(storiesSettled()).toBe(true); // un id absent est alors réellement inconnu
    global.fetch = orig;
    setStories(file); // restaurer pour les autres tests
    expect(storiesSettled()).toBe(true);
    expect(storiesFailed()).toBe(false);
  });
});

describe("store — storyProgress et exprSm2", () => {
  it("revive réintègre storyProgress valide et écarte les entrées cassées", () => {
    const base = makeDefaults(NOW);
    const raw = JSON.stringify({
      ...base,
      storyProgress: {
        "clinical-symptoms-001": {
          storyId: "clinical-symptoms-001",
          contentVersion: 1,
          step: "CIRCLING",
          qIndex: 2,
          drillIndex: 0,
          reviewIndex: 0,
          stats: { firstTryIds: ["q1"], mistakeIds: [], helpedIds: [], difficultPhraseIds: [] },
          retellAttempted: false,
          transferTaskAttempted: false,
          transferTaskPending: false,
          savedAt: NOW,
          completedAt: 0,
          rewardClaimed: false,
        },
        "bogus": { storyId: "bogus", step: "NOT_A_STEP" },
      },
      exprSm2: {
        "en:no-fever:v1": { rep: 1, ef: 2.5, iv: 1, due: NOW },
        "junk": "pas un sched",
      },
    });
    const st = revive(raw, NOW);
    expect(st.storyProgress["clinical-symptoms-001"].qIndex).toBe(2);
    expect(st.storyProgress["bogus"]).toBeUndefined();
    expect(Object.keys(st.exprSm2)).toEqual(["en:no-fever:v1"]);
  });

  it("un store ancien (sans champs histoire) reste valide", () => {
    const legacy = JSON.stringify(makeDefaults(NOW));
    const st = revive(legacy, NOW);
    expect(st.storyProgress).toEqual({});
    expect(st.exprSm2).toEqual({});
  });

  it("les étapes du moteur sont exactement celles du guide", () => {
    expect(STORY_STEPS).toEqual([
      "REVIEW",
      "LISTEN_DISCOVER",
      "LISTEN_WITH_HELP",
      "LISTEN_CHECK",
      "CIRCLING",
      "PATTERN_PRACTICE",
      "RETELL",
      "TRANSFER_AND_SUMMARY",
    ]);
  });
});

describe("store — récompense idempotente (anti double XP)", () => {
  it("le second claim ne rapporte rien ; la reprise d'une séance finie repart à zéro", async () => {
    const store = await import("../../domain/store");
    const { startStorySession, claimStoryReward, STORY_SESSION_XP, getState } = store;
    startStorySession("clinical-symptoms-001", 1, NOW);
    const first = claimStoryReward("clinical-symptoms-001", NOW);
    expect(first.xp).toBe(STORY_SESSION_XP);
    // Re-affichage du bilan, StrictMode, double clic : plus rien.
    const second = claimStoryReward("clinical-symptoms-001", NOW + 5_000);
    expect(second.xp).toBe(0);
    expect(getState().storyProgress["clinical-symptoms-001"].rewardClaimed).toBe(true);
    // Une NOUVELLE exécution (nouvelle séance) repart à zéro.
    startStorySession("clinical-symptoms-001", 1, NOW + 60_000);
    const again = claimStoryReward("clinical-symptoms-001", NOW + 120_000);
    expect(again.xp).toBe(STORY_SESSION_XP);
  });
});
