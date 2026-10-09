# Hand-drawn boxes & flows (CSS only)

A lightweight, self-contained way to draw **rectangle boxes, arrow flows, a database
cylinder, and a test pyramid** directly in a slide - no images, no external tooling. The
look mimics Excalidraw (wobbly hand-drawn borders + a handwriting font) and inherits the
theme's accent color so it sits cleanly on light or dark decks.

Use this for **simple box-and-arrow flows, pipelines, before/after, and pyramids**. For
richer architecture / system / sequence diagrams, prefer the Excalidraw MCP + exported PNG
(see `SKILL.md`).

## How it works

The "hand-drawn" rectangle is plain CSS - no SVG, no `rough.js`:

- **Wobbly border**: an asymmetric `border-radius` with different horizontal/vertical radii
  per corner, e.g. `border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px;`. This is
  the whole trick - corners get uneven curves that read as a hand-drawn rectangle.
- **Handwriting font**: Google Font *Architects Daughter* (close to Excalidraw's Virgil),
  imported by the shared theme.
- **Slight rotation**: `transform: rotate(-0.6deg)` (alternate boxes use `+0.7deg`) so the
  row doesn't look mechanically straight.
- **Soft offset shadow**: `box-shadow: 2px 3px 0 rgba(15,23,42,0.12)` for a sketched lift.
- Colors come from theme tokens (`--pt-heading`, `--pt-link`, `--pt-muted`) so accents match
  the active palette.

## Drop-in CSS

The helpers (`.flow`, `.sketch`, `.arrow`, `.db`, `.pyramid`) and the *Architects Daughter*
font import already ship in `shared/theme/pragmatech.css`, so **no paste is needed**. The
block below is kept as a reference of what the theme defines. Deck-specific variants (new
grid layouts, sizes) go in the deck's front-matter `style: |` or a `<style scoped>` block,
never in the shared theme.

```html
<style>
@import url('https://fonts.googleapis.com/css2?family=Architects+Daughter&display=swap');

/* --- Hand-drawn diagram building blocks --- */
.flow {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  gap: 14px;
  font-family: 'Architects Daughter', cursive;
  margin: 0.4em 0 1.3em;          /* bottom margin keeps boxes off the next block (code/text) */
}
.flow.col { flex-direction: column; align-items: stretch; }

.sketch {
  font-family: 'Architects Daughter', cursive;
  font-size: 0.8em;
  line-height: 1.2;
  text-align: center;
  padding: 0.55em 0.9em;
  border: 2.5px solid var(--pt-heading);
  background: var(--pt-bg-1);     /* white on light decks; swap to var(--pt-surface) for more contrast on dark */
  border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px;  /* the hand-drawn corner trick */
  box-shadow: 2px 3px 0 rgba(15, 23, 42, 0.12);
  transform: rotate(-0.6deg);
}
.sketch.accent { border-color: var(--pt-link); color: var(--pt-link); }  /* highlight a box */
.sketch.alt    { transform: rotate(0.7deg); }                            /* tilt the other way */
.sketch small  { display: block; font-size: 0.7em; color: var(--pt-muted); margin-top: 0.15em; }

/* Database cylinder in the same sketch look */
.db {
  font-family: 'Architects Daughter', cursive;
  font-size: 0.8em;
  text-align: center;
  padding: 0.9em 1.1em 0.7em;
  border: 2.5px solid var(--pt-heading);
  background: var(--pt-bg-1);
  border-radius: 50% 50% 12px 12px / 22px 22px 12px 12px;  /* rounded top = cylinder */
  box-shadow: 2px 3px 0 rgba(15, 23, 42, 0.12);
}
.db small { display: block; font-size: 0.7em; color: var(--pt-muted); margin-top: 0.15em; }

/* Arrow between boxes */
.arrow {
  font-family: 'Architects Daughter', cursive;
  font-size: 1.6em;
  font-weight: 700;
  color: var(--pt-link);
  transform: rotate(-2deg);
}

/* Test pyramid (or any 3-tier stack) */
.pyramid {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  font-family: 'Architects Daughter', cursive;
}
.pyramid .layer {
  text-align: center;
  padding: 0.5em 0.7em;
  border: 2.5px solid var(--pt-heading);
  background: var(--pt-bg-1);
  border-radius: 18px 8px 22px 8px / 8px 20px 8px 18px;
  box-shadow: 2px 3px 0 rgba(15, 23, 42, 0.12);
}
.pyramid .e2e  { width: 38%; }   /* top    - narrow */
.pyramid .int  { width: 62%; }   /* middle */
.pyramid .unit { width: 92%; border-color: var(--pt-link); color: var(--pt-link); }  /* base - wide */
.pyramid .layer small { display: block; font-size: 0.7em; color: var(--pt-muted); }
</style>
```

## Usage

**Horizontal flow** (default `.flow` is a centered, wrapping row). Each box optionally carries
a `<small>` sub-label. Mark the key box with `accent`; alternate `alt` for visual variety:

```html
<div class="flow">
  <div class="sketch accent">Entwickeln<small>Code + Test</small></div>
  <div class="arrow">→</div>
  <div class="sketch alt">Pipeline<small>CI/CD · Pflicht</small></div>
  <div class="arrow">→</div>
  <div class="sketch accent">Produktion<small>grün = live</small></div>
</div>
```

**Vertical flow**: add `col` to the container (`<div class="flow col">`). Rotate the arrows
with an inline style if you want them to point down: `<div class="arrow" style="transform: rotate(90deg);">→</div>`.

**Dashed note box** (e.g. a feedback loop or error path): add an inline border style:

```html
<div class="flow">
  <div class="sketch" style="border-style: dashed; transform: rotate(0.3deg);">↺ Test rot? → zurück zum Entwickeln</div>
</div>
```

**App → database**: pair a `.sketch` with a `.db` cylinder:

```html
<div class="flow">
  <div class="sketch accent">OASIS App<small>createTenant()</small></div>
  <div class="arrow">→</div>
  <div class="db">SQL Server<small>UNIQUE(slug)</small></div>
</div>
```

**Test pyramid** (base = most/fastest/cheapest, top = fewest/slowest):

```html
<div class="pyramid">
  <div class="layer e2e">E2E<small>wenige · langsam · teuer</small></div>
  <div class="layer int">Integration<small>einige</small></div>
  <div class="layer unit">Unit<small>viele · schnell · billig</small></div>
</div>
```

## Layout tips (avoid overflow)

- **Don't combine a multi-box flow AND a code block inside a `split`/`split-60` slide** - the
  grid auto-places the diagram and code into the wrong cells and they overlap. Use a plain
  full-width slide: heading -> short sentence -> a compact horizontal `.flow` -> code below.
- Keep flows **horizontal and compact** when a code block follows; vertical flows eat too much
  height. The `1.3em` bottom margin on `.flow` keeps boxes off the code block.
- A box label that is one or two words plus a `<small>` line fits best. Long labels widen the
  row and force wrapping.
- Always render to PDF/HTML and eyeball it - these slides overflow silently if too tall.
