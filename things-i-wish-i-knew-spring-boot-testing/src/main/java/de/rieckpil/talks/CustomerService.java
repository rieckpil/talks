package de.rieckpil.talks;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Long createNewCustomer(ObjectNode payload) {
    return 42L;
  }

  public void notifyAllCustomers() {

    List<String> allCustomers = customerRepository.findAllCustomerIds();

    // send email, etc.
  }
}
