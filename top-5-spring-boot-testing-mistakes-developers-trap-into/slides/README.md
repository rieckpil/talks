# Top 5 Spring Boot Testing Mistakes Developers Trap Into - Slides

Marp slides for the talk. The deck is `content.md`, the theme is `pragmatech.css`
(light palette) and `engine.js` adds code line numbers and `{1,3-5}` line highlighting.

## Prerequisites

- [Node.js](https://nodejs.org/) (LTS)
- [Marp CLI](https://github.com/marp-team/marp-cli): `npm install -g @marp-team/marp-cli`
- Optional: ImageMagick (`brew install imagemagick`) and Ghostscript (`brew install ghostscript`) for small PDFs
- Optional: Playwright (`npx playwright install chromium`) to re-export the canvas visuals

## Build

Run all commands from this `slides/` folder. `.marprc.yml` wires the engine and theme, so no flags are needed.

```bash
# self-contained HTML (transitions and presenter mode work here)
marp content.md -o content.html

# live preview while editing
marp -p -w content.md

# one PNG per slide for a quick visual check (gitignored)
marp content.md --images png -o preview/slide.png

# shareable PDF with resized images and Ghostscript compression
./resize_images.sh
./generate_sharable_pdf.sh slides-<venue>-<date>.pdf
```

Note: when Marp runs from a script or CI without a terminal, add `< /dev/null` to the command. Marp CLI otherwise waits for Markdown on stdin and looks like it hangs.

Exported PDFs follow the naming `slides-<venue>-<date>.pdf` and are committed next to the deck.

## Visuals

The recurring "trap map" images (`assets/trap-map-*.png`, `assets/trap-zoom-*.png`,
`assets/cover-green-lie.png`) are drawn by `visuals/trap-map.html` (an HTML canvas, no
external libraries). Open the file in a browser with query parameters to preview a scene:

- `trap-map.html?view=map&disarmed=3` - the wide map, traps 1-3 disarmed
- `trap-map.html?view=zoom&trap=4` - close-up of trap 4
- `trap-map.html?view=cover` - the Green Lie cover image

Re-export all 12 PNGs into `assets/`:

```bash
node visuals/export-visuals.mjs
```

## House style

- No em dashes, use `-`. Separators in bylines use `·`.
- One idea per slide. Use the layout classes (`title`, `section`, `agenda`, `split`,
  `statement`, `quote`, `metrics`, `closing`) as `<!-- _class: light <layout> -->`.
- Speaker notes live in `<!-- Notes: ... -->` comments above the slide content.
