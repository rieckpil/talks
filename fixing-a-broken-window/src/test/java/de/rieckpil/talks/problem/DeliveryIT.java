package de.rieckpil.talks.problem;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Disabled("Showcasing purposes only - don't do this at home")
class DeliveryIT extends AbstractIntegrationTest {

  @Test
  void shouldDeliverMessageToCustomer(@Autowired Environment environment) {
    System.out.println(environment.getProperty("message"));
  }
}
