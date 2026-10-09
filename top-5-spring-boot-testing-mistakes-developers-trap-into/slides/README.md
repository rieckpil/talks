# Top 5 Spring Boot Testing Mistakes Developers Trap Into - Slides

Marp deck: content.md. Theme, engine, shared images and scripts live in `../../shared/`,
see the `pragmatech-slides` skill (`.claude/skills/pragmatech-slides/SKILL.md`) for layouts,
house style and the full workflow. `.marprc.yml` wires the theme into plain `marp` and the editor preview.

## Build

Run from the repo root. `<slug>` is `top-5-spring-boot-testing-mistakes-developers-trap-into`.

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
