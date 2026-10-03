---
marp: true
theme: pragmatech
title: 'Prompt It Right: Spring Boot Testing in the AI Era'
class: light
paginate: true
transition: pt-fade
header: 'Prompt It Right @ Devoxx Belgium 2026 - Questions @ menti.com Code: <strong>7108 0067</strong>'
footer: '![](assets/logo.webp) Philip Riecks · [@rieckpil](https://x.com/rieckpil) · [PragmaTech GmbH](https://pragmatech.digital/)'
---

<!--
Menti: https://www.menti.com/ - code 7108 0067
Timeline: see ../TALK-PLAN.md. Search TODO to find open slides.
-->

<!-- _paginate: false -->
<!-- _header: '' -->
<!-- _footer: '' -->

<!--
Notes:
- Opener: Antwerp, the city of this talk. Warm welcome before the title slide.
-->

![bg](assets/antwerp.jpg)

---

<!-- _class: light title -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Prompt It Right: **Spring Boot Testing** in the AI Era

## Your agent writes the code. Your test suite decides if it ships.

Devoxx Belgium 2026 · Monday, October 5, 2026 · 09:30

<!--
Notes:
- Welcome. 180 minutes, one break at about 1h15.
-->

---

<!--
Notes:
- This is the session as listed on the Devoxx schedule. Point at the BEGINNER badge.
- Say it: I marked this as beginner on purpose. We cover the fundamentals first, so everyone can follow the agent part.
-->

![center w:1100](assets/talk-overview.png)

---

### About Philip

