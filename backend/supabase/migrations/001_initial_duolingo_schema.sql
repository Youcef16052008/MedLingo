-- MedLingo Supabase Schema - Duolingo 100% Compliant
-- Basé sur architecture_finale_duolingo_pour_medlingo.md Prisma schema + Clonemrr
-- Date: 29 Sept 2026

-- Enable UUID extension
create extension if not exists "uuid-ossp";

-- === USERS (comme Duolingo User Service) ===
create table users (
  id uuid primary key default uuid_generate_v4(),
  phone text unique, -- Haina Vietnam localisation: login téléphone pas email
  email text unique,
  display_name text not null default 'Dr. Youcef',
  avatar_emoji text default '👨‍⚕️',
  user_type text default 'MED_STUDENT', -- MED_STUDENT, MED_DOCTOR, PARAMEDICAL, NON_MEDICAL
  med_year text default 'YEAR_1',
  selected_language_code text default 'fr',
  
  -- Gamification (Phase 1)
  streak_days int default 0,
  total_xp int default 0,
  weekly_xp int default 0,
  gems int default 100,
  hearts int default 5,
  hearts_updated_at timestamptz default now(),
  max_hearts int default 5,
  is_super boolean default false,
  super_expires_at timestamptz,
  streak_freeze_count int default 1,
  perfect_lessons_count int default 0,
  lessons_completed int default 0,
  
  -- Learning
  level1_score int default 0,
  level2_score int default 0,
  level3_score int default 0,
  level4_score int default 0,
  level5_score int default 0,
  level6_score int default 0,
  
  -- League
  league_cohort_id text,
  league_tier text default 'BRONZE',
  
  -- Birdbrain V2 vector 40-dim (comme Duolingo)
  birdbrain_vector jsonb default '[]'::jsonb, -- 40 floats
  birdbrain_updated_at timestamptz default now(),
  
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);

-- === UNITS (comme Duolingo Skill Tree) ===
create table units (
  id text primary key, -- ex: "anat", "physio"
  title_fr text not null,
  title_en text not null,
  title_ar text not null,
  icon text not null,
  color_hex bigint not null,
  order_index int not null,
  chapters_count int default 4,
  estimated_size_mb float default 10.0,
  is_free boolean default true,
  created_at timestamptz default now()
);

-- === LESSONS (comme Duolingo lessons) ===
create table lessons (
  id text primary key, -- ex: "anat-l1-vocab-1"
  unit_id text references units(id) on delete cascade,
  level int not null, -- 1..6
  title_fr text not null,
  title_en text not null,
  title_ar text not null,
  order_index int not null,
  xp_reward int default 20,
  gems_reward int default 10,
  is_free boolean default true,
  difficulty text default 'beginner', -- beginner, intermediate, advanced
  estimated_minutes int default 5,
  created_at timestamptz default now()
);

-- === EXERCISES (ExerciseSpec JSONB - Phase 2) ===
create table exercises (
  id uuid primary key default uuid_generate_v4(),
  lesson_id text references lessons(id) on delete cascade,
  level int not null,
  type text not null, -- choice, wordbank, match, fill, clinical_case, reading
  difficulty text default 'beginner',
  module text not null,
  chapter text not null,
  
  -- Duolingo Exercise Engine as Data: JSONB Zod validated
  spec jsonb not null, -- ExerciseSpec Choice|Wordbank|Match|Fill|ClinicalCase|Reading
  
  -- Legacy fallback
  question_en text,
  question_fr text,
  question_ar text,
  options_raw text,
  correct_answer text,
  
  points int default 10,
  context_text_en text,
  context_text_fr text,
  context_text_ar text,
  
  -- Birdbrain metadata
  birdbrain_difficulty float default 0.5, -- 0..1 predicted difficulty
  birdbrain_discrimination float default 0.5, -- how well it discriminates ability
  
  version int default 1,
  created_at timestamptz default now()
);

-- Index for fast Session Generator
create index idx_exercises_lesson_id on exercises(lesson_id);
create index idx_exercises_level on exercises(level);
create index idx_exercises_module on exercises(module);
create index idx_exercises_type on exercises(type);

