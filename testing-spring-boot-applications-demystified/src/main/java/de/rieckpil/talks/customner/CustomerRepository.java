package de.rieckpil.talks.customner;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

  private final ObjectMapper objectMapper;

  public CustomerRepository(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public List<String> findAllCustomerIds() {
    return List.of("42");
  }

  public JsonNode findAll() {
    return objectMapper
      .createArrayNode()
      .add(
        objectMapper
          .createObjectNode()
          .put("id", UUID.randomUUID().toString())
          .put("name", "duke")
      );
  }
}

