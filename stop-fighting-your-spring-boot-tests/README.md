# Stop Fighting Your Spring Boot Tests

## Slides

You can find the rendered PDF slides for each talk in the [slides](slides) folder to preview in the browser or download.

## Notes

Diffblue Cover CLI usage:

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

Instructions to use the open-source Spring Test Profiler [are on GitHub](https://github.com/PragmaTech-GmbH/spring-test-profiler).

Report at `file:///Users/rieckpil/Development/git/spring-test-profiler/demo/spring-boot-3.5-maven/target/spring-test-profiler/latest.html`
