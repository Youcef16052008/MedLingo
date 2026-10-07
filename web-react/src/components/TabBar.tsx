import { useT } from "../i18n/useT";
import { activeTab } from "../lib/router";
import { useNow } from "../lib/useNow";
import { useAppState } from "../domain/store";
import { totalDue, type Sm2Map } from "../domain/progress";

/* Cinq destinations, comme la cible Duolingo :
   Parcours / Révision / Ligues / Cours / Moi. */
const TABS = [
  { id: "path", icon: "📍", key: "navPath" },
  { id: "practice", icon: "🏋️", key: "navPractice" },
  { id: "leagues", icon: "🏆", key: "navLeagues" },
  { id: "modules", icon: "📚", key: "navCourses" },
  { id: "profile", icon: "👤", key: "navProfile" },
] as const;

export function TabBar({ route }: { route: string }) {
  const { t } = useT();
  const s = useAppState();
  const active = activeTab(route);
  const now = useNow();
  const due = totalDue(s.sm2 as Sm2Map, now);

  return (
    <nav className="tabbar" aria-label={t("navMainLbl")}>
      {TABS.map((tab) => (
        <a
          key={tab.id}
          href={`#/${tab.id}`}
          className={active === tab.id ? "active" : undefined}
          aria-current={active === tab.id ? "page" : undefined}
        >
          <span className="tab-ico" aria-hidden="true">
            {tab.icon}
            {tab.id === "practice" && due > 0 && (
              <span className="tab-badge" aria-label={`${due} ${t("cardsDue")}`}>
                {due > 99 ? "99+" : due}
              </span>
            )}
          </span>
          <span>{t(tab.key)}</span>
        </a>
      ))}
    </nav>
  );
}
