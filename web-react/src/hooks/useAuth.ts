/**
 * Hook d'authentification Supabase (ADR-3)
 * 
 * Fournit :
 * - État de connexion
 * - Fonctions auth (signIn, signUp, signOut, signInWithGoogle)
 * - Progress synchronisation
 */

import { useEffect, useState, useCallback } from 'react'
import { setStorageScope, clearStoredProgress } from '../domain/store'
import { 
  supabase, 
  type ProgressInput,
  signIn as signInService,
  signUp as signUpService,
  signInWithGoogle as signInWithGoogleService,
  signOut as signOutService,
  saveProgress as saveProgressService,
  loadProgress as loadProgressService,
  exportUserData as exportUserDataService,
  checkForUpdates as checkForUpdatesService,
} from '../services/supabase'

type AuthState = {
  user: {
    id: string
    email: string | null
    user_metadata: Record<string, unknown>
  } | null
  loading: boolean
  error: string | null
}

export function useAuth() {
  const [authState, setAuthState] = useState<AuthState>({
    user: null,
    loading: true,
    error: null,
  })

  useEffect(() => {
    // Écouter les changements d'authentification
    const { data: { subscription } } = supabase.auth.onAuthStateChange(
      (event, session) => {
        if (session?.user) {
          setStorageScope(session.user.id)
        } else if (event === 'SIGNED_OUT') {
          setStorageScope(null)
          clearStoredProgress()
        }
        setAuthState(prev => ({
          ...prev,
          user: session?.user ? {
            id: session.user.id,
            email: session.user.email as string | null,
            user_metadata: session.user.user_metadata || {},
          } : null,
          loading: false,
        }))
      }
    )

    // Vérifier la session actuelle (au chargement)
    supabase.auth.getSession().then(({ data: { session }, error }) => {
      setAuthState({
        user: session?.user ? {
          id: session.user.id,
          email: session.user.email as string | null,
          user_metadata: session.user.user_metadata || {},
        } : null,
        loading: false,
        error: error?.message || null,
      })
    })

    return () => subscription.unsubscribe()
  }, [])

  const signIn = useCallback(async (email: string, password: string) => {
    return await signInService(email, password)
  }, [])

  const signUp = useCallback(async (email: string, password: string) => {
    return await signUpService(email, password)
  }, [])

  const signInWithGoogle = useCallback(async () => {
    await signInWithGoogleService()
  }, [])

  const signOut = useCallback(async () => {
    return await signOutService()
  }, [])

  const saveProgress = useCallback(async (progress: ProgressInput) => {
    return await saveProgressService(progress)
  }, [])

  const loadProgress = useCallback(async () => {
    return await loadProgressService()
  }, [])

  const exportUserData = useCallback(async () => {
    return await exportUserDataService()
  }, [])

  return { 
    ...authState, 
    signIn, 
    signUp, 
    signInWithGoogle, 
    signOut,
    saveProgress,
    loadProgress,
    exportUserData,
  }
}

/**
 * Hook dédié à la synchronisation
 */
export function useSync() {
  const { user } = useAuth()
  const [syncing, setSyncing] = useState(false)

  const syncProgress = useCallback(async (localProgress: ProgressInput): Promise<boolean> => {
    if (!user) return false
    
    setSyncing(true)
    try {
      // Vérifier si besoin de sync
      const { data: remote, error } = await supabase
        .from('user_progress')
        .select('updated_at')
        .eq('user_id', user.id)
        .single()

      if (error && error.code !== 'PGRST116') {
        // Pas de progression distante, créer
        const { error: insertError } = await supabase
          .from('user_progress')
          .insert({
            user_id: user.id,
            ...localProgress,
            updated_at: new Date().toISOString(),
          })
        return !insertError
      }

      // Comparaison timestamps
      if (remote?.updated_at) {
        const remoteTime = new Date(remote.updated_at).getTime()
        const localTime = new Date(localProgress.updated_at || Date.now().toString()).getTime()
        
        if (localTime > remoteTime) {
          // Local plus récent, faire upsert
          const { error: upsertError } = await supabase
            .from('user_progress')
            .upsert({
              user_id: user.id,
              ...localProgress,
              updated_at: new Date().toISOString(),
            }, { onConflict: 'user_id' })
          return !upsertError
        }
      }
      
      return true
    } finally {
      setSyncing(false)
    }
  }, [user])

  const checkForUpdates = useCallback(async (lastUpdate: string) => {
    if (!user) return { hasUpdates: false, data: null }
    return await checkForUpdatesService(lastUpdate)
  }, [user])

  return { syncing, syncProgress, checkForUpdates }
}