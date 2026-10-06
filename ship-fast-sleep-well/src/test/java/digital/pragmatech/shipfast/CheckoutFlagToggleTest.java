package digital.pragmatech.shipfast;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

@SpringBootTest
@AutoConfigureMockMvc
class CheckoutFlagToggleTest {

  @Autowired
  private FeatureManager featureManager;

  @Test
  void shouldReflectTheFeatureStateInTheFeatureManager() {
    featureManager.setFeatureState(new FeatureState(Features.NEW_CHECKOUT, true));

    assertThat(Features.NEW_CHECKOUT.isActive()).isTrue();
  }
}
