-- MedLingo security hardening: RLS policies, users bootstrap, view exposure.
-- Idempotent: safe to re-run.

-- === 1. users.id must default to the Auth user id ===
-- RLS predicates are `auth.uid() = id`; a random uuid_generate_v4() default
-- could never satisfy them, locking every client out of its own row.
alter table users alter column id set default auth.uid();

-- === 2. users bootstrap: INSERT policy + signup trigger ===
drop policy if exists "Users can insert own data" on users;
create policy "Users can insert own data" on users for insert with check (auth.uid() = id);

create or replace function public.handle_new_user()
returns trigger
language plpgsql
security definer set search_path = public
as $$
begin
  insert into public.users (id, display_name, email, phone, avatar_emoji)
  values (
    new.id,
    coalesce(new.raw_user_meta_data->>'display_name', 'Dr. Youcef'),
    new.email,
    new.phone,
    coalesce(new.raw_user_meta_data->>'avatar_emoji', '👨‍⚕️')
  )
  on conflict (id) do nothing;
  return new;
end;
$$;

drop trigger if exists on_auth_user_created on auth.users;
create trigger on_auth_user_created
  after insert on auth.users
  for each row execute function public.handle_new_user();

-- === 3. users: column-level UPDATE grant ===
-- Clients may only update profile columns. Sensitive columns (gems, xp,
-- is_super, hearts, league_tier, …) are writable by the service role only
-- (edge functions keep their table-level grant).
revoke update on users from anon, authenticated;
grant update (display_name, avatar_emoji, user_type, med_year, selected_language_code, email, phone)
  on users to authenticated;

-- === 4. Policies for tables that had RLS enabled but zero policies ===
create policy "Users can view own gems_ledger" on gems_ledger
  for select using (auth.uid() = user_id);

create policy "Users can view own streak_days" on streak_days
  for select using (auth.uid() = user_id);
create policy "Users can insert own streak_days" on streak_days
  for insert with check (auth.uid() = user_id);

create policy "Users can view own flashcard_progress" on flashcard_progress
  for select using (auth.uid() = user_id);
create policy "Users can insert own flashcard_progress" on flashcard_progress
  for insert with check (auth.uid() = user_id);
create policy "Users can update own flashcard_progress" on flashcard_progress
  for update using (auth.uid() = user_id) with check (auth.uid() = user_id);

create policy "Users can view own notifications" on notifications_queue
  for select using (auth.uid() = user_id);
create policy "Users can update own notifications" on notifications_queue
  for update using (auth.uid() = user_id) with check (auth.uid() = user_id);

-- === 5. Tables with no RLS at all ===
-- Course content: public read, no client writes.
alter table units enable row level security;
create policy "Units are readable by everyone" on units
  for select to anon, authenticated using (true);

alter table lessons enable row level security;
create policy "Lessons are readable by everyone" on lessons
  for select to anon, authenticated using (true);

alter table exercises enable row level security;
create policy "Exercises are readable by everyone" on exercises
  for select to anon, authenticated using (true);

alter table medical_terms enable row level security;
create policy "Medical terms are readable by everyone" on medical_terms
  for select to anon, authenticated using (true);

-- League: public read (leaderboard), no client writes.
alter table league_cohorts enable row level security;
create policy "League cohorts are readable by everyone" on league_cohorts
  for select to anon, authenticated using (true);

alter table league_members enable row level security;
create policy "League members are readable by everyone" on league_members
  for select to anon, authenticated using (true);

-- Purchases: own rows only.
alter table purchases enable row level security;
create policy "Users can view own purchases" on purchases
  for select using (auth.uid() = user_id);
create policy "Users can insert own purchases" on purchases
  for insert with check (auth.uid() = user_id);

-- Friendships: own rows only.
alter table friendships enable row level security;
create policy "Users can view own friendships" on friendships
  for select using (auth.uid() = user_id or auth.uid() = friend_id);
create policy "Users can insert own friendships" on friendships
  for insert with check (auth.uid() = user_id);
create policy "Users can update own friendships" on friendships
  for update using (auth.uid() = user_id or auth.uid() = friend_id)
  with check (auth.uid() = user_id or auth.uid() = friend_id);
create policy "Users can delete own friendships" on friendships
  for delete using (auth.uid() = user_id or auth.uid() = friend_id);

-- === 6. Views: no PII leak ===
-- Public profiles expose only non-sensitive columns (leaderboard display).
create or replace view public_profiles as
select id, display_name, avatar_emoji from users;

create or replace view leaderboard_current_week as
select
  lm.cohort_id,
  lm.user_id,
  p.display_name,
  p.avatar_emoji,
  lm.weekly_xp,
  lm.rank,
  lc.tier,
  lc.week_start,
  lc.week_end
from league_members lm
join league_cohorts lc on lm.cohort_id = lc.cohort_id
left join public_profiles p on lm.user_id = p.id
where lc.is_active = true
order by lm.cohort_id, lm.weekly_xp desc;

-- user_stats_computed runs with invoker rights: RLS on users applies, so a
-- caller only ever sees their own row (no email/phone cross-read).
alter view user_stats_computed set (security_invoker = true);

-- === 7. FK constraints that the backend intentionally violates ===
-- birdbrain writes sentinel lesson/unit ids ('birdbrain-update', 'system').
alter table progress drop constraint if exists progress_lesson_id_fkey;
alter table progress drop constraint if exists progress_unit_id_fkey;
-- League bots use synthetic user ids with no users row.
alter table league_members drop constraint if exists league_members_user_id_fkey;

-- === 8. Dead code ===
-- Trigger function defined but never attached to any trigger.
drop function if exists check_league_week_ended();

-- === 9. FTS: stem AR/FR content with the 'simple' config ===
drop index if exists idx_medical_terms_search;
create index idx_medical_terms_search on medical_terms
  using gin(to_tsvector('simple', term_en || ' ' || term_fr || ' ' || term_ar));

-- === 10. Index for the league-cron hot filter ===
create index if not exists idx_league_cohorts_active_week_end
  on league_cohorts(is_active, week_end);
