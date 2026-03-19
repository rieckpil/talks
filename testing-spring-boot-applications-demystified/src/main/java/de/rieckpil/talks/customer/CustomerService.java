package de.rieckpil.talks.customer;

import java.util.List;
import java.util.Optional;

import tools.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public String createNewCustomer(String customerName) {

    Optional<CustomerEntity> existingCustomer = customerRepository.findByCustomerName(customerName);

    if (existingCustomer.isPresent()) {
      throw new IllegalArgumentException("Customer already exists");
    }

    CustomerEntity createdCustomer = this.customerRepository.save(new CustomerEntity(customerName));

    return createdCustomer.getId();
  }

  public void notifyAllCustomers() {

    List<String> allCustomers = customerRepository.findAllCustomerIds();

    // send email, etc.
  }

  public JsonNode getAll() {
    return this.customerRepository.findAll();
  }
}
