package de.rieckpil.talks.advanced;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIT {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationIT.class);

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void contextLoads() {
    LOG.info("Port: {}", port);

    assertNotNull(testRestTemplate);
    assertNotNull(webTestClient);

    this.webTestClient
      .get()
      .uri("/fibonacci")
      .exchange()
      .expectStatus()
      .isOk();
  }
}
