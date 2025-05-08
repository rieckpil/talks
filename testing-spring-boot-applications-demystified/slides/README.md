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

### HTML Slides

To generate HTML slides that you can view in a browser:

```bash
marp --html marp-slides.md
```

This will create `marp-slides.html` in the current directory.

### PDF Slides

To generate a PDF version of the slides:

```bash
marp --pdf marp-slides.md
```

This will create `marp-slides.pdf` in the current directory.

You can also use the Chrome PDF engine for better rendering:

```bash
marp --pdf --engine chrome marp-slides.md
```

If you encounter any layout issues, you can also try adjusting the PDF size:

```bash
marp --pdf --size 16:9 --engine chrome marp-slides.md
```

### Presentation Mode

For presenting the slides with speaker notes:

```bash
marp -s marp-slides.md
```

This will start a local server and open the presentation in your default browser.

## Customization

The slides use a custom PragmaTech theme defined in `pragmatech-theme.css`. The theme includes:

- Custom colors matching PragmaTech's branding
- A footer with the PragmaTech logo on the left, company name in the center, and page numbers on the right
- Proper content padding to prevent overflow
- Responsive layout for different screen sizes

### Images and Assets

- All images are stored in the `assets/` directory
- The PragmaTech logo is included in the footer of each slide
- SVG diagrams for context caching and other concepts

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