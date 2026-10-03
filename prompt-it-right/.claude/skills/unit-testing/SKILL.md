---
name: unit-testing
description: Write, review or refactor unit tests for Spring Boot classes (services, domain logic, mappers, validators) with JUnit Jupiter, Mockito and AssertJ, without a Spring context. Use when the user asks for a unit test, mentions JUnit, Mockito or AssertJ, or when a change adds a method with logic (a rule, a comparison, a calculation) that needs its own test. Not for @SpringBootTest, slice tests (@WebMvcTest, @DataJpaTest) or Testcontainers - those belong to other skills.
---

# Spring Boot Unit Testing (sample skill)

This is a condensed sample of one skill from my test skill library. It shows the structure:
a trigger in the frontmatter, project knobs, a scope table, non-negotiable rules with IDs and a
workflow. The full rule catalog is in `references/testing-standards.md`, good and bad pairs are in
`references/examples.md`. Every finding or decision refers to a rule ID, for example `[T7-H]`.

A unit test here means: one class under test, collaborators replaced by real objects, fakes or
Mockito stubs, **no Spring context**, runtime in milliseconds.

## Adapt this template (CUSTOMIZE)

Change this table per project. Every other section refers to these choices.

| Knob | Default | Note |
|---|---|---|
| Assertion library | AssertJ with `.as("...")` | Switch to Hamcrest or JUnit assertions if the project uses them |
| Unit test naming | `<ClassUnderTest>Test` | Everything that starts a context is `*IT` (other skills) |
| Build command (single class) | `./mvnw -q test -Dtest=OrderServiceTest` | Gradle: `./gradlew test --tests '*.OrderServiceTest'` |
| Build command (unit suite) | `./mvnw -q test` | Gradle: `./gradlew test` |
| Parallel execution | JUnit parallel, random order | See `junit-platform.properties` in the project |
| Disabled rules | none | List rule IDs the team disabled, one reason each |

## Scope: when a unit test is the right tool

| Component | Unit test | Better test type |
|---|---|---|
| Domain objects, pricing and calculation rules | Sufficient | none |
| Services and use cases | Sufficient | one integration journey for the critical flow |
| Mappers, validators, parsers | Sufficient | none |
| Controllers, filters, Spring Security | Not sufficient | `@WebMvcTest` (slice-web-testing skill) |
| Repositories and queries | Not sufficient | `@DataJpaTest` with Testcontainers (slice-testing skill) |

When the class sits in a "Not sufficient" row, say so, name the better test type and offer to extract
the logic into a class a unit test covers well. Do not write a unit test full of mocks `[U7-M]`.

## Non-negotiables

- `[T1-C]` Every behavior change ships with a unit test that reproduces it.
- `[T2-H]` One assertion chain per test, no `SoftAssertions`. The assertion is the last statement `[T3-H]`.
- `[T7-H]` Every assertion carries a failure message, for example `.as("Order was stamped with the clock time")`.
- `[T9-H]` `[T10-H]` No test-class fields, no `@BeforeEach`, `@Mock`, `@InjectMocks`. Each test builds its own objects.
- `[T27-H]` Every wait has a timeout. Never `Thread.sleep`.
- `[U1-H]` No Spring context in a unit test.
- `[U2-H]` Time comes from an injected `java.time.Clock`, never `Instant.now()` without a clock.
- `[U8-H]` Tests are order independent and safe under parallel execution.

## Workflow: write a unit test

1. Read the class under test and decide with the scope table whether a unit test is the right tool.
2. List the behaviors (rule, boundary, error path). One test per behavior.
3. Write the tests following the non-negotiables. Use random business data, never shared fixtures.
4. Run the single class with the build command from the table, then the whole unit suite twice.
5. Report: tests written with their rule IDs, tests not written and why, anything hand-over to another skill.
