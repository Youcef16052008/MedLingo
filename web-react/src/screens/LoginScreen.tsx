/** 
 * LoginScreen - Authentification MedLingo (ADR-3)
 * 
 * Fonctionnalités :
 * - Connexion e-mail
 * - Inscription
 * - Connexion Google
 */

import { useState } from "react"
import { useAuth } from "../hooks/useAuth"
import { useToast } from "../components/ToastProvider"

export function LoginScreen() {
  const { signIn, signUp, signInWithGoogle } = useAuth()
  const toast = useToast()

  const [isLogin, setIsLogin] = useState(true)
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [loading, setLoading] = useState(false)

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true)
    const result = isLogin 
      ? await signIn(email, password)
      : await signUp(email, password)
    setLoading(false)
    if (result.error) {
      toast("Erreur : " + (result.error?.message || "inconnu"))
    } else {
      toast("Succès")
      // Pour le moment, on reste en mode offline (ADR-3)
    }
  }

  const handleGoogle = async () => {
    setLoading(true)
    await signInWithGoogle()
    setLoading(false)
  }

  return (
    <div className="login-screen">
      <div className="login-card">
        <div className="login-header">
          <div className="login-logo">🦅</div>
          <h1>{"MedLingo DZ - Authentifié"}</h1>
          <p className="muted">Supabase Sync · Optionnel</p>
        </div>

        <div className="login-toggle">
          <button
            type="button"
            className={`toggle ${isLogin ? "active" : ""}`}
            onClick={() => setIsLogin(true)}
          >
            {"Connexion"}
          </button>
          <button
            type="button"
            className={`toggle ${!isLogin ? "active" : ""}`}
            onClick={() => setIsLogin(false)}
          >
            {"Inscription"}
          </button>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="email">{"Email"}</label>
            <input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="vous@exemple.com"
              required
              autoComplete="email"
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">{"Mot de passe"}</label>
            <div className="input-with-icon">
              <input
                id="password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder={"Mot de passe"}
                minLength={6}
                required
                autoComplete={isLogin ? "current-password" : "new-password"}
              />
            </div>
          </div>

          <button
            type="submit"
            className="btn primary full"
            disabled={loading || !email || !password}
          >
            {loading ? (
              <span className="spinner">⟳</span>
            ) : isLogin ? (
              "Connexion"
            ) : (
              "Inscription"
            )}
          </button>
        </form>

        <div className="login-divider">
          <span>{"ou"}</span>
        </div>

        <button
          type="button"
          className="btn google full"
          onClick={handleGoogle}
          disabled={loading}
        >
          <span className="google-icon">🔍</span>
          {"Google"}
        </button>

        <p className="login-footer">
          {"Pas de compte ? "}
          <button
            type="button"
            className="link"
            onClick={() => setIsLogin(false)}
          >
            {"Inscription"}
          </button>
        </p>
      </div>
    </div>
  )
}