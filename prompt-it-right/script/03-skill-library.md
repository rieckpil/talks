# Block 2, part 2: Deterministic results - the test skill library (17 min, 11:25 - 11:42)

Goal: show how my skill library is structured and adapted to each project. Hints, not a full tour.

| Min | Beat | Content |
|---|---|---|
| 1 | Problem | Same prompt, different tests every day |
| 2 | Skills as written-down standards | Markdown in the repository, loaded when the task matches. More predictable, not deterministic |
| 2 | Template + onboarding | Template library, onboarding prompt (detect, ask, replace), result: project-specific skills |
| 2 | The seven skills by name | unit-testing, slice-testing, slice-web-testing, integration-testing, testcontainers-setup, e2e-ui-testing, test-setup-review, plus the router spring-boot-testing |
| 4 | How a skill is built | Folder tree, `SKILL.md` (frontmatter trigger, adapt-this-template knobs, scope, non-negotiables, workflow), `references/testing-standards.md` (rule IDs and severity), `examples.md` (good/bad pairs), `project-setup.md` |
| 1 | The router | Cheapest test that proves the behavior. Plan table and "not tested" list |
| 5 | **Demo 1** | Review skill on PetClinic (`demos/demo-1-review-skill.md`), then numbers and findings slides |

Do not walk through every skill. Name them, show two files.
