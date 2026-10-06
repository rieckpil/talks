package digital.pragmatech.shipfast;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PayByCardFlagTest {

  @Test
  void shouldEnablePayByCardByDefault() {
    assertThat(Features.PAY_BY_CARD.isActive()).isTrue();
  }
}
