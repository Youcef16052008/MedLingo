"""Génère toutes les icônes de MedLingo Web à partir de la mascotte Android.

Source : ``app/src/main/res/drawable-nodpi/ic_brand_foreground.png``
(1024×1024, RGB — fond blanc, carré vert arrondi avec un liseré sombre, hibou).

Traitement :
  1. aplatissement du fond en vert plein ``#35BE56`` : on remplit tout ce qui est
     hors d'un rectangle interne (ça avale le liseré et l'anti-crénelage) puis on
     complète par un ``floodfill`` depuis le coin pour les angles restants ;
  2. lissage du dégradé du carré (blur itératif sur le masque de fond) ;
  3. recadrage autour du hibou (cadrages ``FULL`` / ``MASKABLE``) ;
  4. export PNG plein-cadre opaque + ``icon.svg`` embarquant la vignette en base64.

Aucun pixel transparent ne subsiste : l'onglet affiche un carré vert plein.

Usage : python scripts/make_icons.py   (ou : npm run icons)
"""
import base64
import io
import pathlib
import sys

from PIL import Image, ImageChops, ImageDraw, ImageFilter

sys.stdout.reconfigure(encoding="utf-8", errors="replace")

ROOT = pathlib.Path(__file__).resolve().parent.parent
PUBLIC = ROOT / "public"
MASCOT = ROOT.parent / "app" / "src" / "main" / "res" / "drawable-nodpi" / "ic_brand_foreground.png"

SQ = (53, 190, 86)  # vert mesuré sur la mascotte
SQ_HEX = "#35BE56"
CANVAS = (1024, 1024)

# géométrie mesurée sur la source
OWL_BBOX = (197, 232, 777, 799)  # hibou
INNER = (164, 164, 860, 860)  # intérieur du carré vert, liseré de 20 px sacrifié
SEED = (512, 620)  # point d'amorce dans la blouse du hibou

# cadrages (centre, côté en px sur le canevas 1024)
FULL = ((487, 515), 760)  # hibou entier, marge verte autour
MASKABLE = ((487, 515), 940)  # hibou entier + marge (zone sûre 80 %)

TARGETS = [
    ("favicon-16.png", FULL, 16),
    ("favicon-32.png", FULL, 32),
    ("icon-192.png", FULL, 192),
    ("icon-512.png", FULL, 512),
    ("apple-touch-icon.png", FULL, 180),
    ("icon-maskable-512.png", MASKABLE, 512),
]

SVG_LOGICAL_PX = 128


def over_limit(diff: Image.Image, limit: int) -> Image.Image:
    """Masque binaire : au moins un canal dépasse ``limit``."""
    hi = lambda v: 255 if v > limit else 0
    r, g, b = diff.split()
    return ImageChops.lighter(ImageChops.lighter(r.point(hi), g.point(hi)), b.point(hi))


def flatten(src: Image.Image) -> tuple[Image.Image, Image.Image]:
    """Aplatie tout le décor en vert plein en ne conservant que le hibou.

    Le hibou est la seule composante, à l'intérieur du carré vert, dont les
    pixels s'écartent nettement du vert de la mascotte ; le fond blanc, le liseré,
    l'anti-crénelage et le dégradé du carré n'y figurent pas et deviennent du
    vert uni. Renvoie (image aplatie, masque du sujet conservé)."""
    loin = over_limit(ImageChops.difference(src, Image.new("RGB", CANVAS, SQ)), 12)
    interne = Image.new("L", CANVAS, 0)
    ImageDraw.Draw(interne).rectangle(list(INNER), fill=255)
    candidat = ImageChops.multiply(loin, interne)

    tmp = candidat.copy()
    ImageDraw.floodfill(tmp, SEED, 128, thresh=0)  # composante du hibou
    sujet = tmp.point(lambda v: 255 if v == 128 else 0)
    # +2 px pour conserver l'anti-crénelage du pourtour du hibou
    sujet = ImageChops.multiply(sujet.filter(ImageFilter.MaxFilter(5)), interne)

    out = src.copy()
    out.paste(SQ, (0, 0), ImageChops.invert(sujet))
    return out, sujet


def hors_du(mask: Image.Image) -> int:
    return mask.histogram()[255]


