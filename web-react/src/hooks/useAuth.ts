/**
 * Hook d'authentification Supabase (ADR-3)
 * 
 * Fournit :
 * - État de connexion
 * - Fonctions auth (signIn, signUp, signOut, signInWithGoogle)
 * - Progress synchronisation
 */

import { useEffect, useState, useCallback } from 'react'
import { setStorageScope, clearStoredProgress, getState, update } from '../domain/store'
import { mergeSyncBlobs, toSyncBlob, applySyncBlob, type SyncBlob } from '../domain/syncMerge'
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
 * Hook dédié à la synchronisation (R-07 / R-08).
 *
 * `syncNow()` exécute un cycle pull → merge carte par carte → push :
 * 1. charge le blob distant (`user_progress.data`) ;
 * 2. le fusionne avec l'état local via `mergeSyncBlobs` (aucune régression
 *    de carte SM-2, de score ou de trophée, quel que soit le sens) ;
 * 3. réapplique le résultat sur le store local puis repousse le blob fusionné.
 *
 * En l'absence de session ou de réseau, on renvoie un statut explicite au
 * lieu d'échouer silencieusement.
 */
export function useSync() {
  const { user } = useAuth()
  const [syncing, setSyncing] = useState(false)

  type SyncResult = 'ok' | 'nouser' | 'offline' | 'error'

  const syncNow = useCallback(async (): Promise<SyncResult> => {
    if (!user) return 'nouser'
    setSyncing(true)
    try {
      // 1. Pull
      const { data: remoteRow, error: pullError } = await supabase
        .from('user_progress')
        .select('data, updated_at')
        .eq('user_id', user.id)
        .maybeSingle()

      if (pullError) return 'offline'

      // 2. Merge (carte par carte, symétrique)
      const localBlob = toSyncBlob(getState())
      const remoteBlob = (remoteRow?.data ?? null) as Partial<SyncBlob> | null
      const merged = remoteBlob
        ? mergeSyncBlobs(localBlob, remoteBlob)
        : localBlob

      // 3. Appliquer localement puis pousser le fusionné
      update((draft) => applySyncBlob(draft, merged))
      const { error: pushError } = await saveProgressService({
        data: merged as unknown as Record<string, unknown>,
        updated_at: new Date().toISOString(),
      })
      if (pushError) return 'error'
      return 'ok'
    } catch {
      return 'offline'
    } finally {
      setSyncing(false)
    }
  }, [user])

  const checkForUpdates = useCallback(async (lastUpdate: string) => {
    if (!user) return { hasUpdates: false, data: null }
    return await checkForUpdatesService(lastUpdate)
  }, [user])

  return { syncing, syncNow, checkForUpdates }
}