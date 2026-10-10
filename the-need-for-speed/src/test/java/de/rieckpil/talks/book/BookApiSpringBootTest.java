package de.rieckpil.talks.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class BookApiSpringBootTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void shouldCreateAndFetchBook() {
    BookRequest request = new BookRequest("Clean Code", "Robert C. Martin", "9780132350884");

    ResponseEntity<Book> createResponse = testRestTemplate.postForEntity("/api/books", request, Book.class);

    assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(createResponse.getBody()).isNotNull();

    Book fetchedBook = testRestTemplate.getForObject("/api/books/" + createResponse.getBody().getId(), Book.class);

    assertThat(fetchedBook.getTitle()).isEqualTo("Clean Code");
  }

  @Test
  void shouldReturnNotFoundForUnknownBook() {
    ResponseEntity<String> response = testRestTemplate.getForEntity("/api/books/9999", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}
