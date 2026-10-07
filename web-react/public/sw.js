/* Service worker MedLingo DZ — application hors-ligne (mode production uniquement). */
const CACHE = "medlingo-web-v3";

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
              .then((c) => c.put("./index.html", copy))
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
            const copy = res.clone();
            caches.open(CACHE).then((c) => c.put(req, copy)).catch(() => undefined);
          }
          return res;
        })
        .catch(() => undefined);

      if (hit) return hit; // cache d'abord, la revalidation mettra à jour le cache
      return refresh.then((res) => res || Response.error());
    })
  );
});
