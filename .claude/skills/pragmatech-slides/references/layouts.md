# PragmaTech theme - layout reference

Every layout is a CSS class on `<section>`. Apply it per slide with an HTML comment directive
`<!-- _class: name -->`, or to the whole deck via the `class:` front-matter key. On a light
deck, combine with `light`, e.g. `<!-- _class: light split -->`.

Slides are separated by `---`. House style: no em dashes - use `-`. Separators in bylines use `·`.

---

## Dark vs light

- **Dark is the default.** No directive needed.
- **Light:** add `class: light` to the front-matter (whole deck), or `_class: light` on a single
  slide. When a slide also needs a layout, list both: `_class: light title`.

```yaml
# whole deck light:
---
marp: true
theme: pragmatech
class: light
---
```

---

## `title` - cover slide

Wrap the accent word in `**bold**` to get the cyan->blue gradient text clip. Usually pairs with
`_paginate: false` and an empty header.

```markdown
<!-- _class: title -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Build & Ship Software **With Confidence**

## A talk subtitle goes here

Philip Riecks · [pragmatech.digital](https://pragmatech.digital/) · [@rieckpil](https://x.com/rieckpil)
```

Optional left panel image: add `![bg left:33%](assets/banner.jpg)` under the directives.

---

## `section` - section divider

A mono, uppercased, letter-spaced `##` kicker over a large `#` heading. Uses the darker
`--pt-bg-0` backdrop and a thicker accent bar.

```markdown
<!-- _class: section -->
<!-- _paginate: false -->

## 01 - Foundations

# Getting Started
```

---

## `agenda` - numbered agenda

An ordered list renders as cards with gradient `01` / `02` / ... markers (leading zero).

```markdown
<!-- _class: agenda -->

## Agenda

1. First topic
2. Second topic
3. Third topic
```

---

## `split` - two columns

Equal `1fr 1fr` columns. Any `h1`/`h2` spans both columns. Great for text + image or text + code.
Images get a rounded border and are capped at 360px tall.

```markdown
<!-- _class: split -->

## Split - Text & Image

Short supporting copy on the left column.

- Point one
- Point two

![](assets/diagram.png)
```

### `split-60` - 60/40 modifier

Use **together** with `split` to widen the first column to `1.5fr / 1fr`. Good for text + code.

```markdown
<!-- _class: split split-60 -->

## Split - Text & Code

Explanation on the wider left column.

```java {2,4}
@SpringBootTest
@Testcontainers
class ApplicationIT {
  @Container @ServiceConnection
  static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:16-alpine");
}
```
```

---

## `quote` - pull quote

A large opening quotation mark is added automatically. The blockquote is the quote; a following
paragraph is the attribution.

```markdown
<!-- _class: quote -->

> Tests are the brakes that let you drive fast. The faster you want to ship, the better your brakes need to be.

Philip Riecks · Founder, PragmaTech GmbH
```

---

## `statement` - big centered statement

Centered, oversized heading. `**bold**` words get the gradient clip. Dark `--pt-bg-0` backdrop.

```markdown
<!-- _class: statement -->

# Move fast **without** breaking production.
```

---

## `metrics` - 3-up stat cards

A bullet list of exactly three items, each starting with a `**stat**`. The bold value becomes the
large gradient number; the rest is the caption.

```markdown
<!-- _class: metrics -->

## By The Numbers

- **70%** Faster feedback loops
- **3×** More confident deploys
- **0** Flaky tests tolerated
```

---

## `closing` - closing / contact

Closing slide over the dark backdrop. Usually pairs with `_paginate: false` and an empty header.

```markdown
<!-- _class: closing -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Let's Talk

**PragmaTech GmbH** - Enabling developers to frequently deliver software with more confidence.

[pragmatech.digital](https://pragmatech.digital/) · mail@philipriecks.de · [@rieckpil](https://x.com/rieckpil)
```

---

## Image syntax cheatsheet

```markdown
![](../../shared/assets/logo.webp)                 inline image
![center h:500 w:1000](assets/x.png)  centered + sized (the `center` keyword matches img[alt~='center'])
![bg](assets/cover.jpg)               full-bleed background
![bg right:33% h:750](assets/x.png)   positioned + sized background panel
![bg left:38%](assets/x.png)          left panel (pairs with split / title)
![w:32 h:32](../../shared/assets/logo.webp)        explicitly sized (e.g. a small footer logo)
```

## Code highlighting

The fence info string takes a `{ranges}` token, rendered by `engine.js`:

````markdown
```java {1,5-7}
// line 1 highlighted, lines 5-7 highlighted; all lines get numbers
```
````

Rendered automatically when you build with `shared/scripts/build.sh` (it passes `shared/theme/engine.js`).
