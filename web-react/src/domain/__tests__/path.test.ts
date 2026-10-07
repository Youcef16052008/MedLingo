import { describe, it, expect } from "vitest";
import { MODULES } from "../../data/modules";
import {
  buildPath,
  lessonKey,
  nextLesson,
  UNLOCK_SCORE,
  LEVELS_PER_UNIT,
} from "../path";

describe("parcours", () => {
  it("génère 15 unités de 6 leçons (dont L6 BOSS)", () => {
    const path = buildPath({}, {});
    expect(path).toHaveLength(MODULES.length);
    expect(path.every((u) => u.nodes.length === LEVELS_PER_UNIT)).toBe(true);
    expect(path.every((u) => u.nodes[5].isBoss)).toBe(true);
    expect(path.every((u) => !u.nodes.slice(0, 5).some((n) => n.isBoss))).toBe(true);
  });

  it("sans progression : L1 ouverte, L2 → L6 verrouillées", () => {
    const [unit] = buildPath({}, {});
    expect(unit.nodes[0].state).toBe("available");
    expect(unit.nodes.slice(1).every((n) => n.state === "locked")).toBe(true);
    expect(unit.completed).toBe(0);
  });

  it("une leçon validée à ≥ 70 % ouvre la suivante", () => {
    const best = { [lessonKey("anat", 1)]: UNLOCK_SCORE };
    const [unit] = buildPath(best, {});
    expect(unit.nodes[0].state).toBe("done");
    expect(unit.nodes[1].state).toBe("available");
    expect(unit.nodes[2].state).toBe("locked");
    expect(unit.completed).toBe(1);
  });

  it("une leçon sous le seuil ne débloque rien", () => {
    const best = { [lessonKey("anat", 1)]: UNLOCK_SCORE - 1 };
    const [unit] = buildPath(best, {});
    expect(unit.nodes[0].state).toBe("available");
    expect(unit.nodes[0].score).toBe(69);
    expect(unit.nodes[1].state).toBe("locked");
  });

  it("propage le déblocage sur les 6 leçons", () => {
    const best: Record<string, number> = {};
    for (let l = 1; l <= 5; l++) best[lessonKey("physio", l)] = 85;
    const unit = buildPath(best, {})[1];
    expect(unit.module.id).toBe("physio");
    expect(unit.nodes.slice(0, 5).every((n) => n.state === "done")).toBe(true);
    expect(unit.nodes[5].state).toBe("available");
    expect(unit.completed).toBe(5);
  });

  it("les unités sont indépendantes : une unité validée ne débloche pas la suivante", () => {
    const best: Record<string, number> = {};
    for (let l = 1; l <= 6; l++) best[lessonKey("anat", l)] = 95;
    const path = buildPath(best, {});
    expect(path[0].completed).toBe(6);
    expect(path[1].nodes[0].state).toBe("available");
    expect(path[1].completed).toBe(0);
  });

  it("nextLesson renvoie la première leçon ouverte", () => {
    const [unit] = buildPath({ [lessonKey("anat", 1)]: 70 }, {});
    expect(nextLesson(unit)?.level).toBe(2);
    expect(nextLesson(buildPath({}, {})[0])?.level).toBe(1);
  });

  it("moduleBest ne débloque rien : c'est un repli d'affichage", () => {
    const best = { [lessonKey("anat", 1)]: 85 };
    const [unit] = buildPath(best, { anat: 85 });
    expect(unit.nodes[0].state).toBe("done");
    // L2 s'ouvre (L1 validée), mais les leçons suivantes restent verrouillées
    expect(unit.nodes[1].state).toBe("available");
    expect(unit.nodes[1].score).toBe(85);
    expect(unit.nodes[2].state).toBe("locked");
    expect(unit.completed).toBe(1);
  });
});
