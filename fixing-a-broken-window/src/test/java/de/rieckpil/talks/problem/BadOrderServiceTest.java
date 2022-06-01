package de.rieckpil.talks.problem;

import java.math.BigDecimal;

import de.rieckpil.talks.CustomerService;
import de.rieckpil.talks.OrderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

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
