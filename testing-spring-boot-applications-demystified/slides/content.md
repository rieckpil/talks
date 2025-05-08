# Welcome

## Intro

- Raise hands if you enjoy writing tests
- My story towards testing
- What I mean with testing: automated tests written by a developer
- Shifting left
- How much confidence do I have to deploy on a friday afternoon to prod on a dependabot update?
- Don't work towards 100% code coverage
- Fast feedback loops
- QUicker devleopment, errors pop up more early, wouldn't say bugs but sometimes we are over confident only to relaize aftr the deployment we missed a parameter or misspelled it. Avoid friction

## About Me & Agenda

- Interrupt me any time during the talk
- self employed consultant
- enjoys writing tests

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

### Naming Test Wirr Warr

White box test, integrated test, black box test, fast test

Word cloud and mention that it's complicated

### My Pragmatich Approach

1. Unit
2. Integration
3. E2E

### Spring Boot Starter Test

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

- Pick one assertion library or at least not mix it within the same test class


## Unit Testing with Spring Boot

- Unit tests are the fastest tests
- Provide collaborators from outside -> no new
- avoid static (possible to Mock)
- rely only on JUnit and Mockito

## Sliced Testing with Spring Boot

- For some application parts it will become not beneficial
- best use cases web layer: show how we don't get far with a plain unit test -> validation, security, status code, mapping
- Same is true for DataJapTest
- THere are more slices available
- You can write your own slice
- See `WebMvcTypeExcludeFilter`

## Integration Testing with Spring Boot

- Start everything up
- Difference with PORT to start tomcat or not
- Differnece between MockMvc and WebTestClient
- Context Caching! Best practices, pitfalls

## Best Practices and Pitfalls

### Best Practice 1: Test Parallelization

- Goal: reduce build time and get faster feedback
- Requirements:
  - No shared state
  - No dependency between tests and their execution order
  - No mutatation of global state
  - No `@DirtiesContext`

Two ways to achieve this:
- Fork new JVM with Surefire/Failsafe and let it run in parallel -> more resources but isolated execution
- Use JUnit Jupiter's parallelization mode and let it run in the same JVM with multiple threads, more fine-grained parallelization possible, define how to parallelize test classes and method, e.g. parallelize test class exection but within the same class run the test in sequence
- Parallel access to shared resources like database? Cleanup after each test to not pollute the next test
- Might not work out of the box, but worth investing
- Combine both approaches, multiple JVM and witin the JVM run in parallel

### Best Practice 2: Use the help of AI

- Show Diffblue, a niche AI tool that generates unit tests for Java code: `dcover create de.rieckpil.workshop.CustomerContr
oller`
- TDD with an LLM?
- LLM very usueful for boilerplate setup, test data, test migration (e.g. Kotlin -> Java)
- ChatBots might not produce compilable/working test code, agents are better
- Take a look at OpenRewrite for migrations (not AI but super useful)
- Clearly define your test requirements in your copiolot instructions, claude.md or cursor rule
- Showcase Claude Code and my `CLAUDE.md` file

### Best Practice 3: Use Mutation Testing If You Are Keen on Code Coverage

- PIT
- Mutation Testing
- Show example where it makes sense
- Considerations for bigger projects: only run on the new diffs, not on the whole codebase

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

### Testing Pitfall 3: JUnit 4 vs. JUnit 5 Pitfall

- Usually transition period, old projects have not time to fix tech debt, let alone test tech debt
- Browsing through the internet for solutions, you might find test setups that are for JUnit 5
- you can mix both versions in the same project but not in the same test class
- Easily import the wrong @Test and you end up wasting one hour because the Sppring context does not work as expected
- If you keep that in mind, good - small hint and tip

## Outlook

- Still new features coming: Still many new features (@ServiceConnection, Testcontainers Support, more AssertJ integrations, etc.) are coming in the ecosystem, Testcontainers being one of the most important one in recent years. The company AtomicJar got acquired by Docker
- Offer: in house 1 or 2 day workshop for this topic to cover more in depth
- On-demand online course Masterclass
- Open for consulting
- Thank you!
- Now it's time for questions
