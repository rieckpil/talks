---
name: pragmatech-slides
description: Create and maintain Philip's conference talk decks (Marp, PragmaTech theme) in the talks repo. Use when the user wants a new talk or slide deck, edits slides, builds or exports a PDF, adds a diagram, box drawing or Remotion animation, or mentions Marp, slides, the pragmatech theme, or the shared folder.
---

# PragmaTech Slides (Marp, talks repo)

All talks live in the `talks` repo. Every deck shares one setup in `shared/`. Nothing is copied
into a deck: a deck folder only holds its content, visual sources and PDFs. All images live in `shared/assets/`.

```
talks/
  shared/
    theme/pragmatech.css      one theme: dark default, light via `class: light`, plus reveal, verdict columns, .flow/.sketch helpers, @media print
    theme/engine.js           code line numbers and {n,m-k} highlighting
    assets/                   ALL images of all talks. Common ones flat (logo.webp, end.jpg, QR codes, ...), new talks in assets/<talk-slug>/
    templates/content.md      scaffold for a new deck
    scripts/                  build.sh, new-deck.sh, resize-images.sh, sharable-pdf.sh, compare-pdfs.sh
    visuals/export-visuals.mjs  HTML/canvas scene -> PNG exporter
    remotion/                 one Remotion project for all complex animations
  <talk-slug>/slides/
    content.md                the deck (content-de.md for a German version)
    .marprc.yml               3 lines, points at shared/ (editor preview and plain `marp` work)
    visuals/                  scenes.mjs + HTML sources for exported scenes
    pdf-swaps.txt             optional, see "PDF export"
    slides-<venue>-<date>.pdf shareable PDFs, committed
```

## New deck in one command

```bash
shared/scripts/new-deck.sh <slug> "Title with **accent**" "Event" "October 6, 2026" [banner-image]
shared/scripts/build.sh <slug> watch      # live preview with hot reload
```

This creates `<slug>/slides/` from `shared/templates/content.md`: banner slide 0, title,
Mentimeter, About Philip, agenda, section, statement and closing slide. Images for the talk go
to `shared/assets/<slug>/`. Without a banner image it generates a gradient placeholder, so ask
the user for the venue photo or find one. Then replace the Mentimeter QR
(`shared/assets/<slug>/mentimeter-qr.png`) and the code, and write the talk.

## General requirements (apply to every deck)

**Slide 0 is a banner.** The very first slide is a full-bleed photo of the venue or town, with
no title text. It hides all chrome, and the Mentimeter URL sits in an HTML comment above it:

```markdown
<!-- Menti: https://www.menti.com/... -->
<!-- _paginate: false -->
<!-- _header: '' -->
<!-- _footer: '' -->

![bg](../../shared/assets/<slug>/antwerp.jpg)
```

Use `![bg fit](...)` when the image is a designed banner that must not be cropped. The title
slide is slide 1 (`_class: light title`, `![bg left:33%](../../shared/assets/abstract-blue-left.png)`).

**Standard flow.** Banner, title, "Participate During the Talk" (Mentimeter QR + code), About
Philip (`../../shared/assets/location.png`), agenda, content, closing slide with
`../../shared/assets/end.jpg`, the newsletter QR and contact links.

**Front-matter.** Light palette for talks:

```yaml
---
marp: true
theme: pragmatech
title: 'Talk title'
class: light
paginate: true
transition: pt-fade
header: 'Talk title · Event 2026'
footer: '![](../../shared/assets/logo.webp) Philip Riecks · [@rieckpil](https://x.com/rieckpil) · [PragmaTech GmbH](https://pragmatech.digital/)'
---
```

With `class: light` every per-slide directive is `<!-- _class: light <layout> -->`. Speaker
notes go in a `<!-- Notes: ... -->` comment on the slide.

**Images.** Every image lives in `shared/assets/` and is referenced as
`../../shared/assets/x.png`. A deck has no `assets/` folder of its own. Existing images are flat
in `shared/assets/`. Put the images of a new talk in `shared/assets/<talk-slug>/` so names
cannot clash. Before adding a file, check `shared/assets/` for an existing copy (logo, QR codes,
`end.jpg`, `location.png`, ...). If a name already exists with different content, use a
talk-specific name. Sizing:
`![center h:500](...)`, `![bg right:33% h:750](...)`, `![bg left:38%](...)`.

**Copy rules.**
- Never use em dashes or en dashes. Use a hyphen `-`. Footer separators use the middle dot `·`.
- One idea per slide. Pick the layout that fits (`statement`, `quote`, `metrics`, `split`)
  instead of dumping bullets onto a plain slide. Keep lines short.
- Always pass `< /dev/null` to `marp` in scripts and tool calls, or it waits for stdin.

### Diagrams and box drawing

Pick the lightest tool that works, in this order:

1. **Boxes, arrows, pipelines, pyramids, app to database**: the CSS helpers `.flow`, `.sketch`,
   `.arrow`, `.db`, `.pyramid` from the shared theme. Hand-drawn look, no images, works on
   dark and light. Usage and overflow tips: `references/handdrawn-boxes.md`.
2. **Trees and vertical flows in code blocks**: Unicode box-drawing characters inside a fenced
   `text` block, never in prose and never as boxes.

   ````markdown
   ```text
   my-skill/
   ├── SKILL.md
   └── references/
       └── checklist.md

   Test class
     │
     ▼
   MergedContextConfiguration  ← cache key
   ```
   ````

   Allowed: `├── └── │ ─ ▼ ▲ ← →`. Do not draw rectangles with `┌─┐` or `+---+` (they
   misalign with the proportional fonts and PDF export). Use `.sketch` boxes for that.
