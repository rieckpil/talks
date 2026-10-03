---
marp: true
theme: pragmatech
title: 'Prompt It Right: Spring Boot Testing in the AI Era'
class: light
paginate: true
transition: pt-fade
header: 'Prompt It Right @ Devoxx Belgium 2026 - Questions @ menti.com Code: <strong>7108 0067</strong>'
footer: '![](assets/logo.webp) Philip Riecks · [PragmaTech GmbH](https://pragmatech.digital/) · [@rieckpil](https://x.com/rieckpil)'
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

<!-- footer: '![](assets/logo.webp)' -->

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
    <small>11:15 - 12:30 · rationale · skills · fast pipeline · tooling · demos · FAQ</small>
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

You will leave with **ideas you can use on Monday**, not a sales pitch.

<!--
Notes:
- Say it: everything shown works without buying anything. One pointer at the end.
-->

---

<!-- _class: light section -->
<!-- header: 'Block 1: Spring Boot Testing in a Nutshell - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## 01 - Spring Boot Testing in a Nutshell

# Know your toolbox

<!--
Notes:
- Block 1 is the fundament: 60 minutes, five topics. Beginner level on purpose.
-->

---

<!-- _class: light statement -->

Good tests don't just catch bugs - they give you **fast feedback** and **confident deployments**.

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

Pick the **cheapest test** that proves the behavior.

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

... but what about **Problem #3**: How to keep our build time at a reasonable duration?

---

<!-- _class: light section -->

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
<!-- header: 'Block 2 · Why It Matters - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## 02 - Why it matters

# Verification is the new constraint

<!--
Notes:
- 15 min. Baseline: course module 2 (testing as afterthought, Formula 1 engine, trustworthy suite).
- Open with the recap slide you just showed, then say: now we put an AI agent on top of this fundament.
-->

---

<!-- _class: light statement -->

Most developers have a **love-hate relationship** with testing.

<!--
Notes:
- Honest, not accusatory. Everyone in the room has done this.
-->

---

## Be Honest About the Last Five Years

<div class="flow">
  <div class="sketch">Written last<small>after the feature</small></div>
  <div class="sketch alt">Skimmed fastest<small>in code review</small></div>
  <div class="sketch">A dashboard number<small>nobody reads closely</small></div>
  <div class="sketch alt">"Later"<small>said, not meant</small></div>
</div>

That was the deal we already had with testing, before any of this.

---

## Coding Was Never the Whole Job

<div class="flow">
  <div class="sketch alt">Understand<small>customer wants, bug analysis</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">Write<small>code and tests, minutes now</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">Verify<small>the new bottleneck</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Ship<small>seconds</small></div>
</div>

AI made the write box fast. It never touched the other three.

---

<!-- _class: light statement -->

Everything got faster **except the part where you decide it is correct**.

---

## The Pipeline, Honestly Drawn

<div class="flow">
  <div class="sketch">Prompt<small>seconds</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Diff<small>seconds</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">Review<small>you, reading</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Merge<small>seconds</small></div>
</div>

Three of these four boxes got an order of magnitude faster. The blue one did not.

---

## The Prompt Everybody Types

```text
add tests for OrderService
```

Forty seconds later there is a new file, it is green, and the pull request is open.

You have typed this. I have typed this. It is the path of least resistance, and the tool was built to make that path shorter.

---

<!-- _class: light statement -->

It is green. **That is the only thing you know.**

---

## What Is Missing Is Not Effort

The agent did not get lazy. It has no way to know:

<div class="flow">
  <div class="sketch">Which behavior is worth proving?</div>
  <div class="sketch alt">Does the mock prove the query?</div>
  <div class="sketch">Will this read at 3am?</div>
  <div class="sketch alt">Does it run in parallel?</div>
</div>

Those are judgments. They came from you, and you stopped supplying them at scale.

---

<!-- _class: light quote -->

> Somebody handed your team a Formula 1 engine. Nobody fixed the brakes, the road, or the driver. On a bumpy road, faster does not mean safer. It means the wall arrives sooner.

