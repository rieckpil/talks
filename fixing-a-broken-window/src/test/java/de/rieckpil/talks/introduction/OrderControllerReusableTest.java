package de.rieckpil.talks.introduction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class OrderControllerReusableTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldAllowAccessForAnonymousUsers() throws Exception {
    this.mockMvc
            .perform(get("/api/orders")
                    .header(ACCEPT, APPLICATION_JSON))
            .andExpect(status().is(200))
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.size()", is(1)));
  }
}
