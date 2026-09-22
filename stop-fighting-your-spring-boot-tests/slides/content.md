---
marp: true
theme: pragmatech
title: 'Stop Fighting Your Spring Boot Tests'
class: light
paginate: true
transition: pt-fade
header: 'Stop Fighting Your Spring Boot Tests · Software Craft Community @ DATEV · September 22, 2026'
footer: '![](assets/logo.webp) Philip Riecks · [PragmaTech GmbH](https://pragmatech.digital/) · [@rieckpil](https://x.com/rieckpil)'
---

<!-- _paginate: false -->
<!-- _header: '' -->
<!-- _footer: '' -->
<!-- _backgroundColor: '#090d14' -->

![bg fit](assets/datev-coding-craft-2026.png)

---


<!-- _class: light title -->
<!-- _paginate: false -->
<!-- _header: '' -->

![bg left:33%](assets/stop-fighting-your-spring-boot-tests.jpg)

# Stop **Fighting** Your Spring Boot Tests

## Why your tests fight back - and how to make them fight for you 🥊

Software Craft Community @ DATEV · September 22, 2026

---

<!-- _paginate: false -->

![bg right:33%](assets/hza.jpg)

## About Philip

- Self-employed developer from **Herzogenaurach**, living in Erlangen, Germany (close to Nuremberg) 🍻
- Met Sven Hansen during the **Spring I/O** conference in Barcelona this year 🇪🇸
- Blogging & content creation with a focus on **testing Java Spring Boot** applications 🍃
- Founded **PragmaTech GmbH** - We Help Developers to Frequently Deliver Software with More Confidence 🚤

---

## Participate During the Talk

