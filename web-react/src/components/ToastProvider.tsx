import { createContext, useCallback, useContext, useEffect, useRef, useState, type ReactNode } from "react";

const ToastCtx = createContext<(msg: string) => void>(() => {});

export function ToastProvider({ children }: { children: ReactNode }) {
  const [msg, setMsg] = useState("");
  const timer = useRef<number | undefined>(undefined);

  useEffect(() => () => window.clearTimeout(timer.current), []);

  const show = useCallback((m: string) => {
    setMsg(m);
    window.clearTimeout(timer.current);
    timer.current = window.setTimeout(() => setMsg(""), 2400);
  }, []);

  return (
    <ToastCtx.Provider value={show}>
      {children}
      <div className="toast" role="status" aria-live="polite" hidden={!msg}>
        {msg}
      </div>
    </ToastCtx.Provider>
  );
}

export function useToast(): (msg: string) => void {
  return useContext(ToastCtx);
}
