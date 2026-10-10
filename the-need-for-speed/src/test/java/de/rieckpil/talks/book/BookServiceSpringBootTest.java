package de.rieckpil.talks.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceSpringBootTest {

  @Autowired
  private BookService bookService;

  @MockitoSpyBean
  private BookRepository bookRepository;

  @Test
  void shouldUpdateBook() {
    Book createdBook = bookService.create(new BookRequest("Old title", "Author", "111"));

    Book updatedBook = bookService.update(createdBook.getId(), new BookRequest("New title", "Author", "111"));

    assertThat(updatedBook.getTitle()).isEqualTo("New title");
    verify(bookRepository).findById(createdBook.getId());
  }

  @Test
  void shouldThrowForUnknownBook() {
    assertThatThrownBy(() -> bookService.findById(4711L)).isInstanceOf(BookNotFoundException.class);
  }
}