- Software Engineer from Erlangen, Germany 🍻
- Blogging and content creation about testing Java and Spring Boot applications 🍃
- Founder of [PragmaTech GmbH](https://pragmatech.digital/) - **Enabling Developers to Frequently Deliver** Software with **More Confidence**
- Using coding agents daily for Spring Boot projects

<!--
Notes:
- TODO add assets/location.png as bg right panel: ![bg right:33%](assets/location.png)
-->

---

## Participate During the Talk

![bg right:36% h:420](assets/mentimeter-qr-devoxx-be-2026-padded.png)

Go to [menti.com](https://www.menti.com/) and use the code **7108 0067** to **anonymously** submit answers for the polls during the talk.

- How much of your code is AI-written today?
- How long does your test suite take?
- Do you trust a green build?
---

<!-- _header: 'How to Ask Questions - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## Questions? Three Ways to Ask

![bg right:34% h:420](assets/devoxx-questions-qr.png)

1. **Live, during the talk** - raise your hand and interrupt me, it is a beginner-friendly session
2. **Dedicated FAQ** - 10 minutes at the end of each block
3. **Devoxx app comments** - scan the QR code, save your question in the comment section of the talk, and I pick up the top ones in the FAQ

<!--
Notes:
- Say it early: interrupting is welcome. Comments in the Devoxx app are the parking lot for questions that come up while you talk.
- Check the comments before each FAQ (10:35 and 12:20), sort by what fits the block.
-->

---

<!-- _class: light -->

## Today's Deep Dive: 180 Minutes, Two Blocks

<style scoped>
.stack { display: flex; flex-direction: column; align-items: center; gap: 14px; margin-top: 0.4em; }
.stack .sketch { box-sizing: border-box; }
.stack .top   { width: 62%; font-size: 1.15em; padding: 0.8em 1em; }
.stack .pause { width: 34%; font-size: 1em; border-style: dashed; transform: rotate(0.3deg); }
.stack .base  { width: 100%; font-size: 1.3em; border-width: 5px; padding: 1.1em 1em; }
.stack small  { font-size: 0.7em; }
</style>

<div class="stack">
  <div class="sketch accent alt top">
    <strong>2 · Agentic Development and Testing</strong>
    <small>11:15 - 12:30 · goal · skills · parallel · MCPs · practices · FAQ</small>
  </div>
  <div class="sketch pause">☕ Break · 10:45 - 11:15</div>
  <div class="sketch accent base">
    <strong>1 · Spring Boot Testing in a Nutshell</strong>
    <small>09:30 - 10:45 · unit · slice · integration · Testcontainers · context cache · FAQ</small>
  </div>
</div>

<!--
Notes:
- Read it from the bottom up: the fundament comes first, the agent part is built on top of it.
- Block 1 (75 min): get a shared understanding of what makes a good test suite. You need these concepts to judge what an agent produces.
- Break (30 min), then block 2 (75 min): agentic development and testing.
- Beginner level is on purpose: without the fundament the agent part does not hold.
-->

---

<!-- _class: light statement -->

# You will leave with **ideas you can use on Monday**, not a sales pitch.

<!--
Notes:
- Say it: everything shown works without buying anything. One pointer at the end.
-->

---

<!-- _class: light section -->
<!-- header: 'Block 1: Spring Boot Testing in a Nutshell - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

![bg right:33%](assets/m1-welcome.jpg)

## 01 - Spring Boot Testing in a Nutshell

# Know your toolbox

<!--
Notes:
- Block 1 is the fundament: 60 minutes, five topics. Beginner level on purpose.
-->

---

<!-- _class: light statement -->

# Good tests don't just catch bugs - they give you **fast feedback** and **confident deployments**.

---

<!-- _class: light reveal -->

<!--
Notes:
- Revealed step by step (fragmented list, works in the HTML deck only): headline and image first, then the pull request, then the question.
- PDF and PNG exports show everything at once.
-->

![bg right:33%](assets/northstar.jpg)

### My Northstar for Engineering Excellence

* Imagine seeing this pull request on a Friday afternoon:

  ![](assets/northstar-pr.png)

* How **confident** are you to merge this major Spring Boot upgrade and **deploy** it to production once the pipeline turns green?

---

### Goals for Block 1

- **Provide a clear mental map** for choosing between unit, slice, and integration tests
- Get **better judgment** for AI-written tests
- Understand the **testing concepts** to better reason about a test strategy or test failures
- **Build confidence** into your development work to **ship fearlessly**

---

<!-- _class: light statement -->

# Pick the **cheapest test** that proves the behavior.

---

<style scoped>
.pyramid { width: 78%; margin: 0.2em auto 0; }
.pyramid .e2e   { width: 30%; }
.pyramid .int   { width: 52%; }
.pyramid .slice { width: 72%; }
.pyramid .unit  { width: 92%; }
.pyramid .layer { font-size: 0.85em; }
</style>

## The Shape of Your Test Suite

<div class="pyramid">
  <div class="layer e2e">E2E<small>few · slow · expensive</small></div>
  <div class="layer int">Integration<small>whole application · real infrastructure</small></div>
  <div class="layer slice">Slice<small>one layer of the application context</small></div>
  <div class="layer unit">Unit<small>many · fast · cheap</small></div>
</div>

<!--
Notes:
- Pyramid, honeycomb, trophy: the names do not matter. Speed and confidence matter.
- Every level has a job. The skill is choosing the cheapest level that proves the behavior.
-->

---

<!-- _class: light section -->

## 01.1 - Unit tests

# No Spring. No excuses.

---

## Our Foundation: Spring Boot Starter Test

<!--
Notes:
- Show the `spring-boot-starter-test` dependency and the Maven dependency tree.
- Tips: favor JUnit over JUnit 4. Pick one assertion library or at least do not mix them within the same test class.
-->

![bg right:33%](assets/swiss.jpg)

- The "Testing Swiss Army Knife"

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>
```

- Batteries-included for testing by transitively including popular testing libraries
- Out-of-the-box dependency management to ensure compatibility

---

```shell {4-6,13,15-16,17,25,29}
./mvnw dependency:tree
[INFO] ...
[INFO] +- org.springframework.boot:spring-boot-starter-test:jar:4.0.2:test
[INFO] |  +- org.springframework.boot:spring-boot-test:jar:4.0.2:test
[INFO] |  +- org.springframework.boot:spring-boot-test-autoconfigure:jar:4.0.2:test
[INFO] |  +- com.jayway.jsonpath:json-path:jar:2.10.0:test
[INFO] |  |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] |  +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.4:test
[INFO] |  |  \- jakarta.activation:jakarta.activation-api:jar:2.1.4:test
[INFO] |  +- net.minidev:json-smart:jar:2.6.0:test
[INFO] |  |  \- net.minidev:accessors-smart:jar:2.6.0:test
[INFO] |  |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO] |  +- org.assertj:assertj-core:jar:3.27.6:test
[INFO] |  |  \- net.bytebuddy:byte-buddy:jar:1.17.8:test
[INFO] |  +- org.awaitility:awaitility:jar:4.3.0:test
[INFO] |  +- org.hamcrest:hamcrest:jar:3.0:test
[INFO] |  +- org.junit.jupiter:junit-jupiter:jar:6.0.2:test
[INFO] |  |  +- org.junit.jupiter:junit-jupiter-api:jar:6.0.2:test
[INFO] |  |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO] |  |  |  +- org.junit.platform:junit-platform-commons:jar:6.0.2:test
[INFO] |  |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO] |  |  +- org.junit.jupiter:junit-jupiter-params:jar:6.0.2:test
[INFO] |  |  \- org.junit.jupiter:junit-jupiter-engine:jar:6.0.2:test
[INFO] |  |     \- org.junit.platform:junit-platform-engine:jar:6.0.2:test
[INFO] |  +- org.mockito:mockito-core:jar:5.5.0:test
[INFO] |  |  +- net.bytebuddy:byte-buddy-agent:jar:1.17.8:test
[INFO] |  |  \- org.objenesis:objenesis:jar:3.3:test
[INFO] |  +- org.mockito:mockito-junit-jupiter:jar:5.5.0:test
[INFO] |  +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO] |  |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO] |  +- org.springframework:spring-core:jar:7.0.3:compile
[INFO] |  |  +- commons-logging:commons-logging:jar:1.3.5:compile
[INFO] |  |  \- org.jspecify:jspecify:jar:1.0.0:compile
[INFO] |  +- org.springframework:spring-test:jar:7.0.3:test
[INFO] |  \- org.xmlunit:xmlunit-core:jar:2.10.4:test
```

---

## What's Inside the Testing Swiss Army Knife?

- **JUnit**: Java's de-facto standard testing framework and foundation.
- **Mockito**: Creating mock objects to simulate dependencies and verify interactions.
- **AssertJ**: Provides fluent, chainable, and readable assertions.
- **Hamcrest**: Offers flexible matchers for creating custom assertions.
- **JSONAssert**: Compares JSON strings with flexible matching options.
- **JsonPath**: Extracts and queries data from JSON similar to XPath.
- **XMLUnit**: Compares and validates XML documents.
- **Awaitility**: Handles asynchronous testing with fluent conditions.

---

<!--
Notes:
- One question decides most of it: most tests answer "no" and never need Spring.
- Only the "yes" branch splits again. Annotations come on the next slides.
-->

## Three Ways to Write Tests for Spring Boot Applications

![center h:500](assets/test-choice.png)

---

## Unit Testing Java/Spring Boot Applications 101

- **Core Concept**: Test individual components in isolation from dependencies - one unit of work at a time.
- **Confidence Gained**: Fast, high-volume verification that the smallest building blocks behave correctly under various conditions.
- **Pitfall**: Poor class design leads to untestable god classes. Good tests start with good design.
- **Tools**: JUnit, Mockito, AssertJ (or Spock, TestNG, Hamcrest).

---

## Unit Testing has Limits

Consider this sample REST controller, what could we verify with a unit test?

```java
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public ResponseEntity<Void> createNewCustomer(@Validated CustomerCreationRequest payload, UriComponentsBuilder uriBuilder) {

    String customerId = customerService.createNewCustomer(payload.firstName());

    UriComponents uriComponents = uriBuilder
      .path("/api/customers/{id}")
      .buildAndExpand(customerId);

    return ResponseEntity.created(uriComponents.toUri()).build();
  }
}
```

---

```java {1,7,12,13,15-18}
@ExtendWith(MockitoExtension.class)
class CustomerControllerUnitTests {

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private CustomerController customerController;