---

## You Are the Conductor Now

<div class="flow">
  <div class="sketch alt">You typed it<small>line by line</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">You guide it<small>and judge what comes back</small></div>
</div>

What is left for you to supply is **judgment**. In production code and in tests.

---

<!-- _class: light statement -->

The outage is still **yours to explain**.

<!--
Notes:
- Nobody at the incident review wants to hear which model wrote the method. Accountability did not move, only the typing did.
-->

---

<!-- _class: light statement -->

If review cannot scale, the **green check** has to carry more.

---

<!-- _class: light reveal -->

## Good News: The Fix Got Cheap Too

* The same shift that made generation cheap also made **verification at scale** almost free
* With clear guidelines and context, the agent that wrote the mock-everything test writes a **fast, comprehensive** one instead
* The goal: **confidence in every commit**

---

## Which Property Breaks First With an Agent?

| Property | What the agent does when it is missing |
|---|---|
| **Fast** | Spawns a new Spring context per test class, the loop gets slow |
| **Deterministic** | `Instant.now()` and `Thread.sleep`, flaky tests teach everyone to re-run |
| **Isolated** | Shared fields and `@DirtiesContext`, no parallel runs |
| **One reason to fail** | Six assertions in one test, a name like `shouldWorkCorrectly` |
| **Locating message** | A red build that costs a nine minute debug |

---

## Telling It Once Per Prompt Does Not Scale

<div class="flow">
  <div class="sketch">Per prompt<small>retyped, reworded, forgotten</small></div>
  <div class="arrow">&#8800;</div>
  <div class="sketch accent">Permanent<small>written down once</small></div>
</div>

That permanent version is what a **skill** is.

---

<!-- _class: light section -->
<!-- header: 'Block 2 · Skills - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## 03 - Skills

# Your testing judgment, written down once

<!--
Notes:
- 20 min. Baseline: course module 3 (what is a skill, the seven skills) and module 4 (set up, review).
- Hints only: show two skills in detail, name the others. No hard sell.
-->

---

## Every Agent Session Starts From Zero

| A prompt | A skill |
|---|---|
| Lives in one chat window | Lives in the repository |
| Gets retyped, reworded, forgotten | Loads automatically when the task fits |
| Quality depends on your patience that day | Applies the same rules in every session |

---

<!-- _class: light statement -->

"Write meaningful tests, make no mistakes" **fails** because the agent never learned what meaningful means in your project.

---

<!-- _class: light split split-60 -->

## A Skill Is a Folder With One Markdown File

- **Frontmatter** is the trigger
- **Body** is the instruction
- `references/` loads on demand

```yaml {3-4}
---
name: unit-testing
description: Write, review or
  refactor unit tests ... Use when
  the user asks for a unit test,
  mentions JUnit, Mockito ...
  Not for @SpringBootTest ...
---
```

---

## Why the Rules Live in `references/`

```text
.claude/skills/
└── spring-boot-testing/
    └── unit-testing/
        ├── SKILL.md                     always loaded when the skill fires
        └── references/
            ├── testing-standards.md     rule catalog, loaded when needed
            ├── examples.md              good and bad pairs
            └── project-setup.md         config you copy once
```

The agent reads `SKILL.md` first and pulls a reference file only when it needs it. A large library does not cost you its full size in context on every prompt.

---

## How a Skill Loads

<div class="flow">
  <div class="sketch accent">Always<small>name and description sit in context</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Task matches<small>the agent reads the full SKILL.md</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">A rule points to it<small>references load on demand</small></div>
</div>

The description is the trigger. Write it like a routing rule: when to use the skill, and when **not** to.

---

## Skills Turn Open Questions Into Fixed Decisions