-- === PROGRESS (server-authoritative, comme Duolingo progress) ===
create table progress (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid references users(id) on delete cascade,
  lesson_id text references lessons(id) on delete cascade,
  unit_id text references units(id) on delete cascade,
  
  accuracy float not null, -- 0..1
  correct_answers int not null,
  total_questions int not null,
  xp_earned int not null,
  gems_earned int default 0,
  hearts_lost int default 0,
  time_spent_millis bigint not null,
  hints_used int default 0,
  is_perfect boolean default false,
  
  -- Anti-cheat
  is_anti_cheat_failed boolean default false,
  
  completed_at timestamptz default now(),
  
  unique(user_id, lesson_id, completed_at) -- allow multiple completions same lesson different times
);

create index idx_progress_user_id on progress(user_id);
create index idx_progress_lesson_id on progress(lesson_id);
create index idx_progress_completed_at on progress(completed_at desc);

-- === STREAK_DAYS (idempotent, comme Duolingo) ===
create table streak_days (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid references users(id) on delete cascade,
  date date not null, -- YYYY-MM-DD UTC
  xp_earned int default 0,
  lessons_completed int default 0,
  created_at timestamptz default now(),
  
  unique(user_id, date) -- idempotent streak, pas de compteur mutable
);

create index idx_streak_days_user_date on streak_days(user_id, date desc);

-- === GEMS_LEDGER (append-only, anti-cheat, comme Duolingo gems_ledger) ===
create table gems_ledger (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid references users(id) on delete cascade,
  type text not null, -- EARN, SPEND, PURCHASE, REFILL_HEARTS, STREAK_FREEZE, LEAGUE_REWARD
  amount int not null, -- positif earn, négatif spend
  reason text not null, -- lesson_complete, perfect_lesson, heart_refill, etc.
  balance_after int not null, -- balance dérivée
  metadata jsonb default '{}'::jsonb,
  created_at timestamptz default now()
);

create index idx_gems_ledger_user_id on gems_ledger(user_id, created_at desc);

-- === LEAGUE_COHORTS (comme Duolingo Redis league:{week}:{cohort}) ===
create table league_cohorts (
  cohort_id text primary key,
  week_start timestamptz not null,
  week_end timestamptz not null,
  tier text default 'BRONZE', -- BRONZE, SILVER, GOLD, SAPPHIRE, RUBY, EMERALD, AMETHYST, PEARL, OBSIDIAN, DIAMOND
  is_active boolean default true,
  is_promoted boolean default false,
  is_demoted boolean default false,
  created_at timestamptz default now()
);

create index idx_league_cohorts_week_start on league_cohorts(week_start desc);
create index idx_league_cohorts_tier on league_cohorts(tier);

-- === LEAGUE_MEMBERS (sorted set par weekly_xp DESC) ===
create table league_members (
  id uuid primary key default uuid_generate_v4(),
  cohort_id text references league_cohorts(cohort_id) on delete cascade,
  user_id uuid references users(id) on delete cascade,
  display_name text not null,
  avatar_emoji text default '👨‍⚕️',
  weekly_xp int default 0,
  total_xp int default 0,
  streak_days int default 0,
  rank int default 0,
  is_current_user boolean default false,
  is_bot boolean default false,
  last_active_at timestamptz default now(),
  
  unique(cohort_id, user_id)
);

create index idx_league_members_cohort_xp on league_members(cohort_id, weekly_xp desc);
create index idx_league_members_user_id on league_members(user_id);

-- === PURCHASES (RevenueCat + BaridiMob/CCP manuel DZ) ===
create table purchases (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid references users(id) on delete cascade,
  product_id text not null, -- super_monthly, super_yearly, gems_100, etc.
  amount_da int, -- 500 DA, 4000 DA
  amount_gems int,
  payment_method text, -- baridimob, ccp, revenuecat, free
  status text default 'pending', -- pending, completed, failed
  revenuecat_transaction_id text,
  baridimob_reference text,
  expires_at timestamptz,
  created_at timestamptz default now()
);

create index idx_purchases_user_id on purchases(user_id, created_at desc);