  @Test
  void shouldCreateCustomerWhenPayloadRequestIsValid() {
    when(customerService.createNewCustomer(anyString()))
      .thenReturn("42");

    ResponseEntity<Void> result = customerController.createNewCustomer(
      new CustomerCreationRequest("Java", "Duke", "duke@spring.io"),
      UriComponentsBuilder.newInstance()
    );

    assertThat(result.getStatusCode().value())
      .isEqualTo(201);
    assertThat(result.getHeaders().getLocation().toString())
      .isEqualTo("/api/customers/42");
  }
}
```

---

<!-- _class: light reveal -->

## What a Unit Test Can't Cover

* **Request mapping**: Does HTTP GET `/api/customers/{id}` actually resolve to our desired method?
* **Validation**: Will an incomplete request body result in a 400 bad request or return an accidental 201?
* **Serialization**: Are our JSON objects serialized and deserialized correctly?
* **Security**: Are our Spring Security configuration and other authorization checks enforced?

<!--
Notes:
- Every item needs the framework in the loop. This is where slice tests come in.
-->

---

<!-- _class: light section -->

![bg right:33%](assets/m2-past.jpg)

## 01.2 - Slice tests

# Test one layer

---

![center h:600 w:700](assets/typical-context.png)

---

![center h:600 w:700](assets/typical-context-colored.png)

---

![center h:500 w:600](assets/typical-context-sliced.png)

---

![](assets/typical-context-webmvctest-example.png)

---

### Spring Boot Test Slice Example: `@WebMvcTest`

```java {1,12,6}
@WebMvcTest(CustomerController.class)
@Import(SecurityConfig.class)
class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CustomerService customerService;

  @Test
  @WithMockUser
  void shouldReturnLocationOfNewlyCreatedCustomer() throws Exception {
    // ...
  }
}
```

---

## Common Test Slices

- `@WebMvcTest`/`@WebFluxTest` - Controller layer
- `@DataJpaTest`/`@JdbcTest` - Persistence layer
- `@JsonTest` - JSON serialization/deserialization
- `@RestClientTest` - RestTemplate testing
- etc.

---

![center](assets/slicing-annotations.png)

---

### Persistence Slice: `@DataJpaTest` on the Real Database

```java {1,2,3}
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void shouldFindCustomerByEmail() {
    // ...
  }
}
```

- Do not test against an embedded database when production runs on another one
- Same SQL dialect, same constraints, same behavior - start the real one with Testcontainers

---

## Sliced Testing Spring Boot Applications 101

- **Core Concept**: Test a specific "slice" or layer of your application by loading a minimal, relevant part of the Spring `ApplicationContext`.
- **Confidence Gained**: Helps validate parts of your application where pure unit testing is insufficient, like the web, messaging, or data layer.
- **Prominent Examples:** Web layer (`@WebMvcTest`) and database layer (`@DataJpaTest`)
- **Pitfalls**: Requires careful configuration to ensure only the necessary slice of the context is loaded.
- **Tools**: JUnit, Mockito, Spring Test, Spring Boot, Testcontainers

---

<!-- _class: light section -->

## 01.3 - Testcontainers

# Real infrastructure, no mocks

---

## Provide External Infrastructure with Testcontainers

Running infrastructure components (databases, message brokers, etc.) in Docker containers for our tests becomes a breeze with [Testcontainers](https://testcontainers.com/):

```java
@Container
@ServiceConnection
static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine")
  .withDatabaseName("testdb")
  .withUsername("test")
  .withPassword("test")
  .withInitScript("init-postgres.sql");
```

This gives us an ephemeral PostgreSQL database for our tests:

```shell {3}
$ docker ps
CONTAINER ID   IMAGE                        STATUS         PORTS
a958ee2887c6   postgres:16-alpine           Up 9 seconds   0.0.0.0:32776->5432/tcp
ad0f804068dc   testcontainers/ryuk:0.12.0   Up 9 seconds   0.0.0.0:32775->8080/tcp
```

<!--
Notes:
- Ask who is using Testcontainers.
- `@ServiceConnection` wires the container into the Spring Boot properties, no `@DynamicPropertySource` needed.
-->

---

<!-- _class: light section -->

## 01.4 - Integration tests

# The whole app, over HTTP

---

![](assets/spring-boot-test-setup.png)

---

## Challenges when Starting the Entire `ApplicationContext`

- **Problem #1**: How to ensure surrounding infrastructure (e.g. database, queues, etc.) is present?
- **Problem #2**: How to interact with our application for integration tests?
- **Problem #3**: How to keep our build time at a reasonable duration?

---

## There's Even More...

- **Problem #4**: How to handle HTTP communication from our application to remote services?
- **Problem #5**: How to provide test data and maintain a clean state between tests?
- **Problem #6**: How to handle authentication and security contexts during tests?

---

## How to Interact with our Application for Integration Tests?

Option #1:

```java {1,3}
@SpringBootTest
// which is @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ApplicationMockWebIT {

  // @LocalServerPort
  // private int port; <-- this would fail the test, there is no local port occupied

  @Test
  @WithMockUser
  void givenCustomersThenReturnListForAuthenticatedUser(@Autowired MockMvc mockMvc) throws Exception {
    mockMvc
      .perform(get("/api/customers")
        .header(ACCEPT, APPLICATION_JSON))
      .andExpect(status().is(200))
      .andExpect(content().contentType(APPLICATION_JSON))
      .andExpect(jsonPath("$.size()", is(1)));
  }
}
```

---

## How to Interact with our Application for Integration Tests?

Option #2:

```java {1,2}
@AutoConfigureWebTestClient // required since Spring Boot 4
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationServletContainerIT {

  @Autowired
  WebTestClient webTestClient;

  @Test
  void contextLoads() {
    webTestClient
      .get()
      .uri("/api/customers")
      .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("user:dummy".getBytes()))
      .exchange()
      .expectStatus()
      .isOk();
  }
}
```

---

## Integration Testing Spring Boot Applications 101

- **Core Concept**: Start the entire Spring application context, often on a random local port, and test the application through its external interfaces (e.g., REST API).
- **Confidence Gained**: Validates the integration of all internal components working together as a complete application.
- **Best Practices**: Use `@SpringBootTest` to run the app on a local port.
- **Pitfalls**: Slower to run than unit or sliced tests. Managing the lifecycle of dependent services can be complex.
- **Tools**: JUnit, Mockito, Spring Test, Spring Boot, Testcontainers, WireMock (for mocking external HTTP services), Selenium (for browser-based UI testing)

---

<!-- _class: light statement -->

# ... but what about **Problem #3**: How to keep our build time at a reasonable duration?

---

<!-- _class: light section -->

![bg right:33%](assets/m4-feature.jpg)

## 01.5 - The context cache

# The silent speed killer

---

<!-- _class: light reveal -->

## Integration Testing - The Need for Speed

* **The Problem:** Integration tests require a started and initialized Spring `ApplicationContext`, which slows down the build
* **The Solution:** Spring Test `TestContext` caching - stores an already started Spring `ApplicationContext` for later reuse
* This feature is part of Spring Test (included in every Spring Boot project via `spring-boot-starter-test`)
* Example of speed improvement:

  ![](assets/context-cache-improvements.png)

---

![](assets/caching-explained-00.png)

---

![](assets/caching-explained-01.png)

---

![](assets/caching-explained-02.png)

---

### How the Cache Key is Built

```java
// DefaultContextCache.java
private final Map<MergedContextConfiguration, ApplicationContext> contextMap =
  Collections.synchronizedMap(new LinkedHashMap<>(32, 0.75f, true));
