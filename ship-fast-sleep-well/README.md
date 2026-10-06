# Ship Fast, Sleep Well: Fearless Spring Boot Deployments in the AI Era

Talk at the DATEV Coding Festival 2k26 on October 6, 2026. Push to production on Friday 16:00 and sleep well: a first-class test suite, feature flags, runbooks, monitoring and alerts.

See [slides/README.md](slides/README.md) for the build commands.

Slides: `slides/content.md` (English) and `slides/content-de.md` (German). Keep both in sync.

## Demo project

Spring Boot 4.1.1, Java 21 and Togglz 4.6.4 with the admin console. It is the source of the Togglz screenshot in the slides.

```bash
JAVA_HOME=<jdk21> ./mvnw verify
JAVA_HOME=<jdk21> ./mvnw spring-boot:run     # then open http://localhost:8080/togglz-console/index
```

`GET /checkout` returns the new or the classic checkout flow, depending on the `NEW_CHECKOUT` feature flag.
