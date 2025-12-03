#!/bin/bash

# Script to generate a shareable PDF using resized images
# Usage: ./generate_sharable_pdf.sh <output-pdf-name> [de]
# Example: ./generate_sharable_pdf.sh talk-abc-2025.pdf
# Example (German): ./generate_sharable_pdf.sh talk-abc-2025.pdf de

set -e  # Exit on error

# Check if output filename is provided
if [ $# -eq 0 ]; then
    echo "Error: No output PDF filename provided"
    echo "Usage: $0 <output-pdf-name> [de]"
    echo "Example: $0 talk-abc-2025.pdf"
    echo "Example (German): $0 talk-abc-2025.pdf de"
    exit 1
fi

OUTPUT_PDF="$1"
LANGUAGE="${2:-en}"

# Ensure the output filename ends with .pdf
if [[ ! "$OUTPUT_PDF" == *.pdf ]]; then
    OUTPUT_PDF="${OUTPUT_PDF}.pdf"
fi

# Configuration - select content file based on language parameter
if [[ "$LANGUAGE" == "de" ]]; then
    MARKDOWN_FILE="./content-de.md"
    BACKUP_FILE="./content-de.md.tmp"
    echo "Using German content: $MARKDOWN_FILE"
else
    MARKDOWN_FILE="./content.md"
    BACKUP_FILE="./content.md.tmp"
    echo "Using English content: $MARKDOWN_FILE"
fi

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
    TEMP_PDF="${OUTPUT_PDF%.pdf}-temp.pdf"
    marp --pdf "$MARKDOWN_FILE" --theme "$THEME_FILE" --engine "$ENGINE_FILE" --allow-local-files -o "$TEMP_PDF"

    # Check if Ghostscript is available for reduction
    if command -v gs &> /dev/null && [ -f "$TEMP_PDF" ]; then
        echo "Reducing PDF size..."
        gs -sDEVICE=pdfwrite -dCompatibilityLevel=1.4 -dPDFSETTINGS=/ebook -dNOPAUSE -dQUIET -dBATCH -sOutputFile="$OUTPUT_PDF" "$TEMP_PDF"
        if [ $? -eq 0 ]; then
            original_size=$(du -h "$TEMP_PDF" | cut -f1)
            reduced_size=$(du -h "$OUTPUT_PDF" | cut -f1)
            echo "Original: $original_size → Reduced: $reduced_size"
            rm "$TEMP_PDF"
        else
            mv "$TEMP_PDF" "$OUTPUT_PDF"
        fi
    else
        [ -f "$TEMP_PDF" ] && mv "$TEMP_PDF" "$OUTPUT_PDF"
    fi

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
TEMP_PDF="${OUTPUT_PDF%.pdf}-temp.pdf"
marp --pdf "$MARKDOWN_FILE" --theme "$THEME_FILE" --engine "$ENGINE_FILE" --allow-local-files -o "$TEMP_PDF"

# Check if PDF generation was successful
if [ ! -f "$TEMP_PDF" ]; then
    echo "Error: PDF generation failed"
    exit 1
fi

# Get file size before reduction
original_file_size=$(du -h "$TEMP_PDF" | cut -f1)
echo ""
echo "✓ PDF generated successfully: $TEMP_PDF"
echo "  Original file size: $original_file_size"

# Check if Ghostscript is installed for PDF reduction
if ! command -v gs &> /dev/null; then
    echo ""
    echo "Warning: Ghostscript not found. Skipping PDF reduction."
    echo "Install Ghostscript to enable PDF size reduction:"
    echo "  brew install ghostscript"
    mv "$TEMP_PDF" "$OUTPUT_PDF"
    echo ""
    echo "Final PDF: $OUTPUT_PDF"
    echo "The original $MARKDOWN_FILE has been restored."
    exit 0
fi

# Reduce PDF size using Ghostscript
echo ""
echo "Reducing PDF size..."
gs -sDEVICE=pdfwrite -dCompatibilityLevel=1.4 -dPDFSETTINGS=/ebook -dNOPAUSE -dQUIET -dBATCH -sOutputFile="$OUTPUT_PDF" "$TEMP_PDF"

if [ $? -eq 0 ] && [ -f "$OUTPUT_PDF" ]; then
    reduced_file_size=$(du -h "$OUTPUT_PDF" | cut -f1)
    echo "✓ PDF reduced successfully"
    echo "  Original: $original_file_size → Reduced: $reduced_file_size"

    # Remove temporary PDF
    rm "$TEMP_PDF"

    echo ""
    echo "✓ Final PDF: $OUTPUT_PDF"
    echo "The original $MARKDOWN_FILE has been restored."
else
    echo "Error: PDF reduction failed, keeping original"
    mv "$TEMP_PDF" "$OUTPUT_PDF"
    exit 1
fi
