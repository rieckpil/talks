package digital.pragmatech.shipfast;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

/**
 * Seeds the in-memory feature state so the admin console shows different activation strategies.
 */
@Configuration
class FeatureStateConfig {

  @Bean
  ApplicationRunner seedFeatureStates(FeatureManager featureManager) {
    return args -> {
      featureManager.setFeatureState(new FeatureState(Features.NEW_CHECKOUT, true)
        .setStrategyId("username")
        .setParameter("users", "anna, ben"));
      featureManager.setFeatureState(new FeatureState(Features.FAST_SEARCH, true)
        .setStrategyId("gradual")
        .setParameter("percentage", "20"));
    };
  }
}
