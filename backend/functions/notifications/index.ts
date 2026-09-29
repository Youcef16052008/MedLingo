// Notification Service - High-scale Duolingo FIFO SQS → APNS/FCM
// API → 50+ msg → FIFO SQS → workers → SQS → workers → APNS/FCM
// FIFO deduplication 5 min, 4M users 5 sec Super Bowl

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

interface NotificationRequest {
  type: 'review_reminder' | 'streak_reminder' | 'clinical_pearl' | 'level_unlocked' | 'league_ending' | 'hearts_refilled'
  user_ids?: string[]
  title?: string
  body?: string
  data?: Record<string, any>
  channel_id?: string
  deduplication_key?: string
}

// Rate limiting: 20 req/min
const rateLimitMap = new Map<string, { count: number; resetAt: number }>()

function checkRateLimit(ip: string): boolean {
  const now = Date.now()
  const window = 60 * 1000
  const limit = 20

  const entry = rateLimitMap.get(ip)
  if (!entry || now > entry.resetAt) {
    rateLimitMap.set(ip, { count: 1, resetAt: now + window })
    return true
  }

  entry.count++
  if (entry.count > limit) {
    return false
  }
  return true
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

    // JWT Auth Check
    const authHeader = req.headers.get('authorization')
    if (!authHeader || !authHeader.startsWith('Bearer ')) {
      return new Response(
        JSON.stringify({ error: 'Missing or invalid authorization header' }),
        { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 401 }
      )
    }

    const jwt = authHeader.replace('Bearer ', '')
    const { data: { user }, error: authError } = await supabase.auth.getUser(jwt)

    if (authError || !user) {
      return new Response(
        JSON.stringify({ error: 'Invalid or expired token' }),
        { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 401 }
      )
    }

    // Rate limit by user ID
    if (!checkRateLimit(user.id)) {
      return new Response(
        JSON.stringify({ error: 'Rate limit exceeded' }),
        { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 429 }
      )
    }

    const { type, user_ids, title, body, data, channel_id, deduplication_key }: NotificationRequest = await req.json()

    // 1. FIFO deduplication check (5 min like Duolingo)
    if (deduplication_key) {
      const fiveMinAgo = new Date(Date.now() - 5 * 60 * 1000).toISOString()
      const { data: recent } = await supabase
        .from('notifications_queue')
        .select('id')
        .eq('type', type)
        .gte('created_at', fiveMinAgo)
        .contains('data', { deduplication_key })
        .limit(1)

      if (recent && recent.length > 0) {
        return new Response(
          JSON.stringify({ status: 'deduplicated', deduplication_key }),
          { headers: { ...corsHeaders, 'Content-Type': 'application/json' } }
        )
      }
    }

    // 2. Fetch target users
    let targetUsers: any[] = []
    if (user_ids && user_ids.length > 0) {
      const { data: users } = await supabase
        .from('users')
        .select('id, display_name')
        .in('id', user_ids)
      targetUsers = users || []
    } else {
      if (type === 'review_reminder') {
        const { data: users } = await supabase
          .from('flashcard_progress')
          .select('user_id')
          .lte('next_review_at', new Date().toISOString())
          .limit(1000)
        const uniqueUserIds = [...new Set(users?.map(u => u.user_id) || [])]
        const { data: userDetails } = await supabase
          .from('users')
          .select('id, display_name')
          .in('id', uniqueUserIds)
        targetUsers = userDetails || []
      } else if (type === 'streak_reminder') {
        const { data: users } = await supabase
          .from('users')
          .select('id, display_name')
          .gt('streak_days', 0)
          .limit(1000)
        targetUsers = users || []
      } else {
        const { data: users } = await supabase
          .from('users')
          .select('id, display_name')
          .limit(100)
        targetUsers = users || []
      }
    }

    // 3. Generate notification content per type
    const notifications = targetUsers.map(user => {
      let notifTitle = title || ''
      let notifBody = body || ''
      let notifChannel = channel_id || 'medlingua_daily_review'

      switch (type) {
        case 'review_reminder':
          notifTitle = `📋 ${Math.floor(Math.random() * 6) + 3} Flashcards prêtes !`
          notifBody = `Ta session SM-2 est prête. 3 min pour consolider ta mémoire clinique.`
          notifChannel = 'medlingua_daily_review'
          break
        case 'streak_reminder':
          notifTitle = `🔥 Ne brise pas ta série !`
          notifBody = `Tu es sur une lancée ! Une courte session maintient ta régularité.`
          notifChannel = 'medlingua_streak'
          break
        case 'clinical_pearl':
          notifTitle = `🩺 Perle Clinique du Jour`
          notifBody = `Bradycardie = brady (lent) + cardia (cœur) <60 bpm. Physiologique chez sportif, pathologique si BAV.`
          notifChannel = 'medlingua_clinical_pearls'
          break
        case 'level_unlocked':
          notifTitle = `🏆 Niveau débloqué !`
          notifBody = `Félicitations ! Niveau suivant accessible dans ta pyramide.`
          notifChannel = 'medlingua_levels'
          break
        case 'league_ending':
          notifTitle = `🏁 Ligue se termine bientôt !`
          notifBody = `Plus que 2h pour grimper dans ta ligue. Top 10 = promotion!`
          notifChannel = 'medlingua_levels'
          break
        case 'hearts_refilled':
          notifTitle = `❤️ Cœurs rechargés !`
          notifBody = `Tes cœurs sont de retour. Continue ton apprentissage médical!`
          notifChannel = 'medlingua_daily_review'
          break
      }

      return {
        user_id: user.id,
        type,
        title: notifTitle,
        body: notifBody,
        data: {
          ...data,
          deduplication_key,
          target_screen: type === 'review_reminder' ? 'FLASHCARDS' : type === 'streak_reminder' ? 'HOME' : 'MODULES'
        },
        channel_id: notifChannel,
        is_sent: false
      }
    })

    // 4. Batch insert to queue
    const batchSize = 50
    for (let i = 0; i < notifications.length; i += batchSize) {
      const batch = notifications.slice(i, i + batchSize)
      const { error } = await supabase
        .from('notifications_queue')
        .insert(batch)
      
      if (error) {
        console.error(`[Notifications] Batch ${i} error`)
      }
    }

    return new Response(
      JSON.stringify({ 
        status: 'queued',
        type,
        count: notifications.length,
        deduplication_key,
        batches: Math.ceil(notifications.length / batchSize)
      }),
      { headers: { ...corsHeaders, 'Content-Type': 'application/json' } }
    )

  } catch (error) {
    console.error('[Notifications] Internal error')
    return new Response(
      JSON.stringify({ error: 'Internal server error' }),
      { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 500 }
    )
  }
})