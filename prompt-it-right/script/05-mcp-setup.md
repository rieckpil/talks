# Block 2, part 4: My dev setup - MCP servers and a local harness (8 min, 11:57 - 12:05)

Baseline: course lesson `m5-agentic-setup/03-mcp-servers.lesson.md`, but this is my setup.

| Min | Beat | Content |
|---|---|---|
| 1 | Divider + three servers | My three go-to MCPs: Context7 (docs for your version), GitHub (workflow runs and logs), Playwright (rendered page, observed locators). The actual MCPs vary per project and tech stack |
| 1 | Context7 | What it is: up-to-date, version-specific docs in the prompt. `use context7`, name the version |
| 1 | GitHub MCP | Dedicated slide: the agent reads workflow runs and job logs and closes the CI loop. Actions toolset, remote server, token |
| 2 | Playwright | Accessibility tree, `--isolated`, `--storage-state`, visible browser |
| 3 | The local harness | Graphic: PetClinic runs locally, the agent reaches the app, the browser (Playwright MCP), the tests (shell) and CI (GitHub MCP) itself. Fast tests and CI access close the feedback loop |

Optional backup: Demo 4 (`demos/demo-4-mcp-ci.md`). Demo 3 (feature flow) stays a backup recording.
