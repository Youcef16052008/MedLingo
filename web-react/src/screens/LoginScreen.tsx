/**
 * LoginScreen - Authentification MedLingo (ADR-3)
 *
 * Fonctionnalités :
 * - Connexion e-mail
 * - Inscription (avec message "vérifiez votre e-mail" si confirmation requise)
 * - Connexion Google
 */

import { useState } from "react"
import { useAuth } from "../hooks/useAuth"
import { useT } from "../i18n/useT"
import { useToast } from "../components/ToastProvider"

export function LoginScreen() {
  const { signIn, signUp, signInWithGoogle } = useAuth()
  const { t } = useT()
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
      toast(t("loginErrorPrefix") + (result.error?.message || "?"))
    } else if (!isLogin && !result.session) {
      // R-06: signUp succeeded but email confirmation is pending —
      // do NOT report a plain success that implies an active session.
      toast(t("loginCheckEmail"))
    } else {
      toast("✓ " + t("loginSuccess"))
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
          <div className="login-logo">🩺</div>
          <h1>{t("loginTitle")}</h1>
          <p className="muted">{t("loginSubtitle")}</p>
        </div>

        <div className="login-toggle">
          <button
            type="button"
            className={`toggle ${isLogin ? "active" : ""}`}
            onClick={() => setIsLogin(true)}
          >
            {t("loginSignIn")}
          </button>
          <button
            type="button"
            className={`toggle ${!isLogin ? "active" : ""}`}
            onClick={() => setIsLogin(false)}
          >
            {t("loginSignUp")}
          </button>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="email">{t("loginEmail")}</label>
            <input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder={t("loginEmailPlaceholder")}
              required
              autoComplete="email"
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">{t("loginPassword")}</label>
            <div className="input-with-icon">
              <input
                id="password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder={t("loginPassword")}
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
              <span className="spinner">⏳</span>
            ) : isLogin ? (
              t("loginSignIn")
            ) : (
              t("loginSignUp")
            )}
          </button>
        </form>

        <div className="login-divider">
          <span>{t("loginOr")}</span>
        </div>

        <button
          type="button"
          className="btn google full"
          onClick={handleGoogle}
          disabled={loading}
        >
          <span className="google-icon">G</span>
          {"Google"}
        </button>

        <p className="login-footer">
          {t("loginNoAccount")}{" "}
          <button
            type="button"
            className="link"
            onClick={() => setIsLogin(false)}
          >
            {t("loginSignUp")}
          </button>
        </p>
      </div>
    </div>
  )
}
