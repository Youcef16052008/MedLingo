// League Cron - Weekly reset lundi 00:00 UTC, promotion/demotion
// Like Duolingo: 30 users/cohort, job Vercel cron hebdo close + promotions
// Runs every Monday 00:00 UTC via Supabase cron or Vercel cron

import { serve } from "https://deno.land/std@0.168.0/http/server.ts"
import { createClient } from 'https://esm.sh/@supabase/supabase-js@2'

const ALLOWED_ORIGINS = [
  'https://medlingo.dz',
  'https://www.medlingo.dz',
  'http://localhost:3000',
  'http://localhost:8080',
  'http://127.0.0.1:3000',
  'app://'
]

function getCorsHeaders(origin: string | null): Record<string, string> {
  const headers: Record<string, string> = {
    'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type',
    'Access-Control-Allow-Methods': 'POST, OPTIONS'
  }
  if (origin && ALLOWED_ORIGINS.includes(origin)) {
    headers['Access-Control-Allow-Origin'] = origin
    headers['Access-Control-Allow-Credentials'] = 'true'
  }
  return headers
}

serve(async (req) => {
  const origin = req.headers.get('origin')
  const corsHeaders = getCorsHeaders(origin)

  if (req.method === 'OPTIONS') {
    return new Response('ok', { headers: corsHeaders })
  }

  try {
    const supabase = createClient(
      Deno.env.get('SUPABASE_URL') ?? '',
      Deno.env.get('SUPABASE_SERVICE_ROLE_KEY') ?? ''
    )

    // Verify cron secret header
    const cronSecret = req.headers.get('x-cron-secret')
    const expectedSecret = Deno.env.get('CRON_SECRET')
    if (!expectedSecret || cronSecret !== expectedSecret) {
      return new Response(
        JSON.stringify({ error: 'Unauthorized' }),
        { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 401 }
      )
    }

    // 1. Fetch all active cohorts where week_end < now
    const now = new Date()
    const { data: endedCohorts, error: cohortsError } = await supabase
      .from('league_cohorts')
      .select('*')
      .eq('is_active', true)
      .lt('week_end', now.toISOString())

    if (cohortsError) throw cohortsError

    if (!endedCohorts || endedCohorts.length === 0) {
      return new Response(
        JSON.stringify({ status: 'no_ended_cohorts', now: now.toISOString() }),
        { headers: { ...corsHeaders, 'Content-Type': 'application/json' } }
      )
    }

    const tierOrder = ['BRONZE', 'SILVER', 'GOLD', 'SAPPHIRE', 'RUBY', 'EMERALD', 'AMETHYST', 'PEARL', 'OBSIDIAN', 'DIAMOND']
    const results = []

    for (const cohort of endedCohorts) {
      // 2. Fetch members sorted by weekly_xp DESC
      const { data: members, error: membersError } = await supabase
        .from('league_members')
        .select('*')
        .eq('cohort_id', cohort.cohort_id)
        .order('weekly_xp', { ascending: false })

      if (membersError) {
        console.error(`[LeagueCron] Error fetching members`)
        continue
      }

      if (!members || members.length === 0) {
        await supabase
          .from('league_cohorts')
          .update({ is_active: false })
          .eq('cohort_id', cohort.cohort_id)
        continue
      }

      // 3. Determine promotion/demotion
      const promotionCount = 10
      const demotionCount = 5
      const currentTierIndex = tierOrder.indexOf(cohort.tier)
      
      for (let i = 0; i < members.length; i++) {
        const member = members[i]
        const isPromotion = i < promotionCount
        const isDemotion = i >= members.length - demotionCount
        
        let nextTier = cohort.tier
        let gemsReward = 0

        if (isPromotion && currentTierIndex < tierOrder.length - 1) {
          nextTier = tierOrder[currentTierIndex + 1]
          gemsReward = 100
        } else if (isDemotion && currentTierIndex > 0) {
          nextTier = tierOrder[currentTierIndex - 1]
        }

        // 4. For real users (not bots), create new cohort and update
        if (!member.is_bot) {
          const newCohortId = `${Date.now()}-${nextTier.toLowerCase()}-${Math.random().toString(36).substring(2, 6)}`
          const weekStart = getNextMonday()
          const weekEnd = new Date(weekStart.getTime() + 7 * 24 * 60 * 60 * 1000 - 1)

          let targetCohortId = newCohortId
          const { data: existingCohort } = await supabase
            .from('league_cohorts')
            .select('cohort_id')
            .eq('tier', nextTier)
            .eq('is_active', true)
            .gte('week_start', weekStart.toISOString())
            .limit(1)
            .single()

          if (existingCohort) {
            targetCohortId = existingCohort.cohort_id
          } else {
            await supabase.from('league_cohorts').insert({
              cohort_id: newCohortId,
              week_start: weekStart.toISOString(),
              week_end: weekEnd.toISOString(),
              tier: nextTier,
              is_active: true
            })
            targetCohortId = newCohortId

            const bots = generateBotsForCohort(targetCohortId, nextTier)
            await supabase.from('league_members').insert(bots)
          }

          const { data: user } = await supabase
            .from('users')
            .select('gems')
            .eq('id', member.user_id)
            .single()

          const newGems = (user?.gems || 0) + gemsReward

          await supabase
            .from('users')
            .update({
              league_cohort_id: targetCohortId,
              league_tier: nextTier,
              weekly_xp: 0,
              gems: newGems
            })
            .eq('id', member.user_id)

          await supabase.from('league_members').insert({
            cohort_id: targetCohortId,
            user_id: member.user_id,
            display_name: member.display_name,
            avatar_emoji: member.avatar_emoji,
            weekly_xp: 0,
            total_xp: member.total_xp,
            streak_days: member.streak_days,
            is_current_user: true,
            is_bot: false
          })

          if (gemsReward > 0) {
            await supabase.from('gems_ledger').insert({
              user_id: member.user_id,
              type: 'EARN',
              amount: gemsReward,
              reason: 'league_promotion',
              balance_after: newGems,
              metadata: { from: cohort.tier, to: nextTier, rank: i + 1 }
            })
          }

          results.push({
            user_id: member.user_id,
            old_cohort: cohort.cohort_id,
            new_cohort: targetCohortId,
            old_tier: cohort.tier,
            new_tier: nextTier,
            rank: i + 1,
            weekly_xp: member.weekly_xp,
            promotion: isPromotion,
            demotion: isDemotion,
            gems_reward: gemsReward
          })
        }
      }

      await supabase
        .from('league_cohorts')
        .update({ is_active: false })
        .eq('cohort_id', cohort.cohort_id)
    }

    return new Response(
      JSON.stringify({ 
        status: 'completed',
        processed_cohorts: endedCohorts.length,
        results,
        now: now.toISOString()
      }),
      { headers: { ...corsHeaders, 'Content-Type': 'application/json' } }
    )

  } catch (error) {
    console.error('[LeagueCron] Internal error')
    return new Response(
      JSON.stringify({ error: 'Internal server error' }),
      { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 500 }
    )
  }
})