-- === MEDICAL_TERMS (S3 course data cache) ===
create table medical_terms (
  id int primary key,
  term_en text not null,
  term_fr text not null,
  term_ar text not null,
  definition_en text,
  definition_fr text,
  definition_ar text,
  etymology text,
  clinical_pearl text,
  mnemonic text,
  module text not null,
  chapter text,
  example_en text,
  example_fr text,
  example_ar text,
  ipa_phonetic text,
  image_asset text,
  audio_asset text,
  is_bookmarked boolean default false,
  created_at timestamptz default now()
);

create index idx_medical_terms_module on medical_terms(module);
create index idx_medical_terms_search on medical_terms using gin(to_tsvector('english', term_en || ' ' || term_fr || ' ' || term_ar));

-- === FLASHCARD_PROGRESS (SM-2) ===
create table flashcard_progress (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid references users(id) on delete cascade,
  term_id int references medical_terms(id) on delete cascade,
  repetitions int default 0,
  ease_factor float default 2.5,
  interval_days int default 0,
  next_review_at timestamptz default now(),
  last_reviewed_at timestamptz,
  last_quality int default 0,
  created_at timestamptz default now(),
  
  unique(user_id, term_id)
);

create index idx_flashcard_progress_user_next on flashcard_progress(user_id, next_review_at);

-- === NOTIFICATIONS_QUEUE (FIFO SQS-like) ===
create table notifications_queue (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid references users(id) on delete cascade,
  type text not null, -- review_reminder, streak_reminder, clinical_pearl, level_unlocked, league_ending
  title text not null,
  body text not null,
  data jsonb default '{}'::jsonb, -- deep link, etc.
  channel_id text default 'medlingua_daily_review',
  is_sent boolean default false,
  sent_at timestamptz,
  is_read boolean default false,
  created_at timestamptz default now()
);

create index idx_notifications_queue_user_sent on notifications_queue(user_id, is_sent, created_at desc);

-- === FRIENDS (Social) ===
create table friendships (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid references users(id) on delete cascade,
  friend_id uuid references users(id) on delete cascade,
  status text default 'pending', -- pending, accepted, blocked
  created_at timestamptz default now(),
  
  unique(user_id, friend_id)
);

-- === FUNCTIONS ===

-- Function to update updated_at
create or replace function update_updated_at_column()
returns trigger as $$
begin
  new.updated_at = now();
  return new;
end;
$$ language plpgsql;

-- Trigger for users
create trigger update_users_updated_at before update on users
  for each row execute function update_updated_at_column();

-- Function to calculate current hearts (lazy regen)
create or replace function get_current_hearts(user_row users)
returns int as $$
declare
  elapsed_hours float;
  regen_count int;
begin
  if user_row.is_super then
    return user_row.max_hearts;
  end if;
  if user_row.hearts >= user_row.max_hearts then
    return user_row.max_hearts;
  end if;
  elapsed_hours := extract(epoch from (now() - user_row.hearts_updated_at)) / 3600.0;
  regen_count := floor(elapsed_hours / 2.0)::int;
  return least(user_row.max_hearts, user_row.hearts + regen_count);
end;
$$ language plpgsql;

-- Function to check and refresh league if week ended
create or replace function check_league_week_ended()
returns trigger as $$
begin
  -- This would be called by cron job weekly
  return new;
end;
$$ language plpgsql;

