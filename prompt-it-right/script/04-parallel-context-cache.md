# Block 2, part 3: Best practices - parallelizable and context-cache friendly (15 min, 11:42 - 11:57)

The part with the biggest payoff for build time. Refer back to block 1 (context cache, parallelization).

| Min | Beat | Content |
|---|---|---|
| 1 | Divider + statement | Two properties decide build time: parallel and cache-friendly |
| 1 | Why the agent loop cares | Edit, run, read, edit. A slow suite makes the loop useless |
| 2 | Must have: parallel unit tests | No fields or `@BeforeEach` state, injected `Clock`, no sleeps, concurrent and random order via `junit-platform.properties` |
| 2 | Must have: parallel integration tests | Random business keys, own rows, black box over HTTP on a random port, WireMock on a dynamic port, `@Execution(CONCURRENT)` on the base class, run twice |
| 2 | Testcontainers properly | One `static final` container per image, `@ServiceConnection`, pinned tags, no fixed ports, wait strategy, real database not H2, reuse only locally, pre-pull in CI |
| 2 | Context caching | One abstract base class, no `@MockitoBean` on app beans, no `@DirtiesContext`, context limit enforced by the profiler |
| 3 | Measure and gate | Slide 1: container starts with `docker events` (once per image). Slide 2: context starts with the cache DEBUG log and the Spring Test Profiler JSON. Then the JSON and the pipeline gate (`.github/workflows/prompt-it-right-context-gate.yml`, `prompt-it-right/scripts/check-context-count.sh`) |
| 2 | **Demo 2** | Count the containers (`demos/demo-2-containers.md`) |
