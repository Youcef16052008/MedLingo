/**
 * MedLingo - Service Supabase (ADR-3)
 * Synchronisation des comptes médicaux
 * 
 * Fonctionnalités :
 * - Auth e-mail + Google
 * - Sync progression SM-2 (last-write wins)
 * - Export/import données utilisateur
 */

import { createClient } from '@supabase/supabase-js'

const SUPABASE_URL = import.meta.env.VITE_SUPABASE_URL || 'https://fdqqabkormdiwahgmnlx.supabase.co'
const SUPABASE_KEY = import.meta.env.VITE_SUPABASE_KEY || 'sb_publishable_N7iBhNyrY00a8f87rKExvA_MbUX3V1d'

// Client Supabase côté client
export const supabase = createClient(SUPABASE_URL, SUPABASE_KEY)

// Types pour la synchronisation
// Migration 003 : la table user_progress est (user_id uuid, data jsonb, updated_at).
// Le blob distant est le SyncBlob du store (voir domain/syncMerge.ts), fusionné
// carte par carte — jamais un last-write-wins sur le blob entier (R-08).
export type UserProgress = {
  user_id: string
  data: Record<string, unknown>
  updated_at: string
  created_at?: string
}

// Type pour l'entrée utilisateur (sans user_id, mais avec updated_at optionnel pour sync)
export type ProgressInput = {
  data: Record<string, unknown>
  updated_at?: string
}

/**
 * Obtenir l'ID utilisateur actuel (ou lever une erreur)
 */
async function getCurrentUserId(): Promise<string> {
  const { data: { session }, error } = await supabase.auth.getSession()
  if (error || !session) {
    throw new Error('User not authenticated')
  }
  return session.user.id
}

/**
 * Connexion e-mail
 */
export async function signIn(email: string, password: string) {
  const { data, error } = await supabase.auth.signInWithPassword({ email, password })
  return { user: data?.user, session: data?.session, error }
}

/**
 * Inscription
 */
export async function signUp(email: string, password: string) {
  const { data, error } = await supabase.auth.signUp({ email, password })
  return { user: data?.user, session: data?.session, error }
}

/**
 * Connexion Google
 */
export async function signInWithGoogle() {
  const { data, error } = await supabase.auth.signInWithOAuth({
    provider: 'google',
    options: { redirectTo: window.location.origin },
  })
  return { url: data?.url, error }
}

/**
 * Déconnexion
 */
export async function signOut() {
  const { error } = await supabase.auth.signOut()
  return { error }
}

/**
 * Récupérer l'utilisateur connecté
 */
export async function getCurrentUser() {
  const { data: { user }, error } = await supabase.auth.getUser()
  return { user, error }
}

/**
 * Sauvegarder la progression (sync local → cloud)
 * Le blob est stocké tel quel dans la colonne jsonb `data` (migration 003).
 * La fusion avec l'état distant est faite côté client par mergeSyncBlobs.
 */
export async function saveProgress(progress: ProgressInput): Promise<{ data: any; error: any }> {
  const userId = await getCurrentUserId()
  
  const { data, error } = await supabase
    .from('user_progress')
    .upsert({
      user_id: userId,
      data: progress.data,
      updated_at: progress.updated_at ?? new Date().toISOString(),
    }, { onConflict: 'user_id' })
  
  return { data, error }
}

/**
 * Récupérer la progression (utilise la session courante)
 */
export async function loadProgress(): Promise<{ data: UserProgress | null; error: any }> {
  const userId = await getCurrentUserId()
  
  const { data, error } = await supabase
    .from('user_progress')
    .select('*')
    .eq('user_id', userId)
    .single()
  
  return { data: data as UserProgress | null, error }
}

/**
 * Export des données utilisateur (feature "Mes données")
 */
export async function exportUserData(): Promise<{ data: any; error: any }> {
  const userId = await getCurrentUserId()
  
  // Exporter : progrès, leçons, stats
  const { data: progress, error: progressError } = await supabase
    .from('user_progress')
    .select('*')
    .eq('user_id', userId)
    .single()
  
  if (progressError) return { data: null, error: progressError }
  
  return { 
    data: {
      exported_on: new Date().toISOString(),
      user_id: userId,
      progress,
      version: '1.0',
    },
    error: null
  }
}

/**
 * Nettoyer l'ancienne progression (reprise appareil)
 */
export async function clearRemoteProgress(): Promise<{ success: boolean; error: any }> {
  const userId = await getCurrentUserId()
  
  const { error } = await supabase
    .from('user_progress')
    .delete()
    .eq('user_id', userId)
  
  return { success: !error, error }
}

/**
 * Vérifier les notifications de progression depuis le cloud
 */
export async function checkForUpdates(lastUpdate: string): Promise<{ hasUpdates: boolean; data: UserProgress | null }> {
  const userId = await getCurrentUserId()
  
  const { data, error } = await supabase
    .from('user_progress')
    .select('*')
    .eq('user_id', userId)
    .gt('updated_at', lastUpdate)
    .single()
  
  return { 
    hasUpdates: !error && data !== null, 
    data: data as UserProgress | null 
  }
}

/**
 * Charger la progression avec un ID utilisateur spécifique
 */
export async function loadProgressForUser(userId: string): Promise<{ data: UserProgress | null; error: any }> {
  const { data, error } = await supabase
    .from('user_progress')
    .select('*')
    .eq('user_id', userId)
    .single()
  
  return { data: data as UserProgress | null, error }
}

/**
 * Vérifier les nouvelles notifications de progression (version avec userId explicite)
 */
export async function checkForUpdatesWithUserId(userId: string, lastUpdate: string): Promise<{ hasUpdates: boolean; data: UserProgress | null }> {
  const { data, error } = await supabase
    .from('user_progress')
    .select('*')
    .eq('user_id', userId)
    .gt('updated_at', lastUpdate)
    .single()
  
  return { 
    hasUpdates: !error && data !== null, 
    data: data as UserProgress | null 
  }
}

export default supabase