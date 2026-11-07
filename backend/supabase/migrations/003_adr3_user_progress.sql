-- Migration 003 : ADR-3 user progress (corrected 2026-11-07, R-09)
--
-- Idempotent: safe to re-run.
-- `user_id` is a uuid FK to auth.users with cascade delete, and authenticated
-- users get full CRUD on their own row. Service role retains full access.

CREATE TABLE IF NOT EXISTS user_progress (
  user_id uuid PRIMARY KEY REFERENCES auth.users(id) ON DELETE CASCADE,
  data jsonb NOT NULL DEFAULT '{}'::jsonb,
  updated_at timestamptz NOT NULL DEFAULT now()
);

-- Index is redundant with the PK; keep only if a non-PK lookup is needed.
-- (Removed idx_user_progress_user_id.)

ALTER TABLE user_progress ENABLE ROW LEVEL SECURITY;

DROP POLICY IF EXISTS "Users can view own progress" ON user_progress;
DROP POLICY IF EXISTS "Service role can manage all progress" ON user_progress;
DROP POLICY IF EXISTS "user_progress_own" ON user_progress;

CREATE POLICY "user_progress_own" ON user_progress
  FOR ALL TO authenticated
  USING (auth.uid() = user_id)
  WITH CHECK (auth.uid() = user_id);

CREATE POLICY "user_progress_service" ON user_progress
  FOR ALL TO service_role
  USING (true)
  WITH CHECK (true);

CREATE OR REPLACE FUNCTION touch_user_progress_updated_at()
RETURNS trigger AS $$
BEGIN
  NEW.updated_at = now();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS user_progress_touch_updated_at ON user_progress;
CREATE TRIGGER user_progress_touch_updated_at
  BEFORE UPDATE ON user_progress
  FOR EACH ROW EXECUTE FUNCTION touch_user_progress_updated_at();