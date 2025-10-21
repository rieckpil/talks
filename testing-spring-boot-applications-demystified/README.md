# Testing Spring Boot Applications Demystified

## Notes

```
dcover create de.rieckpil.talks.customer.CustomerController
```

Example prompt to develop a feature TDD:

```
For my CustomerController, please implement a HTTP PUT API to update existing
customer entities.

Make sure this can only be done by authenticated users with the "ADMIN" role.
```

OpenRewrite:

- https://docs.openrewrite.org/recipes/java/spring/boot3/replacemockbeanandspybean
- https://docs.openrewrite.org/recipes/java/testing/junit5

## Spring Test Profiler

Report at `file:///Users/rieckpil/Development/git/spring-test-profiler/demo/spring-boot-3.5-maven/target/spring-test-profiler/latest.html`
