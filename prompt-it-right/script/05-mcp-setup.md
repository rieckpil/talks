# Block 2, part 4: How I configure MCP servers (8 min, 11:57 - 12:05)

Baseline: course lesson `m5-agentic-setup/03-mcp-servers.lesson.md`, but this is my setup.

| Min | Beat | Content |
|---|---|---|
| 1 | Divider + three servers, three gaps | Context7 (docs for your version), GitHub (workflow runs and logs), Playwright (rendered page, observed locators) |
| 1 | Adding and scopes | `claude mcp add`, local vs project (`.mcp.json`) vs user scope |
| 2 | Context7 and GitHub | `use context7`, Actions toolset, read always on, write only with me, read-only mode, minimal token |
| 1 | Playwright | Accessibility tree, `--isolated`, `--storage-state`, visible browser, slowMo |
| 2 | Playwright in the local dev loop | Graphic: PetClinic runs locally, the agent reaches GitHub (GitHub MCP), the browser (Playwright MCP) and the tests (shell) itself. Then the five-step loop: start, look, change, run, push and read CI |
| 1 | Rules | Trust each server (prompt injection), few servers, no token in `.mcp.json`, prune with `claude mcp list` |

Optional backup: Demo 4 (`demos/demo-4-mcp-ci.md`) if time allows. Demo 3 (feature flow) is no longer on the slides, keep it as a backup recording.
