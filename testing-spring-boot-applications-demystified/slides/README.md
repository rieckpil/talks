# Testing Spring Boot Applications Demystified - Slides

This directory contains the presentation slides for the "Testing Spring Boot Applications Demystified" talk.

## Prerequisites

To generate the slides, you'll need:

1. [Node.js](https://nodejs.org/) (latest LTS version recommended)
2. [Marp CLI](https://github.com/marp-team/marp-cli) for converting Markdown to PDF/HTML

## Installation

Install Marp CLI globally:

```bash
npm install -g @marp-team/marp-cli
```

## Generating Slides

The deck is `webinar.md`, the theme is `pragmatech.css` (light palette, `class: light`)
and `engine.js` adds code line numbers and `{1,3-5}` line highlighting. `.marprc.yml`
wires the engine and theme, so no flags are needed. Run all commands from this folder.

```bash
# self-contained HTML (transitions and presenter mode work here)
marp webinar.md -o webinar.html

# live preview while editing
marp -p -w webinar.md

# one PNG per slide for a quick visual check
marp webinar.md --images png -o preview/slide.png

# shareable PDF with resized images and Ghostscript compression
./resize_images.sh
./generate_sharable_pdf.sh slides-<venue>-<date>.pdf
```

Note: when Marp runs from a script or CI without a terminal, add `< /dev/null` to the
command. Marp CLI otherwise waits for Markdown on stdin and looks like it hangs.

Exported PDFs follow the naming `slides-<venue>-<date>.pdf` and are committed next to the deck.

## Customization

The slides use the PragmaTech Marp theme in `pragmatech.css`. It is the same file as in
the "Top 5 Spring Boot Testing Mistakes" deck and ships two palettes in one file: dark
(default) and light (`class: light`, used here). Layout classes are applied per slide as
`<!-- _class: light <layout> -->`: `title`, `section`, `agenda`, `split`, `statement`,
`metrics`, `closing`.

House style:

- No em dashes, use `-`. Separators in bylines use `·`.
- One idea per slide. Quest and quest-item intros use the `section` layout.
- Speaker notes live in `<!-- Notes: ... -->` comments above the slide content.

### Images and Assets

- All images are stored in the `assets/` directory
- The PragmaTech logo (`assets/logo.webp`) sits in the footer of each slide
- SVG diagrams for context caching and other concepts

## Image Optimization

To reduce the size of the generated PDF, you can use the included image optimization scripts:

### Quick Method: Generate Shareable PDF (Recommended)

The easiest way to generate a shareable PDF with optimized images:

```bash
./generate_sharable_pdf.sh talk-abc-2025.pdf
```

This script will:
1. Check if resized images exist in `assets/generated` (if not, prompts to run `resize_images.sh` first)
2. Temporarily update image references in `content.md` to use resized images
3. Generate the PDF with Marp using the specified filename
4. Automatically restore the original `content.md` file

**Usage:**
```bash
./generate_sharable_pdf.sh <output-pdf-name>

# Examples:
./generate_sharable_pdf.sh talk-jug-zurich-2025.pdf
./generate_sharable_pdf.sh presentation.pdf
```

The script handles all the temporary modifications and cleanup automatically, so you don't have to worry about manually restoring your markdown file.

### Manual Method: Step-by-Step

If you prefer to do it manually or need more control:

1. Resize and optimize all images:

```bash
./resize_images.sh
```

This script creates resized versions of all images in an `assets/generated` directory.

2. Update image references in the markdown file:

```bash
./update_image_links.sh
```

This script updates all image references in `content.md` to point to the optimized versions.

3. Generate the PDF as usual:

```bash
marp --pdf content.md --theme pragmatech.css --allow-local-files
```

4. Restore the original markdown file:

```bash
mv content.md.bak content.md
```

## Troubleshooting

If you encounter any issues with slide rendering:

1. **Footer issues**: Make sure the assets/logo.webp file exists and is correctly referenced
2. **Content overflow**: If content is still overflowing, consider breaking into multiple slides
3. **Image scaling**: Try adding `width="X%"` to image tags to control their size
4. **PDF generation**: Use the `--engine chrome` option for better PDF output

For any CSS-specific issues:

```bash
marp --html --preview marp-slides.md
```

This will open a preview where you can inspect elements and debug styling.

## License

This presentation is copyright Philip Riecks / PragmaTech Digital - All rights reserved.
