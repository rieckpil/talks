package digital.pragmatech.shipfast;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "app.variant=a")
class ProfileVariantTest {

  @Value("${app.variant}")
  private String variant;

  @Test
  void shouldReadTheVariantProperty() {
    assertThat(variant).isEqualTo("a");
  }
}
