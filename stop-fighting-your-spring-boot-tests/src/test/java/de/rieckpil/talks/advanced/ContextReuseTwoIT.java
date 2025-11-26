package de.rieckpil.talks.advanced;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.cache.ContextCacheUtils;

@SpringBootTest
@ActiveProfiles("test")
class ContextReuseTwoIT {

  @Test
  void doStuff() {
    System.out.println("Doing stuff in second test");
  }

  @Test
  void configureCache() {
    // spring.test.context.cache.maxSize=42
    System.setProperty("spring.test.context.cache.maxSize", "13");
    System.out.println(ContextCacheUtils.retrieveMaxCacheSize());
    System.out.println(ContextCacheUtils.retrieveContextFailureThreshold());
  }
}
