package de.rieckpil.talks.time;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.assertj.core.api.Assertions.assertThat;

class BirthdayServiceWithClockTest {

  private static final ZoneId ZONE_ID = ZoneId.systemDefault();

  @Nested
  class BenefitsOfInjectedClock {

    @Test
    void shouldReturnTrueWhenTodayIsBirthday() {
      // Arrange
      LocalDate fixedDate = LocalDate.of(2025, 5, 15);
      Clock fixedClock = Clock.fixed(
        fixedDate.atStartOfDay(ZONE_ID).toInstant(),
        ZONE_ID
      );

      BirthdayServiceWithClock cut = new BirthdayServiceWithClock(fixedClock);
      LocalDate birthday = LocalDate.of(1990, 5, 15); // Same month and day

      // Act
      boolean result = cut.isTodayBirthday(birthday);

      // Assert
      assertThat(result).isTrue();
    }

    @Test
    @DisplayName("should return false when today is not birthday")
    void shouldReturnFalseWhenTodayIsNotBirthday() {
      // Arrange
      LocalDate fixedDate = LocalDate.of(2025, 5, 15);
      Clock fixedClock = Clock.fixed(
        fixedDate.atStartOfDay(ZONE_ID).toInstant(),
        ZONE_ID
      );

      BirthdayServiceWithClock cut = new BirthdayServiceWithClock(fixedClock);
      LocalDate birthday = LocalDate.of(1990, 5, 16); // Next day

      // Act
      boolean result = cut.isTodayBirthday(birthday);

      // Assert
      assertThat(result).isFalse();
    }

    /**
     * BENEFITS OF THIS APPROACH:
     *
     * 1. Tests are deterministic and repeatable - they don't depend on the current date
     *
     * 2. Easy to test edge cases by controlling the current date:
     *    - Day before birthday
     *    - Day of birthday
     *    - Day after birthday
     *    - Leap years
     *
     * 3. Parameterized tests can verify multiple scenarios efficiently
     *
     * 4. No more "time bomb" tests that suddenly fail at certain dates
     *
     * 5. Production code is more modular and follows dependency injection principles
     */
  }

  @Nested
  class BenefitsForIntegrationTests {

    @TestConfiguration
    static class TestConfig {
      @Bean
      @Primary
      public Clock testClock() {
        return Clock.fixed(
          LocalDate.of(2025, 5, 15).atStartOfDay(ZoneId.systemDefault()).toInstant(),
          ZoneId.systemDefault()
        );
      }
    }
  }
}
