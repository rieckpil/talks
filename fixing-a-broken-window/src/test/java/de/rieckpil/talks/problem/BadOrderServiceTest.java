package de.rieckpil.talks.problem;

import static org.mockito.Mockito.verify;

import de.rieckpil.talks.CustomerService;
import de.rieckpil.talks.OrderService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@Disabled("Showcasing purposes only - don't do this at home")
class BadOrderServiceTest {

  @Autowired
  private OrderService orderService;

  @MockBean
  public CustomerService customerService;

  @Test
  void shouldNotifyCustomerIfPriceReachesAlertThreshold() {
    orderService.processOrder("MacBook Pro M1", BigDecimal.valueOf(1499));

    verify(customerService).informCustomer();
  }
}
