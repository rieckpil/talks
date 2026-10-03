# FAQ block 1: Spring Boot testing in a nutshell (10 min, 10:35 - 10:45)

Read the comments on the Devoxx app talk page and take live questions. If the room is quiet, use these prepared ones.

| Question | Short answer |
|---|---|
| Do I still need unit tests if I have integration tests with Testcontainers? | Yes. Unit tests give the fastest feedback and pin down logic. Integration tests prove the wiring. Pick the cheapest test that proves the behavior |
| Why not H2 for repository tests? | Different SQL dialect and behavior than production. Use the real database in a container (`@ServiceConnection`) |
| `@MockitoBean` or a fake? | Mocks create a new context each combination. Prefer real beans or fakes in integration tests, mocks in slices and unit tests |
| How many Spring contexts are OK? | Single digit. Measure with the cache DEBUG log. Avoid `@DirtiesContext` |
| Can I run tests in parallel? | Unit tests yes. Slice tests share mocks in a cached context, keep them same-thread. Integration tests with random data yes |
| What coverage number should I target? | Coverage shows what is not tested, not that tests are good. Mutation testing is the better signal |
| Is `@SpringBootTest` bad? | No. Use it for few journeys, not for everything |
| What about Kotlin, Gradle, JUnit 6? | Same concepts. Show your own setup if asked |
| Testcontainers needs Docker on CI: any tip? | Pre-pull pinned images, one container per image, never fixed ports |

Parking lot for agent questions: "We cover this in block 2 after the break."
