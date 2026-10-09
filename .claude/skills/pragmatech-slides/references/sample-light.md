---
marp: true
theme: pragmatech
class: light
paginate: true
transition: pt-fade
header: 'Lab 1: Testing Pyramid 2.0'
footer: '![](../../shared/assets/logo.webp) PragmaTech GmbH · Effective Spring Boot Testing · 13.04.2026'
---

<!--
Light variant of the same `pragmatech` theme. The whole deck opts in via the
`class: light` global directive above; content slides inherit it automatically,
and layout slides combine it, e.g. `_class: light title`.
-->

<!-- _class: light title -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Build & Ship Software **With Confidence**

## A PragmaTech Slide Template - Light Theme

Philip Riecks · [pragmatech.digital](https://pragmatech.digital/) · [@rieckpil](https://x.com/rieckpil)

---

<!-- _class: light section -->
<!-- _paginate: false -->

## 01 - Section Divider

# Getting Started

---

<!-- _class: light agenda -->

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

<!-- _class: light split -->

## Split - Text & Image

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod
tempor incididunt ut labore et dolore magna aliqua.

Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.

![](../../shared/assets/logo.webp)

---

<!-- _class: light split split-60 -->

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

<!-- _class: light statement -->

# Move fast **without** breaking production.

---

<!-- _class: light quote -->

> Tests are the brakes that let you drive fast. The faster you want to ship, the better your brakes need to be.

Philip Riecks · Founder, PragmaTech GmbH

---

<!-- _class: light metrics -->

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

<!-- _class: light split -->
![bg right:38%](../../shared/assets/logo.webp)

## Image Background Panel

Pair a full-bleed brand panel with a heading and supporting copy.

- Lorem ipsum dolor sit amet
- Consectetur adipiscing elit
- Sed do eiusmod tempor

---

<!-- _class: light closing -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Let's Talk

**PragmaTech GmbH** - Enabling developers to frequently deliver software with more confidence.

[pragmatech.digital](https://pragmatech.digital/) · mail@philipriecks.de · [@rieckpil](https://x.com/rieckpil)
