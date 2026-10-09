#!/bin/zsh
# Build a shareable (small) PDF from a deck.
# Usage: shared/scripts/sharable-pdf.sh <deck-dir> <output-pdf-name>
# Example: shared/scripts/sharable-pdf.sh ship-fast-sleep-well slides-ship-fast-sleep-well.pdf
#
# Steps: swap image links to assets/generated/* (made by resize-images.sh, for the deck
# and for shared/), build the PDF with Marp, then shrink it with Ghostscript.
# The deck's content.md is restored afterwards (also on errors).
#
# Optional per-deck file <deck-dir>/pdf-swaps.txt: one "<animated.gif> <still.png>" pair per
# line. An animated GIF exports to PDF as its FIRST frame, so the PDF uses the still instead.
#
# REDUCE=0 skips Ghostscript. DECK_FILE overrides content.md.
set -e

SCRIPT_DIR="${0:A:h}"
SHARED_DIR="${SCRIPT_DIR:h}"

if [ $# -lt 2 ]; then
  echo "Usage: $0 <deck-dir> <output-pdf-name>" >&2
  exit 1
fi

DECK_DIR="${1:A}"
[ -d "$DECK_DIR/slides" ] && DECK_DIR="$DECK_DIR/slides"
OUTPUT_PDF="$2"
[[ "$OUTPUT_PDF" == *.pdf ]] || OUTPUT_PDF="${OUTPUT_PDF}.pdf"

THEME_FILE="$SHARED_DIR/theme/pragmatech.css"
ENGINE_FILE="$SHARED_DIR/theme/engine.js"
MARKDOWN_FILE="${DECK_FILE:-content.md}"
BACKUP_FILE="$MARKDOWN_FILE.tmp"

command -v marp > /dev/null || { echo "Marp CLI missing: npm install -g @marp-team/marp-cli" >&2; exit 1; }

cd "$DECK_DIR"
GENERATED_DIRS=("$DECK_DIR/assets/generated" "$SHARED_DIR/assets/generated")

if [ ! -d "${GENERATED_DIRS[1]}" ] && [ ! -d "${GENERATED_DIRS[2]}" ]; then
  echo "Warning: no resized images found. Run resize-images.sh for this deck and for shared/ first."
  read -q "REPLY?Continue with original images? (y/n) " || exit 1
  echo
fi

cp "$MARKDOWN_FILE" "$BACKUP_FILE"
cleanup() {
  if [ -f "$BACKUP_FILE" ]; then
    mv "$BACKUP_FILE" "$MARKDOWN_FILE"
    echo "Restored original $MARKDOWN_FILE"
  fi
}
trap cleanup EXIT

# "assets/x" -> "assets/generated/x" also covers "../../shared/assets/x".
for generated_dir in "${GENERATED_DIRS[@]}"; do
  [ -d "$generated_dir" ] || continue
  for img in "$generated_dir"/*(N.); do
    filename=$(basename "$img")
    sed -i '' "s|assets/$filename|assets/generated/$filename|g" "$MARKDOWN_FILE"
  done
done

if [ -f pdf-swaps.txt ]; then
  while read -r animated still; do
    [ -z "$animated" ] && continue
    if [ -f "assets/$still" ]; then
      sed -i '' "s|assets/$animated|assets/$still|g" "$MARKDOWN_FILE"
      echo "PDF: using $still in place of $animated"
    else
      echo "! assets/$still missing - PDF will show the first frame of $animated"
    fi
  done < pdf-swaps.txt
fi

echo "Generating PDF: $OUTPUT_PDF"
# `< /dev/null` is required: marp waits for stdin when run in a pipe or script.
marp --pdf "$MARKDOWN_FILE" --theme-set "$THEME_FILE" --engine "$ENGINE_FILE" --allow-local-files -o "$OUTPUT_PDF" < /dev/null
[ -f "$OUTPUT_PDF" ] || { echo "Error: PDF generation failed" >&2; exit 1; }
raw_size=$(du -h "$OUTPUT_PDF" | cut -f1)

# Ghostscript is only safe because the theme has an "@media print" block: it swaps the
# gradient-clipped text for solid colour. Without it gs ERASES that text.
if ! grep -q "@media print" "$THEME_FILE"; then
  echo "! $THEME_FILE has no '@media print' block - skipping Ghostscript (would erase text)."
  echo "PDF generated (unreduced): $OUTPUT_PDF ($raw_size)"
  exit 0
fi

if [[ "${REDUCE:-1}" != "1" ]]; then
  echo "PDF generated (REDUCE=0, unreduced): $OUTPUT_PDF ($raw_size)"
  exit 0
fi

source ~/.zshrc
REDUCED_PDF="${OUTPUT_PDF%.pdf}-reduced.pdf"
reduce_pdf "$OUTPUT_PDF" "$REDUCED_PDF"
if [ -f "$REDUCED_PDF" ]; then
  mv "$REDUCED_PDF" "$OUTPUT_PDF"
  echo "PDF generated: $OUTPUT_PDF ($(du -h "$OUTPUT_PDF" | cut -f1), was $raw_size)"
else
  echo "Warning: reduction failed, keeping unreduced $OUTPUT_PDF ($raw_size)"
fi
