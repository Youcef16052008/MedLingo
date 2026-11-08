-- Migration 004 : shared rate limiting (R-29, 2026-11-08)
--
-- Les Edge Functions Supabase tournent sur des isolats éphémères : une Map
-- en mémoire ne partage rien d'une requête à l'autre (ni d'un isolate à
-- l'autre). Ce compteur vit en Postgres et est incrémenté atomiquement par
-- l'RPC `rate_limit_hit`, appelée avec la service role depuis les fonctions.
--
-- Idempotent: safe to re-run.

CREATE TABLE IF NOT EXISTS rate_limits (
  bucket text NOT NULL,          -- fonction: 'birdbrain' | 'notifications' | 'session-generator'
  rl_key text NOT NULL,          -- user_id ou ip
  count int NOT NULL DEFAULT 0,
  reset_at timestamptz NOT NULL,
  PRIMARY KEY (bucket, rl_key)
);

-- Lecture/écriture réservées à la service role (RLS actif, aucune policy
-- pour authenticated/anon : la service role contourne le RLS).
ALTER TABLE rate_limits ENABLE ROW LEVEL SECURITY;

DROP POLICY IF EXISTS "rate_limits_service" ON rate_limits;
CREATE POLICY "rate_limits_service" ON rate_limits
  FOR ALL TO service_role
  USING (true)
  WITH CHECK (true);

-- Incrémente le compteur de (bucket, rl_key) et renvoie true si la requête
-- est autorisée (count <= p_limit). Fenêtre glissante par réinitialisation :
-- quand reset_at est atteint, le compteur repart à 1 pour une nouvelle fenêtre.
--
-- SECURITY DEFINER : appelée uniquement depuis les Edge Functions avec la
-- service role ; aucune policy n'expose la table aux clients.
CREATE OR REPLACE FUNCTION rate_limit_hit(
  p_bucket text,
  p_key text,
  p_limit int,
  p_window_seconds int
)
RETURNS boolean
LANGUAGE plpgsql
SECURITY DEFINER
SET search_path = public
AS $$
DECLARE
  v_count int;
BEGIN
  IF p_limit IS NULL OR p_limit < 1 OR p_window_seconds IS NULL OR p_window_seconds < 1 THEN
    RAISE EXCEPTION 'rate_limit_hit: invalid limit/window';
  END IF;

  INSERT INTO rate_limits (bucket, rl_key, count, reset_at)
  VALUES (p_bucket, p_key, 1, now() + make_interval(secs => p_window_seconds))
  ON CONFLICT (bucket, rl_key) DO UPDATE
    SET count = CASE
                  WHEN rate_limits.reset_at <= now() THEN 1
                  ELSE rate_limits.count + 1
                END,
        reset_at = CASE
                     WHEN rate_limits.reset_at <= now() THEN now() + make_interval(secs => p_window_seconds)
                     ELSE rate_limits.reset_at
                   END
  RETURNING count INTO v_count;

  -- Balayage opportuniste des fenêtres expirées (garde la table petite).
  DELETE FROM rate_limits WHERE reset_at <= now() - interval '1 hour';

  RETURN v_count <= p_limit;
END;
$$;

REVOKE ALL ON FUNCTION rate_limit_hit(text, text, int, int) FROM PUBLIC, anon, authenticated;
GRANT EXECUTE ON FUNCTION rate_limit_hit(text, text, int, int) TO service_role;
