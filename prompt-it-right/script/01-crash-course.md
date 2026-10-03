# Block 1: Spring Boot testing in a nutshell (60 min, 09:35 - 10:35)

Goal: everyone shares the same vocabulary and mental map. Not a deep dive. The agent part in block 2 builds on these terms.
Style: content first, no story line. Slides are adapted from `testing-spring-boot-applications-demystified/slides/webinar.md` without the quest and boss narrative. Boot 4 and Java 25.

| Min | Section (deck) | Key message | Slides and visuals |
|---|---|---|---|
| 5 | Intro + shape of the suite | Fast feedback and confident deploys. Pick the cheapest test that proves the behavior | Statement, Northstar (Friday PR) reveal, goals, statement, pyramid (CSS, 4 layers) |
| 10 | 01.1 Unit tests | No Spring context. Know the tools in `spring-boot-starter-test` and what a unit test cannot cover | Starter test, dependency tree, Swiss army knife, unit 101, controller example, "can't cover" reveal (mapping, validation, serialization, security) |
| 10 | 01.2 Slice tests | Load only one layer. Real DB for the persistence slice | 4 context diagrams, `@WebMvcTest`, common slices, slicing annotations, `@DataJpaTest` on Testcontainers, slice 101 |
| 5 | 01.3 Testcontainers | Real infrastructure, `@ServiceConnection` | Container code, `docker ps` |
| 9 | 01.4 Integration tests | Whole app over HTTP. Six problems to solve | Setup diagram, problems 1-6, MockMvc vs `RANDOM_PORT` client, integration 101 |
| 9 | 01.5 Context cache | Every new combination of mocks, properties, profiles = new context = slow. Detect it | Need for speed, caching diagrams 00-02, cache key, hints, logs, Spring Test Profiler, `@DirtiesContext` anti-pattern |
| 4 | 01.6 Speed and quality | Parallelization needs isolated tests. Coverage lies, mutation testing tells | Parallelization (3 slides), coverage, PIT mutation (3 slides) |
| 3 | E2E + Boot 4 | Few journeys, separate profile. What is new in Boot 4 | E2E in one slide, Boot 4 testing improvements |
| 5 | What makes a good test suite | Closing beat: fast, deterministic, isolated, one reason to fail, a message that locates the defect | "Five properties" reveal (block 2 builds on it) |

Sum: 5 + 10 + 10 + 5 + 9 + 9 + 4 + 3 + 5 = 60.

Transition to the FAQ and break: "That is the fundament. After the break we put an AI agent on top of it."

Cut candidates if late: E2E slide, dependency tree slide, Boot 4 slide, one of the three caching diagrams, mutation testing (keep one slide).

## Notes

- Images copied from the demystified talk into `slides/assets/`: context diagrams, cache diagrams and logs, Spring Test Profiler logo, parallelization SVG, mutation testing image, Northstar images.
- Not reused on purpose: quest and boss artwork, Hero's Journey map, Act slides.
- Code samples use `WebTestClient` as in the original talk. Check the `RestTestClient` API in Spring Boot 4 before the talk and consider swapping it in, since the skills use it.
- TODO: confirm the `@DataJpaTest` slide imports for Boot 4 (the `@AutoConfigureTestDatabase` package moved in Boot 4).
