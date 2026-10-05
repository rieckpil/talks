# Demo 4: MCP servers and the CI loop (8 min)

Where: backup, MCP part (optional).

## Setup
- Servers added: Context7, GitHub (read-only, Actions toolset), Playwright
- A PetClinic fork with a failing workflow run (push a deliberately broken test before the talk)
- Token in the user scope, never in `.mcp.json`

## Steps
1. `claude mcp list` shows the three servers and scopes (local, project, user)
2. GitHub MCP: "Why did the last workflow run fail? Fix it." Agent reads job logs, finds the failing test, proposes a fix
3. Playwright MCP: "Open the app, find the add-owner form and tell me the locators." Show the accessibility tree, link to page objects with `data-testid`
4. Context7: ask for a Spring Boot 4 specific API (`RestTestClient`, `MockMvcTester`) and show the version-correct answer
5. Close with the risks: prompt injection from tool output, tool descriptions cost context, keep the list short

## Fallback
Screenshots of each step in `slides/assets/` (TODO).
