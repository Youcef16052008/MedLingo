import { useEffect, useState } from "react";
import { useT } from "../i18n/useT";
import { update, useAppState } from "../domain/store";
import { openChest, applyChestReward, type ChestReward } from "../domain/chests";
import { claimTrophies, trophyById, type TrophyId } from "../domain/trophies";
import { Medi } from "./Medi";

export interface RewardData {
  /** XP gagnés pendant la session. */
  xp: number;
  /** Gemmes gagnées pendant la session. */
  gems: number;
  /** L'objectif quotidien vient d'être atteint. */
  goalHit: boolean;
  /** Nouveaux trophées débloqués par cette session. */
  trophies: TrophyId[];
}

/**
 * Pop-up de récompense (spec §11) — surcouche centrée en 3 temps :
 * compteur `+N XP` → lignes gemmes/objectif/trophées → ouverture de caisse.
 */
export function RewardPopup({
  data,
  onContinue,
}: {
  data: RewardData;
  onContinue: () => void;
}) {
  const { t } = useT();
  const s = useAppState();
  const [shown, setShown] = useState(0);
  const [opened, setOpened] = useState<ChestReward[]>([]);
  const [chestTrophies, setChestTrophies] = useState<TrophyId[]>([]);

  // compteur XP animé
  useEffect(() => {
    if (data.xp <= 0) {
      setShown(0);
      return;
    }
    let current = 0;
    const step = Math.max(1, Math.round(data.xp / 12));
    const id = window.setInterval(() => {
      current = Math.min(data.xp, current + step);
      setShown(current);
      if (current >= data.xp) window.clearInterval(id);
    }, 55);
    return () => window.clearInterval(id);
  }, [data.xp]);

  const openBox = () => {
    const reward = openChest(Math.random);
    const fresh: TrophyId[] = [];
    update((d) => {
      if (d.pendingChests > 0) d.pendingChests -= 1;
      d.chestsOpened += 1;
      applyChestReward(d, reward, Date.now());
      // Ouvrir des caisses peut aussi débloquer le trophée `chests_5`.
      fresh.push(...claimTrophies(d, Date.now()));
    });
    setOpened((prev) => [...prev, reward]);
    if (fresh.length) setChestTrophies((prev) => [...prev, ...fresh]);
  };

  const chestLabel = (r: ChestReward): string =>
    r.kind === "gems"
      ? `💎 +${r.amount}`
      : r.kind === "freeze"
        ? `❄️ +${r.amount}`
        : `⭐ +${r.amount}`;

  return (
    <div className="reward-overlay" role="dialog" aria-label={t("rewardTitle")}>
      <div className="confetti" aria-hidden="true">
        {Array.from({ length: 12 }, (_, i) => (
          <i
            key={i}
            className={`c${i % 6}`}
            style={{ left: `${(i * 8.3 + 3) % 96}%`, animationDelay: `${i * 90}ms` }}
          />
        ))}
      </div>
      <div className="reward-card">
        <Medi mood={data.goalHit || data.trophies.length ? "celebrating" : "happy"} />

        {/* B29 : pas de compteur « +0 ⭐ » quand la session n'a gagné aucun XP
            (popup uniquement caisse/trophées). */}
        {data.xp > 0 && <div className="reward-xp">+{shown} ⭐</div>}
        <p className="reward-label">{t("rewardTitle")}</p>

        <ul className="reward-rows">
          {data.gems > 0 && (
            <li>
              💎 +{data.gems} <span>{t("statGems")}</span>
            </li>
          )}
          {data.goalHit && (
            <li className="gold">
              🎯 {t("rewardGoal")} <span>✅</span>
            </li>
          )}
          {data.trophies.map((id) => {
            const trophy = trophyById(id);
            return (
              <li key={id} className="gold">
                {trophy.icon} {t(trophy.titleKey as never)}{" "}
                <span>{t("rewardTrophy")}</span>
              </li>
            );
          })}
        </ul>

        {s.pendingChests > 0 && (
          <button type="button" className="btn reward-chest" onClick={openBox}>
            📦 {t("rewardChest")} ({s.pendingChests})
          </button>
        )}
        {opened.map((r, i) => (
          <div key={i} className="reward-chest-open">
            <span aria-hidden="true">🎁</span> {chestLabel(r)}
          </div>
        ))}
        {chestTrophies.map((id) => {
          const trophy = trophyById(id);
          return (
            <div key={id} className="reward-chest-open">
              {trophy.icon} {t(trophy.titleKey as never)}
            </div>
          );
        })}

        <button type="button" className="btn block" onClick={onContinue}>
          {t("rewardContinue")}
        </button>
      </div>
    </div>
  );
}
