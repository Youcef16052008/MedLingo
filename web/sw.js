/* MedLingo DZ — Service Worker
   - Code applicatif (HTML/JS/CSS) : network-first → jamais périmé, cache fallback hors-ligne
   - Données JSON : cache-first → 1 640 termes servis instantanément, revalidées en fond */
const VERSION = "medlingo-web-v2";
const SHELL = [
  "./",
  "./index.html",
  "./styles.css",
  "./app.js",
  "./manifest.webmanifest",
  "./icon.svg",
  "./icon-maskable.svg",
];

self.addEventListener("install", (e) => {
  e.waitUntil(caches.open(VERSION).then((c) => c.addAll(SHELL)).then(() => self.skipWaiting()));
});

self.addEventListener("activate", (e) => {
  e.waitUntil(
    caches.keys()
      .then((keys) => Promise.all(keys.filter((k) => k !== VERSION).map((k) => caches.delete(k))))
      .then(() => self.clients.claim())
  );
});

const isData = (p) => p.includes("/data/") || p.endsWith(".json");

self.addEventListener("fetch", (e) => {
  if (e.request.method !== "GET") return;
  const url = new URL(e.request.url);
  if (url.origin !== location.origin) return; // laisser passer Google Fonts

  if (isData(url.pathname)) {
    // cache-first + revalidation en arrière-plan
    e.respondWith(
      caches.match(e.request).then((hit) => {
        const refresh = fetch(e.request).then((res) => {
          if (res.ok) caches.open(VERSION).then((c) => c.put(e.request, res.clone()));
          return res;
        }).catch(() => hit);
        return hit || refresh;
      })
    );
    return;
  }

  // network-first pour l'app shell
  e.respondWith(
    fetch(e.request).then((res) => {
      if (res.ok) {
        const copy = res.clone();
        caches.open(VERSION).then((c) => c.put(e.request, copy));
      }
      return res;
    }).catch(() => caches.match(e.request).then((h) => h || caches.match("./index.html")))
  );
});
