# Ship Fast, Sleep Well - Slides

Marp decks: `content.md` (English) and `content-de.md` (German). Keep both in sync, same slide order. Theme and engine live in `../../shared/theme/` (see the `pragmatech-slides` skill), `.marprc.yml` wires them in.

## Build

Run from this folder. Always add `< /dev/null` in scripts, or Marp waits for stdin.

```bash
marp -p -w content.md                                   # live preview
marp content.md -o content.html < /dev/null             # HTML (transitions, presenter mode)
marp content-de.md -o content-de.html < /dev/null       # German HTML
marp content.md --images png -o preview/slide.png < /dev/null   # PNG per slide (gitignored)
../../shared/scripts/resize-images.sh ../../shared && ../../shared/scripts/sharable-pdf.sh . slides-ship-fast-sleep-well.pdf
```

## House style

- No em dashes. Use `-`. Use `·` in bylines.
- One idea per slide. Layout classes: `title`, `section`, `agenda`, `split`, `statement`, `quote`, `metrics`, `closing`, as `<!-- _class: light <layout> -->`.
- Step-by-step builds use `reveal` with `*` list items. Do not split one slide into many.
- Update `<!-- header: '...' -->` at each section start.
- Speaker notes: `<!-- Notes: ... -->` above the slide.
- Slide marker `TODO:` means content is not written yet. Find them with `grep -n TODO content.md`.

## Hand-drawn pictures (slide 3 and slide 5)

`visuals/scenes.html` draws the horror and target pictures in HTML and SVG. Export them to `assets/horror-friday.png` and `assets/target-state.png` with:

```bash
node visuals/export-scenes.mjs
```

Needs Google Chrome and the global Marp CLI (it uses the `puppeteer-core` of Marp).
