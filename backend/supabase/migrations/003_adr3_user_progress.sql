-- MedLingo ADR-3 : Migration pour la table user_progress
-- Version : 1.0
-- Date : 2026-11-01

-- Création de la table user_progress pour la synchronisation des comptes
CREATE TABLE IF NOT EXISTS user_progress (
  user_id text PRIMARY KEY,
  xp integer NOT NULL DEFAULT 0,
  level integer NOT NULL DEFAULT 1,
  streak integer NOT NULL DEFAULT 0,
  heart integer NOT NULL DEFAULT 5,
  birdbrain_vector jsonb,
  lessons_completed text[],
  lessons_in_progress text[],
  lessons_next_review jsonb,
  league_position integer,
  updated_at timestamptz NOT NULL DEFAULT now(),
  created_at timestamptz NOT NULL DEFAULT now()
);

-- Index pour les performances
CREATE INDEX IF NOT EXISTS idx_user_progress_updated ON user_progress (updated_at);
CREATE INDEX IF NOT EXISTS idx_user_progress_user_id ON user_progress (user_id);
CREATE INDEX IF NOT EXISTS idx_user_progress_lessons_completed ON user_progress USING GIN (lessons_completed);

-- Policy pour l'accès
-- Seuls l'utilisateur et le service role peuvent accéder
ALTER TABLE user_progress ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Users can view own progress" ON user_progress
  FOR SELECT USING (auth.uid() = user_id);

CREATE POLICY "Service role can manage all progress" ON user_progress
  FOR ALL USING (auth.role() = 'service_role');

-- Trigger pour mettre à jour updated_at
CREATE OR REPLACE FUNCTION update_user_progress_updated_at()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = now();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_user_progress_updated_at
  BEFORE UPDATE ON user_progress
  FOR EACH ROW
  EXECUTE FUNCTION update_user_progress_updated_at();

-- Drop trigger for dev cleanup (to be reapplied after testing)
-- DROP TRIGGER IF EXISTS trigger_user_progress_updated_at ON user_progress;
