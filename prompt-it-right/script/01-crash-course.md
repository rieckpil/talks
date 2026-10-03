# Part 1: Spring Boot testing crash course (60 min, 0:05 - 1:05)

Goal: everyone shares the same vocabulary. Not a deep dive. The agent needs these terms later.
Style: short code slides with `{ranges}`, then one pitfall per topic. Boot 4 and Java 25 first.

| Min | Topic | Key message | Code or visual |
|---|---|---|---|
| 5 | Why test + shape of the suite | Pyramid, honeycomb, trophy: the names do not matter, speed and confidence do | `.pyramid` CSS slide, mini graphic |
| 9 | Unit tests | No Spring context. JUnit 5 + AssertJ + Mockito. One assertion chain, `.as(...)` message, injected `Clock` | `PetTypeFormatter` style example |
| 10 | Slice tests: web | `@WebMvcTest`, `MockMvcTester`, security matrix (anonymous, wrong role, right role), validation 400 | Controller test |
| 6 | Slice tests: data + JSON | `@DataJpaTest` on the real DB via Testcontainers (no H2), `@JsonTest`, `TestEntityManager` flush and clear | Repository test |
| 7 | Testcontainers | `@ServiceConnection`, one static container per image, no fixed ports | `TestcontainersConfiguration` |
| 10 | Integration tests | `@SpringBootTest(RANDOM_PORT)`, `RestTestClient`, WireMock, one abstract base class, black box via HTTP | Journey test |
| 5 | The context cache | Every new combination of `@MockitoBean`, properties, profiles, `@Import` = new context = slow. `@DirtiesContext` is a smell | Cache DEBUG log, `context-caching-logs.png` |
| 3 | E2E (brief) | Few journeys, page objects, run in a separate profile. Cut if late | One slide |
| 5 | What makes a good test suite | Closing beat: five properties - fast, deterministic, isolated, one reason to fail, a message that locates the defect. Block 2 builds on these | "Five properties" slide |

Transition to the FAQ and break: "That is the fundament. After the break we put an AI agent on top of it."

Reuse: `testing-spring-boot-applications-demystified/slides/webinar.md` (quests), `stop-fighting-your-spring-boot-tests/slides/content.md` (context cache), `top-5-.../content.md`.

TODO: pick final code samples. Prefer the PetClinic classes so Part 3 demos connect back.
