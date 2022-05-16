package de.rieckpil.talks.investigation;

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

class ApplicationFourIT extends GeneralTestSetup{

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private ApplicationContext applicationContext;

  @AfterEach
  void afterEach() {
    System.out.println(applicationContext.getClass());
    System.out.println(applicationContext.getBeanDefinitionCount());
  }

  @Test
  void contextLoads() throws Exception{
    this.webTestClient
            .get()
            .uri("/actuator/health")
            .exchange()
            .expectStatus().is2xxSuccessful();

    Thread.sleep(20_000);
  }
}