```

The following information is part of the cache key (`MergedContextConfiguration`):

- activeProfiles (`@ActiveProfiles`)
- contextInitializersClasses (`@ContextConfiguration`)
- propertySourceLocations (`@TestPropertySource`)
- propertySourceProperties (`@TestPropertySource`)
- contextCustomizer (`@MockitoBean`, `@MockBean`, `@DynamicPropertySource`, ...)
- etc.

---

```text
Test class
    │
    ▼
MergedContextConfiguration(
  testClass, locations, classes,
  activeProfiles, propertyValues,
  contextInitializers, contextCustomizers   ← every @MockitoBean lands here
  ... etc.
)
    │
    ▼  hashCode() / equals()

Cache hit? → reuse context ✅
Cache miss? → start new context and store it 🆕
```

---

### Detect Context Restarts - Visually

![](assets/context-caching-hints.png)

---

### Detect Context Restarts - with Logs

![](assets/context-caching-logs.png)

---

### Detect Context Restarts - with Tooling

![center](assets/spring-test-profiler-logo.png)

An [open-source Spring Test utility](https://github.com/PragmaTech-GmbH/spring-test-profiler) that provides visualization and insights for Spring Test execution, with a focus on Spring context caching statistics.

**Overall goal**: Identify optimization opportunities in your Spring Test suite to speed up your builds and ship to production faster and with more confidence.

---

### A Common Anti-Pattern: `@DirtiesContext`

Developers tend to consult AI/Stack Overflow for integration test issues and often copy advice from the internet without knowing the implications:

```java
@SpringBootTest
@DirtiesContext
// this instructs Spring to remove the context from the cache
// and rebuild a new context on every request
public abstract class AbstractIntegrationTest {

}
```

The setup above will **disable** the context caching feature and slow down the builds significantly!

---

<!-- _class: light section -->

## 01.6 - Speed and quality

# Run faster, test better

---

## Test Parallelization

**Goal**: Reduce build time and get faster feedback

Requirements:
- No shared state
- No dependency between tests and their execution order
- No mutation of global state

Two ways to achieve this:
- Fork a new JVM with Surefire/Failsafe (or for the Gradle test task) and let it run in parallel
- Use JUnit Jupiter's parallelization mode and let it run in the same JVM with multiple threads

---

![bg w:800 h:900 center](assets/parallel-testing.svg)

---

## Test Parallelization 101

Using Surefire/Failsafe:

```xml
<plugin>
  <artifactId>maven-surefire-plugin</artifactId>
  <configuration>
    <forkCount>1C</forkCount> <!-- 1 JVM per CPU core -->
  </configuration>