Go to [menti.com](https://www.menti.com/) and use the code **7735 0833** to **anonymously** submit answers:

![center h:250](assets/mentimeter-datev-coding-craft-2026.png)

Please answer the **first three questions**:

- Who wrote your tests last week?
- How long does your test suite take end to end?
- How confident are you deploying on a Friday afternoon (0 to 10)?

---

<!-- header: 'Software Craft Community @ DATEV · September 22, 2026 · Questions @ menti.com Code: <strong>7735 0833</strong>' -->
<!-- _paginate: false -->

![bg right:33%](assets/why-test-software.jpg)

# Why Test Software?

---

<!-- _class: light statement -->

# In 2026, most of our code is **not written by us**.

---

<!--
Notes:
- The joke lands, then the point: we ship code we did not type.
- The test suite is the only thing that still reads every line.
-->

![center h:500](assets/ai-frog-meme.jpg)

---

<!-- _paginate: false -->

![bg right:33%](assets/northstar.jpg)

### My Overall Northstar for Engineering Excellence

Imagine seeing this pull request on a Friday afternoon:

![](assets/northstar-pr.png)

How confident are you to merge this major Spring Boot upgrade and deploy it to production once the pipeline turns green?

---

<!-- _class: light statement -->

# Good tests don't just catch bugs - they give you **fast feedback** and **confident deployments**.

---

<!--
Notes:
- Ask the room which of these they recognize. Hands go up on all four.
- Name the feeling first, then promise the diagnosis.
-->

![bg right:33%](assets/fight-back.jpg)

## When Your **Spring Boot Tests** Fight Back

- The suite takes > 30 minutes, so nobody runs it before pushing
- A test goes red and you cannot tell whether the code or the test is broken
- You reach for `@SpringBootTest` because it is the only thing that works
- The pipeline is green and you are hesitant to deploy

---

<!-- _class: light agenda -->

## The Three Spring Boot Testing Myths We Bust Today

1. "I need @SpringBootTest for that."
2. "Spring Boot tests are slow."
3. "It is green, so it works."

---

<!-- _class: light section -->
<!-- _paginate: false -->

![bg right:40%](assets/pitfalls.jpg)

## Myth #1

# "Every test needs the full context."

_Spoiler: you don't always need `@SpringBootTest`._

---


<!--
Notes:
- One question decides most of it: most tests answer "no" and never need Spring.
- Only the "yes" branch splits again. Annotations come on the next slides.
-->

## Three Ways to Write Tests for Spring Boot applications

![center h:500](assets/test-choice.png)

---

<!--
Notes:
- Ask: who has tried to unit test a @PreAuthorize rule? It always passes.
- The right column is the reason the rest of this section exists.
-->

<!-- _class: light split -->

## Do We Even Need a Context?

<div class="yes">

### A plain unit test is fine

- Business logic: calculations, branching, state transitions
- Validation rules you wrote yourself
- Verify small units of work independently
- Usually the core parts of your application

</div>

<div class="no">

### But it can never tell you

- Does `@PreAuthorize` actually **block** that caller?
- Does `GET /api/customers/{id}` **map** to this method?
- Does that JPQL return what you think against **real SQL**?
- Does an invalid body give **400**, not an accidental 201?

</div>


---

## A Typical Spring `ApplicationContext`

![center h:500](assets/spring-context.png)

---

## You Don't Always Need the Entire Context

![center h:500](assets/spring-sliced-context.png)

---

<!--
Notes:
- Both start a context. The question is only how much of one you pay for.
- Land the last line: the slice is the default, the full context is the exception.
-->

<!-- _class: light split -->

## Sliced or Full Context?

<div class="yes">

### Slice it when

- You verify **one layer's contract** with Spring
- The collaborators behind it can be **replaced** with `@MockitoBean`
- You want framework behaviour **without paying** for the whole application
- Annotations of choice: `@WebMvcTest`, `@DataJpaTest`, `@JsonTest`, etc.

</div>

<div class="warn">

### Take the full context when

- One flow **crosses several layers** end to end
- You need everything wired **as in production**
- Verification of entire user journeys
- Annotation of choice: `@SpringBootTest`

</div>

---

<!--
Notes:
- Looks fine. Passes. Green. Everybody copies it.
- Ask the room: what does this test actually need?
-->

## The `@SpringBootTest` Obsession

![](assets/spring-boot-test-obsession-masked.png)



---

## Decision Paralysis: When to Include a Context

A simplified decision table:

| Question                                        | Tool                                             |
|-------------------------------------------------|--------------------------------------------------|
| Does my business logic work?                    | Plain JUnit + Mockito, no Spring                 |
| Does my HTTP layer map, validate, serialize?    | `@WebMvcTest`                                    |
| Does my query return what I think?              | `@DataJpaTest`                                   |
| Does my client talk to the remote API?          | `@RestClientTest` (including a mock HTTP server) |
| Does the whole thing start and work end to end? | `@SpringBootTest`                                |

---

<!-- _class: light statement -->

# Strategy: Pick the test type that gives you the **most confidence** in your safety net for the **cheapest execution time**.

---

<!-- _class: light section -->
<!-- _paginate: false -->

![bg right:40%](assets/speed.jpg)

## Myth #2

# "Spring Boot tests are slow."

_You are not paying for tests. You are paying for context starts._

---

<!--
Notes:
- Reframe: nobody complains about a slow test. They complain about slow feedback.
- Four angles, and we have already used the first one in part 1.
-->

## Slow Tests Are Not the Problem. Slow **Feedback** Is.

Attack it from four angles:

1. **Right test level** - the cheapest test that answers the question (Myth #1)
2. **Fewer context starts** - Spring Test's hidden gem
3. **Parallel execution** - use the cores you are paying for

---

## Angle 2: Spring Context Caching (Spring Test's Hidden Gem)

* **The problem**: integration tests need a started and initialized `ApplicationContext`, which takes time
* **The solution**: the Spring TestContext framework caches a started context for later reuse
* Part of every Spring Boot project already, via `spring-boot-starter-test`

* Speed improvement example:

  ![](assets/context-cache-improvements.png)

---

<!--
Notes:
- Animated GIF: loops on its own, ~19s per cycle. Let it run one full loop.
- Beat 1 OrderIT misses and pays 3506 ms. Beat 2 PaymentIT hits, 406 ms.
- Beat 3 CheckoutIT has one different key, so it pays full price again.
- In the exported PDF this shows the first frame only (empty cache).
-->

## Context Caching in Action

![center h:470](assets/context-caching.gif)

---

## How the Cache Key is Built

```java
// DefaultContextCache.java
private final Map<MergedContextConfiguration, ApplicationContext> contextMap =
  Collections.synchronizedMap(new LruCache(32, 0.75f));
```

This goes into the cache key (`MergedContextConfiguration`):

- activeProfiles (`@ActiveProfiles`)
- propertySourceProperties (`@TestPropertySource`)
- contextCustomizer (`@MockitoBean`, `@MockBean`, `@DynamicPropertySource`, ...)
- etc.

**Every unique combination is a new context start.**

---

## Detect Context Restarts - Visually

![](assets/context-caching-hints.png)

---

## Detect Context Restarts - with Logs

![](assets/context-caching-logs.png)

---

## Detect Context Restarts - with Tooling

![center](assets/spring-test-profiler-logo.png)

An [open-source Spring Test utility](https://github.com/PragmaTech-GmbH/spring-test-profiler) that provides visualization and insights for Spring Test execution, with a focus on Spring context caching statistics.

**Overall goal**: Identify optimization opportunities in your Spring Test suite to speed up your builds and ship to production faster and with more confidence.

---

## Angle 3: Test Parallelization

**Goal**: use the cores you already pay for.

Requirements:

- No shared state
- No dependency between tests or their execution order
- No mutation of global state

Two ways to get there:

- Fork a new JVM with Maven/Gradle - more resources, fully isolated
- Use JUnit Jupiter's parallel execution mode - same JVM, multiple threads

---

![bg w:800 h:900 center](assets/parallel-testing.svg)

---

## Make the Most of Both

- Understand how the cache key is built before you add another annotation
- Avoid `@DirtiesContext`, especially in central base classes
- Monitor and investigate context restarts instead of guessing
- Align the number of unique context configurations across the suite
- Turn on parallel test execution, start selectively

---

<!-- _class: light section -->
<!-- _paginate: false -->

![bg right:40%](assets/prod-example.jpg)

## Myth #3

# "It is green, so it works."

_Green proves your tests ran. Not that they would have caught anything._

---

## Where Tests Drift Away From Production

- **The database**: H2 in tests, PostgreSQL in production. Different SQL dialect, different constraints, different behaviour.
- **Remote services**: mocked away with Mockito, so the HTTP layer, the serialization and the error handling are never exercised.
- **The test itself**: it asserts that the code ran, not that the code is right.

Each gap is a place where a green build still ships a bug.

---

## Must-Have #1: Testcontainers (Real Infrastructure)

**Use case**: your test needs the real database, broker or cache - not an in-memory stand-in.

```java
@Container // <-- Testcontainers manages the lifecycle of the container
@ServiceConnection // <-- automatically configures Spring Boot datasource properties
static PostgreSQLContainer postgres = new PostgreSQLContaine("postgres:16-alpine")
  .withDatabaseName("testdb")
  .withInitScript("init-postgres-users.sql");
```

Same engine, same version, same dialect as production.

---

## Keep Testcontainers Fast

- Enable container reuse where possible: `.withReuse(true)`
- Prefer a singleton container per test run over `@Testcontainers` (which starts one per test class)
- Speed up startup with prebuilt images that already contain your schema and seed data

```java
private static PostgreSQLContainer postgres =
  new PostgreSQLContainer("myteampostgres:42");

static {
  postgres.start(); // started once, shared by every test that needs it
}
```

---

## Must-Have #2: WireMock (Real HTTP)

**Use case**: your application calls a remote API. You want to exercise your own HTTP client, serialization and error handling - without depending on someone else's uptime.

Mocking the client with Mockito skips exactly the part that breaks in production:

- Wrong URL or query parameter
- Unexpected response shape
- Timeouts, 500s, and retry behaviour

---

![bg w:900 center](assets/wiremock-usage.svg)

---

## Be Aware of: Watermelon Tests

... green on the outside, red on the inside.

- **100% coverage** and still broken: coverage measures which lines *ran*, not which behavior was *verified*
- A test that asserts the mock works as previously instructed
- Auto-generated tests can give you the **feeling** of safety
- Agents produce these at scale: plausible names, green checks, zero judgment

---

## Let's Challenge Code Coverage

Imagine a set of unit tests for this isolated business logic:

```java
public Long registerUser(int age, String username) {

  if (age <= 18) {
    throw new IllegalArgumentException("User must be at least 18 years old");
  }

  if ("ADMIN".equalsIgnoreCase(username)) {
    throw new IllegalArgumentException("Username 'ADMIN' is not allowed");
  }

  // ...

}
```

---

## Idea: Introduce Regressions to Verify Test Quality

... with the help of PIT:

![center](assets/mutation-testing-explained-corrected.png)

---


<!-- _class: light agenda -->

## The Three Myths, Busted

1. Most tests need no Spring context - climb only when forced to
2. Your suite is not slow, it is restarting contexts - cache, align, parallelize
3. Green is not proof - real infrastructure, real HTTP, meaningful assertions

---

<!-- _class: light metrics -->

## Upcoming Talk at the DATEV Coding Festival

_Ship Fast, Sleep Well: Fearless Spring Boot Deployments in the AI Era_ - my next talk at the **DATEV Coding Festival**.

We pick up where today stops: shipping the code an agent wrote, without losing sleep over it. Come and join:

- **Oct 6** 2026
- **14:30** to 15:15
- **DATEV** Coding Festival


---


## What I Do to Build Confidence in the Agentic Coding Era

- **Review generated tests** like a pull request from a new hire
- **Make the rules executable**: ArchUnit rules, testing conventions in `CLAUDE.md` / `AGENTS.md`, mutation score thresholds in CI
- **Custom skills**: define once per project how testing is tackled, with best practices and antipatterns
- **Keep the feedback loop tight**: fast, trustworthy tests let the agent iterate without me watching every step

---

## My Agentic Testing Setup for Spring Boot

Define a **skillset** for fast and comprehensive tests, including a **test strategy** for the given project:

```text
.claude/skills/
├── unit-testing            fast tests without context bloat
├── slice-testing           right-sized Spring context slices
├── slice-testing-webmvc    web layer with @WebMvcTest
├── integration-testing     full-context tests that stay fast
├── e2e-testing             user journeys against the running app
├── testcontainers-setup    real infrastructure, reused containers
└── test-setup-reviewer     flags test anti-patterns for you
```

Each skill carries rules, references, best practices and antipatterns, described as code and text.

---


## Get Notified

I am building an **agentic Spring Boot testing course**: how to make code agents produce tests you would have written yourself.

![h:340 center](assets/agentic-testing-course.png)

See the QR code on the slide for more information.

---


<!-- _class: light statement reveal -->
<!-- _paginate: false -->

<!--
Notes:
- One line per click (fragmented list, HTML deck only).
- The agent can write the code and the tests, but the pager still rings for you.
-->

# You can delegate the typing.
* You can't delegate the ownership.
* **You** get paged at 3 AM.
* Invest in a test suite that gives you **confidence in every commit**.


---

<!-- _class: light closing -->
<!-- _paginate: false -->
<!-- _header: '' -->

![bg right:33%](assets/end.jpg)

# Joyful Testing!

Get notified for Agentic Testing for Spring Boot:

![center h:300](assets/agentic-testing-course-qr.png)

- [LinkedIn](https://www.linkedin.com/in/rieckpil) (Philip Riecks)
- [Mail](mailto:philip@pragmatech.digital) (philip@pragmatech.digital)
