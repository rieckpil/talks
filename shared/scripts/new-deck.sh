#!/bin/zsh
# Scaffold a new talk with the shared PragmaTech setup.
# Usage: shared/scripts/new-deck.sh <slug> "<Title>" "<Event>" "<Date>" [banner-image]
# Example: shared/scripts/new-deck.sh my-new-talk "Ship **Fast**" "Devoxx 2027" "March 3, 2027" ~/Pictures/antwerp.jpg
# Wrap the accent word of the title in **bold** (gradient on the title slide).
# Without a banner image a gradient placeholder is generated: replace it with a venue photo.
set -e

SCRIPT_DIR="${0:A:h}"
SHARED_DIR="${SCRIPT_DIR:h}"
ROOT_DIR="${SHARED_DIR:h}"

if [ $# -lt 4 ]; then
  echo "Usage: $0 <slug> \"<Title with **accent**>\" \"<Event>\" \"<Date>\" [banner-image]" >&2
  exit 1
fi

SLUG="$1"; TITLE_WITH_ACCENT="$2"; EVENT="$3"; DATE="$4"; BANNER_SOURCE="$5"
TITLE="${TITLE_WITH_ACCENT//\*\*/}"
SLIDES_DIR="$ROOT_DIR/$SLUG/slides"

[ -e "$ROOT_DIR/$SLUG" ] && { echo "Error: $SLUG already exists" >&2; exit 1; }
mkdir -p "$SLIDES_DIR/assets" "$SLIDES_DIR/visuals"

if [ -n "$BANNER_SOURCE" ]; then
  BANNER_FILE="banner.${BANNER_SOURCE##*.}"
  cp "$BANNER_SOURCE" "$SLIDES_DIR/assets/$BANNER_FILE"
else
  BANNER_FILE="banner.jpg"
  magick -size 1920x1080 gradient:'#0ea5e9'-'#1e293b' "$SLIDES_DIR/assets/$BANNER_FILE"
  echo "No banner image given: generated a placeholder at $SLUG/slides/assets/$BANNER_FILE"
fi

# Placeholder for the Mentimeter QR code: replace with the real one.
magick -size 600x600 xc:'#e2e8f0' "$SLIDES_DIR/assets/mentimeter-qr.png"

# Escape for sed replacement (& and |)
esc() { printf '%s' "$1" | sed -e 's/[&|]/\\&/g'; }
sed -e "s|{{TITLE_WITH_ACCENT}}|$(esc "$TITLE_WITH_ACCENT")|g" \
    -e "s|{{TITLE}}|$(esc "$TITLE")|g" \
    -e "s|{{EVENT}}|$(esc "$EVENT")|g" \
    -e "s|{{DATE}}|$(esc "$DATE")|g" \
    -e "s|{{SUBTITLE}}|Subtitle goes here|g" \
    -e "s|{{BANNER_FILE}}|$BANNER_FILE|g" \
    "$SHARED_DIR/templates/content.md" > "$SLIDES_DIR/content.md"

# Lets `marp content.md` and the Marp VS Code extension work from the slides folder.
cat > "$SLIDES_DIR/.marprc.yml" <<RC
engine: ../../shared/theme/engine.js
themeSet:
  - ../../shared/theme/pragmatech.css
allowLocalFiles: true
RC

echo "Created $SLUG/slides/"
echo "  preview:  shared/scripts/build.sh $SLUG watch"
echo "  pdf:      shared/scripts/build.sh $SLUG pdf"
echo "  sharable: shared/scripts/resize-images.sh $SLUG && shared/scripts/resize-images.sh shared && shared/scripts/sharable-pdf.sh $SLUG slides-<venue>-<date>.pdf"
