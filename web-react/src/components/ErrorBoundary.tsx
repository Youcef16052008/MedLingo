import { Component, type ReactNode } from "react";

interface Props {
  children: ReactNode;
}

interface State {
  hasError: boolean;
  error: Error | null;
}

/**
 * Top-level error boundary (R-04, Phase 2).
 *
 * Catches render/import failures (including rejected lazy chunks) and
 * offers a recovery action instead of unmounting the whole tree.
 */
export class ErrorBoundary extends Component<Props, State> {
  state: State = { hasError: false, error: null };

  static getDerivedStateFromError(error: Error): State {
    return { hasError: true, error };
  }

  componentDidCatch(error: Error, info: unknown) {
    // eslint-disable-next-line no-console
    console.error("[ErrorBoundary]", error, info);
  }

  reset = () => this.setState({ hasError: false, error: null });

  render() {
    if (this.state.hasError) {
      return (
        <div className="empty" style={{ padding: 32, textAlign: "center" }}>
          <p style={{ marginBottom: 16 }}>Une erreur est survenue.</p>
          <button className="btn primary" onClick={() => window.location.reload()}>
            Recharger
          </button>
          <button className="btn" style={{ marginLeft: 8 }} onClick={this.reset}>
            Réessayer
          </button>
        </div>
      );
    }
    return this.props.children;
  }
}