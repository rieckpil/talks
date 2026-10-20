# Talks Repository

## Published PDFs

- Never delete or rename old shareable PDFs (`slides-<venue>-<date>.pdf` and similar). Attendees and students hold links to download them, so they must always remain.
- For a new venue, add a new PDF next to the old ones instead of replacing them.

## Local Marp Server

- Start the Marp server from the repo root, not from the deck folder: `marp -s .` (via `run_in_background`, with `< /dev/null`). Slides use `../../shared/assets/` image paths, which only resolve when the root is served.
- Open a deck at `http://localhost:8080/<deck-folder>/slides/<file>.md`, e.g. `http://localhost:8080/testing-spring-boot-applications-demystified/slides/webinar.md`.