| Decision | Without a skill | With a skill |
|---|---|---|
| Assertions | JUnit, Hamcrest and AssertJ mixed | AssertJ, every time |
| Test scope | `@SpringBootTest` by default | Smallest slice that proves the behavior |
| Mocking | Mock everything, verify mock calls | Mock only at the boundaries |
| Test data | Copy-pasted setup in every class | Shared builders with sensible defaults |
| What to test | Getters, setters, framework code | Behavior that can actually break |

More predictable, not deterministic: the model still varies, but inside your guardrails.

---

## Instruction Files Are Always On, Skills Load on Demand

| Always-on instructions | Skills |
|---|---|
| `CLAUDE.md`, `AGENTS.md`, `.github/copilot-instructions.md` | `.claude/skills/*/SKILL.md`, `.agents/skills/*/SKILL.md` |
| Read in every session | Loaded only when the task matches |
| Keep them short: how to build, how to run the tests, project facts | The place for detailed judgment |

<!--
Notes:
- Install paths differ per tool: .claude/skills (Claude Code), .agents/skills (Codex, Cursor, Copilot), .github/skills (Copilot). Folder conventions change quickly, check the tool docs.
-->

---

<!-- _class: light statement -->

"How to run the tests" goes in AGENTS.md. "How to write **good** tests" goes in a skill.

---

## One Namespace, Seven Specialist Skills

| Skill | Handles |
|---|---|
| `unit-testing` | plain classes, no Spring context |
| `slice-testing` | `@DataJpaTest`, `@JsonTest`, HTTP clients, message listeners |
| `slice-web-testing` | `@WebMvcTest`, controllers, filters, Spring Security |
| `integration-testing` | `@SpringBootTest`, a full journey through the API |
| `testcontainers-setup` | container definitions the other skills consume |
| `e2e-ui-testing` | a few critical journeys through the browser |
| `test-setup-review` | reviews a test or the whole suite and gives hints |

<!--
Notes:
- One router skill is the only one the tool lists. It opens the right child skill by path. The cheapest test that proves the behavior wins: unit, slice, integration, browser.
-->

---

## Each Skill Splits Universal Rules From Your Conventions

- Test behavior, not implementation details
- Pick the smallest scope that proves the behavior
- One reason to fail per test
- Don't test framework or generated code

```markdown
## Project conventions
- Assertions: AssertJ
- Test names: shouldDoXWhenY
- Base class: AbstractIntegrationTest
- Database: postgres:16-alpine
- Run tests: ./mvnw verify
```

---

## Example: The `unit-testing` Skill

| Always | Never |
|---|---|
| One assertion chain, as the last statement `[T2-H]` | No test-class fields, `@BeforeEach` or shared fixtures `[T9-H]` |
| A failure message on every assertion `[T7-H]` | No `@SpringBootTest` or `@Autowired` `[U1-H]` |
| Time from an injected `Clock`, never `now()` `[U2-H]` | No `Thread.sleep` or an unbounded `await()` `[T27-H]` |
| Run the suite twice before calling it done `[U8-H]` | No test that only passes when another test ran first `[U8-H]` |

Every rule has an ID and a severity (C, H, M, L). The agent reports them, so you can see which rule fired.

---

## Example: The `slice-web-testing` Skill

| Always | Never |
|---|---|
| Security matrix for every endpoint `[W1-H]` | `addFilters = false` `[W2-H]` |
| CSRF proven once per controller `[W3-H]` | Request DTO via `ObjectMapper` `[W4-H]` |
| Status code asserted first `[W6-H]` | `@WithMockUser` on the class `[W14-M]` |
| Strict JSON contract per response `[W5-H]` | Filters tested with mocks `[W11-H]` |

---

## Setting It Up: Copy, Onboard, Install

<div class="flow">
  <div class="sketch">Copy<small>the library into your project</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">Onboard<small>one prompt, the agent adapts it</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Install<small>for this project or for you</small></div>
</div>

```text
Read spring-boot-testing/ONBOARDING-PROMPT.md and follow it exactly. Start with step 0.
```

The agent detects your build tool, Java, Spring Boot version and assertions, asks only what it cannot find, and answers to "no opinion" are `default`.

