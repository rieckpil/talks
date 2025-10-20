package de.rieckpil.talks.basics;


import de.rieckpil.talks.config.SecurityConfig;
import de.rieckpil.talks.customner.CustomerController;
import de.rieckpil.talks.customner.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
//        .with(SecurityMockMvcRequestPostProcessors.jwt())
          .contentType(APPLICATION_JSON)
          .content("""
             {
               "first_name": "Mike",
               "last_name": "Doe",
               "email": "john.doe@jug.ch"
             }
            """)
      )
      .andExpect(status().isCreated())
      .andExpect(header().string("Location",
        Matchers.containsString("/api/customers/42")));
  }
}



