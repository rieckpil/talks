#!/bin/zsh
# Build a deck with the shared PragmaTech theme.
# Usage: shared/scripts/build.sh <deck-dir> [html|pdf|png|pptx|watch] [output]
#   <deck-dir>  the slides folder (or the talk folder, "/slides" is added when it exists)
#   html        content.html (default)        pdf  content.pdf (raw, no resize, no gs)
#   png         one PNG per slide in <deck-dir>/preview/
#   watch       live preview server with hot reload
# Environment: DECK_FILE (default content.md)
set -e

SCRIPT_DIR="${0:A:h}"
SHARED_DIR="${SCRIPT_DIR:h}"

if [ $# -lt 1 ]; then
  echo "Usage: $0 <deck-dir> [html|pdf|png|pptx|watch] [output]" >&2
  exit 1
fi

DECK_DIR="${1:A}"
[ -d "$DECK_DIR/slides" ] && DECK_DIR="$DECK_DIR/slides"
MODE="${2:-html}"
DECK_FILE="${DECK_FILE:-content.md}"
BASENAME="${DECK_FILE%.md}"

command -v marp > /dev/null || { echo "Marp CLI missing: npm install -g @marp-team/marp-cli" >&2; exit 1; }

cd "$DECK_DIR"
# `< /dev/null` is required: marp waits for stdin when run in a pipe or script.
COMMON=(--theme-set "$SHARED_DIR/theme/pragmatech.css" --engine "$SHARED_DIR/theme/engine.js" --allow-local-files)

case "$MODE" in
  html)  marp "$DECK_FILE" "${COMMON[@]}" -o "${3:-$BASENAME.html}" < /dev/null ;;
  pdf)   marp --pdf "$DECK_FILE" "${COMMON[@]}" -o "${3:-$BASENAME.pdf}" < /dev/null ;;
  pptx)  marp --pptx "$DECK_FILE" "${COMMON[@]}" -o "${3:-$BASENAME.pptx}" < /dev/null ;;
  png)   mkdir -p preview && marp "$DECK_FILE" --images png "${COMMON[@]}" -o "${3:-preview/slide.png}" < /dev/null ;;
  watch) marp -p -w "$DECK_FILE" "${COMMON[@]}" ;;
  *) echo "Unknown mode: $MODE" >&2; exit 1 ;;
esac
