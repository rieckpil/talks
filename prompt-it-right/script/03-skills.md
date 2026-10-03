# Block 2, Part 3a: Teach the agent how you test (skills and how I structure them) (20 min, 11:30 - 11:50)

Goal: show how a skill library is built. Give ideas, not a full tour of every skill.

| Min | Beat | Content |
|---|---|---|
| 2 | Recap + problem | Agent without guidance: H2 instead of prod DB, `@MockitoBean` everywhere, 10+ contexts, `Thread.sleep` |
| 2 | AGENTS.md vs skill | AGENTS.md: always loaded, keep small. Skill: loaded when the task matches. Install paths per tool (`.claude/skills`, `.agents/skills`, `.github/skills`) |
| 4 | Anatomy | One router skill (the only one the tool lists), child skills opened by path. Progressive disclosure: description always, body on match, `references/` on demand. Frontmatter description "Use when ... Not for ..." |
| 2 | Rules with IDs | Non-negotiables with ID + severity C/H/M/L (e.g. `[T7-H]` message on every assertion, `[C1]` one container per image). CUSTOMIZE table for team choices |
| 1 | Routing rule | Cheapest test that proves the behavior: unit, slice, integration, browser |
| 9 | **Demo 1** | Review skill on PetClinic (see `demos/demo-1-review-skill.md`) |

Hints only: show 2 skills in detail (unit-testing, testcontainers-setup), name the others on one slide.
Soft course mention: one line "I teach this in more detail elsewhere" at most. Save the pointer for the wrap-up.

Reuse: course repo `slides/m3-skill-library/01-what-is-a-skill.deck.md`, per-skill cheat sheets. Source skills: `spring-boot-testing-skills/`.
