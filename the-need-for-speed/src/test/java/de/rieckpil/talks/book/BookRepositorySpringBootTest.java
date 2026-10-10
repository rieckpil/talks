package de.rieckpil.talks.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.jpa.show-sql=true")
class BookRepositorySpringBootTest {

  @Autowired
  private BookRepository bookRepository;

  @Test
  void shouldFindBookByIsbn() {
    bookRepository.save(new Book("Refactoring", "Martin Fowler", "9780134757599"));

    assertThat(bookRepository.findByIsbn("9780134757599")).isPresent();
  }

  @Test
  void shouldReturnEmptyForUnknownIsbn() {
    assertThat(bookRepository.findByIsbn("0000000000000")).isEmpty();
  }
}
