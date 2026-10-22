# Talks Repository

## Published PDFs

- Never delete or rename old shareable PDFs (`slides-<venue>-<date>.pdf` and similar). Attendees and students hold links to download them, so they must always remain.
- For a new venue, add a new PDF next to the old ones instead of replacing them.

## Local Marp Server

- Start the Marp server from the repo root, not from the deck folder: `marp -s .` (via `run_in_background`, with `< /dev/null`). Slides use `../../shared/assets/` image paths, which only resolve when the root is served.
- Open a deck at `http://localhost:8080/<deck-folder>/slides/<file>.md`, e.g. `http://localhost:8080/testing-spring-boot-applications-demystified/slides/webinar.md`.

## Slide tooling

All Marp decks share one setup in [`shared/`](shared/): theme (`shared/theme/pragmatech.css`), engine, all images (`shared/assets/`), build scripts and a Remotion project for complex animations. A deck folder only holds its `slides/content.md`, visual sources and PDFs.

```bash
shared/scripts/new-deck.sh my-talk "My **Talk**" "Event 2027" "March 3, 2027" [banner.jpg]   # new deck
shared/scripts/build.sh my-talk watch                                                         # live preview
shared/scripts/sharable-pdf.sh my-talk slides-event-2027-03-03.pdf                            # small PDF
shared/scripts/compare-pdfs.sh before.pdf after.pdf                                           # pixel diff after theme changes
```

Rules, layouts, diagrams and animations are documented in the `pragmatech-slides` skill (`.claude/skills/pragmatech-slides/SKILL.md`).
