#!/bin/bash
# Resize every image in <root>/assets into <root>/assets/generated (smaller PDFs).
# Usage: shared/scripts/resize-images.sh <root>
#   <root> is the shared folder (all images). A slides folder works too if it has an assets/ folder.
# Requires ImageMagick: brew install imagemagick
set -e

if [ $# -lt 1 ]; then
  echo "Usage: $0 <root>   (a slides folder or the shared folder)" >&2
  exit 1
fi

ROOT="$1"
[ -d "$ROOT/slides" ] && ROOT="$ROOT/slides"
ASSETS_DIR="$ROOT/assets"
GENERATED_DIR="$ASSETS_DIR/generated"
MAX_WIDTH=1200
QUALITY=80

if command -v magick &> /dev/null; then
  IM=(magick)
elif command -v convert &> /dev/null; then
  IM=(convert)
else
  echo "Error: ImageMagick not found. brew install imagemagick" >&2
  exit 1
fi

mkdir -p "$GENERATED_DIR"

# Recurses into subfolders (shared/assets/<talk-slug>/), mirrors them below generated/.
find "$ASSETS_DIR" -type f -not -path "$GENERATED_DIR/*" -not -name '.DS_Store' -not -name '*.svg' | while read -r img; do
  relative_path="${img#$ASSETS_DIR/}"
  mkdir -p "$GENERATED_DIR/$(dirname "$relative_path")"
  echo "Processing: $relative_path"
  "${IM[@]}" "$img" -resize "${MAX_WIDTH}x>" -quality "$QUALITY" "$GENERATED_DIR/$relative_path"
  echo "  Original: $(du -h "$img" | cut -f1), Resized: $(du -h "$GENERATED_DIR/$relative_path" | cut -f1)"
done

echo "Done. Resized images are in $GENERATED_DIR"
