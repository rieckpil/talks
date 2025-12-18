package de.rieckpil.talks.customer;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  Optional<String> findByUsername(String username) {
    return Optional.empty();
  }

  Long save(String username) {
    return 42L;
  }
}
