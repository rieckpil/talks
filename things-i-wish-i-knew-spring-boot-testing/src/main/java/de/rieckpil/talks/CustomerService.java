package de.rieckpil.talks;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  public Long createNewCustomer(ObjectNode payload) {
    return 42L;
  }
}
