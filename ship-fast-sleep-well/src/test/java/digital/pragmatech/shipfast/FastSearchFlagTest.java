package digital.pragmatech.shipfast;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
class FastSearchFlagTest {

  @Test
  void shouldKnowTheFastSearchFeature() {
    assertThat(Features.valueOf("FAST_SEARCH")).isNotNull();
  }
}
