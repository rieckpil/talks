package de.rieckpil.talks.problem;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@DirtiesContext
@Testcontainers
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractIntegrationTest {

  @Autowired
  protected WebTestClient webTestClient;

  @Container
  static PostgreSQLContainer database = new PostgreSQLContainer<>(
    DockerImageName.parse("postgres:14.2")
  )
    .withDatabaseName("brokenwindow")
    .withUsername("brokenwindow");

  @Container
  public static LocalStackContainer localStack = new LocalStackContainer(
    DockerImageName.parse("localstack/localstack:0.14.2")
  )
    .withServices(SQS);

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
    registry.add("cloud.aws.sqs.endpoint", () -> localStack.getEndpointOverride(SQS));
    registry.add("cloud.aws.credentials.access-key", localStack::getAccessKey);
    registry.add("cloud.aws.credentials.secret-key", localStack::getSecretKey);
    registry.add("invoice-queue-name", () -> QUEUE_NAME);
  }
}
