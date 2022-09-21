package de.rieckpil.talks;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;

  @Test
  void shouldReturnLocationOfNewlyCreatedCustomer() throws Exception {

    when(customerService.createNewCustomer(any(ObjectNode.class)))
      .thenReturn(42L);

    this.mockMvc
      .perform(post("/api/customers")
        .contentType(APPLICATION_JSON)
        .content("""
           {
             "first_name": "John",
             "last_name": "Doe",
             "email": "john.doe@devoxx.be"
           }
          """)
      )
      .andExpect(status().isCreated())

      .andExpect(header().string("Location",
        Matchers.containsString("/api/customers/42")));
  }
}


