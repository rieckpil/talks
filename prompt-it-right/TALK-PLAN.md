# Prompt It Right: Spring Boot Testing in the AI Era

Format: 180 min session (150 min content + 30 min break) · Devoxx Belgium 2026 · Monday, October 5, 2026, 09:30 - 12:30

## One-sentence thesis

When AI writes the code, the test suite is the only thing that tells you the code is right. A fast and complete suite is the bottleneck of agentic development, so teach the agent how you test.

## Audience promise

1. You can place every Spring Boot test type (unit, slice, integration, e2e) and pick the cheapest one that proves a behavior.
2. You know why a slow or weak suite breaks agentic development.
3. You leave with concrete ideas: skill structure, MCP setup, fast pipeline. No hard sell. One soft pointer to the course at the end.

## Timeline

Block 1 is the complete crash course (the fundament). Block 2 starts with the rationale, then skills, structure, tooling. Each block ends with a 10 min FAQ. 75 + 30 + 75 = 180.

| Start (Mon) | Min | Block | Script |
|---|---|---|---|
| 09:30 | 5 | Opening: Mentimeter, About Philip, goals, agenda | script/00-timeline.md |
| 09:35 | 60 | **Block 1**: Spring Boot testing in a nutshell, the full crash course, ends with "what makes a good suite" | script/01-crash-course.md |
| 10:35 | 10 | **Block 1 FAQ** | script/faq-block-1.md |
| 10:45 | 30 | BREAK | |
| 11:15 | 15 | **Block 2** Rationale: why a fast, complete suite is the constraint for agents | script/02-why-it-matters.md |
| 11:30 | 20 | **Block 2** Skills: how I structure them + Demo 1 | script/03-skills.md |
| 11:50 | 12 | **Block 2** Fast feedback (pipeline) + Demo 2 | script/04-fast-pipeline.md |
| 12:02 | 13 | **Block 2** Tooling around it: workmode, LSP, RTK, MCPs, guardrails + Demo 3 or 4 | script/05-agent-setup.md |
| 12:15 | 5 | **Block 2** Evidence, takeaways, soft pointer | script/06-wrap-up.md |
| 12:20 | 10 | **Block 2 FAQ** | script/faq-block-2.md |

Check: block 1 = 5 + 60 + 10 = 75. Block 2 = 15 + 20 + 12 + 13 + 5 + 10 = 75. No buffer in block 2, so use the cut list early.

FAQ method: questions arrive live (interrupting is welcome) and as comments in the Devoxx app talk page (QR on the 'How to ask' slide). Pick the best ones at the end of each block. Prepared fallback questions are in the FAQ scripts.

## Cut list (if you run late)

1. E2E slides in Part 1 (3 min)
2. Demo 4 (MCP) - show screenshots only (8 min)
3. TDD-or-not slide, ArchUnit slide (3 min)
4. Skip live run of Demo 2, show the recorded output (4 min)
5. Shorten a FAQ to 5 min (never skip it)
6. Block 2 has no buffer: drop Demo 4 and the TDD/ArchUnit slides first

## Demo plan (PetClinic, pinned commit, see `demo/setup.sh`)

| Demo | Where | Min | Wow moment |
|---|---|---|---|
| 1 Review skill on PetClinic | Part 3a | 10 | Skill finds 10 contexts, double MySQL, H2 vs prod DB, and does NOT flag `@DisabledInNativeImage` |
| 2 One container per image | Part 3b | 6 | `docker events` count drops from 2 to 1 MySQL start |
| 3 Feature flow ("no two visits per day") | Part 3c | 8 | Router picks unit + web slice only, prints "not tested" list |
| 4 MCP (Playwright locators, GitHub logs) | Part 3c (backup) | 8 | Agent reads CI failure and fixes it |

## Sources

- Course repo: `~/Development/git/agentic-testing-for-spring-boot-course` (skills in `spring-boot-testing-skills/`, M2 and M5 slide decks, `resources/petclinic-skill-comparison.md`)
- Course page: https://pragmatech.digital/agentic-spring-boot-testing-course/
- Earlier talks in this repo for crash course material: `testing-spring-boot-applications-demystified/slides/webinar.md`, `stop-fighting-your-spring-boot-tests/slides/content.md`

## Open points

- [ ] Devoxx room (Mentimeter code is 7108 0067)
- [x] Offer QR points to https://pragmatech.digital/lp/devoxx-belgium-2026/
- [ ] Decide: live Demo 3 or Demo 4 (only one fits)
- [ ] Record fallback videos for all demos
- [ ] Check the missing image `m3-what-is-a-skill.jpg` from the course deck (draw a new one)
- [ ] Write one example GitHub Actions workflow for Part 3b (the course repo has none)
- [ ] Rerun the PetClinic skill comparison with more than one run per arm (current evidence is one run)

## Block 2 slide baseline (course repo `slides/`)

| Deck section | Adapted from |
|---|---|
| Why it matters | `m2-verification-constraint/` (afterthought, Formula 1 engine, trustworthy suite) |
| Skills | `m3-skill-library/` (what is a skill, unit, slice-web, review) and `m4-adoption/` (set up, review) |
| Fast feedback | `m3-skill-library/` (testcontainers-setup, integration-testing cheat sheets) and `m4-adoption/02-reviewing-your-tests` |
| Tooling | `m5-agentic-setup/` (how I work, eyes and budget, MCPs and guardrails) and `m4-adoption/03-building-a-feature` |
| Wrap-up | `resources/petclinic-skill-comparison.md`, `m6-wrap-up/01-where-to-go` |

Course branding, "module" and "lesson" wording and the course-specific CTAs were removed. Only the soft pointer slide at the end mentions the course.
