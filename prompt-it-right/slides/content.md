---
marp: true
theme: pragmatech
title: 'Prompt It Right: Spring Boot Testing in the AI Era'
class: light
paginate: true
transition: pt-fade
header: 'Prompt It Right: Spring Boot Testing in the AI Era @ Devoxx Belgium 2026'
footer: '![](assets/logo.webp) Philip Riecks · [PragmaTech GmbH](https://pragmatech.digital/) · [@rieckpil](https://x.com/rieckpil)'
---

<!--
Menti: TODO code
Timeline: see ../TALK-PLAN.md. Search TODO to find open slides.
-->

<!-- _paginate: false -->
<!-- _header: '' -->
<!-- _footer: '' -->

<!--
Notes:
- Opener: Antwerp, the city of this talk. Warm welcome before the title slide.
-->

![bg](assets/antwerp.jpg)

---

<!-- _class: light title -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Prompt It Right: **Spring Boot Testing** in the AI Era

## Your agent writes the code. Your test suite decides if it ships.

Devoxx Belgium 2026 · October 5, 2026

<!--
Notes:
- Welcome. 180 minutes, one break at about 1h15.
-->

---

<!--
Notes:
- This is the session as listed on the Devoxx schedule. Point at the BEGINNER badge.
- Say it: I marked this as beginner on purpose. We cover the fundamentals first, so everyone can follow the agent part.
-->

![center w:1100](assets/talk-overview.png)

---

<!-- footer: '![](assets/logo.webp)' -->

### About Philip

