package de.rieckpil.talks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

@ContextConfiguration(classes = {CustomerController.class, CustomerService.class, CustomerRepository.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerDiffblueTest {
  @Autowired
  private CustomerController customerController;

  /**
   * Test {@link CustomerController#createNewCustomer(ObjectNode, UriComponentsBuilder)}.
   * <p>
   * Method under test: {@link CustomerController#createNewCustomer(ObjectNode, UriComponentsBuilder)}
   */
  @Test
  @DisplayName("Test createNewCustomer(ObjectNode, UriComponentsBuilder)")
  @Tag("MaintainedByDiffblue")
  void testCreateNewCustomer() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/customers")
        .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content(objectMapper.writeValueAsString(new ObjectNode(JsonNodeFactory.withExactBigDecimals(true))));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(customerController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/customers/42"));
  }
}
