package de.rieckpil.workshop;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.cache.ContextCacheUtils;
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
    // spring.test.context.cache.maxSize=42
    System.setProperty("spring.test.context.cache.maxSize", "13");
    LOG.info(String.valueOf(ContextCacheUtils.retrieveMaxCacheSize()));
    LOG.info(String.valueOf(ContextCacheUtils.retrieveContextFailureThreshold()));

    assertNotNull(testRestTemplate);
    assertNotNull(webTestClient);
  }
}
