# Block 2, part 4: My dev setup - MCP servers and a local harness (8 min, 11:57 - 12:05)

Baseline: course lesson `m5-agentic-setup/03-mcp-servers.lesson.md`, but this is my setup.

| Min | Beat | Content |
|---|---|---|
| 1 | Divider + three servers | Context7 (docs for your version), GitHub (workflow runs and logs), Playwright (rendered page, observed locators) |
| 1 | Adding and scopes | `claude mcp add`, local vs project (`.mcp.json`) vs user scope |
| 1 | Context7 and GitHub | `use context7`, Actions toolset, read always on, write only with me, minimal token |
| 1 | Playwright | Accessibility tree, `--isolated`, `--storage-state`, visible browser |
| 2 | The local harness | Graphic: PetClinic runs locally, the agent reaches the app, the browser (Playwright MCP), the tests (shell) and CI (GitHub MCP) itself. Fast tests and CI access close the feedback loop |
| 1 | The loop in five steps | Start, look, change, run, push and read CI |
| 1 | Rules | Trust each server (prompt injection), few servers, no token in `.mcp.json`, prune with `claude mcp list` |

Optional backup: Demo 4 (`demos/demo-4-mcp-ci.md`). Demo 3 (feature flow) stays a backup recording.
