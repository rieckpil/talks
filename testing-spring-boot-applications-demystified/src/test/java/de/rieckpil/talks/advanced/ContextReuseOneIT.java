package de.rieckpil.talks.advanced;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContextReuseOneIT {

  @Test
  void doStuff() {
    System.out.println("Doing stuff in first test");
  }
}
