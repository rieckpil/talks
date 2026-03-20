package de.rieckpil.talks.basics;

import de.rieckpil.talks.config.SecurityConfig;
import de.rieckpil.talks.customer.CustomerController;
import de.rieckpil.talks.customer.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@Import(SecurityConfig.class)
class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CustomerService customerService;

  @Test
  @WithMockUser
  void shouldReturnLocationOfNewlyCreatedCustomer() throws Exception {

    when(customerService.createNewCustomer(any(String.class)))
      .thenReturn("42");

    this.mockMvc
      .perform(post("/api/customers")
        // .with(SecurityMockMvcRequestPostProcessors.user("user").roles("ADMIN", "USER"))
        .contentType(APPLICATION_JSON)
        .content("""
           {
             "firstName": "Mike",
             "lastName": "Doe",
             "email": "mike.doe@jug.ch"
           }
          """)
      )
      .andExpect(status().isCreated())
      .andExpect(header().string("Location",
        Matchers.containsString("/api/customers/42")));
  }
}