</plugin>
```

With JUnit Jupiter:

```properties
# src/test/resources/junit-platform.properties
junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.mode.default = same_thread
junit.jupiter.execution.parallel.mode.classes.default = concurrent
```

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

![center](assets/mutation-testing-explained-corrected.png)

---

## Introducing: Mutation Testing

- Having high code coverage might give you a **false sense of security**
- Mutation Testing with [PIT](https://pitest.org/quickstart/)
- Beyond Line Coverage: Traditional tools like JaCoCo show which code runs during tests, but PIT verifies if our tests actually detect when code behaves incorrectly by introducing "**mutations**" to our source code.
- Quality Guarantee: PIT automatically **modifies our code** (changing conditionals, return values, etc.) to ensure our tests fail when they should, **revealing blind spots** in seemingly comprehensive test suites.

---

## E2E Tests in One Slide

- Drive the **running application** through the browser, like a user would
- Keep them **few**: only the critical user journeys
- Use page objects, no locators inside the tests
- Run them in a **separate profile**, not in the fast build
- Tools: Selenium or Playwright, browser in a Testcontainers container

---

## Spring Boot 4 - Testing Support Keeps Improving

- **RestTestClient**: Modern, fluent alternative for the `TestRestTemplate`/`WebTestClient`/`RestAssured`.
- **Context pausing**: Cached test contexts are now automatically paused, eliminating resource conflicts from background processes.
- **JUnit 6**: Drop-in upgrade from JUnit 5 - far smoother than the JUnit 4 → 5 migration.
- **Testcontainers 2.0**: New `testcontainers-` prefix for modules, JUnit 4 support removed.
- **Bean overrides for non-singletons**: `@MockitoBean` and `@TestBean` now work with prototype and custom-scoped beans.

---

<!-- _class: light reveal -->

## What makes a good test suite: five properties

* **Fast** - the agent loop needs seconds, not minutes
* **Deterministic** - flaky tests teach the agent to retry and ignore
* **Isolated** - parallel runs, random order, no shared state
* **One reason to fail** - a red test points at one defect
* **A message that locates it** - `.as("...")` on every assertion

<!--
Notes:
- Closing beat of block 1 (5 min). Sum up the crash course as the five properties a good suite has.
- Block 2 starts from these: what happens to an agent when one of them is missing.
-->

---

<!-- header: 'Block 1 FAQ - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## FAQ 1: Your Questions

![bg right:36% h:420](assets/devoxx-questions-qr.png)

Where and how to ask:

1. **Live** - raise your hand and ask right now
2. **Devoxx app comments** - scan the QR code and write your question in the comment section of the talk, I pick the best ones
3. **After the session** - find me in the hallway

<!--
Notes:
- 10 min. Open the talk page in the Devoxx app, read the comments, pick 4-6 questions, 90 seconds each.
- Prepared backups: script/faq-block-1.md.
- Agent questions: park them for block 2 after the break.
-->

---

<!-- _class: light section -->
<!-- header: 'Break - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## Break

# Back at 11:15

<!--
Notes:
- Read the Devoxx app comments for open questions. Reset the demo during the break.
-->

---

<!-- _header: 'Block 2 · Recap of Block 1 - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

<style scoped>
.recap { display: grid; grid-template-columns: repeat(3, 1fr); gap: 22px; margin-top: 0.5em; font-family: 'Architects Daughter', cursive; }
.recap .sketch { box-sizing: border-box; display: flex; flex-direction: column; justify-content: center; min-height: 190px; font-size: 1.15em; padding: 0.6em 0.5em; }
.recap .sketch strong { font-size: 1.15em; }
.recap .sketch small { font-size: 0.8em; margin-top: 0.5em; }
</style>

## Recap: What We Learned in Block 1

<div class="recap">
  <div class="sketch accent">
    <strong>Testing Toolbox</strong>
    <small>JUnit · Mockito · AssertJ</small>
  </div>
  <div class="sketch alt">
    <strong>Spring Test Types</strong>
    <small>Unit · Slice · Integration</small>
  </div>
  <div class="sketch accent">
    <strong>Testcontainers</strong>
    <small>Real infrastructure</small>
  </div>
  <div class="sketch alt">
    <strong>Context Caching</strong>
    <small>Reuse the context</small>
  </div>
  <div class="sketch accent">
    <strong>Parallelization</strong>
    <small>Isolated tests</small>
  </div>
  <div class="sketch alt">
    <strong>Mutation Testing</strong>
    <small>Coverage lies</small>
  </div>
</div>

<!--
Notes:
- 1 minute recap of block 1, keywords only. Read the six boxes, do not explain them again.
- Bridge: now we put an AI agent on top of this fundament.
-->

---

<!-- _class: light section -->
<!-- header: 'Block 2 · The Goal - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

![bg right:33%](assets/m1-overview.jpg)

## 02 - The goal

# A fast and comprehensive test suite

<!--
Notes:
- 10 min. Align on the goal first. Reasoning: DORA, as on pragmatech.digital.
- Block 2 is my setup, not the course content 1:1. Hints and structure, no hard sell.
-->

---

<!-- _class: light statement -->

# With AI, the bottleneck moves from **writing** code to **verifying** it.

---

<!-- _class: light statement -->

# Somebody handed your team a **Formula 1 engine**.

---

## On a Bumpy Road, Faster Does Not Mean Safer

![center h:500](assets/f1-bumpy-road.png)

<!--
Notes:
- The engine is the AI: code in seconds. Nobody fixed the brakes, the road, or the driver.
- On a bumpy road, faster does not mean safer. It means the wall (production) arrives sooner.
-->

---

## Fix the Brakes, the Road, and the Driver

![center h:500](assets/f1-smooth-road.png)

<!--
Notes:
- Brakes: a fast and comprehensive test suite. Road: fast feedback in CI. Driver: you, with your testing standards written down as skills.
- Same engine, but now you can go fast and arrive with confidence.
-->

---

<!-- _class: light reveal -->

## Throughput Is No Longer the Constraint

* Writing code is cheap, **human time and attention** are not
* The hard problem: design **feedback loops and control systems** so agents build reliable software
* Unattended agents **parallelize** the work, only fast feedback keeps developer attention from becoming the next bottleneck

---

## Fast Feedback: The Engine in the AI World

![center h:470](assets/fast-feedback-foundation.png)

<!--
Notes:
- Fast feedback loops (CI and test automation) turn AI output into shipped, reliable value.
-->

---

## Backed by the DORA Research

![bg right:50% fit](assets/dora-core-summary.png)

DORA's core model puts **fast feedback** next to fast flow and a climate for learning.

- These capabilities predict **software delivery performance**
- Delivery performance predicts **organizational performance** and well-being

<!--
Notes:
- Source: DORA Core Model, as summarized on pragmatech.digital.
-->

---

## What a Slow or Weak Suite Does to the DORA Metrics

| Metric | With a slow or untrusted suite |
|---|---|
| Deployment frequency | Teams fear the next deployment and slow down |
| Lead time for changes | Feedback takes minutes, so everything waits |
| Change failure rate | A green build that proves little lets bugs through |
| Time to restore service | Nobody is sure the fix is safe |

<!--
Notes:
- This is my reasoning, not a quote from DORA.
-->

---

## The Goal: Fast and Comprehensive

<div class="flow">
  <div class="sketch accent">Fast<small>seconds in the agent loop, minutes in CI</small></div>
  <div class="sketch alt">Comprehensive<small>proves behavior, not just coverage</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">Confidence in every commit</div>
</div>

Both at once. Fast but shallow gives false confidence. Deep but slow, nobody runs it.

---

<!-- _class: light section -->
<!-- header: 'Block 2 · Skill Library - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

![bg right:33%](assets/m4-setup.jpg)

## 03 - Deterministic results

# A test skill library, adapted to each project

<!--
Notes:
- 17 min including Demo 1. Hints from my setup, not a full tour of every skill.
-->

---

<!-- _class: light statement -->

# Same prompt, different tests. **Every day.**

---

## Make the Agent's Result More Deterministic

<div class="flow">
  <div class="sketch">My testing standards<small>written down once</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">Skills<small>markdown in the repository</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Every agent session<small>same rules</small></div>
</div>

More predictable, not deterministic: the model still varies, but inside your guardrails.

---

## One Template Library, Adapted to Each Project

<div class="flow">
  <div class="sketch">Template library<small>my default conventions</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">Onboarding prompt<small>detect, ask, replace</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Your project's skills<small>your build, your conventions</small></div>
</div>

The agent reads your build file and existing tests, asks only what it cannot detect, and replaces every template value. Answer `default` if you have no opinion.

---

<style scoped>
.skills { display: grid; grid-template-columns: repeat(4, 1fr); gap: 16px; margin-top: 0.4em; font-family: 'Architects Daughter', cursive; }
.skills .sketch { box-sizing: border-box; display: flex; flex-direction: column; justify-content: center; min-height: 150px; font-size: 0.95em; padding: 0.4em 0.4em; }
.skills .sketch small { font-size: 0.72em; margin-top: 0.4em; }
</style>

## The Seven Skills

<div class="skills">
  <div class="sketch accent"><strong>unit-testing</strong><small>plain classes, no Spring</small></div>
  <div class="sketch alt"><strong>slice-testing</strong><small>JPA, JSON, HTTP clients</small></div>
  <div class="sketch accent"><strong>slice-web-testing</strong><small>controllers, security</small></div>
  <div class="sketch alt"><strong>integration-testing</strong><small>one journey, real HTTP</small></div>
  <div class="sketch accent"><strong>testcontainers-setup</strong><small>one container per image</small></div>
  <div class="sketch alt"><strong>e2e-ui-testing</strong><small>few browser journeys</small></div>
  <div class="sketch accent"><strong>test-setup-review</strong><small>review, read-only</small></div>
  <div class="sketch alt"><strong>spring-boot-testing</strong><small>the router, entry point</small></div>
</div>

<!--
Notes:
- Name them, do not explain each one. The router is the only skill the tool lists. It opens the right child skill by path.
-->

---

## How a Skill Is Built

```text
.claude/skills/spring-boot-testing/
├── SKILL.md                      router, project profile, change workflow
├── unit-testing/
│   ├── SKILL.md                  trigger, scope, non-negotiables, workflow
│   └── references/
│       ├── testing-standards.md  the rule catalog with IDs and severity
│       ├── examples.md           good and bad pairs
│       └── project-setup.md      config you copy once
├── slice-testing/   slice-web-testing/   integration-testing/
├── testcontainers-setup/   e2e-ui-testing/   test-setup-review/
└── ONBOARDING-PROMPT.md          adapts all of it to your project
```

---

## Inside `SKILL.md`

- **Frontmatter**: name and a description written like a routing rule, "Use when ... Not for ..."
- **Adapt this template**: the knobs for your project (assertions, naming, base class, database, build command)
- **Scope**: when the skill is the right tool and what it hands over to
- **Non-negotiables**: the rules that always apply, each with an ID
- **Workflow**: the steps the agent follows, including how to review its own output

---

## `testing-standards.md`: Rules With IDs and Severity

```text
[T7-H]   a failure message on every assertion
[T9-H]   no test-class fields, no @BeforeEach
[U1-H]   no Spring context in a unit test
[U2-H]   time from an injected Clock, never now()
[T27-H]  bounded waits, never Thread.sleep
```

Severity: **C** blocks, **H** must fix, **M** fix or justify, **L** mention only.

The agent reports the rule IDs, so you can see which rule fired and why.

---

## `examples.md`: Good and Bad Pairs

```java {1-2,6-8}
// bad: no message, tells you nothing at 3 AM
assertThat(order.status()).isEqualTo(PLACED);