---

<!-- _class: light statement -->

Six skills write tests. This one **reviews them** and gives you **hints**.

---

## The `test-setup-review` Skill

It reads, it runs the build, it measures, it reports, it hints. **No edits.**

| | Test review | Suite review |
|---|---|---|
| Use it for | One test, a class, the tests in a pull request | The whole suite: "why is the build slow?" |
| It checks | The rules of the skill that owns the test type | Shape, build phases, context cache, containers, signals |
| You get | Findings with a hint each | Numbers, findings, an order by payoff |

A review that also rewrites gives you a diff instead of a decision.

---

<!-- _class: light statement -->

**Demo 1:** review a real test suite

<!--
Notes:
- 9 min. See script/demos/demo-1-review-skill.md. Project: Spring PetClinic, pinned commit.
- Prompt: Review this project's test suite with the test-setup-review skill. Why is the build slow, and what should we fix first?
-->

---

<!-- _class: light metrics -->

## Numbers First: PetClinic Suite Review

- **18** test classes, 76 test methods
- **10** cached Spring contexts
- **0** Docker-free fast phases

---

## What It Finds

```text
[R9-H]  no Surefire or Failsafe split, no Docker-free fast phase
[R16-H] mysql:9.7 declared twice, two containers for one image
[R16-H] fixed host port 5432, the Postgres test fails when it is taken
[R17-H] H2 in the tests, MySQL and PostgreSQL in production
[R13-H] 10 cached contexts, every test class is its own key
```

Each line carries a hint, and the file and line behind it.

---

## Then It Orders the Fixes by Payoff

<div class="flow">
  <div class="sketch accent">Split phases<small>hours</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">One container per image<small>hours</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">One base class<small>a day</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">Off H2<small>a day</small></div>
</div>

No rewrite. A sequence, and the first two steps are build configuration.

---

<!-- _class: light section -->
<!-- header: 'Block 2 · Fast Feedback - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## 04 - Fast feedback

# Seconds, not minutes

<!--
Notes:
- 12 min. Baseline: course skills testcontainers-setup and integration-testing, module 4 review.
- The agent loop pays for every second of the suite, per iteration.
-->

---

<!-- _class: light statement -->

How many containers does your build start? **Most teams guess wrong.**

---

## Count Before You Tune

```shell
docker events --filter event=start \
  --format '{{.Actor.Attributes.image}}' | sort | uniq -c
```

- One command, run it while the suite runs
- Two starts of `mysql:9.7` means two containers for one image
- Every extra container is startup time and a context-cache entry that does not need to exist

---

## Optimized for One Container per Image

| Always | Never |
|---|---|
| One container, `static final` `[C1-H]` | No `latest` tag `[C2-H]` |
| `@ServiceConnection` by default `[C3-H]` | No non-static `@Container` `[C4-H]` |
| An explicit wait strategy `[C6-H]` | No fixed host ports `[C5-H]` |

Container reuse is opt-in per machine, never committed, never on CI `[C9]`.

---

## Two Phases: A Docker-Free Fast Build and a Slow One

<div class="flow">
  <div class="sketch accent">Fast phase<small>Surefire, *Test, no Docker</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Container phase<small>Failsafe, *IT, Testcontainers</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">E2E<small>separate profile, a few journeys</small></div>
</div>

The agent runs the fast phase after every change, the whole build before it calls the work done.

---

## Optimized for One Context, Real HTTP, Parallel Safe

| Always | Never |
|---|---|
| Extend the one abstract base class, end with `IT` `[I1-H]` | No second `@SpringBootTest` or `@DirtiesContext` `[I5-H]` |
| Talk HTTP only, treat the app as a black box `[I2-H]` | No `@MockitoBean` replacing an application bean `[I4-H]` |
| Random keys for every piece of test data `[I7-H]` | No `Thread.sleep`, no fixed IDs, no shared state |

Five good journeys beat fifty `@SpringBootTest` classes.

---

## Keep the Context Count in the Single Digits