def check(src: Image.Image, flat: Image.Image, sujet: Image.Image) -> None:
    """Garantit : pourtour 100 % vert, hibou sain, plus aucun blanc résiduel."""
    # tout le cadre de 8 px est exactement le vert de la mascotte
    cadre = Image.new("L", CANVAS, 0)
    d = ImageDraw.Draw(cadre)
    d.rectangle([0, 0, CANVAS[0] - 1, 7], fill=255)
    d.rectangle([0, CANVAS[1] - 8, CANVAS[0] - 1, CANVAS[1] - 1], fill=255)
    d.rectangle([0, 0, 7, CANVAS[1] - 1], fill=255)
    d.rectangle([CANVAS[0] - 8, 0, CANVAS[0] - 1, CANVAS[1] - 1], fill=255)
    ecart = ImageChops.difference(flat, Image.new("RGB", CANVAS, SQ))
    if hors_du(ImageChops.multiply(over_limit(ecart, 0), cadre)):
        raise SystemExit("  ✗ le cadre de 8 px n'est pas uniformément vert")

    # le hibou n'est pas mangé : les pixels de référence sont identiques à la source
    for px in ((512, 620), (430, 330), (430, 650), (500, 790), (240, 420)):
        if flat.getpixel(px) != src.getpixel(px):
            raise SystemExit(
                f"  ✗ le hibou est altéré en {px} : {src.getpixel(px)} → {flat.getpixel(px)}"
            )
    if sujet.getpixel(SEED) == 0:
        raise SystemExit("  ✗ la composante du hibou n'a pas été trouvée")

    # aucun blanc ni gris ne subsiste hors du hibou
    zone = ImageChops.invert(sujet)
    r, g, b = flat.split()
    mn = ImageChops.darker(ImageChops.darker(r, g), b)
    mx = ImageChops.lighter(ImageChops.lighter(r, g), b)
    clair = mn.point(lambda v: 255 if v >= 150 else 0)
    plat = ImageChops.difference(mx, mn).point(lambda v: 255 if v <= 90 else 0)
    if hors_du(ImageChops.multiply(ImageChops.multiply(clair, plat), zone)):
        raise SystemExit("  ✗ un résidu blanc/gris subsiste hors du hibou")

    # les angles du rectangle interne appartiennent au fond, pas au sujet
    for px in ((INNER[0] + 4, INNER[1] + 4), (INNER[2] - 4, INNER[3] - 4)):
        if sujet.getpixel(px):
            raise SystemExit(f"  ✗ un coin {px} reste dans le sujet")


def box(spec: tuple[tuple[int, int], int]) -> tuple[int, int, int, int]:
    (cx, cy), side = spec
    half = side // 2
    x0 = max(0, min(CANVAS[0] - side, cx - half))
    y0 = max(0, min(CANVAS[1] - side, cy - half))
    return (x0, y0, x0 + side, y0 + side)


def render(flat: Image.Image, spec: tuple[tuple[int, int], int], size: int) -> Image.Image:
    tile = flat.crop(box(spec))
    if size != tile.width:
        tile = tile.resize((size, size), Image.Resampling.LANCZOS)
    return tile


def svg(flat: Image.Image, spec: tuple[tuple[int, int], int]) -> str:
    tile = render(flat, spec, SVG_LOGICAL_PX)
    buf = io.BytesIO()
    tile.save(buf, "PNG", optimize=True)
    data = base64.b64encode(buf.getvalue()).decode("ascii")
    return (
        f'<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"'
        f' viewBox="0 0 {SVG_LOGICAL_PX} {SVG_LOGICAL_PX}" width="{SVG_LOGICAL_PX}"'
        f' height="{SVG_LOGICAL_PX}">'
        f'<rect width="{SVG_LOGICAL_PX}" height="{SVG_LOGICAL_PX}" fill="{SQ_HEX}"/>'
        f'<image x="0" y="0" width="{SVG_LOGICAL_PX}" height="{SVG_LOGICAL_PX}"'
        f' href="data:image/png;base64,{data}"'
        f' xlink:href="data:image/png;base64,{data}"/>'
        "</svg>\n"
    )


def main() -> int:
    if not MASCOT.exists():
        print(f"  ✗ mascotte introuvable : {MASCOT}")
        return 1

    src = Image.open(MASCOT).convert("RGB")
    if src.size != CANVAS:
        src = src.resize(CANVAS, Image.Resampling.LANCZOS)
    print(f"  source : {MASCOT.name} {src.size[0]}×{src.size[1]}")

    flat, sujet = flatten(src)
    check(src, flat, sujet)
    print("  fond aplati en vert plein, hibou intact ✓")

    previews = ROOT / "shots"
    if previews.is_dir():
        render(flat, FULL, 512).save(previews / "_icon_full.png")
        
    for name, spec, size in TARGETS:
        out = PUBLIC / name
        render(flat, spec, size).save(out, "PNG", optimize=True)
        print(f"  ✓ {name} → {out.stat().st_size / 1024:.1f} ko ({size}×{size})")

    out = PUBLIC / "icon.svg"
    out.write_text(svg(flat, FULL), encoding="utf-8")
    ko = out.stat().st_size / 1024
    print(f"  {'✓' if ko <= 90 else '✗'} icon.svg → {ko:.1f} ko (favicon navigateur)")
    if ko > 90:
        return 1

    for name, _, _ in TARGETS:
        if (PUBLIC / name).read_bytes()[:8] != b"\x89PNG\r\n\x1a\n":
            print(f"  ✗ {name} n'est pas un PNG valide")
            return 1

    ancien = PUBLIC / "icon-maskable.svg"
    if ancien.exists():
        ancien.unlink()
        print("  ✓ icon-maskable.svg supprimé (redondant avec le PNG maskable)")

    print(f"  {len(TARGETS) + 1} icônes générées dans {PUBLIC}")
    return 0


if __name__ == "__main__":
    sys.exit(main())


