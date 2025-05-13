---
marp: true
theme: pragmatech
paginate: false
header: 'Testing Spring Boot Applications Demystified @ JUG Hamburg 14.05.2025'
---

![bg](./assets/hamburg-jug-hh.jpg)
<!-- header: "" -->
<!-- footer: ""-->



---

<!-- _class: title -->
![bg left](https://picsum.photos/720?image=29)

# Testing Spring Boot Applications Demystified

## Best Practices, Common Pitfalls, and Real-World Strategies

Java User Group Hamburg 14.05.2025

---
<!-- paginate: true -->
<!-- header: 'Testing Spring Boot Applications Demystified @ JUG Hamburg 14.05.2025' -->
<!-- footer: '![w:32 h:32](/assets/logo.webp)' -->

[//]: # (<!-- transition: pull -->)

## Intro

<!--


Notes:

- Raise hands if you enjoy writing tests -> I do and hope I can change that for some of you today


-->

- My story towards testing
- What I mean with testing: automated tests written by a developer
- Shifting left
- How much confidence do I have to deploy on a friday afternoon to prod on a dependabot update?
- Don't work towards 100% code coverage
- Fast feedback loops
- Quicker development, errors pop up more early, wouldn't say bugs, but sometimes we are overconfident only to realize after the deployment we missed a parameter or misspelled it. Avoid friction

## About Me & Agenda

- Interrupt me any time during the talk
- self employed consultant
- enjoys writing tests


---

![bg left:33%](https://picsum.photos/720?image=27)

## Spring Boot Testing 101

- Maven  Surfire Plugin for unit tests
-> what is a unit test?

Test types with Spring Boot:

- (Plain) Unit Test -> Plain JUnit & Mockito
- Springyfied Unit Test -> Sliced Tests with `@WebMvcTest`
Usually have `*Test` postfix

- Integration Test -> @SpringBootTest
- Web Tests/E2E -> usually involve tests with UI
usually have `*IT/*WT` postix

First two Surefire, last Failsafe

Reason for splitting: parallelize, better grouping
---

### Naming Test Wirr Warr

White box test, integrated test, black box test, fast test, functional test, regression test, system tests, user acceptance tests, unit tests, E2E tests, smoke tests, performance tests, load tests, stress tests, exploratory tests, mutation tests, contract tests, property-based tests, integration tests, boundary value testing

Word cloud and mention that it's complicated

---

### My Pragmatic Test Name Approach

1. Unit: Tests that verify the functionality of a single, isolated component (like a method or class) by mocking or stubbing all external dependencies.
2. Integration: Tests that verify interactions between two or more components work correctly together, with real implementations replacing some mocks.
3. E2E: Tests that validate the entire application workflow from start to finish, simulating real user scenarios across all components and external dependencies.


---

### Spring Boot Starter Test

<!--

Notes:

- Show the `spring-boot-starter-test` dependency and Maven dependency tree
- Show manual overriden


-->

- aka. Testing Swiss Army Knife
- Batteries Included for testing

- What's inside
  - JsonPath
  - AssertJ
  - Awaitility
  - Hamcrest
  - JUnit Jupiter
  - Mockito
  - JSONAssert
  - XmlUnit
- Manually override the versions possible

- Short description of each library with a minimal code example

Tips:
- Favor JUnit 5 over JUnit 4
- Pick one assertion library or at least not mix it within the same test class

---

## Unit Testing with Spring Boot

- Unit tests are the fastest tests
- Provide collaborators from outside -> no `new`
- Avoid static method access
- avoid static (possible to Mock) -> show example of injecting clock

```java
@Service
public class BirthdayService {

  public boolean isTodayBirthday(LocalDate birthday) {
    LocalDate today = LocalDate.now();

    return today.getMonth() == birthday.getMonth()
      && today.getDayOfMonth() == birthday.getDayOfMonth();
  }
}
```

---

```java
@Service
public class BirthdayServiceWithClock {

  private final Clock clock;

  public BirthdayServiceWithClock(Clock clock) {
    this.clock = clock;
  }

  public boolean isTodayBirthday(LocalDate birthday) {
    LocalDate today = LocalDate.now(clock);

    return today.getMonth() == birthday.getMonth()
      && today.getDayOfMonth() == birthday.getDayOfMonth();
  }
}
```

---

```java
@Test
void shouldReturnTrueWhenTodayIsBirthday() {
  // Arrange
  LocalDate fixedDate = LocalDate.of(2025, 5, 15);
  Clock fixedClock = Clock.fixed(
    fixedDate.atStartOfDay(ZONE_ID).toInstant(),
    ZONE_ID
  );

  BirthdayServiceWithClock cut = new BirthdayServiceWithClock(fixedClock);
  LocalDate birthday = LocalDate.of(1990, 5, 15); // Same month and day

  // Act
  boolean result = cut.isTodayBirthday(birthday);

  // Assert
  assertThat(result).isTrue();
}
```

## Check Your Imports

- Nothing Spring related here, master JUnit, Mockito and AssertJ

```java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
```

---

## Test Patterns

- Unify test method naming
- Structure test for the Arrange Act Assert test setup
- Cut naming -> Class Under Test

```java
@Test
void should_When_() {

  // Arrange
  // ... setting up objects, data, collaborators, etc.

  // Act
  // ... performing the action to be tested on the class under test

  // Assert
  // ... verifying the expected outcome
}
```

---

- Unit test comes to break when e.g. controller

- Request Mapping: Does /api/users/{id} actually resolve to your method?
- Status Codes: Will a bad request return a 400 or an accidental 200?
- Headers: Are you setting Content-Type or custom headers correctly?
- Security: Is your @PreAuthorize rule enforced?

---

## Sliced Testing with Spring Boot

<!--

Notes:

- Show the exclude filter in @WebMvcTest

-->


- For some application parts it will become not beneficial
- best use cases web layer: show how we don't get far with a plain unit test -> validation, security, status code, mapping
- Same is true for DataJapTest
- There are more slices available
- You can write your own slice
- See `WebMvcTypeExcludeFilter`

---

## Integration Testing with Spring Boot

<!--

Notes:

- Ask who is using Testcontainers?

-->

- Start everything up
- BUto how? Testcontainers to the resuce
- Difference with PORT to start tomcat or not
- Difference between MockMvc and WebTestClient!
- Context Caching!

---

## Best Practices and Pitfalls

---

### Best Practice 1: Test Parallelization

- Goal: reduce build time and get faster feedback

- Requirements:
  - No shared state
  - No dependency between tests and their execution order
  - No mutation of global state
  - No `@DirtiesContext`

Two ways to achieve this:
- Fork new JVM with Surefire/Failsafe and let it run in parallel -> more resources but isolated execution
- Use JUnit Jupiter's parallelization mode and let it run in the same JVM with multiple threads, more fine-grained parallelization possible, define how to parallelize test classes and method, e.g. parallelize test class exection but within the same class run the test in sequence
- Parallel access to shared resources like database? Cleanup after each test to not pollute the next test
- Might not work out of the box, but worth investing
- Combine both approaches, multiple JVM and witin the JVM run in parallel

---

### Best Practice 2: Use the help of AI

- Show Diffblue, a niche AI tool that generates unit tests for Java code: `dcover create de.rieckpil.talks.CustomerContr
oller`
- TDD with an LLM? Example: following my TDD instructions, implement a Spring Boot
  Web REST API to fetch the stock price from a given
  company using their ticker symbol (e.g. MSTR), use the
  Spring WebClient and implement meaningful unit and
  integration tests in a test-driven manner

- LLM very usueful for boilerplate setup, test data, test migration (e.g. Kotlin -> Java)
- ChatBots might not produce compilable/working test code, agents are better
- Take a look at OpenRewrite for migrations (not AI but super useful)
- Clearly define your test requirements in your copiolot instructions, claude.md or cursor rule
- Showcase Claude Code and my `CLAUDE.md` file
- GitHub Copilot in IDEA not sooo good (much filtering, UX could be improved) but in VSCode it should be better
- OpenRewrite byy Moderne recipes:
  - https://docs.openrewrite.org/recipes/java/spring/boot3/replacemockbeanandspybean
  - https://docs.openrewrite.org/recipes/java/testing/junit5
---

### Best Practice 3: Use Mutation Testing If You Are Keen on Code Coverage

- aka. your tests may give you a false sense of security
- Having code coverage but not testing the right things
- introduction:  Mutation Testing with e.g. PIT
- Show example where it makes sense
- Considerations for bigger projects: only run on the new diffs, not on the whole codebase
- Beyond Line Coverage: Traditional tools like JaCoCo show which code runs during tests, but PIT verifies if your tests actually detect when code behaves incorrectly by introducing "mutations" to your source code.
- Quality Guarantee: PIT automatically modifies your code (changing conditionals, return values, etc.) to ensure your tests fail when they should, revealing blind spots in seemingly comprehensive test suites.
- Business Logic Protection: For Spring Boot services with complex business workflows, PIT helps identify untested edge cases that could lead to critical production bugs in your application.
- Easy Integration: PIT seamlessly integrates with Maven/Gradle build processes, with minimal configuration required to start testing your Spring Boot application.
- Prioritize Improvements: PIT's HTML reports clearly identify which parts of your codebase have mutations that survived testing, helping teams focus their testing efforts where they'll have the most impact.
- Production Confidence: While requiring more computational resources than basic unit tests, the enhanced detection of subtle logic errors provides significantly greater confidence in mission-critical Spring Boot components.

### Testing Pitfall 1: Using @SpringBootTest for Everything

- The name could apply it's a one size fits all solution, but it isn't
- It comes with costs: the application context
- Useful for integration tests that verify the whole application but not for testing a time manipulation service class works as expected
- Start with unit tests, see if sliced tests are applicable and only then use @SpringBootTest

### Testing Pitfall 2: @MockitoBean vs. @MockBean vs. @Mock

- Might be complex
- @MockBean is a Spring Boot specific annotation that replaces a bean in the application context with a Mockito mock
- New @MockitoBean annotation is a Spring Boot specific annotation that replaces a bean in the application context with a Mockito mock
- @Mock is Mockito only for unit tests

- Golden Mockito Rules:
  - Do not mock types you don't own -> e.g. Framework Boundary
  - Don't mock value objects -> e.g. DTOs
  - Don't mock everything
  - Show some love with your tests

---

### Testing Pitfall 3: JUnit 4 vs. JUnit 5 Pitfall

- Usually transition period, old projects have not time to fix tech debt, let alone test tech debt
- Browsing through the internet for solutions, you might find test setups that are for JUnit 5
- you can mix both versions in the same project but not in the same test class
- Easily import the wrong @Test and you end up wasting one hour because the Spring context does not work as expected
- If you keep that in mind, good - small hint and tip

---

## Outlook & Summary

- Still new features coming: Still many new features (@ServiceConnection, Testcontainers Support, more AssertJ integrations, etc.) are coming in the ecosystem, Testcontainers being one of the most important one in recent years. The company AtomicJar got acquired by Docker
- Offer: in house 1 or 2 day workshop for this topic to cover more in depth
- On-demand online course Masterclass
- Open for consulting
- Thank you! Joyful testing!
- Now it's time for questions
