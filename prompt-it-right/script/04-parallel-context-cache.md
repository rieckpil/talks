# Block 2, part 3: Most important - parallelizable and context-cache friendly (15 min, 11:42 - 11:57)

The part with the biggest payoff for build time. Refer back to block 1 (context cache, parallelization).

| Min | Beat | Content |
|---|---|---|
| 1 | Divider + statement | Two properties decide build time: parallel and cache-friendly |
| 2 | Why the agent loop cares | Edit, run, read, edit. Slow suite means the agent runs one test or none |
| 3 | Parallel by construction | Random keys, no shared fields, injected Clock, no sleeps, run twice in random order. Unit concurrent, slice same-thread, integration concurrent with random data |
| 3 | Cache-friendly by construction | One abstract base class, no `@MockitoBean` on app beans, no `@DirtiesContext`, context limit (default 9) |
| 2 | Containers and phases | One static container per image, fast phase (no Docker) and container phase, E2E profile, single-test-class command knob |
| 2 | Measure and gate | `docker events` per image, Spring Test Profiler `jq` check in CI |
| 2 | **Demo 2** | Count the containers (`demos/demo-2-containers.md`) |

Example workflow: `.github/workflows/prompt-it-right-context-gate.yml` (builds `prompt-it-right`, reads `results.json`, fails above `MAX_CONTEXTS`, default 9). Local check: `prompt-it-right/scripts/check-context-count.sh 9`. Slides: "What the Profiler JSON Looks Like" and "Fail the Build Above the Limit".
