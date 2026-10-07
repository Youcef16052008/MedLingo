import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useRef,
  useState,
  type ReactNode,
} from "react";

export interface DialogAction {
  id: string;
  label: string;
  cls?: string;
}

export interface DialogOptions {
  icon?: string;
  title: string;
  text?: string;
  actions: DialogAction[];
}

interface DialogApi {
  open: (opts: DialogOptions) => Promise<string>;
}

const DialogCtx = createContext<DialogApi | null>(null);

/**
 * Boîte de dialogue modale à promesse, équivalent React de la fonction
 * `dialog()` du référentiel vanilla : `await open({...})` résout avec l'id
 * de l'action cliquée.
 */
export function DialogProvider({ children }: { children: ReactNode }) {
  const [opts, setOpts] = useState<DialogOptions | null>(null);
  const resolver = useRef<((id: string) => void) | null>(null);
  const dlgRef = useRef<HTMLDivElement>(null);
  const prevFocus = useRef<HTMLElement | null>(null);

  const open = useCallback(
    (o: DialogOptions) =>
      new Promise<string>((resolve) => {
        // Un second `open()` pendant qu'un premier est encore affiché : on
        // libère l'ancienne promesse (résolution "" = annulé) pour ne pas la
        // laisser suspendue à jamais.
        resolver.current?.("");
        resolver.current = resolve;
        prevFocus.current =
          document.activeElement instanceof HTMLElement ? document.activeElement : null;
        setOpts(o);
      }),
    []
  );

  const close = useCallback((id: string) => {
    setOpts(null);
    const r = resolver.current;
    resolver.current = null;
    r?.(id);
  }, []);

  // B19 : focus initial sur la première action, Échap ferme, piège à Tab,
  // et retour du focus à l'élément qui a ouvert la boîte.
  useEffect(() => {
    if (!opts) {
      const prev = prevFocus.current;
      prevFocus.current = null;
      if (prev && document.contains(prev)) prev.focus();
      return;
    }
    dlgRef.current?.querySelector<HTMLElement>("button")?.focus();
    const onKey = (e: KeyboardEvent) => {
      if (e.key === "Escape") {
        e.preventDefault();
        close("");
        return;
      }
      if (e.key !== "Tab") return;
      const nodes = dlgRef.current?.querySelectorAll<HTMLElement>("button, a[href]");
      if (!nodes || nodes.length === 0) return;
      const firstEl = nodes[0];
      const lastEl = nodes[nodes.length - 1];
      const active = document.activeElement;
      const inDlg = active instanceof Node && dlgRef.current?.contains(active);
      if (!inDlg) {
        e.preventDefault();
        (e.shiftKey ? lastEl : firstEl).focus();
      } else if (e.shiftKey && active === firstEl) {
        e.preventDefault();
        lastEl.focus();
      } else if (!e.shiftKey && active === lastEl) {
        e.preventDefault();
        firstEl.focus();
      }
    };
    document.addEventListener("keydown", onKey);
    return () => document.removeEventListener("keydown", onKey);
  }, [opts, close]);

  return (
    <DialogCtx.Provider value={{ open }}>
      {children}
      {opts && (
        <div className="overlay" onClick={(e) => e.target === e.currentTarget && close("")}>
          <div
            className="dialog"
            role="dialog"
            aria-modal="true"
            aria-label={opts.title}
            ref={dlgRef}
          >
            <div className="dlg-ico">{opts.icon ?? "⚕"}</div>
            <h2>{opts.title}</h2>
            {opts.text && <p>{opts.text}</p>}
            <div className="dlg-actions">
              {opts.actions.map((a) => (
                <button
                  key={a.id}
                  className={`btn ${a.cls ?? ""} block`}
                  onClick={() => close(a.id)}
                >
                  {a.label}
                </button>
              ))}
            </div>
          </div>
        </div>
      )}
    </DialogCtx.Provider>
  );
}

export function useDialog(): DialogApi {
  const ctx = useContext(DialogCtx);
  if (!ctx) throw new Error("useDialog must be used inside <DialogProvider>");
  return ctx;
}