- Software Engineer from Erlangen, Germany 🍻
- Blogging and content creation about testing Java and Spring Boot applications 🍃
- Founder of [PragmaTech GmbH](https://pragmatech.digital/) - **Enabling Developers to Frequently Deliver** Software with **More Confidence**
- Using coding agents daily for Spring Boot projects

<!--
Notes:
- TODO add assets/location.png as bg right panel: ![bg right:33%](assets/location.png)
-->

---

## Participate During the Talk

Go to [menti.com](https://www.menti.com/) and use the code **TODO** to **anonymously** submit answers.

- How much of your code is AI-written today?
- How long does your test suite take?
- Do you trust a green build?

**Questions?** Post them in the Mentimeter Q&A during the block. We answer the top ones in a 10 minute FAQ at the end of each block.

---

<!-- _class: light -->

## Today's Deep Dive: 180 Minutes, Two Blocks

<style scoped>
.stack { display: flex; flex-direction: column; align-items: center; gap: 14px; margin-top: 0.4em; }
.stack .sketch { box-sizing: border-box; }
.stack .top   { width: 62%; font-size: 1.15em; padding: 0.8em 1em; }
.stack .pause { width: 34%; font-size: 1em; border-style: dashed; transform: rotate(0.3deg); }
.stack .base  { width: 100%; font-size: 1.3em; border-width: 5px; padding: 1.1em 1em; }
.stack small  { font-size: 0.7em; }
</style>

<div class="stack">
  <div class="sketch accent alt top">
    <strong>2 · Agentic Development and Testing</strong>
    <small>75 min · rationale · skills · fast pipeline · tooling · demos · FAQ</small>
  </div>
  <div class="sketch pause">☕ Break · 30 min</div>
  <div class="sketch accent base">
    <strong>1 · Spring Boot Testing in a Nutshell</strong>
    <small>75 min · unit · slice · integration · Testcontainers · context cache · FAQ</small>
  </div>
</div>

<!--
Notes:
- Read it from the bottom up: the fundament comes first, the agent part is built on top of it.
- Block 1 (75 min): get a shared understanding of what makes a good test suite. You need these concepts to judge what an agent produces.
- Break (30 min), then block 2 (75 min): agentic development and testing.
- Beginner level is on purpose: without the fundament the agent part does not hold.
-->

---

<!-- _class: light statement -->

You will leave with **ideas you can use on Monday**, not a sales pitch.

<!--
Notes:
- Say it: everything shown works without buying anything. One pointer at the end.
-->

---

<!-- _class: light section -->
<!-- header: 'Part 1: Spring Boot Testing Crash Course' -->

## 01 - Crash course

# Know your toolbox

---

<!-- _class: light statement -->

Pick the **cheapest test** that proves the behavior.

---

## The shape of your test suite

TODO: pyramid / honeycomb / trophy. Use the `.pyramid` CSS helper (see skill references/handdrawn-boxes.md).

<!--
Notes:
- Names do not matter. Speed and confidence matter.
-->

---

<!-- _class: light section -->

## 01.1 - Unit tests

# No Spring. No excuses.

---

TODO: unit test code slide with `{ranges}` (PetClinic `PetTypeFormatter` style): one assertion chain, `.as(...)`, injected `Clock`.

---

TODO: pitfall slide - "unit tests" that start a Spring context.

---

<!-- _class: light section -->

## 01.2 - Slice tests

# Test one layer

---

TODO: `@WebMvcTest` + `MockMvcTester` code slide.

---

TODO: security matrix slide - anonymous, wrong role, right role.

---

TODO: `@DataJpaTest` on the real database with Testcontainers. Why not H2.

---

TODO: `@JsonTest` short example.

---

<!-- _class: light section -->

## 01.3 - Testcontainers

# Real infrastructure, no mocks

---

TODO: `TestcontainersConfiguration` with `@ServiceConnection`, one static container per image.

---

<!-- _class: light section -->

## 01.4 - Integration tests

# The whole app, over HTTP

---

TODO: `@SpringBootTest(webEnvironment = RANDOM_PORT)` + `RestTestClient` journey test.

---

TODO: WireMock for outgoing HTTP, abstract base class.

---

<!-- _class: light section -->

## 01.5 - The context cache

# The silent speed killer

---

TODO: what makes a new context (`@MockitoBean`, properties, profiles, `@Import`). Use `assets/context-caching-logs.png` if still wanted (copy from the Bozen talk assets).

---

TODO: `@DirtiesContext` smell, cache DEBUG logging setting.

---

TODO: E2E in one slide (cut if running late).

---

<!-- _class: light reveal -->

## What makes a good test suite: five properties

* **Fast** - the agent loop needs seconds, not minutes
* **Deterministic** - flaky tests teach the agent to retry and ignore
* **Isolated** - parallel runs, random order, no shared state
* **One reason to fail** - a red test points at one defect
* **A message that locates it** - `.as("...")` on every assertion

<!--
Notes:
- Closing beat of block 1 (5 min). Sum up the crash course as the five properties a good suite has.
- Block 2 starts from these: what happens to an agent when one of them is missing.
-->

---

<!-- _class: light section -->
<!-- header: 'Block 1 FAQ' -->

## FAQ 1

# Your questions

<!--
Notes:
- 10 min. Open the Mentimeter Q&A, sort by votes, answer 4-6 questions, 90 seconds each.
- Prepared backups: script/faq-block-1.md.
- Agent questions: park them for block 2 after the break.
-->

---

<!-- _class: light section -->
<!-- header: 'Break' -->

## Break

# Back at 1:45

<!--
Notes:
- Collect questions on Mentimeter. Reset the demo during the break.
-->

---

<!-- _class: light section -->
<!-- header: 'Block 2 · Why It Matters' -->

## 02 - Why it matters

# Verification is the new constraint

---

<!-- _class: light statement -->

Generating code got **cheap**. Trusting it did not.

---

<!-- _class: light metrics -->

## The review arithmetic

- **2,000** lines an agent can write per day
- **200** lines a human can review per hour
- **1** person who explains the outage

<!--
Notes:
- Numbers are illustrative, say so. Source: course module 2 (Formula 1 engine).
-->

---

## The pipeline you actually have

<div class="flow">
  <div class="sketch">Prompt</div>
  <div class="arrow">→</div>
  <div class="sketch">Diff</div>
  <div class="arrow">→</div>
  <div class="sketch accent">Review</div>
  <div class="arrow">→</div>
  <div class="sketch">Merge</div>
</div>

<!--
Notes:
- Generation is no longer the slow step. Review is. Tests are the review you can automate.
-->

---

<!-- _class: light quote -->

> A rule in the build does not care which model wrote the code.

TODO: decide attribution (own quote or source)

---

<!-- _class: light reveal -->

## What an agent does with a bad suite

* Slow suite: runs one test, or none
* Flaky suite: retries until green, or deletes the test
* Weak assertions: "fixes" the test instead of the code
* Mock everything: green build, wrong behavior

<!--
Notes:
- TODO add 2-3 own anecdotes.
-->

---

<!-- _class: light statement -->

The **green check** has to carry the weight.

---

<!-- _class: light section -->
<!-- header: 'Part 3a: Teach the Agent How You Test' -->

## 03 - Skills

# Teach the agent how YOU test

---

TODO: problem slide - agent without guidance (H2, `@MockitoBean` everywhere, 10+ contexts, `Thread.sleep`).

---

TODO: AGENTS.md vs skill statement slide.

---

TODO: anatomy of a skill - router + child skills, `references/` tree (CSS `.flow` or Excalidraw PNG).

---

TODO: progressive disclosure - description always loaded, body on match, references on demand.

---

TODO: rules with IDs and severity (C/H/M/L), CUSTOMIZE table.

---

TODO: routing rule - cheapest test that proves the behavior.

---

<!-- _class: light statement -->

**Demo 1:** review a real test suite

<!--
Notes:
- See script/demos/demo-1-review-skill.md
-->

---

<!-- _class: light section -->
<!-- header: 'Part 3b: Fast Feedback and Pipeline' -->

## 04 - Fast feedback

# Seconds, not minutes

---

TODO: two-phase build (Surefire `*Test`, Failsafe `*IT`) Maven snippet.

---

TODO: one static container per image, pre-pull images in CI.

---

TODO: context budget + Spring Test Profiler + `jq` gate.

---

TODO: parallelism per test type.

---

TODO: agent-side speed - single test class command, quiet logs.

---

<!-- _class: light statement -->

**Demo 2:** count the containers

---

<!-- _class: light section -->
<!-- header: 'Part 3c: Agent Setup' -->

## 05 - Agent setup

# Small steps, good tools, guardrails

---

TODO: workmode - atomic changes, worktrees, plan mode.

---

TODO: eyes and budget - LSP, RTK, status line.

---

TODO: MCP servers - Context7, GitHub, Playwright, and the risks.

---

TODO: guardrails - ArchUnit, TDD or not.

---

<!-- _class: light statement -->

**Demo 3 or 4:** feature flow or MCP + CI

---

<!-- _class: light section -->
<!-- header: 'Wrap-up' -->

## 06 - Wrap-up

# What to take home

---

TODO: evidence slide - PetClinic comparison (team vs AI only vs AI + skill) with honest limits.

---

<!-- _class: light reveal -->

## Five takeaways

* Pick the cheapest test that proves the behavior
* Verification is the new constraint
* Teach the agent your rules with skills
* Keep feedback fast: two phases, one container per image, few contexts
* Work in small steps and add guardrails

---

## Want more?

![bg right:33% h:400](assets/agentic-testing-course-qr.png)

- Everything in this talk works without the course
- Deeper walkthrough: [pragmatech.digital/agentic-spring-boot-testing-course](https://pragmatech.digital/agentic-spring-boot-testing-course/)
- Newsletter: tips on testing Spring Boot applications

---

<!-- _class: light section -->
<!-- header: 'Block 2 FAQ' -->

## FAQ 2

# Your questions

<!--
Notes:
- 10 min. Open the Mentimeter Q&A, sort by votes, answer 4-6 questions, 90 seconds each.
- Prepared backups: script/faq-block-2.md.
- Long answers: promise a follow-up in the hallway or the newsletter.
-->

---

<!-- header: 'Feedback' -->

## Rate this Session

Your feedback helps me improve the talk and helps Devoxx.

- Open the **Devoxx Belgium app**
- Find **"Prompt It Right"** and leave a rating
- Tell me what worked and what to change

[m.devoxx.com/events/dvbe26/talks/7006](https://m.devoxx.com/events/dvbe26/talks/7006/prompt-it-right-spring-boot-testing-in-the-ai-era)

![bg right:36% h:420](assets/devoxx-feedback-qr.png)

<!--
Notes:
- Leave this slide up for a moment after FAQ 2. Ask for honest feedback, also on the beginner level and the length of the two blocks.
-->

---

<!-- _class: light closing -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Thank you

## Questions?

Philip Riecks · [pragmatech.digital](https://pragmatech.digital/) · [@rieckpil](https://x.com/rieckpil)
