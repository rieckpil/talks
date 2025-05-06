# Welcome


## Intro

- Shifting left
- How much confidence do I have to deploy on a friday afternoon to prod on a dependabot update?
- Don't work towarsd 100% code coverage
- Fast feedback loops
- QUicker devleopment, errors pop up more early, wouldn't say bugs but sometimes we are over confident only to relaize aftr the deployment we missed a parameter or misspelled it. Avoid friction


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

## Spring Boot Starter Test

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
- Context Caching! Best pracitces, pitfalls

## AI


## Outlook

- Still new features coming: Still many new features (@ServiceConnection, Testcontainers Support, more AssertJ integrations, etc.

- In house 1 or 2 day workshop for this topic
- On-demand online course