3. **Architecture, system and sequence diagrams**: draw with the Excalidraw MCP
   (`mcp__excalidraw__read_diagram_guide` first, then `batch_create_elements`), export PNG
   into the deck's `assets/`, embed with `![center](assets/x.png)`.
4. **Scenes with illustration, maps, charts**: HTML/canvas scene in `<slides>/visuals/`,
   exported with the exporter (below).
5. Never hand-roll raw inline SVG or ASCII art.

### Animations

- **Step by step builds**: Marp fragments. Add the class `reveal` and write the steps as `*`
  list items (paragraphs and images can sit inside an item). Do not split the slide into
  several slides: hidden items keep their space, so content stays at its final position.
  Works in the HTML deck only, the PDF shows everything at once.
  - Plain slide: `<!-- _class: light reveal -->`; statement: `<!-- _class: light statement reveal -->`.
- **Complex animations: Remotion**, shared project in `shared/remotion/`:
  1. Add `src/<talk-slug>/MyAnimation.tsx` exporting the component plus `TOTAL_FRAMES`, `FPS`,
     `WIDTH`, `HEIGHT` (see `src/stop-fighting/ContextCache.tsx`).
  2. Register a `<Composition id="MyAnimation" ...>` in `src/Root.tsx`.
  3. Add scripts to `package.json` that render into the owning deck:
     `remotion render MyAnimation ../assets/<slug>/my-animation.gif --codec=gif --number-of-gif-loops=0`
     and a still of the last frame: `remotion still MyAnimation ../assets/<slug>/my-animation-final.png --frame=<last>`.
  4. `cd shared/remotion && npm ci && npm run preview` to design it, run the render scripts
     when done, embed the GIF with `![center](../../shared/assets/<slug>/my-animation.gif)`.
  5. A GIF exports to PDF as its first frame. Add the line `my-animation.gif my-animation-final.png`
     to the deck's `pdf-swaps.txt` so the sharable PDF shows the final state.
  Chrome path for Remotion is configured in `remotion.config.ts` (override with `CHROME_PATH`).
- **Exported scenes**: `<slides>/visuals/scenes.mjs` lists scenes (`page`, `query`, `file`,
  `width`, `height`, `selector`, `waitRendered`, `scale`), then
  `node shared/visuals/export-visuals.mjs <slug>` writes the PNGs to `shared/assets/`. The scene
  `file` is relative to it (`'<slug>/map.png'`).

## Layout classes

Apply per slide via `<!-- _class: name -->`; combine with `light` on a light deck.

| Class       | Use it for                                                        |
|-------------|-------------------------------------------------------------------|
| `title`     | Cover slide. Wrap an accent word in `**bold**` for the gradient.  |
| `section`   | Section divider. `## 01 - Label` over a big `#` heading.          |
| `agenda`    | Numbered agenda cards from an ordered list.                       |
| `split`     | Two equal columns; `split-60` for 60/40. `div.yes/.no/.warn` columns add verdict headings. |
| `quote`     | Pull quote, trailing `p` is the attribution.                      |
| `statement` | Centered big statement. `**bold**` words get the accent.          |
| `metrics`   | 3-up stat cards, bullet list with `**stat**` first.               |
| `closing`   | Closing / contact slide.                                          |
| `reveal`    | Modifier: fragmented list builds step by step (HTML only).        |

Details and snippets: `references/layouts.md`. Full examples: `references/sample-light.md`,
`references/sample-dark.md`.

Code blocks get line numbers from the engine, and ranges highlight from the fence info:
` ```java {2,5-7} `. This only works through `build.sh` or the deck's `.marprc.yml`.

## Build and export

```bash
shared/scripts/build.sh <slug> html|pdf|png|pptx|watch [output]   # raw output next to content.md
```

**PDF export (sharable, small).** Run from the repo root:

```bash
shared/scripts/resize-images.sh shared      # resize all images into shared/assets/generated (gitignored)
shared/scripts/sharable-pdf.sh <slug> slides-<venue>-<date>.pdf
```

`sharable-pdf.sh` temporarily points links at the resized images, builds the PDF, shrinks it
with Ghostscript (`reduce_pdf` from `~/.zshrc`) and restores `content.md`. `REDUCE=0` skips
Ghostscript. Ghostscript is only safe because the theme has an `@media print` block (without
it Chrome boxes gradient text and gs erases it). Do not remove that block from the theme.
Name PDFs `slides-<venue>-<date>.pdf` and commit them next to `content.md`.

## Changing the shared theme or scripts: regression check

Any edit to `shared/theme/` or `shared/scripts/` affects every deck. Before and after the
change, build each deck and compare page by page:

```bash
shared/scripts/build.sh <slug> pdf /tmp/before-<slug>.pdf     # before the edit
shared/scripts/build.sh <slug> pdf /tmp/after-<slug>.pdf      # after the edit
shared/scripts/compare-pdfs.sh /tmp/before-<slug>.pdf /tmp/after-<slug>.pdf 0
```

`compare-pdfs.sh` rasterizes both PDFs at 96 dpi with `pdftoppm` (not `magick`, it goes
through Ghostscript) and runs `magick compare -metric AE` per page. Result `differing=0`
means pixel identical. Use the fuzz argument `12` only to compare against a resized or
Ghostscript-reduced PDF (anti-aliasing noise, below about 5% per page). Scope new CSS rules to
a class so older decks do not change.
