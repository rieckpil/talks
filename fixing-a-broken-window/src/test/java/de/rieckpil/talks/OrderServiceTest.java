package de.rieckpil.talks;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

  @SpringBootTest
  class OrderServiceTest {

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
