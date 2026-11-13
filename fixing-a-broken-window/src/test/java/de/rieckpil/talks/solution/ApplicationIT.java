package de.rieckpil.talks.solution;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.localstack.LocalStackContainer;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@AutoConfigureWebTestClient
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
  initializers = { MessagingQueueInitializer.class, WireMockInitializer.class }
)
class ApplicationIT {

  @Container
  public static LocalStackContainer localStack = new LocalStackContainer(
    DockerImageName.parse("localstack/localstack:4.4")
  )
    .withServices("sqs");

  @Container
  static PostgreSQLContainer database = new PostgreSQLContainer(
    DockerImageName.parse("postgres:14.2")
  )
    .withDatabaseName("brokenwindow")
    .withUsername("brokenwindow");

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private ApplicationContext applicationContext;

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", database::getJdbcUrl);
    registry.add("spring.datasource.password", database::getPassword);
    registry.add("spring.datasource.username", database::getUsername);
    registry.add("spring.cloud.aws.sqs.endpoint", () -> localStack.getEndpoint());
    registry.add("spring.cloud.aws.credentials.access-key", localStack::getAccessKey);
    registry.add("spring.cloud.aws.credentials.secret-key", localStack::getSecretKey);
    registry.add("spring.cloud.aws.region.static", () -> localStack.getRegion());
  }

  @AfterEach
  void afterEach() {
    System.out.println(applicationContext.getClass());
    System.out.println(applicationContext.getBeanDefinitionCount());
  }

  @Test
  void contextLoads() {
    this.webTestClient.get()
      .uri("/actuator/health")
      .exchange()
      .expectStatus()
      .is2xxSuccessful();
  }
}
