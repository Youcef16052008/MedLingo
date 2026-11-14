/* Service worker MedLingo DZ — application hors-ligne (mode production uniquement). */
const CACHE = "medlingo-web-v4";

const SHELL = [
  "./",
  "./index.html",
  "./manifest.webmanifest",
  "./icon.svg",
  "./favicon-16.png",
  "./favicon-32.png",
  "./icon-192.png",
  "./icon-512.png",
  "./apple-touch-icon.png",
  "./icon-maskable-512.png",
  "./data/terms.json",
  "./data/exercises.json",
  "./data/stories.v1.json",
  // WS2.1 : parcours par chapitres (LOT2) — précâchés maintenant qu'ils
  // sont chargés paresseusement (offline complet, cf. BUG-012).
  "./data/chapters.json",
  "./data/lesson_map.json",
];

self.addEventListener("install", (event) => {
  event.waitUntil(
    caches
      .open(CACHE)
      .then((cache) => cache.addAll(SHELL))
      .then(() => self.skipWaiting())
  );
});

self.addEventListener("activate", (event) => {
  event.waitUntil(
    caches
      .keys()
      .then((keys) =>
        Promise.all(keys.filter((k) => k !== CACHE).map((k) => caches.delete(k)))
      )
      .then(() => self.clients.claim())
  );
});

/**
 * putSanitized — stocke une réponse SANS `Content-Encoding`/`Content-Length`
 * ni `Vary`.
 *
 * Pourquoi (audit P2.4, 12/11/2026, deux constats réels hors-ligne) :
 *  1. le corps fourni par fetch() est déjà décompressé mais la réponse garde
 *     l'en-tête `Content-Encoding: gzip` du serveur (vite preview, nginx,
 *     CDN...) — resservie telle quelle depuis le cache hors-ligne, elle casse
 *     (net::ERR_FAILED) et l'application apparaît vide ;
 *  2. `Vary: Origin` émis par le serveur casse le matching du cache du SW
 *     pour les sous-ressources chargées par le document (les requêtes du SW
 *     et celles du document n'exposent pas le même Origin) → mêmes
 *     symptômes. Dans CE cache, on ne sert que cette application sur cette
 *     origine : `Vary` n'a pas d'utilité.
 * Les réponses opaques (polices) ne sont pas lisibles : elles restent
 * stockées brutes (repli).
 */
async function putSanitized(cache, req, res) {
  try {
    const headers = new Headers(res.headers);
    headers.delete("Content-Encoding");
    headers.delete("Content-Length");
    headers.delete("Vary");
    const body = await res.arrayBuffer();
    await cache.put(req, new Response(body, { status: res.status, statusText: res.statusText, headers }));
  } catch {
    await cache.put(req, res).catch(() => undefined);
  }
}

self.addEventListener("install", (event) => {
  event.waitUntil(
    (async () => {
      const cache = await caches.open(CACHE);
      // Les entrées sont récupérées en parallèle (l'ancienne boucle séquentielle
      // aurait ajouté ~22 allers-retours au temps d'installation).
      await Promise.allSettled(
        SHELL.map(async (url) => {
          const res = await fetch(url, { cache: "reload" });
          if (res.ok) await putSanitized(cache, url, res);
        })
      );
      await self.skipWaiting();
    })()
  );
});

/**
 * Stratégie :
 *  - navigations (SPA) → réseau d'abord pour propager les mises à jour,
 *    repli sur le shell caché uniquement hors-ligne ;
 *  - actifs/données → cache d'abord (dispo hors-ligne) avec revalidation
 *    en arrière-plan ;
 *  - polices Google → stale-while-revalidate : hors-ligne, la feuille CSS
 *    et les woff2 restent disponibles (B36). Les réponses « opaque » de la
 *    feuille (no-cors) sont mises en cache malgré `status = 0` ;
 *  - le repli `index.html` ne s'applique JAMAIS aux données/actifs : servir
 *    du HTML à une requête JSON/cassait le démarrage hors-ligne.
 */
const FONT_HOSTS = ["fonts.googleapis.com", "fonts.gstatic.com"];

self.addEventListener("fetch", (event) => {
  const req = event.request;
  if (req.method !== "GET") return;

  const host = new URL(req.url).host;
  if (FONT_HOSTS.includes(host)) {
    event.respondWith(
      caches.open(CACHE).then(async (cache) => {
        const hit = await cache.match(req);
        const refresh = fetch(req)
          .then((res) => {
            if (res.ok || res.type === "opaque") {
              const copy = res.clone();
              cache.put(req, copy).catch(() => undefined);
            }
            return res;
          })
          .catch(() => undefined);
        if (hit) return hit;
        return (await refresh) || Response.error();
      })
    );
    return;
  }

  if (new URL(req.url).origin !== self.location.origin) return;

  if (req.mode === "navigate") {
    event.respondWith(
      fetch(req)
        .then((res) => {
          if (res.ok) {
            const copy = res.clone();
            caches
              .open(CACHE)
              .then((c) => putSanitized(c, "./index.html", copy))
              .catch(() => undefined);
          }
          return res;
        })
        .catch(() =>
          caches.match("./index.html").then((hit) => hit || Response.error())
        )
    );
    return;
  }

  event.respondWith(
    caches.match(req).then((hit) => {
      const refresh = fetch(req)
        .then((res) => {
          if (res.ok) {
            caches.open(CACHE).then((c) => putSanitized(c, req, res)).catch(() => undefined);
          }
          return res;
        })
        .catch(() => undefined);

      if (hit) return hit; // cache d'abord, la revalidation mettra à jour le cache
      return refresh.then((res) => res || Response.error());
    })
  );
});
