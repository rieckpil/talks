package de.rieckpil.talks.time;

import java.time.LocalDate;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BirthdayServiceTest {

    private final BirthdayService cut = new BirthdayService();

    @Nested
    class ProblemsWithLocalDateNow {

        @Test
        void shouldReturnTrueWhenTodayIsBirthday() {
            // Arrange
            LocalDate today = LocalDate.now();
            LocalDate birthday = LocalDate.of(1990, today.getMonth(), today.getDayOfMonth());

            // Act
            boolean result = cut.isTodayBirthday(birthday);

            // Assert
            assertThat(result).isTrue();
        }

        @Test
        void shouldReturnFalseWhenTodayIsNotBirthday() {
            // Arrange
            LocalDate today = LocalDate.now();
            LocalDate birthday = today.plusDays(1); // Tomorrow's date

            // Act
            boolean result = cut.isTodayBirthday(birthday);

            // Assert
            assertThat(result).isFalse(); // But this fails on the day before birthday!
        }

        /**
         * PROBLEMS WITH THIS APPROACH:
         *
         * 1. Tests depend on the current date, making them inconsistent and potentially failing
         *    at different times of the year
         *
         * 2. Hard to test edge cases like:
         *    - Day before birthday
         *    - Day of birthday
         *    - Day after birthday
         *    - Birthday on February 29 (leap year)
         *
         * 3. Impossible to get repeatable test results
         *
         * 4. Tests become "time bombs" that suddenly fail at certain dates
         */
    }
}
