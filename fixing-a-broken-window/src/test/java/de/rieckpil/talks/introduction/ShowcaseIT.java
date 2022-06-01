package de.rieckpil.talks.introduction;

import de.rieckpil.talks.CustomerService;
import de.rieckpil.talks.OrderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("Showcasing only")
@ActiveProfiles("integration-test")
@Import(SomeTestConfiguration.class)
@ContextConfiguration(initializers = CustomInitializer.class)
@SpringBootTest(properties = { "features.login-enabled=true", "custom.message=duke42" })
class ShowcaseIT {

  @MockBean
  private OrderService orderService;

  @SpyBean
  private CustomerService customerService;

  @Test
  void shouldInitializeContext(@Autowired ApplicationContext applicationContext) {
    assertThat(applicationContext).isNotNull();
  }
}
