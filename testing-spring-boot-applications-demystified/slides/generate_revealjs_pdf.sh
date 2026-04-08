#!/bin/zsh
# Script to generate a shareable PDF from the reveal.js presentation
# Usage: ./generate_revealjs_pdf.sh <output-pdf-name>
# Example: ./generate_revealjs_pdf.sh talk-abc-2025.pdf
#
# Prerequisites: npm install (in the slides directory)

set -e

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

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
SLIDES_HTML="$SCRIPT_DIR/index.html"
DECKTAPE="$SCRIPT_DIR/node_modules/.bin/decktape"
SERVE="$SCRIPT_DIR/node_modules/.bin/serve"
PORT=9876

# Check if node_modules exist
if [ ! -d "$SCRIPT_DIR/node_modules" ]; then
    echo "Error: node_modules not found. Run 'npm install' first."
    exit 1
fi

# Check if decktape is available
if [ ! -f "$DECKTAPE" ]; then
    echo "Error: decktape not found. Run 'npm install' first."
    exit 1
fi

# Start a local server in the background
echo "Starting local server on port $PORT..."
"$SERVE" "$SCRIPT_DIR" -l "$PORT" --no-clipboard &
SERVER_PID=$!

# Give the server a moment to start
sleep 2

# Cleanup function to stop the server
cleanup() {
    if kill -0 "$SERVER_PID" 2>/dev/null; then
        kill "$SERVER_PID" 2>/dev/null
        wait "$SERVER_PID" 2>/dev/null || true
        echo "Local server stopped."
    fi
}
trap cleanup EXIT

# Generate PDF with decktape
echo "Generating PDF: $OUTPUT_PDF"
"$DECKTAPE" reveal "http://localhost:$PORT/index.html" "$OUTPUT_PDF" \
    --size 1280x720 \
    --pause 1000

# Check if PDF was generated
if [ ! -f "$OUTPUT_PDF" ]; then
    echo "Error: PDF generation failed"
    exit 1
fi

RAW_SIZE=$(du -h "$OUTPUT_PDF" | cut -f1)
echo "Raw PDF generated: $OUTPUT_PDF ($RAW_SIZE)"

# Try to reduce PDF size with Ghostscript if available
if command -v gs &> /dev/null; then
    REDUCED_PDF="${OUTPUT_PDF%.pdf}-reduced.pdf"
    echo "Reducing PDF size with Ghostscript..."
    gs -sDEVICE=pdfwrite -dCompatibilityLevel=1.4 \
       -dPDFSETTINGS=/ebook -dNOPAUSE -dQUIET -dBATCH \
       -sOutputFile="$REDUCED_PDF" "$OUTPUT_PDF" 2>/dev/null

    if [ -f "$REDUCED_PDF" ]; then
        REDUCED_SIZE=$(du -h "$REDUCED_PDF" | cut -f1)
        echo ""
        echo "Reduced PDF generated: $REDUCED_PDF"
        echo "  File size: $REDUCED_SIZE (was $RAW_SIZE before reduction)"

        # Replace raw with reduced
        rm "$OUTPUT_PDF"
        mv "$REDUCED_PDF" "$OUTPUT_PDF"
        echo "Renamed reduced PDF to $OUTPUT_PDF"
    else
        echo "Warning: PDF reduction failed, keeping original PDF"
    fi
else
    echo "Note: Install Ghostscript (gs) for automatic PDF size reduction."
fi

echo ""
echo "Done! PDF available at: $OUTPUT_PDF"
