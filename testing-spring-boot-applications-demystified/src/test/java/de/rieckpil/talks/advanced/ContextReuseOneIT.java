package de.rieckpil.talks.advanced;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.cache.ContextCacheUtils;

@SpringBootTest
class ContextReuseOneIT {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationIT.class);

  @Test
  void doStuff() {
    System.out.println("Doing stuff in first test");

    // spring.test.context.cache.maxSize=42
    System.setProperty("spring.test.context.cache.maxSize", "13");
    LOG.info(String.valueOf(ContextCacheUtils.retrieveMaxCacheSize()));
    LOG.info(String.valueOf(ContextCacheUtils.retrieveContextFailureThreshold()));

  }
}
