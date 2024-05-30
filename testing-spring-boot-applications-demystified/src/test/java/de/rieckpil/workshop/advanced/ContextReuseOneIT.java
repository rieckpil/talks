package de.rieckpil.workshop.advanced;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
public class ContextReuseOneIT {

  @Test
  void doStuff() {
    System.out.println("Doing stuff in first test");
  }
}
