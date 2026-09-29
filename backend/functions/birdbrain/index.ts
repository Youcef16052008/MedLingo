// Birdbrain V2 Lite - Duolingo LSTM 40-dim vector implementation
// PyTorch P3 GPU in production, here TypeScript lite with SM-2 + heuristics
// 1B exercises/day, 14ms decision, tens of billions inferences/day

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

// Seeded PRNG for deterministic vector updates
function mulberry32(a: number): () => number {
  return function() {
    a |= 0; a = a + 0x6D2B79F5 | 0
    var t = Math.imul(a ^ a >>> 15, 1 | a)
    t = t + Math.imul(t ^ t >>> 7, 61 | t) ^ t
    return ((t ^ t >>> 14) >>> 0) / 4294967296
  }
}

interface BirdbrainRequest {
  user_id: string
  exercise_id: string
  is_correct: boolean
  time_spent_millis: number
  hints_used: number
  current_vector: number[] // 40-dim
}

interface BirdbrainResponse {
  new_vector: number[] // 40-dim updated
  predicted_forget_prob: number // HLR Half-Life Regression
  next_review_in_hours: number
  ability_delta: number
}

function validateBirdbrainRequest(body: any): BirdbrainRequest | { error: string } {
  if (!body || typeof body !== 'object') {
    return { error: 'Invalid request body' }
  }
  if (typeof body.user_id !== 'string' || !body.user_id) {
    return { error: 'user_id is required' }
  }
  if (typeof body.exercise_id !== 'string' || !body.exercise_id) {
    return { error: 'exercise_id is required' }
  }
  if (typeof body.is_correct !== 'boolean') {
    return { error: 'is_correct must be a boolean' }
  }
  if (typeof body.time_spent_millis !== 'number' || body.time_spent_millis < 0) {
    return { error: 'time_spent_millis must be a non-negative number' }
  }
  if (typeof body.hints_used !== 'number' || body.hints_used < 0) {
    return { error: 'hints_used must be a non-negative number' }
  }
  if (!Array.isArray(body.current_vector)) {
    return { error: 'current_vector must be an array' }
  }
  if (body.current_vector.length !== 40) {
    return { error: 'current_vector must be 40-dim' }
  }
  return body as BirdbrainRequest
}

// Rate limiting: 100 req/min per user
const rateLimitMap = new Map<string, { count: number; resetAt: number }>()

