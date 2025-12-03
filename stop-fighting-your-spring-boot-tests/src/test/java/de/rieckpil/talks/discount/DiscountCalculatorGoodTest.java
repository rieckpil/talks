package de.rieckpil.talks.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Well-designed tests for DiscountCalculator")
class DiscountCalculatorGoodTest {

    private final DiscountCalculator cut = new DiscountCalculator();

    @Nested
    @DisplayName("Standard customer scenarios")
    class StandardCustomerTests {

        @Test
        @DisplayName("should apply 5% discount for standard customer")
        void shouldApplyStandardDiscountForRegularCustomer() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", false);
            BigDecimal amount = new BigDecimal("100.00");

            // Act
            BigDecimal discount = cut.calculateDiscount(amount);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("5.00"));
        }
    }

    @Nested
    @DisplayName("Premium order scenarios")
    class PremiumOrderTests {

        @Test
        @DisplayName("should apply 15% discount for premium order")
        void shouldApplyPremiumDiscountForLargeOrder() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", false);
            BigDecimal amount = new BigDecimal("2000.00");

            // Act
            BigDecimal discount = cut.calculateDiscount(amount);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("300.00"));
        }

        @Test
        @DisplayName("should apply 15% discount for order at premium threshold")
        void shouldApplyPremiumDiscountAtThreshold() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", false);
            BigDecimal amount = new BigDecimal("1000.00");

            // Act
            BigDecimal discount = cut.calculateDiscount(amount);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("150.00"));
        }
    }

    @ParameterizedTest(name = "should calculate {1} discount for amount={0}")
    @CsvSource({
        "100.00, 5.00",
        "999.99, 50.00",
        "1000.00, 150.00",
        "2000.00, 300.00"
    })
    void shouldCalculateCorrectDiscountForAllScenarios(String amount, String expectedDiscount) {
        // Arrange
        Customer customer = new Customer("1", "John Doe", false);
        BigDecimal orderAmount = new BigDecimal(amount);

        // Act
        BigDecimal discount = cut.calculateDiscount(orderAmount);

        // Assert
        assertThat(discount).isEqualByComparingTo(new BigDecimal(expectedDiscount));
    }
}
