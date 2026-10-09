# Stop Fighting Your Spring Boot Tests - Slides

Marp deck: content.md (English) and content-de.md (German, keep in sync). Theme, engine, shared images and scripts live in `../../shared/`,
see the `pragmatech-slides` skill (`.claude/skills/pragmatech-slides/SKILL.md`) for layouts,
house style and the full workflow. `.marprc.yml` wires the theme into plain `marp` and the editor preview.

## Build

Run from the repo root. `<slug>` is `stop-fighting-your-spring-boot-tests`.

```bash
shared/scripts/build.sh <slug> watch      # live preview
shared/scripts/build.sh <slug> html       # content.html (transitions, presenter mode)
shared/scripts/build.sh <slug> png        # PNG per slide in preview/ (gitignored)
```

## Shareable PDF

```bash
shared/scripts/resize-images.sh shared
shared/scripts/sharable-pdf.sh <slug> slides-<venue>-<date>.pdf
```

Resizes images, swaps links to the resized copies, builds the PDF, shrinks it with
Ghostscript and restores `content.md`. Commit the PDF next to the deck.

`pdf-swaps.txt` swaps the animated context-caching GIF for its final still in the PDF.
The animation itself is a Remotion composition in `shared/remotion/` (`npm run build:context-cache`).
