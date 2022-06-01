package de.rieckpil.talks;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final CustomerService customerService;

  private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

  public OrderService(CustomerService customerService) {
    this.customerService = customerService;
  }

  public void processOrder(String orderItem, BigDecimal price) {
    LOG.info("Processing order for orderItem: '{}'", orderItem);

    if (price.compareTo(BigDecimal.TEN) > 0) {
      customerService.informCustomer();
    }
    // process order
  }
}
