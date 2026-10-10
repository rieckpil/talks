package de.rieckpil.talks.book;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerSpringBootTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private BookService bookService;

  @Test
  void shouldReturnAllBooks() throws Exception {
    given(bookService.findAll()).willReturn(List.of(new Book("Effective Java", "Joshua Bloch", "9780134685991")));

    mockMvc.perform(get("/api/books"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].title").value("Effective Java"));
  }

  @Test
  void shouldRejectBookWithoutTitle() throws Exception {
    mockMvc.perform(post("/api/books")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
          {"title": "", "author": "Joshua Bloch", "isbn": "9780134685991"}
          """))
      .andExpect(status().isBadRequest());
  }
}
