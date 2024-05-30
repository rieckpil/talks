package de.rieckpil.workshop.basics;

import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.time.LocalDate;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

// @ExtendWith(SpringExtension.class)
class SampleTest {

  @BeforeEach
  void setUp() {
    // setup, preparation and initialization
  }

  @Test
  void shouldCreateNewUser() {
    assertEquals("DUKE", "duke".toUpperCase());
  }

  @Test
  void assertJExample() {
    Assertions.assertThat("duke".toUpperCase()).isEqualTo("DUKE");

    Assertions.assertThat(List.of("Duke")).hasSize(1);

    Assertions.assertThat(LocalDate.now()).isAfter(LocalDate.now().minusDays(1));
  }

  @Test
  void hamcrestExample() {
    MatcherAssert.assertThat("duke".toUpperCase(), equalTo("DUKE"));

    MatcherAssert.assertThat(List.of("Duke"), hasSize(1));
  }

  @Test
  void jsonAssertExample() throws JSONException {

    String actual = """
       {
          "name": "duke",
          "age": 42
       }
      """;

    String expected = """
       {
          "name": "duke"
       }
      """;

    JSONAssert
      .assertEquals(expected, actual, false);
  }

  @Test
  void jsonPathExample() {

    String result = """
        {
        "age": "42",
        "name": "duke",
        "tags": ["java", "jdk"]
        }
      """;

    assertEquals(2,
      JsonPath.parse(result)
        .read("$.tags.length()", Long.class));

    assertEquals("duke", JsonPath.parse(result).read("$.name", String.class));
  }

  @Test
  void awaitilityExample() {
    TestCounter counter = new TestCounter();

    new Thread(() -> counter.incrementUntil(10)).start();

    await().atMost(5, SECONDS).until(() -> counter.getCount() == 10);
  }

  static class TestCounter {
    private int count = 0;

    public void increment() {
      count++;
    }

    public int getCount() {
      return count;
    }

    public void incrementUntil(int target) {
      while (count < target) {
        increment();
        try {
          Thread.sleep(100); // Simulate some delay
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}
