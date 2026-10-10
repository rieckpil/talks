package de.rieckpil.talks.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
class BookDeleteSpringBootTest {

  @Autowired
  private BookService bookService;

  @Autowired
  private BookRepository bookRepository;

  @Test
  void shouldDeleteBook() {
    Book createdBook = bookService.create(new BookRequest("To delete", "Author", "222"));

    bookService.delete(createdBook.getId());

    assertThat(bookRepository.findById(createdBook.getId())).isEmpty();
  }
}
