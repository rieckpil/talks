package de.rieckpil.talks.problem;

import org.junit.jupiter.api.Test;

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
