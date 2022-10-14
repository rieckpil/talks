package de.rieckpil.talks;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

  public List<String> findAllCustomerIds() {
    return List.of("42");
  }
}
