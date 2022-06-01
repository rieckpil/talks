package de.rieckpil.talks.problem;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Showcasing purposes only - don't do this at home")
class PurchaseIT extends AbstractIntegrationTest {

  @Test
  void shouldPurchaseInStockItem() {
    this.webTestClient.get()
      .uri("/actuator/health")
      .exchange()
      .expectStatus()
      .is2xxSuccessful();
  }
}