function checkRateLimit(userId: string): boolean {
  const now = Date.now()
  const window = 60 * 1000 // 1 minute
  const limit = 100

  const entry = rateLimitMap.get(userId)
  if (!entry || now > entry.resetAt) {
    rateLimitMap.set(userId, { count: 1, resetAt: now + window })
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

    // Parse and validate request
    const rawBody = await req.json()
    const validation = validateBirdbrainRequest(rawBody)
    if ('error' in validation) {
      return new Response(
        JSON.stringify({ error: validation.error }),
        { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 400 }
      )
    }

    const { user_id, exercise_id, is_correct, time_spent_millis, hints_used, current_vector }: BirdbrainRequest = validation

    // Verify user_id matches authenticated user
    if (user.id !== user_id) {
      return new Response(
        JSON.stringify({ error: 'User ID mismatch' }),
        { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 403 }
      )
    }

    // Rate limit check
    if (!checkRateLimit(user_id)) {
      return new Response(
        JSON.stringify({ error: 'Rate limit exceeded' }),
        { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 429 }
      )
    }

    // 1. Fetch exercise metadata with error boundary
    const { data: exercise, error: exerciseError } = await supabase
      .from('exercises')
      .select('*')
      .eq('id', exercise_id)
      .single()

    if (exerciseError || !exercise) {
      return new Response(
        JSON.stringify({ error: 'Exercise not found' }),
        { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 404 }
      )
    }

    // 2. Birdbrain V1: Elo-like scalar update
    const timeFactor = Math.max(0.5, Math.min(1.5, 10000 / Math.max(1000, time_spent_millis)))
    const hintPenalty = hints_used * 0.1
    const correctnessFactor = is_correct ? 1 : -1

    const abilityDelta = (correctnessFactor * 0.05 * timeFactor) - hintPenalty
    let newAbility = (current_vector[0] || 0.5) + abilityDelta
    newAbility = Math.max(0.05, Math.min(0.95, newAbility))

    // 3. Birdbrain V2: LSTM-like 40-dim vector update (simplified)
    let newVector = [...current_vector]
    if (newVector.length !== 40) {
      newVector = Array(40).fill(0)
      newVector[0] = newAbility
    }

    newVector[0] = newAbility

    const exerciseLevel = exercise?.level || 1
    if (exerciseLevel >= 1 && exerciseLevel <= 6) {
      const levelIdx = exerciseLevel
      if (levelIdx < newVector.length) {
        newVector[levelIdx] = Math.max(0, Math.min(1, (newVector[levelIdx] || 0.5) + abilityDelta * 0.5))
      }
    }

    const speedIdx = 6
    const currentSpeed = newVector[speedIdx] || 0.5
    const newSpeed = is_correct ?
      (currentSpeed * 0.9 + (timeFactor > 1 ? 0.1 : 0)) :
      (currentSpeed * 0.9 - 0.05)
    newVector[speedIdx] = Math.max(0, Math.min(1, newSpeed))

    const hintIdx = 11
    newVector[hintIdx] = Math.max(0, Math.min(1, (newVector[hintIdx] || 0.5) + (hints_used > 0 ? 0.05 : -0.02)))

    const consistencyIdx = 16
    newVector[consistencyIdx] = is_correct ?
      Math.min(1, (newVector[consistencyIdx] || 0.5) + 0.03) :
      Math.max(0, (newVector[consistencyIdx] || 0.5) - 0.1)

    const module = exercise?.module || 'Anatomie'
    const moduleMap: Record<string, number> = {
      'Anatomie': 21, 'Physiologie': 22, 'Biochimie': 23, 'Histologie': 24,
      'Biophysique': 25, 'Génétique': 26, 'Terminologie': 27, 'Anglais Médical': 28,
      'Microbiologie': 29, 'Pharmacologie': 30
    }
    const moduleIdx = moduleMap[module] || 21
    if (moduleIdx < newVector.length) {
      newVector[moduleIdx] = Math.max(0, Math.min(1, (newVector[moduleIdx] || 0.5) + abilityDelta * 0.3))
    }

    // Use seeded PRNG for deterministic noise
    const seed = exercise_id.split('').reduce((acc, char) => acc + char.charCodeAt(0), 0)
    const rng = mulberry32(seed + Date.now())
    for (let i = 31; i < 40; i++) {
      newVector[i] = (newVector[i] || 0) + (rng() - 0.5) * 0.01
      newVector[i] = Math.max(-1, Math.min(1, newVector[i]))
    }

    // 4. HLR (Half-Life Regression)
    const baseHalfLifeHours = 24
    const abilityMultiplier = 1 + newAbility * 2
    const correctnessMultiplier = is_correct ? 1.5 : 0.5
    const halfLifeHours = baseHalfLifeHours * abilityMultiplier * correctnessMultiplier

    const t = 24
    const forgetProb = 1 - Math.pow(2, -t / halfLifeHours)

    const nextReviewHours = halfLifeHours * 0.8

    // 5. Update user in DB
    await supabase
      .from('users')
      .update({
        birdbrain_vector: newVector,
        birdbrain_updated_at: new Date().toISOString()
      })
      .eq('id', user_id)

    // 6. Log to progress table (PII removed)
    await supabase.from('progress').insert({
      lesson_id: 'birdbrain-update',
      unit_id: 'system',
      accuracy: is_correct ? 1 : 0,
      correct_answers: is_correct ? 1 : 0,
      total_questions: 1,
      xp_earned: 0,
      time_spent_millis,
      hints_used,
      is_perfect: is_correct
    })

    const response: BirdbrainResponse = {
      new_vector: newVector,
      predicted_forget_prob: forgetProb,
      next_review_in_hours: nextReviewHours,
      ability_delta: abilityDelta
    }

    return new Response(
      JSON.stringify(response),
      { headers: { ...corsHeaders, 'Content-Type': 'application/json' } }
    )

  } catch (error) {
    console.error('[Birdbrain] Internal error')
    return new Response(
      JSON.stringify({ error: 'Internal server error' }),
      { headers: { ...corsHeaders, 'Content-Type': 'application/json' }, status: 500 }
    )
  }
})
