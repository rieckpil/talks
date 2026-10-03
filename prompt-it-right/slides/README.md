# Prompt It Right - Slides

Marp deck in `content.md`. Theme is `pragmatech.css` (light palette), `engine.js` adds code line numbers and `{1,3-5}` highlighting. `.marprc.yml` wires both.

## Build

Run from this folder. Always add `< /dev/null` in scripts, or Marp waits for stdin.

```bash
marp -p -w content.md                                   # live preview
marp content.md -o content.html < /dev/null             # HTML (transitions, presenter mode)
marp content.md --images png -o preview/slide.png < /dev/null   # PNG per slide (gitignored)
./resize_images.sh && ./generate_sharable_pdf.sh slides-devoxx-be-2026-10-05.pdf   # resizes images, swaps links, Ghostscript-reduces, restores content.md
```

## House style

- No em dashes. Use `-`. Use `·` in bylines.
- One idea per slide. Layout classes: `title`, `section`, `agenda`, `split`, `statement`, `quote`, `metrics`, `closing`, as `<!-- _class: light <layout> -->`.
- Step-by-step builds use `reveal` with `*` list items. Do not split one slide into many.
- Update `<!-- header: '...' -->` at each section start.
- Speaker notes: `<!-- Notes: ... -->` above the slide.
- Simple flows: CSS `.flow` / `.sketch` boxes. Rich diagrams: Excalidraw MCP, export PNG to `assets/`.
- Slide marker `TODO:` means content is not written yet. Find them with `grep -n TODO content.md`.