// good: one chain, a message that locates the defect
assertThat(order.status())
  .as("Order is placed after a successful payment")
  .isEqualTo(PLACED);
```

Models copy examples better than they follow prose.

---

## The Router: Cheapest Test That Proves It

<div class="flow">
  <div class="sketch">A change<small>feature or fix</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">Router<small>test plan per behavior</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Owner skills<small>write the tests</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">Run<small>fast phase, then containers</small></div>
</div>

The final report has a plan table and a **not tested** list. The agent says what it skipped.

---

<!-- _class: light statement -->

# **Demo 1:** review a real test suite

<!--
Notes:
- 8 min. See script/demos/demo-1-review-skill.md. Spring PetClinic, pinned commit.
- Prompt: Review this project's test suite with the test-setup-review skill. Why is the build slow, and what should we fix first?
-->

---

<!-- _class: light metrics -->

## Numbers First: PetClinic Suite Review

- **18** test classes, 76 test methods
- **10** cached Spring contexts
- **0** Docker-free fast phases

---

## What the Review Finds

```text
[R9-H]  no Surefire or Failsafe split, no Docker-free fast phase
[R16-H] mysql:9.7 declared twice, two containers for one image
[R16-H] fixed host port 5432, the Postgres test fails when it is taken
[R17-H] H2 in the tests, MySQL and PostgreSQL in production
[R13-H] 10 cached contexts, every test class is its own key
```

Each line carries a hint, and the file and line behind it.

---

<!-- _class: light section -->
<!-- header: 'Block 2 · Parallel and Cached - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

![bg right:33%](assets/m2-present.jpg)

## 04 - Most important

# Parallelizable and optimized for context caching

<!--
Notes:
- 15 min including Demo 2. This is the part with the biggest payoff for build time.
- Refer back to block 1: context cache and parallelization.
-->

---

<!-- _class: light statement -->

# Two properties decide your build time: **parallel** and **cache-friendly**.

---

## Why the Agent Loop Cares

<div class="flow">
  <div class="sketch">Edit</div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">Run tests<small>every second counts</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Read failure</div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">Edit</div>
</div>

A slow suite makes the loop useless, the agent runs one test or none. Every extra Spring context is a full application start.

---

## Parallel by Construction

| The skills enforce | Why it matters |
|---|---|
| Random business keys, own rows per test | No collisions between concurrent tests |
| No test-class fields, no `@BeforeEach` state | Nothing shared between methods |
| Injected `Clock`, no `Thread.sleep` | No timing flakiness |
| Run the suite twice, random order | Order dependence shows up before CI |

Unit tests run concurrent. Slice tests stay same-thread (shared mocks in a cached context). Integration tests run concurrent with random data.

---

## Cache-Friendly by Construction

| The skills enforce | Effect on the context cache |
|---|---|
| One abstract base class for integration tests | One shared context |
| No `@MockitoBean` replacing application beans | No new cache key per test class |
| No `@DirtiesContext` | The context is never thrown away |
| A context limit (single digit, default 9) | Regressions fail the review |

The agent adds a test class, and the context count stays the same.

---

## One Container per Image, Two Build Phases

<div class="flow">
  <div class="sketch accent">Fast phase<small>*Test, no Docker, seconds</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Container phase<small>*IT, one static container per image</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">E2E<small>separate profile, a few journeys</small></div>
</div>

The agent runs the fast phase after every change, the container phase before it calls the work done. A single-test-class command is a knob in the skill, so it never reruns everything for one test.

---

## Measure It, Gate It in CI

```shell
docker events --filter event=start \
  --format '{{.Actor.Attributes.image}}' | sort | uniq -c
```

```shell
jq '.contextsCreated' target/spring-test-profiler/results.json
```

- Count containers per image while the suite runs
- **Spring Test Profiler** records the contexts a run starts, fail the build above your limit

---

## What the Profiler JSON Looks Like

```json {5,8,10}
{
  "schemaVersion": 1,
  "profilerVersion": "0.3.0",
  "totalDurationMs": 1825,
  "contextsCreated": 2,
  "contextCacheHits": 0,
  "contextCacheMisses": 2,
  "contextCacheHitRatio": 0.0,
  "springContextCacheSize": 2,
  "totalContextCreationTimeMs": 1074,
  "potentialTimeSavingsMs": 273
}
```

`target/spring-test-profiler/results.json`, one flat object per run (shortened). Sample project: `prompt-it-right`.

---

## Fail the Build Above the Limit

```yaml {1,2}
- name: Check the Spring context count
  run: scripts/check-context-count.sh "$MAX_CONTEXTS" \
         target/spring-test-profiler/results.json
