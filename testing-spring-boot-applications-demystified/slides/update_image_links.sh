#!/bin/bash

# Script to update image links in content.md to point to resized images
# Run this after resize_images.sh

# Configuration
MARKDOWN_FILE="./content.md"
BACKUP_FILE="./content.md.bak"
GENERATED_DIR="./assets/generated"

# Check if the generated directory exists
if [ ! -d "$GENERATED_DIR" ]; then
  echo "Error: Generated directory not found. Run resize_images.sh first."
  exit 1
fi

# Create a backup of the original file
cp "$MARKDOWN_FILE" "$BACKUP_FILE"
echo "Created backup of $MARKDOWN_FILE at $BACKUP_FILE"

# Get list of all generated images
generated_images=$(ls "$GENERATED_DIR")

# Replace image references in the Markdown file
for img in $generated_images; do
  # Replace both ./assets/image.ext and assets/image.ext patterns with assets/generated/image.ext
  sed -i '' "s|./assets/$img|./assets/generated/$img|g" "$MARKDOWN_FILE"
  sed -i '' "s|assets/$img|assets/generated/$img|g" "$MARKDOWN_FILE"
done

echo "Updated image links in $MARKDOWN_FILE to point to resized images."
echo ""
echo "To generate the PDF, run: marp content.md --pdf"
echo "To restore the original file if needed, run: mv $BACKUP_FILE $MARKDOWN_FILE"