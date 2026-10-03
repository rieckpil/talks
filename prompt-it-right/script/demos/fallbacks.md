# Demo fallbacks

Every live demo has a recording and a decision rule.

| Demo | Recording file | Switch to fallback when |
|---|---|---|
| 1 Review skill | TODO `recordings/demo-1.mp4` | no result after 3 min |
| 2 Containers | TODO `recordings/demo-2.mp4` | Docker not ready or pull needed |
| 3 Feature flow | TODO `recordings/demo-3.mp4` | agent runs longer than 5 min |
| 4 MCP + CI | TODO `recordings/demo-4.mp4` | network or auth problem |

## Known issues

- `PostgresIntegrationTests` fails with "port is already allocated" if something holds port 5432. This is a fixed-port example of rule `[C5]`. Use `-Dtest='!PostgresIntegrationTests'` for a green run (74 tests).
- Conference Wi-Fi: pre-pull Docker images and warm the Maven cache at the hotel. Use a phone hotspot as a second path.
- Agent output differs on every run. Keep a saved report from a good run.

## Rules

- Say it out loud when you switch: "This is a recording of a run from yesterday."
- Keep recordings under 3 min each, sped up where the agent thinks.
- Store recordings in `script/demos/recordings/` (gitignored if large).
