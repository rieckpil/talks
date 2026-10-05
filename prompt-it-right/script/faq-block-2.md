# FAQ block 2: Agentic development and testing (10 min, 12:20 - 12:30)

Read the comments on the Devoxx app talk page and take live questions. If the room is quiet, use these prepared ones.

| Question | Short answer |
|---|---|
| Does this only work with Claude Code? | No. Skills are plain Markdown. Install paths differ per tool (`.claude/skills`, `.agents/skills`, `.github/skills`). Say which tools you tested |
| AGENTS.md or skills? | AGENTS.md: short, always loaded. Skills: loaded when the task matches, with references on demand |
| How do I stop the agent from deleting or weakening tests? | Rules with severity in the skill, review of the diff, build guardrails (ArchUnit, mutation testing), small changes |
| How big can a skill be before it hurts? | Keep the description tiny, the body focused, move detail to `references/`. Measure context use in the status line |
| Should the agent write the tests first (TDD)? | Honest take: it works for well-defined behavior. Always review the tests, they are the spec |
| Which MCP servers do you really use? | Context7, GitHub (read-only), Playwright. Keep the number small |
| Is it safe to give MCP servers access? | Trust each server, watch prompt injection, minimal token scope, no tokens in `.mcp.json` |
| Does this reduce token cost? | RTK and a fast single-test command help. Measure on your own build, do not trust generic claims |
| What does the evidence look like? | PetClinic comparison, one run per arm. Be honest about the limits |
| Where can I learn more? | Everything shown works without the course. The course page and newsletter are linked on the last slide |