- Every new combination of mocks, properties and profiles is a new application start
- Set a limit (single digit by default) and let the review compare your suite against it
- **Spring Test Profiler** records how many contexts a run starts, fail the build when the limit is crossed

```shell
jq '.contextsCreated' target/spring-test-profiler/results.json
```

---

## Parallelism Per Test Type

| Test type | Execution |
|---|---|
| Unit | Concurrent, random order |
| Slice | Same thread (shared mocks in a cached context) |
| Integration | Concurrent with random data |

Isolation is what makes parallel runs possible: no shared fields, no rows from another test.

---

<!-- _class: light statement -->

**Demo 2:** count the containers

<!--
Notes:
- 6 min. See script/demos/demo-2-containers.md. docker events before and after merging the two MySQL declarations.
- Known issue: PostgresIntegrationTests needs port 5432 free. Use -Dtest='!PostgresIntegrationTests'.
-->

---

<!-- _class: light section -->
<!-- header: 'Block 2 · Tooling Around It - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## 05 - Tooling

# Small steps, good tools, guardrails

<!--
Notes:
- 13 min. Baseline: course module 5 (workmode, eyes and budget, MCPs and guardrails) and module 4 lesson 3.
- This is preference, not doctrine. Say so.
-->

---

## Four Habits

1. **Git worktrees** - so a bad run costs one directory
2. **Plan mode** - read and argue before anything is written
3. **Small units** - a diff you will actually read
4. **Fast local verification** - seconds, not minutes

None of these are about testing. All of them decide how much testing you get away with.

---

<!-- _class: light split split-60 -->

## Worktrees

Same repository, separate directory, separate branch, separate build output.

```bash {1,5}
git worktree add ../pc-unit \
  -b lesson/unit-testing

# a run went badly:
git worktree remove --force ../pc-unit
```

---

## Plan Mode: Argue With It for Free

Reading and searching are allowed. Writing is not.

- You see the approach before there is a diff to feel attached to
- Wrong assumptions surface as sentences, not as forty files
- "Use the existing `AbstractIntegrationTest`" costs one line here

Rejecting a plan is cheap. Rejecting a pull request is not.

---

## Small Units and Fast Local Checks

You will not read a large diff, so do not create one.

- One class, one behavior, one prompt, commit at every green point
- The agent must be able to check one thing in seconds without asking you

```bash {1}
./mvnw -q test -Dtest=PetTypeFormatterTests

# not this, to check one file:
./mvnw verify
```

---

<!-- _class: light statement -->

Grep is for **strings**. The language server is for **code**.

---

## Eyes: a Language Server Instead of Grep

| Question | Operation |
|---|---|
| Where is this defined? | `goToDefinition` |
| Who calls this? | `findReferences` |
| Where is this interface implemented? | `goToImplementation` |
| What is in this file? | `documentSymbol` |

`findReferences` before any rename. Every time.

---

## A Budget: Less Noise in the Context Window

```text
./mvnw verify        ~4,000 lines
./mvnw dependency:tree  ~900 lines
docker compose up      ~300 lines
```

- Almost none of it is about your code, and all of it goes into the context window
- Keep: failures, stack traces, the summary line. Drop: downloads, banners, per-module success lines
- Tools like RTK filter it, `2>&1 | tail -40` gets you most of the way

---

## MCP Servers: Let It See CI, Docs and the Browser

| Server | What it gives the agent |
|---|---|
| **GitHub MCP** | Reads workflow runs and job logs, closes the CI loop |
| **Playwright MCP** | The rendered DOM, real locators instead of guesses |
| **Context7** | Docs for the version you actually use |

Reading CI state: always on. Opening pull requests, merging: **not without me**.

---

## MCP Rules I Follow

- **Trust each server**: tool output is input to the model, prompt injection is real
- **Keep the number small**: tool descriptions cost context
- **Minimal scopes**: read-only where possible, never put a token in `.mcp.json`

