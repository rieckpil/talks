# The Need for Speed: Cut Your Spring Boot Build Times by 70%

Your Spring Boot test suite is a time thief. Every context reload, every unnecessary restart, every `@DirtiesContext` annotation is stealing precious minutes from your development flow. But what if those 20-minute test runs could shrink to just 6 minutes?

This session reveals the game-changing power of Spring's TestContext caching - a feature hiding in plain sight. We dissect how Spring decides to reuse or rebuild application contexts, expose the silent performance killers in your test suite, and demonstrate proven strategies that deliver dramatic speed improvements.

Through hands-on examples, you discover why `@MockBean` might be your worst enemy, how test execution order affects performance, and which configuration patterns maximize context reuse. We benchmark real applications, showing exactly where those 70% gains come from.

Stop accepting slow tests as "just the way it is." Leave with a concrete action plan to accelerate your test suite today - no new frameworks, no rewrites, just smarter testing.

Slides: [slides/content.md](slides/content.md), see [slides/README.md](slides/README.md) for the build commands.

## Demo project

Spring Boot 4.1.1, Java 25, Spring Web MVC, Spring Data JPA with H2 and the [Spring Test Profiler](https://github.com/PragmaTech-GmbH/spring-test-profiler) 0.3.1. Bootstrapped with the Spring Boot CLI (`spring init`). It is a small CRUD API for books (`/api/books`).

The test suite is **unoptimized on purpose**: five test classes create five different application contexts (`RANDOM_PORT` with `TestRestTemplate`, `@MockitoBean`, `@MockitoSpyBean`, `@DirtiesContext`, an extra property). Use it as the baseline and optimize it step by step during the talk.

```bash
JAVA_HOME=<jdk25> ./mvnw verify                      # runs all tests, writes the profiler report
open target/spring-test-profiler/latest.html         # context cache report
jq . target/spring-test-profiler/results.json        # flat metrics for CI
```

The profiler is active through `src/test/resources/META-INF/spring.factories`.

Baseline: 9 tests, 5 contexts created, cache hit ratio 0.44, about 4.9s total runtime.

## Optimize it

`optimize-context-caching.patch` introduces one `AbstractIntegrationTest` and removes the mocks, `@DirtiesContext` and the extra property. Result: 1 context, cache hit ratio 0.89, about 3.0s.

```bash
git apply -p1 optimize-context-caching.patch
git apply -R -p1 optimize-context-caching.patch   # back to the baseline
```

## CI guard

```bash
jq -r '.contextsCreated' target/spring-test-profiler/results.json
```
