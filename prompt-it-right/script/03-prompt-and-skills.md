# Block 2, part 2: Meaningful tests and skills (17 min, 11:25 - 11:42)

Goal: show how a skill library makes the agent's tests meaningful and fast. One sample skill in detail, the other six as boxes.

| Min | Beat | Content |
|---|---|---|
| 1 | Prompt | "Write meaningful tests. Make no mistakes." Models get better, but what is meaningful? |
| 2 | Not obvious | New developers might not prompt the AI to think about fast and parallel tests. Timing is a big thing, keep the suite fast |
| 1 | To get there: skills | Divider "Testing judgment, written down once", flow: standards, skills, every session |
| 5 | Sample skill: unit-testing | Folder tree (`SKILL.md`, `references/testing-standards.md`, `examples.md`), frontmatter as trigger, "Inside SKILL.md", the knobs table, rules with IDs and severity, good/bad pairs. Show the real files: `prompt-it-right/.claude/skills/unit-testing/` |
| 1 | Skill templates | I keep skill templates and reuse them across projects. Only the variable parts (knobs table) change per project |
| 1 | The seven skills | The boxes: unit-testing, slice-testing, slice-web-testing, integration-testing, testcontainers-setup, e2e-ui-testing, test-setup-review, plus the router |
| 6 | **Demo** | The basic skill setup on PetClinic (`demos/demo-1-review-skill.md`) |

The sample skill is condensed on purpose: a subset of the rules, same structure as the full library.
