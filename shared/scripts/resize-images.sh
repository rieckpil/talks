#!/bin/bash
# Resize every image in <root>/assets into <root>/assets/generated (smaller PDFs).
# Usage: shared/scripts/resize-images.sh <root>
#   <root> is a slides folder (talk-specific images) or the shared folder (common images).
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

for img in "$ASSETS_DIR"/*; do
  # Skip directories and SVG files (vector graphics do not need resizing)
  if [ -d "$img" ] || [[ "$img" == *.svg ]]; then
    continue
  fi
  filename=$(basename "$img")
  echo "Processing: $filename"
  "${IM[@]}" "$img" -resize "${MAX_WIDTH}x>" -quality "$QUALITY" "$GENERATED_DIR/$filename"
  echo "  Original: $(du -h "$img" | cut -f1), Resized: $(du -h "$GENERATED_DIR/$filename" | cut -f1)"
done

echo "Done. Resized images are in $GENERATED_DIR"
