/**
 * TypeScript types for Supabase Database
 * Generated for MedLingo ADR-3 synchronisation comptes
 */

export type Database = {
  public: {
    tables: {
      user_progress: {
        Row: {
          user_id: string
          xp: number
          level: number
          streak: number
          heart: number
          birdbrain_vector: number[]
          lessons_completed: string[]
          lessons_in_progress: string[]
          lessons_next_review: { lessonId: string; nextReview: number }[]
          league_position: number | null
          updated_at: string
          created_at: string
        }
        Insert: {
          user_id: string
          xp?: number
          level?: number
          streak?: number
          heart?: number
          birdbrain_vector?: number[]
          lessons_completed?: string[]
          lessons_in_progress?: string[]
          lessons_next_review?: { lessonId: string; nextReview: number }[]
          league_position?: number | null
        }
        Update: {
          xp?: number
          level?: number
          streak?: number
          heart?: number
          birdbrain_vector?: number[]
          lessons_completed?: string[]
          lessons_in_progress?: string[]
          lessons_next_review?: { lessonId: string; nextReview: number }[]
          league_position?: number | null
          updated_at: string
        }
      }
    }
  }
}