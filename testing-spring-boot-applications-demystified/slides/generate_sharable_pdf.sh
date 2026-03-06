#!/bin/zsh
# Script to generate a shareable PDF using resized images
# Usage: ./generate_sharable_pdf.sh <output-pdf-name>
# Example: ./generate_sharable_pdf.sh talk-abc-2025.pdf

set -e  # Exit on error

# Check if output filename is provided
if [ $# -eq 0 ]; then
    echo "Error: No output PDF filename provided"
    echo "Usage: $0 <output-pdf-name>"
    echo "Example: $0 talk-abc-2025.pdf"
    exit 1
fi

OUTPUT_PDF="$1"

# Ensure the output filename ends with .pdf
if [[ ! "$OUTPUT_PDF" == *.pdf ]]; then
    OUTPUT_PDF="${OUTPUT_PDF}.pdf"
fi

# Configuration
MARKDOWN_FILE="./webinar.md"
BACKUP_FILE="./webinar.md.tmp"
GENERATED_DIR="./assets/generated"
THEME_FILE="./pragmatech.css"
ENGINE_FILE="./engine.js"

# Check if Marp CLI is installed
if ! command -v marp &> /dev/null; then
    echo "Error: Marp CLI not found. Please install it first:"
    echo "  npm install -g @marp-team/marp-cli"
    exit 1
fi

# Check if the generated directory exists
if [ ! -d "$GENERATED_DIR" ]; then
    echo "Warning: Generated directory not found at $GENERATED_DIR"
    echo "Run resize_images.sh first to create resized images for smaller PDF size."
    echo ""
    read -p "Continue anyway with original images? (y/n) " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
    # Generate PDF with original images
    echo "Generating PDF with original images..."
    marp --pdf "$MARKDOWN_FILE" --theme "$THEME_FILE" --engine "$ENGINE_FILE" --allow-local-files -o "$OUTPUT_PDF"
    echo "PDF generated successfully: $OUTPUT_PDF"
    exit 0
fi

# Create a backup of the original file
cp "$MARKDOWN_FILE" "$BACKUP_FILE"
echo "Created temporary backup of $MARKDOWN_FILE"

# Function to restore the original file in case of errors
cleanup() {
    if [ -f "$BACKUP_FILE" ]; then
        mv "$BACKUP_FILE" "$MARKDOWN_FILE"
        echo "Restored original $MARKDOWN_FILE"
    fi
}

# Set trap to cleanup on exit (error or success)
trap cleanup EXIT

# Get list of all generated images
if [ "$(ls -A $GENERATED_DIR)" ]; then
    echo "Updating image references to use resized images..."

    # Update image links to point to generated directory
    # Replace both ./assets/image.ext and assets/image.ext patterns
    for img in "$GENERATED_DIR"/*; do
        filename=$(basename "$img")
        # Skip if it's a directory
        if [ -d "$img" ]; then
            continue
        fi

        # Replace image references with generated versions
        sed -i '' "s|assets/$filename|assets/generated/$filename|g" "$MARKDOWN_FILE"
        sed -i '' "s|\./assets/$filename|./assets/generated/$filename|g" "$MARKDOWN_FILE"
    done

    echo "Image references updated successfully"
else
    echo "Warning: Generated directory is empty. Using original images."
fi

# Generate the PDF using Marp
echo "Generating PDF: $OUTPUT_PDF"
marp --pdf "$MARKDOWN_FILE" --theme "$THEME_FILE" --engine "$ENGINE_FILE" --allow-local-files -o "$OUTPUT_PDF"

# Check if PDF was generated
if [ ! -f "$OUTPUT_PDF" ]; then
    echo "Error: PDF generation failed"
    exit 1
fi

raw_size=$(du -h "$OUTPUT_PDF" | cut -f1)
echo "Raw PDF generated: $OUTPUT_PDF ($raw_size)"

source ~/.zshrc

# Reduce PDF size using Ghostscript
REDUCED_PDF="${OUTPUT_PDF%.pdf}-reduced.pdf"
echo "Reducing PDF size..."
reduce_pdf "$OUTPUT_PDF" "$REDUCED_PDF"

if [ -f "$REDUCED_PDF" ]; then
    reduced_size=$(du -h "$REDUCED_PDF" | cut -f1)
    echo ""
    echo "✓ Reduced PDF generated successfully: $REDUCED_PDF"
    echo "  File size: $reduced_size (was $raw_size before reduction)"
    echo ""

    # Remove the raw PDF
    rm "$OUTPUT_PDF"
    echo "Removed raw PDF: $OUTPUT_PDF"

    # Rename reduced PDF to original name
    mv "$REDUCED_PDF" "$OUTPUT_PDF"
    echo "Renamed $REDUCED_PDF to $OUTPUT_PDF"
    echo ""
    echo "The original $MARKDOWN_FILE has been restored."
else
    echo "Warning: PDF reduction failed, keeping original PDF"
    file_size=$(du -h "$OUTPUT_PDF" | cut -f1)
    echo "  File size: $file_size"
fi