```

```shell
contextsCreated=$(jq -r '.contextsCreated' results.json)
[ "$contextsCreated" -gt "$maxContexts" ] && exit 1
```

Limit as a workflow variable, default 9. The HTML report is uploaded as a build artifact.

---

<!-- _class: light statement -->

# **Demo 2:** count the containers

<!--
Notes:
- 5 min. See script/demos/demo-2-containers.md. docker events before and after merging the two MySQL declarations.
- Known issue: PostgresIntegrationTests needs port 5432 free. Use -Dtest='!PostgresIntegrationTests'.
-->

---

<!-- _class: light section -->
<!-- header: 'Block 2 · MCP Setup - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

![bg right:33%](assets/m4-review.jpg)

## 05 - MCP servers

# How I configure them

<!--
Notes:
- 8 min. Baseline: my MCP setup. Keep the number small, trust each server.
-->

---

## Three Servers, Three Gaps

| Server | The gap it closes |
|---|---|
| **Context7** | Docs for the framework version you actually use |
| **GitHub** | The agent reads its own workflow runs and job logs |
| **Playwright** | The agent sees the rendered page, locators are observed |

---

## Adding a Server, and Who Gets It

```shell
claude mcp add --transport http <name> <url>
claude mcp add <name> -- <command> [args...]
```

| Scope | Stored in | Who gets it |
|---|---|---|
| local (default) | `~/.claude.json` | only you, this project |
| project | `.mcp.json` | the whole team via git |
| user | your user config | all your projects |

---

## Context7 and GitHub

```shell
claude mcp add --scope user --transport http context7 https://mcp.context7.com/mcp
```

- **Context7**: add `use context7` to the prompt, name the version if it matters
- **GitHub**: Actions toolset to read runs and job logs
- **Read access always on**, write access only with me
- Read-only mode and a token with minimal scopes

---

## Playwright

```shell
claude mcp add playwright npx @playwright/mcp@latest
```

- Works with the accessibility tree, no vision model needed
- `--isolated` keeps the profile in memory, `--storage-state` reuses a saved login
- Visible browser while I watch, a small `slowMo` for debugging
- Only point it at your own applications

---

<style scoped>
.hub { display: grid; grid-template-columns: 1fr 0.55fr 1.2fr 0.55fr 1fr; grid-template-rows: auto auto auto auto auto; align-items: center; gap: 4px 8px; margin-top: 0.3em; font-family: 'Architects Daughter', cursive; }
.hub .sketch { box-sizing: border-box; font-size: 1em; padding: 0.45em 0.3em; }
.hub .sketch small { font-size: 0.68em; }
.hub .agent { font-size: 1.25em; padding: 0.8em 0.3em; border-width: 4px; }
.hub .link { text-align: center; font-size: 0.78em; line-height: 1.1; color: var(--pt-link); }
.hub .link b { display: block; font-size: 1.6em; line-height: 1; }
.hub .gh { grid-column: 3; grid-row: 1; }
.hub .l1 { grid-column: 3; grid-row: 2; }
.hub .tests { grid-column: 1; grid-row: 3; }
.hub .l2 { grid-column: 2; grid-row: 3; }
.hub .agent { grid-column: 3; grid-row: 3; }
.hub .l3 { grid-column: 4; grid-row: 3; }
.hub .browser { grid-column: 5; grid-row: 3; }
.hub .l4 { grid-column: 3; grid-row: 4; }
.hub .app { grid-column: 3; grid-row: 5; }
</style>

## Local Development With PetClinic: The Agent Closes the Loop

<div class="hub">
  <div class="sketch gh">GitHub<small>PRs, Actions runs, job logs</small></div>
  <div class="link l1"><b>&#8597;</b>GitHub MCP</div>
  <div class="sketch tests">Tests<small>./mvnw test, unit to E2E</small></div>
  <div class="link l2"><b>&#8596;</b>shell</div>
  <div class="sketch accent agent">Agent<small>plan, code, test, fix</small></div>
  <div class="link l3"><b>&#8596;</b>Playwright MCP</div>
  <div class="sketch browser">Browser<small>rendered page, screenshots</small></div>
  <div class="link l4"><b>&#8597;</b>spring-boot:run</div>
  <div class="sketch alt app">PetClinic, local<small>http://localhost:8080</small></div>
</div>

<!--
Notes:
- Three things the agent reaches on its own: GitHub (CI state), the UI (Playwright), the tests (shell). No copy and paste between you and the agent.
- The browser opens the locally running PetClinic.
-->

---

<!-- _class: light reveal -->

## The Loop in Five Steps

* **Start** PetClinic locally, the agent runs `./mvnw spring-boot:run`
* **Look** through Playwright MCP: open the page, read the rendered DOM and the real element ids
* **Change** code and tests, page objects use the observed locators instead of guessed ones
* **Run** the fast phase, then the browser journey
* **Push**, then read the GitHub Actions run and job logs through GitHub MCP, and fix what failed

<!--
Notes:
- Reading CI state is always on. Opening or merging a pull request stays with me.
- Playwright only against my own applications, here the local PetClinic.
-->

---

## Rules I Follow

- **Trust each server**: content it fetches is input, and input can contain instructions
- **Keep the number small**: tool descriptions cost context
- **Never put a token in `.mcp.json`**: use environment variables
- Run `claude mcp list` every few weeks and remove what you do not use

---

<!-- _class: light section -->
<!-- header: 'Block 2 · Engineering Practices - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

![bg right:33%](assets/m2-future.jpg)

## 06 - Engineering practices

# Mistakes will happen. Be ready to act fast.

<!--
Notes:
- 10 min. General engineering hints around the test suite. Tests reduce the failures, these practices reduce the damage.
-->

---

<style scoped>
.cycle { display: grid; grid-template-columns: 1fr 0.35fr 1fr 0.35fr 1fr 0.35fr 1fr; align-items: center; gap: 10px; margin-top: 0.8em; font-family: 'Architects Daughter', cursive; }
.cycle .sketch { box-sizing: border-box; font-size: 1.15em; padding: 0.9em 0.3em; }
.cycle .sketch small { font-size: 0.62em; }
.cycle .arrow { text-align: center; }
.cycle .down { grid-column: 7; text-align: center; }
.cycle .skip { grid-column: 1 / span 6; }
</style>

## The Dev Lifecycle and the Practices That Matter

<div class="cycle">
  <div class="sketch accent">Shift left<small>tests in the agent loop</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">CI<small>fast feedback</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">CD<small>small, frequent releases</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Feature flags<small>decouple deploy from release</small></div>
  <div class="skip"></div>
  <div class="arrow down">&#8595;</div>
  <div class="sketch accent">Fast rollback<small>undo in minutes</small></div>
  <div class="arrow">&#8592;</div>
  <div class="sketch">Runbooks<small>know what to do</small></div>
  <div class="arrow">&#8592;</div>
  <div class="sketch">Alerting<small>the right people, fast</small></div>
  <div class="arrow">&#8592;</div>
  <div class="sketch alt">Monitoring<small>see it in production</small></div>
</div>

<!--
Notes:
- Draw the loop. Left to right on top, back right to left on the bottom. The lesson of each failure goes back into shift left: a new test, a new rule in the skill.
-->

---

## Shift Left: Prevent It Early

- Tests are part of the agent loop, not something after it
- Small, atomic changes you can actually review
- Rules in the build for what you would block a pull request over (ArchUnit)
- A review pass on the tests the agent writes

---

## Ship Safely: CI/CD and Feature Flags

- A fast pipeline gives a verdict in minutes, small releases keep the blast radius small
- **Feature flags** decouple deploying from releasing, switch off instead of redeploying
- Roll out progressively: a few users first, then everyone

---

## Act Fast: Monitor, Alert, Run, Roll Back

- **Monitoring** that shows the user impact, not only CPU
- **Alerting** that wakes the right person, with a clear owner
- **Runbooks** so the 3 AM decision is a checklist, not a debate
- **Fast rollback** or a flag switch, then fix forward with a new small change

---

<!-- _class: light statement -->

# You will ship a bug. **How fast you recover is what counts.**

---

<!-- _class: light section -->
<!-- header: 'Wrap-up - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

![bg right:33%](assets/m6-thank-you.jpg)

## 07 - Wrap-up

# What to take home

<!--
Notes:
- 5 min. Evidence with honest limits, five takeaways, one soft pointer.
-->

---

<!-- _class: light metrics -->

## PetClinic: What the Skill Changed

- **5 to 1** Spring contexts: team integration tests vs one skill-guided journey
- **0 to 18** `.as()` failure messages in the unit tests: AI only vs AI + skill
- **H2 to Postgres** in the slice tests: AI only vs AI + skill

<!--
Notes:
- Source: course repo resources/petclinic-skill-comparison.md, experiment dated 2026-09-29.
- Integration: the team has 10 tests in 5 classes, 5 contexts, fixed IDs. AI + skill wrote 1 journey test, 1 context.
- Unit: AI only 17 tests with 0 messages, AI + skill 18 methods with 18 .as() messages.
-->

---

## Read This Evidence With Care

- **One run per arm.** One sample, not a benchmark
- **No coverage and no mutation score** were measured
- **The prompt named the skill.** In real use the description must trigger it on its own

---

<!-- _class: light reveal -->

## Five Takeaways

* A **fast and comprehensive** suite is the engine for moving fast with agents
* **Skills** written down once make the agent's result more deterministic
* Optimize for **parallel runs** and **context-cache reuse**
* Configure **few, trusted MCP servers**
* Practice **fast recovery**: feature flags, monitoring, runbooks, rollback

---

<!-- header: 'Take It Further - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## Take It Further: The Online Course

![center h:500](assets/agentic-testing-course.png)

<!--
Notes:
- Soft pitch, 1 minute. Everything in this talk works without the course. This is the longer, structured version of block 2.
-->

---

## Optimizing AI-Written Tests With Skills

Get a **skillset** for fast and comprehensive tests, including a **test strategy** for your project:

```text
.claude/skills/spring-boot-testing/
├── unit-testing            fast tests without context bloat
├── slice-testing           right-sized Spring context slices
├── slice-web-testing       web layer with @WebMvcTest
├── integration-testing     full-context tests that stay fast
├── testcontainers-setup    real infrastructure, one container per image
├── e2e-ui-testing          user journeys against the running app
└── test-setup-review       flags test anti-patterns for you
```

Each skill includes rules, references, best practices and anti-patterns as code and text.

---

<!-- _class: light reveal -->

## What the Course Covers

* **Verification is the new constraint**: why a trustworthy suite is the base of agentic development
* **The skill library**: the seven skills above, with the rule catalogs behind them
* **Adopting the skills**: one onboarding prompt adapts them to your project, then review your tests and build a feature
* **My agentic setup**: workmode, language server, MCP servers, TDD or not

---

<!-- header: 'Template and Offer - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## Get the Template Skill Structure

![bg right:36% h:420](assets/offer-qr.png)

Scan the QR code to get my **template skill structure**.

**33% off until the end of Devoxx** if you enroll in the **Course** or the **Bundle** edition.

<!--
Notes:
- assets/offer-qr.png leads to https://pragmatech.digital/lp/devoxx-belgium-2026/ (lead signup landing page).
- Offer text: 33% off, Course or Bundle edition, valid until the end of Devoxx Belgium 2026.
-->

---

<!-- header: 'Block 2 FAQ - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## FAQ 2: Your Questions

![bg right:36% h:420](assets/devoxx-questions-qr.png)

Where and how to ask:

1. **Live** - raise your hand and ask right now
2. **Devoxx app comments** - scan the QR code and write your question in the comment section of the talk, I pick the best ones
3. **After the session** - find me in the hallway

<!--
Notes:
- 10 min. Open the talk page in the Devoxx app, read the comments, pick 4-6 questions, 90 seconds each.
- Prepared backups: script/faq-block-2.md.
- Long answers: promise a follow-up in the hallway or the newsletter.
-->

---

<!-- header: 'Feedback - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## Rate this Session

Your feedback helps me improve the talk and helps Devoxx.

- Open the **Devoxx Belgium app**
- Find **"Prompt It Right"** and leave a rating
- Tell me what worked and what to change

[m.devoxx.com/events/dvbe26/talks/7006](https://m.devoxx.com/events/dvbe26/talks/7006/prompt-it-right-spring-boot-testing-in-the-ai-era)

![bg right:36% h:420](assets/devoxx-feedback-qr.png)

<!--
Notes:
- Leave this slide up for a moment after FAQ 2. Ask for honest feedback, also on the beginner level and the length of the two blocks.
-->

---

<!-- _class: light statement reveal -->
<!-- _paginate: false -->

<!--
Notes:
- One line per click (fragmented list, works in the HTML deck only).
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
<!-- _footer: '' -->

![bg right:33%](assets/end.jpg)

# Thank you!

Get my template skill structure and **33% off** until the end of Devoxx (Course or Bundle edition):

![center h:260](assets/offer-qr.png)

Philip Riecks · [PragmaTech GmbH](https://pragmatech.digital/)
- [LinkedIn](https://www.linkedin.com/in/rieckpil) (Philip Riecks)
- [Mail](mailto:philip@pragmatech.digital) (philip@pragmatech.digital)
