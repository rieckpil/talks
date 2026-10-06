package digital.pragmatech.shipfast;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

@SpringBootTest
@AutoConfigureMockMvc
class CheckoutControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private FeatureManager featureManager;

  @Test
  void shouldServeClassicCheckoutWhenFlagIsOff() throws Exception {
    featureManager.setFeatureState(new FeatureState(Features.NEW_CHECKOUT, false));

    mockMvc.perform(get("/checkout"))
      .andExpect(status().isOk())
      .andExpect(content().string("Classic checkout flow"));
  }

  @Test
  void shouldServeNewCheckoutWhenFlagIsOn() throws Exception {
    featureManager.setFeatureState(new FeatureState(Features.NEW_CHECKOUT, true));

    mockMvc.perform(get("/checkout"))
      .andExpect(status().isOk())
      .andExpect(content().string("New checkout flow"));
  }
}