```bash
claude mcp add --scope user --transport http context7 https://mcp.context7.com/mcp
claude mcp add playwright npx @playwright/mcp@latest
```

---

<!-- _class: light statement -->

Everything so far still depends on the model **choosing** to cooperate.

---

## A Rule That Fails the Build

A skill is a prompt, and a prompt can be overridden. Anything you would block a pull request over belongs in the build.

```java {3-5}
@ArchTest
static final ArchRule noFieldInjection =
    noFields().should()
        .beAnnotatedWith(Autowired.class)
        .because("constructor injection only");
```

---

## Which Rules Go Where

| ArchUnit (the build) | The skill |
|---|---|
| Layering and package dependencies | How to name a test |
| No field injection | Which assertion library |
| No `@SpringBootTest` outside `*IT` | Which slice to pick |
| Controllers return DTOs, not entities | How to phrase a failure message |

---

<!-- _class: light statement -->

A rule in the build does not care **which model** wrote the code.

---

## Demo 3: A Normal Feature Request, No Skill Named

```text
A pet cannot have two visits on the same day. When someone books
a second visit for a pet on a day that already has a visit for that
pet, the booking form is shown again with an error on the date field
and a clear message, and nothing is saved. Booking on another day
still works. Make sure the feature is properly tested.
```

<!--
Notes:
- 8 min live (or Demo 4, MCP + CI, as the backup). See script/demos/demo-3-feature-flow.md.
- Start in plan mode on the onboarded PetClinic, in a fresh session.
-->

---

## What Happens on Its Own

<div class="flow">
  <div class="sketch">Your prompt<small>a feature</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch accent">Router<small>spring-boot-testing</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch alt">Two skills<small>unit and slice-web</small></div>
  <div class="arrow">&#8594;</div>
  <div class="sketch">Tests<small>in your standards</small></div>
</div>

The router looks at what changed, not at what you asked for.

---

## A Test Plan, Not Just Tests

| Behavior | Test | Skill |
|---|---|---|
| The rule: another visit on the same day | unit test of the `Pet` method | `unit-testing` |
| Form shown again, error on `date`, nothing saved | web slice test | `slice-web-testing` |
| A full journey on a real database | skipped, with the reason | - |

---

## Watch for These in the Output

<div class="flow">
  <div class="sketch accent">Skill calls<small>router first</small></div>
  <div class="sketch alt">Test plan<small>behavior to test</small></div>
  <div class="sketch">Whole suite<small>run before done</small></div>
  <div class="sketch alt">Not tested<small>said out loud</small></div>
</div>

An agent that follows the skills tells you what it tested, what it skipped, and why.

---

<!-- _class: light statement -->

The agent writes. **You** judge.

---

<!-- _class: light section -->
<!-- header: 'Wrap-up - Questions @ menti.com Code: <strong>7108 0067</strong>' -->

## 06 - Wrap-up

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
- Where the AI was already good, say so: a static `@ServiceConnection` container

<!--
Notes:
- Honesty buys credibility. Say it out loud.
-->

---

<!-- _class: light reveal -->

## Five Takeaways

* Pick the cheapest test that proves the behavior
* Verification is the new constraint
* Teach the agent your rules with skills
* Keep feedback fast: two phases, one container per image, few contexts
* Work in small steps and add guardrails to the build

---

<!-- _class: light statement -->

You are not trying to write more tests. You are trying to **trust the green check**.

---

## Want More?

![bg right:33% h:400](assets/agentic-testing-course-qr.png)

- Everything in this talk works without the course
- Deeper walkthrough: [pragmatech.digital/agentic-spring-boot-testing-course](https://pragmatech.digital/agentic-spring-boot-testing-course/)
- Newsletter: tips on testing Spring Boot applications

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

<!-- _class: light closing -->
<!-- _paginate: false -->
<!-- _header: '' -->

# Thank you

## Questions?

Philip Riecks · [pragmatech.digital](https://pragmatech.digital/) · [@rieckpil](https://x.com/rieckpil)
