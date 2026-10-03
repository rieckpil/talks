# Part 3c: Tooling around the skills - workmode, MCPs, guardrails (13 min, 2:32 - 2:45)

| Min | Beat | Content |
|---|---|---|
| 2 | Workmode | Small atomic changes, one branch each, git worktrees, plan mode first, `/clear` between tasks |
| 2 | Eyes and budget | Language server instead of grep, RTK to cut noisy Maven output (measure on your own build), status line with context percent |
| 4 | MCP servers | Context7 (docs for your exact version), GitHub MCP (read workflow runs and job logs, close the CI loop, read-only by default), Playwright MCP (observe locators, do not guess). Rules: trust each server (prompt injection), keep the number small (tool descriptions cost context), no tokens in `.mcp.json` |
| 2 | Guardrails | ArchUnit: a rule in the build does not care which model wrote the code. TDD or not: honest take |
| 3 | **Demo 3 or 4** | Pick one live. Demo 3: feature flow. Demo 4: MCP + CI |

Install lines for slides:

```
claude mcp add --scope user --transport http context7 https://mcp.context7.com/mcp
claude mcp add playwright npx @playwright/mcp@latest
```

Reuse: course repo `slides/m5-agentic-setup/` (lessons 01-03 and archived decks `01-how-i-work`, `02-eyes-and-budget`, `03-mcps-and-guardrails`).
