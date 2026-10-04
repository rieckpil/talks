# Block 1: Spring Boot testing in a nutshell (60 min, 09:35 - 10:35)

Goal: everyone shares the same vocabulary and mental map. Not a deep dive. The agent part in block 2 builds on these terms.
Style: content first, no story line. Slides are adapted from `testing-spring-boot-applications-demystified/slides/webinar.md` without the quest and boss narrative. Boot 4 and Java 25.

| Min | Section (deck) | Key message | Slides and visuals |
|---|---|---|---|
| 5 | Intro, shape, naming, phases | Goals as four boxes. Pyramid, honeycomb or trophy: it depends on the project. Decision tree: does the test need a context? Naming: unit test (no context) is `*Test`, everything else `*IT`. Why separate: Surefire vs Failsafe phases (lifecycle image), parallelization configured differently | Goals boxes, shape cards, decision tree, naming boxes, Maven lifecycle image |
| 12 | Toolbox + 01.1 Unit tests | Toolbox first: `spring-boot-starter-test`, dependency tree, Swiss army knife. Then unit tests: no Spring context, and four slides on what a unit test cannot cover (request mapping, validation, serialization, security) | Starter test, dependency tree, Swiss army knife, unit 101, controller example, four limit slides |
| 11 | 01.2 Slice tests | Load only one layer. Slices for each layer | 4 context diagrams, `@WebMvcTest`, common slices, slicing annotations, slice 101 |
| 14 | 01.3 Integration tests | Whole app over HTTP. Six problems to solve, Testcontainers as the answer to problem #1 | Setup diagram, problems 1-6, Testcontainers code and `docker ps`, MockMvc vs `RANDOM_PORT` client, integration 101 |
| 9 | 01.4 Context cache | Every new combination of mocks, properties, profiles = new context = slow. Detect it | Need for speed, caching diagrams 00-02, cache key, hints, logs, Spring Test Profiler, `@DirtiesContext` anti-pattern |
| 7 | 01.5 Speed and quality | Parallelization needs isolated tests. Coverage lies, mutation testing tells | Parallelization (3 slides), coverage, PIT mutation (3 slides) |
| 2 | Boot 4 | What is new in Boot 4 testing | Boot 4 testing improvements |

Sum: 5 + 12 + 11 + 14 + 9 + 7 + 2 = 60.

Transition to the FAQ and break: "That is the fundament. After the break we put an AI agent on top of it."

Cut candidates if late: dependency tree slide, Boot 4 slide, one of the three caching diagrams, mutation testing (keep one slide).

## Notes

- Images copied from the demystified talk into `slides/assets/`: context diagrams, cache diagrams and logs, Spring Test Profiler logo, parallelization SVG, mutation testing image, Northstar images.
- Not reused on purpose: quest and boss artwork, Hero's Journey map, Act slides.
- Code samples use `WebTestClient` as in the original talk. Check the `RestTestClient` API in Spring Boot 4 before the talk and consider swapping it in, since the skills use it.
- Slide code names slice tests `*IT` (`CustomerControllerIT`). The sample repo class is still `CustomerControllerTest` and Failsafe is not configured in its pom yet.
