package de.rieckpil.talks.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Poorly designed tests for DiscountCalculator")
class DiscountCalculatorPoorTest {

    private final DiscountCalculator cut = new DiscountCalculator();

    @Test
    @DisplayName("should calculate discount for regular customer")
    void shouldCalculateDiscountForRegularCustomer() {
        // Arrange
        Customer customer = new Customer("1", "John Doe", false);
        BigDecimal amount = new BigDecimal("100.00");

        // Act
        BigDecimal discount = cut.calculateDiscount(amount);

        // Assert
        // This assertion is too general - doesn't validate the actual value
        assertThat(discount).isNotNull();
        assertThat(discount).isPositive();
    }

    @Test
    @DisplayName("should calculate discount for premium order")
    void shouldCalculateDiscountForPremiumOrder() {
        // Arrange
        Customer customer = new Customer("1", "John Doe", false);
        BigDecimal amount = new BigDecimal("2000.00");

        // Act
        BigDecimal discount = cut.calculateDiscount(amount);

        // Assert
        // This covers the code path but doesn't verify the correct calculation
        assertThat(discount).isGreaterThan(new BigDecimal("100.00"));
    }
}
