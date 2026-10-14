# The Need for Speed - Slides

Marp deck: `content.md` (English). Theme and engine live in `../../shared/theme/` (see the `pragmatech-slides` skill), `.marprc.yml` wires them in.

## Build

Run from this folder. Always add `< /dev/null` in scripts, or Marp waits for stdin.

```bash
marp -p -w content.md                          # live preview
marp content.md -o content.html < /dev/null    # HTML (transitions, presenter mode)
../../shared/scripts/resize-images.sh ../../shared && ../../shared/scripts/sharable-pdf.sh . slides-<venue>-<date>.pdf
```

Slide marker `TODO:` means content is not written yet. Find them with `grep -n TODO content.md`.
