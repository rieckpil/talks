---
marp: true
theme: pragmatech
paginate: true
transition: pt-fade
header: 'Lab 1: Testing Pyramid 2.0'
footer: '![](../../shared/assets/logo.webp) PragmaTech GmbH · Effective Spring Boot Testing · 13.04.2026'
---

<!--
Dark deck (default theme). For the LIGHT variant, add `class: light` to the
front-matter and prefix layout classes per slide, e.g. `_class: light title`
(see sample-light.md).
Footer format: '![](../../shared/assets/logo.webp) PragmaTech GmbH · «Workshop» · «Date»'
The `header:` directive shows where students are; it persists across slides
until changed. Update it at each section by setting a new header directive on
that slide (header: 'Lab 2: Slicing Spring Boot Tests').
Transition: pt-fade (global). Only animates in presentation/HTML output.
-->

<!-- _class: title -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Build & Ship Software **With Confidence**

## A PragmaTech Slide Template - Layout Gallery

Philip Riecks · [pragmatech.digital](https://pragmatech.digital/) · [@rieckpil](https://x.com/rieckpil)

---

<!-- _class: section -->
<!-- _paginate: false -->

## 01 - Section Divider

# Getting Started

---

<!-- _class: agenda -->

## Agenda

1. Lorem ipsum dolor sit amet
2. Consectetur adipiscing elit
3. Sed do eiusmod tempor incididunt
4. Ut labore et dolore magna aliqua
5. Duis aute irure dolor in reprehenderit

---

## Content Only - Bullet List

Lorem ipsum dolor sit amet, **consectetur adipiscing elit**, sed do eiusmod
tempor incididunt ut labore et dolore.

- Ut enim ad minim veniam, quis nostrud exercitation
- Ullamco laboris nisi ut aliquip ex ea commodo
- Duis aute irure dolor in reprehenderit in `voluptate`
- Excepteur sint occaecat cupidatat non proident

> Velit esse cillum dolore eu fugiat nulla pariatur - a callout for the key takeaway.

---

<!-- _class: split -->

## Split - Text & Image

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod
tempor incididunt ut labore et dolore magna aliqua.

Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.

![](../../shared/assets/logo.webp)

---

<!-- _class: split split-60 -->

## Split - Text & Code

Use `@ServiceConnection` to auto-wire Spring Boot to a Testcontainer - no manual
URL plumbing required.

- Real PostgreSQL in tests
- Zero config wiring
- Production parity

```java {2,4}
@SpringBootTest
@Testcontainers
class ApplicationIT {
  @Container @ServiceConnection
  static PostgreSQLContainer<?> db =
      new PostgreSQLContainer<>("postgres:16-alpine");
}
```

---

<!-- _class: statement -->

# Move fast **without** breaking production.

---

<!-- _class: quote -->

> Tests are the brakes that let you drive fast. The faster you want to ship, the better your brakes need to be.

Philip Riecks · Founder, PragmaTech GmbH

---

<!-- _class: metrics -->

## By The Numbers

- **70%** Faster feedback loops
- **3×** More confident deploys
- **0** Flaky tests tolerated

---

## Code-Focused Slide

```java {1,5-7}
@Test
void shouldStoreBookAndSendNotification() {
  var book = new Book("Effective Testing", "Philip Riecks");

  bookService.create(book);

  assertThat(bookRepository.findAll()).hasSize(1);
  verify(mailClient).sendDeletionNotice(any());
}
```

Line numbers and `{1,5-7}` highlighting are rendered by `engine.js`.

---

<!-- _class: split -->
![bg right:38%](../../shared/assets/logo.webp)

## Image Background Panel

Pair a full-bleed brand panel with a heading and supporting copy.

- Lorem ipsum dolor sit amet
- Consectetur adipiscing elit
- Sed do eiusmod tempor

---

<!-- _class: closing -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Let's Talk

**PragmaTech GmbH** - Enabling developers to frequently deliver software with more confidence.

[pragmatech.digital](https://pragmatech.digital/) · mail@philipriecks.de · [@rieckpil](https://x.com/rieckpil)