function getNextMonday(): Date {
  const now = new Date()
  const day = now.getUTCDay()
  const diff = day === 0 ? 1 : 8 - day
  const nextMonday = new Date(now)
  nextMonday.setUTCDate(now.getUTCDate() + diff)
  nextMonday.setUTCHours(0, 0, 0, 0)
  return nextMonday
}

function generateBotsForCohort(cohortId: string, tier: string) {
  const botNames = [
    ["Amine_Med", "👨‍⚕️"], ["Sara_Anat", "👩‍⚕️"], ["Yacine_Pharm", "💊"],
    ["Nour_Histo", "🔬"], ["Mehdi_Cardio", "❤️"], ["Lina_Pedia", "👶"],
    ["Karim_Chir", "🏥"], ["Imane_Bio", "🧬"], ["Omar_Urge", "🚑"],
    ["Fatima_Radio", "🩻"], ["Anis_Neuro", "🧠"], ["Zineb_Gyne", "👩‍⚕️"],
    ["Riad_Ortho", "🦴"], ["Samia_Derm", "🧴"], ["Bilal_Oph", "👁️"],
    ["Hana_Psy", "🧠"], ["Tarek_Anest", "💉"], ["Mouna_Labo", "🧪"],
    ["Fares_Inter", "🎓"], ["Dounia_Exter", "📚"], ["Sofiane_Res", "⚕️"],
    ["Amina_Infi", "💉"], ["Nadir_Kine", "🦾"], ["Leila_Sage", "🤱"],
    ["Hakim_Gene", "🧬"], ["Salma_Phys", "❤️"], ["Youcef_Bio", "🧪"],
    ["Rania_Micro", "🦠"], ["Walid_Semio", "🩺"], ["Ines_Anapath", "🫀"]
  ]

  return botNames.map(([name, emoji], idx) => {
    let xp = 0
    if (idx < 3) xp = Math.floor(Math.random() * 200) + 400
    else if (idx < 10) xp = Math.floor(Math.random() * 250) + 150
    else xp = Math.floor(Math.random() * 200)

    return {
      cohort_id: cohortId,
      user_id: `00000000-0000-0000-0000-${String(100 + idx).padStart(12, '0')}`,
      display_name: name,
      avatar_emoji: emoji,
      weekly_xp: 0,
      total_xp: Math.floor(Math.random() * 4000) + 1000,
      streak_days: Math.floor(Math.random() * 20) + 1,
      is_current_user: false,
      is_bot: true
    }
  })
}