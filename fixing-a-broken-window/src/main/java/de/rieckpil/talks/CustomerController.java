package de.rieckpil.talks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final ObjectMapper objectMapper;

  public CustomerController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @GetMapping
  public JsonNode getOrders() {
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
