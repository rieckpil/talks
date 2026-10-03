# Part 3b: Fast feedback and pipeline (12 min, 11:50 - 12:02)

Goal: the agent and CI both need a fast, trustworthy signal.

| Min | Beat | Content |
|---|---|---|
| 2 | Two-phase build | Surefire runs `*Test` with no Docker, target under 60 s. Failsafe runs `*IT`. `*E2E` only in an `e2e` profile. Maven snippet |
| 2 | Containers | One static container per image shared by all contexts. Local reuse is opt-in and never committed. Pre-pull pinned images in CI. One JVM fork |
| 3 | Context cache budget | Single-digit contexts (limit 9). Measure with the cache DEBUG log. Spring Test Profiler: `results.json` + `jq` gate in CI, HTML report as artifact |
| 1 | Parallelism | Unit tests concurrent and random order. Slice tests same thread (shared mocks). Integration tests `@Execution(CONCURRENT)` with random data |
| 1 | Agent-side speed | Single-test-class command in the skill CUSTOMIZE table so the agent does not run the full suite. Silence logs (`logback-test.xml`) |
| 3 | **Demo 2** | `docker events` before and after (see `demos/demo-2-containers.md`) |

Slide idea: GitHub Actions workflow with the fast phase first, slow phase second, `jq` gate on context count.

TODO: write the example workflow (the course repo has only a plain `./mvnw -B verify`).
TODO: add real numbers from a project (before/after build times).
