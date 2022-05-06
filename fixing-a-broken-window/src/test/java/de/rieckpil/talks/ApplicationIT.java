package de.rieckpil.talks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIT {

  @Container
  static PostgreSQLContainer database = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.2"))
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
  }

  @AfterEach
  void afterEach() {
    System.out.println(applicationContext.getClass());
    System.out.println(applicationContext.getBeanDefinitionCount());
  }

  @Test
  void contextLoads() {
    this.webTestClient
            .get()
            .uri("/actuator/health")
            .exchange()
            .expectStatus().is2xxSuccessful();
  }
}
