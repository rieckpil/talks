package de.rieckpil.talks;

import java.time.LocalDate;
import java.util.List;

import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
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
}
