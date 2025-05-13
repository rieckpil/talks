package de.rieckpil.talks.basics;

import com.fasterxml.jackson.databind.node.ObjectNode;
import de.rieckpil.talks.CustomerController;
import de.rieckpil.talks.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@DisplayName("Customer Controller Claude Tests")
class CustomerControllerClaudeTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CustomerService customerService;

  @Nested
  @DisplayName("Create customer endpoint tests")
  class CreateCustomerTests {

    @Test
    @DisplayName("Should return 201 and location header when customer is created")
    void shouldReturnCreatedStatusWhenCustomerIsCreated() throws Exception {
      when(customerService.createNewCustomer(any(ObjectNode.class)))
        .thenReturn(42L);

      var result = mockMvc
        .perform(post("/api/customers")
          .contentType(APPLICATION_JSON)
          .content("""
             {
               "first_name": "Jane",
               "last_name": "Smith",
               "email": "jane.smith@example.com"
             }
            """)
        )
        .andExpect(status().isCreated())
        .andExpect(header().exists("Location"))
        .andReturn();

      assertThat(result.getResponse().getHeader("Location"))
        .isNotNull()
        .contains("/api/customers/42");
    }

    @ParameterizedTest
    @DisplayName("Should handle different customer IDs")
    @ValueSource(longs = {1L, 42L, 9999L})
    void shouldHandleDifferentCustomerIds(long customerId) throws Exception {
      when(customerService.createNewCustomer(any(ObjectNode.class)))
        .thenReturn(customerId);

      mockMvc
        .perform(post("/api/customers")
          .contentType(APPLICATION_JSON)
          .content("""
             {
               "first_name": "Test",
               "last_name": "User",
               "email": "test.user@example.com"
             }
            """)
        )
        .andExpect(status().isCreated())
        .andExpect(header().string("Location",
          Matchers.containsString(String.format("/api/customers/%d", customerId))));
    }

    @Test
    @DisplayName("Should forward customer data to service")
    void shouldForwardCustomerDataToService() throws Exception {
      when(customerService.createNewCustomer(any(ObjectNode.class)))
        .thenReturn(1L);

      mockMvc
        .perform(post("/api/customers")
          .contentType(APPLICATION_JSON)
          .content("""
             {
               "first_name": "Richard",
               "last_name": "Roe",
               "email": "richard.roe@example.com"
             }
            """)
        )
        .andExpect(status().isCreated());
    }
  }
}
