// Mini-client CDP pour tester MedLingo web : navigation + clics + capture.
// Usage : node tools/cdp.mjs <url> [js-expression] [screenshot-out.png]
// Chrome doit tourner avec --remote-debugging-port=9222.
const [, , url, expr, out] = process.argv;

const delay = (ms) => new Promise((r) => setTimeout(r, ms));

async function main() {
  const targets = await fetch("http://127.0.0.1:9222/json/list").then((r) => r.json());
  const page = targets.find((t) => t.type === "page");
  if (!page) throw new Error("aucune page CDP");

  const ws = new WebSocket(page.webSocketDebuggerUrl);
  await new Promise((res, rej) => { ws.onopen = res; ws.onerror = rej; });

  let id = 0;
  const pending = new Map();
  ws.onmessage = (ev) => {
    const msg = JSON.parse(ev.data);
    if (msg.id && pending.has(msg.id)) {
      const { res, rej } = pending.get(msg.id);
      pending.delete(msg.id);
      msg.error ? rej(new Error(msg.error.message)) : res(msg.result);
    }
  };
  const send = (method, params = {}) => new Promise((res, rej) => {
    const mid = ++id;
    pending.set(mid, { res, rej });
    ws.send(JSON.stringify({ id: mid, method, params }));
  });

  await send("Page.enable");
  await send("Network.enable");
  await send("Network.setCacheDisabled", { cacheDisabled: true }); // tests toujours à jour
  // query cache-bust → force un vrai rechargement du document (même URL/hash)
  const target = new URL(url);
  target.searchParams.set("_cb", String(Date.now()));
  await send("Page.navigate", { url: target.toString() });
  await delay(2500); // fetch des données + rendu

  if (expr) {
    const r = await send("Runtime.evaluate", { expression: expr, awaitPromise: true, returnByValue: true });
    console.log("eval →", JSON.stringify(r.result?.value ?? r.result ?? null));
    await delay(1200);
  }

  if (out) {
    const shot = await send("Page.captureScreenshot", { format: "png" });
    const { writeFileSync } = await import("node:fs");
    writeFileSync(out, Buffer.from(shot.data, "base64"));
    console.log("screenshot →", out);
  }
  ws.close();
}

main().catch((e) => { console.error("CDP ERROR:", e.message); process.exit(1); });
