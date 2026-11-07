// Feature flags (R-07/R-08, Phase 4, decision D2).
//
// ADR-3 auth/sync is deliberately OFF by default: the implementation is
// incomplete (no per-user isolation, no per-card merge, broken RLS) and
// shipping it half-wired is worse than not shipping it. Flip with
// VITE_ENABLE_SYNC=true once Phase 4's full implementation lands.
export const SYNC_ENABLED: boolean =
  typeof import.meta !== "undefined" &&
  import.meta.env &&
  import.meta.env.VITE_ENABLE_SYNC === "true";