#!/bin/zsh
# Compare two PDFs page by page, pixel by pixel.
# Usage: shared/scripts/compare-pdfs.sh <a.pdf> <b.pdf> [fuzz-percent]
# Rasterizes both at 96 dpi with pdftoppm (poppler; NOT magick - it goes through Ghostscript
# and adds its own artifacts), then runs `magick compare -metric AE` per page.
# Prints one line per page with differing pixels; exit code 1 if any page differs.
# Default fuzz is 0 (exact). A fuzz of 12 ignores glyph anti-aliasing noise.
set -e

if [ $# -lt 2 ]; then
  echo "Usage: $0 <a.pdf> <b.pdf> [fuzz-percent]" >&2
  exit 2
fi

FUZZ="${3:-0}"
WORK_DIR=$(mktemp -d)
trap 'rm -rf "$WORK_DIR"' EXIT
mkdir "$WORK_DIR/a" "$WORK_DIR/b"

pdftoppm -r 96 -png "$1" "$WORK_DIR/a/p"
pdftoppm -r 96 -png "$2" "$WORK_DIR/b/p"

PAGES_A=$(ls "$WORK_DIR/a" | wc -l | tr -d ' ')
PAGES_B=$(ls "$WORK_DIR/b" | wc -l | tr -d ' ')
if [ "$PAGES_A" != "$PAGES_B" ]; then
  echo "PAGE COUNT DIFFERS: $PAGES_A vs $PAGES_B" >&2
  exit 1
fi

DIFFERING=0
MAX_AE=0
for page in "$WORK_DIR"/a/*.png; do
  name=$(basename "$page")
  # compare exits 1 when images differ, so do not let set -e stop us
  diff_pixels=$(magick compare -metric AE -fuzz "${FUZZ}%" "$page" "$WORK_DIR/b/$name" null: 2>&1 || true)
  diff_pixels=${diff_pixels%% *}
  if [ "$diff_pixels" != "0" ]; then
    DIFFERING=$((DIFFERING + 1))
    (( diff_pixels > MAX_AE )) && MAX_AE=$diff_pixels
    echo "page ${name%.png}: $diff_pixels pixels differ"
  fi
done

echo "pages=$PAGES_A differing=$DIFFERING max_AE=$MAX_AE fuzz=${FUZZ}%"
[ "$DIFFERING" = "0" ]