-- === SEED DATA - 15 Modules ===
-- chapters_count = nombre réel de chapitres distincts dans le seed Kotlin
insert into units (id, title_fr, title_en, title_ar, icon, color_hex, order_index, chapters_count, estimated_size_mb) values
('anat', 'Anatomie', 'Anatomy', 'علم التشريح', '🦴', x'1B5E20'::bigint, 1, 11, 14.2),
('physio', 'Physiologie', 'Physiology', 'علم وظائف الأعضاء', '❤️', x'00695C'::bigint, 2, 14, 18.5),
('biochim', 'Biochimie', 'Biochemistry', 'الكيمياء الحيوية', '🧬', x'1565C0'::bigint, 3, 32, 12.8),
('histo', 'Histologie', 'Histology', 'علم الأنسجة', '🔬', x'6A1B9A'::bigint, 4, 18, 16.0),
('biophys', 'Biophysique', 'Biophysics', 'الفيزياء الحيوية', '🧪', x'E65100'::bigint, 5, 17, 9.4),
('genet', 'Génétique', 'Genetics', 'الوراثة', '🧬', x'004D40'::bigint, 6, 19, 11.0),
('termino', 'Terminologie', 'Terminology', 'المصطلحات', '📙', x'E65100'::bigint, 7, 6, 8.5),
('clinical_en', 'Anglais Médical', 'Medical English', 'الإنجليزية الطبية', '🩺', x'00695C'::bigint, 8, 2, 7.2),
('cytol', 'Cytologie', 'Cytology', 'علم الخلايا', '🧫', x'00796B'::bigint, 9, 5, 10.5),
('info_med', 'Informatique Médicale', 'Medical Informatics', 'المعلوماتية الطبية', '💻', x'1976D2'::bigint, 10, 20, 8.0),
('embryo', 'Embryologie', 'Embryology', 'علم الأجنة', '👶', x'C2185B'::bigint, 11, 5, 9.2),
('microbio', 'Microbiologie', 'Microbiology', 'الأحياء الدقيقة', '🦠', x'00897B'::bigint, 12, 15, 13.5),
('pharmaco', 'Pharmacologie', 'Pharmacology', 'علم الأدوية', '💊', x'7B1FA2'::bigint, 13, 15, 14.8),
('semio', 'Sémiologie', 'Semiology', 'علم الأعراض', '🩺', x'0288D1'::bigint, 14, 15, 15.2),
('anapath', 'Anapath', 'Pathology', 'علم الأمراض', '🫀', x'C2185B'::bigint, 15, 13, 16.0)
on conflict (id) do nothing;

-- === SEED LESSONS - 6 Levels x 15 modules = 90 lessons ===
-- Level 1: Vocabulaire
insert into lessons (id, unit_id, level, title_fr, title_en, title_ar, order_index, xp_reward, difficulty) values
('anat-l1-vocab', 'anat', 1, 'Vocabulaire Anatomique', 'Anatomical Vocabulary', 'مفردات تشريحية', 1, 20, 'beginner'),
('physio-l1-vocab', 'physio', 1, 'Vocabulaire Physiologique', 'Physiology Vocabulary', 'مفردات فسيولوجية', 2, 20, 'beginner'),
('biochim-l1-vocab', 'biochim', 1, 'Vocabulaire Biochimique', 'Biochemistry Vocabulary', 'مفردات بيوكيميائية', 3, 20, 'beginner')
on conflict (id) do nothing;

-- Enable RLS (Row Level Security) for production
alter table users enable row level security;
alter table progress enable row level security;
alter table gems_ledger enable row level security;
alter table streak_days enable row level security;
alter table flashcard_progress enable row level security;
alter table notifications_queue enable row level security;

-- Policies: users can only read/write their own data
create policy "Users can view own data" on users for select using (auth.uid() = id);
create policy "Users can update own data" on users for update using (auth.uid() = id);

create policy "Users can view own progress" on progress for select using (auth.uid() = user_id);
create policy "Users can insert own progress" on progress for insert with check (auth.uid() = user_id);

-- For MVP, allow all (disable RLS for local dev)
-- alter table users disable row level security;
-- etc.

-- === VIEWS ===

-- View: Leaderboard current week
create or replace view leaderboard_current_week as
select 
  lm.cohort_id,
  lm.user_id,
  u.display_name,
  u.avatar_emoji,
  lm.weekly_xp,
  lm.rank,
  lc.tier,
  lc.week_start,
  lc.week_end
from league_members lm
join league_cohorts lc on lm.cohort_id = lc.cohort_id
left join users u on lm.user_id = u.id
where lc.is_active = true
order by lm.cohort_id, lm.weekly_xp desc;

-- View: User stats with computed hearts
create or replace view user_stats_computed as
select 
  *,
  get_current_hearts(users.*) as current_hearts_computed
from users;
