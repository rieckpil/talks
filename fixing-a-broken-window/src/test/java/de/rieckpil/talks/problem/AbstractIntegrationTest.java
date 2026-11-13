package de.rieckpil.talks.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.localstack.LocalStackContainer;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@DirtiesContext
@Testcontainers
@ActiveProfiles("integration-test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled("Showcasing purposes only - don't do this at home")
abstract class AbstractIntegrationTest {

  @Autowired
  protected WebTestClient webTestClient;

  @Container
  static PostgreSQLContainer database = new PostgreSQLContainer(
    DockerImageName.parse("postgres:14.2")
  )
    .withDatabaseName("brokenwindow")
    .withUsername("brokenwindow");

  @Container
  public static LocalStackContainer localStack = new LocalStackContainer(
    DockerImageName.parse("localstack/localstack:4.4")
  )
    .withServices("sqs");

  private static final String QUEUE_NAME = "duke42";

  @BeforeAll
  static void createQueue() throws Exception {
    localStack.execInContainer(
      "awslocal",
      "sqs",
      "create-queue",
      "--queue-name",
      QUEUE_NAME
    );
  }

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", database::getJdbcUrl);
    registry.add("spring.datasource.password", database::getPassword);
    registry.add("spring.datasource.username", database::getUsername);
    registry.add("spring.cloud.aws.sqs.endpoint", () -> localStack.getEndpoint());
    registry.add("spring.cloud.aws.credentials.access-key", localStack::getAccessKey);
    registry.add("spring.cloud.aws.credentials.secret-key", localStack::getSecretKey);
    registry.add("invoice-queue-name", () -> QUEUE_NAME);
  }
}
