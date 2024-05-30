package de.rieckpil.workshop;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

  public List<String> findAllCustomerIds() {
    return List.of("42");
  }
}

