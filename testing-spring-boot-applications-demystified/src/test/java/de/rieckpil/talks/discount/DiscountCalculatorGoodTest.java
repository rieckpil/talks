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
        @DisplayName("should apply 5% discount for standard customer with no promotions")
        void shouldApplyStandardDiscountForRegularCustomer() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", false);
            BigDecimal amount = new BigDecimal("100.00");
            boolean isSeasonalPromo = false;

            // Act
            BigDecimal discount = cut.calculateDiscount(customer, amount, isSeasonalPromo);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("5.00"));
        }

        @Test
        @DisplayName("should apply 8% discount for standard customer during seasonal promotion")
        void shouldApplyStandardAndSeasonalDiscountForRegularCustomer() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", false);
            BigDecimal amount = new BigDecimal("100.00");
            boolean isSeasonalPromo = true;

            // Act
            BigDecimal discount = cut.calculateDiscount(customer, amount, isSeasonalPromo);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("8.00"));
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
            boolean isSeasonalPromo = false;

            // Act
            BigDecimal discount = cut.calculateDiscount(customer, amount, isSeasonalPromo);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("300.00"));
        }

        @Test
        @DisplayName("should apply 18% discount for premium order during seasonal promotion")
        void shouldApplyPremiumAndSeasonalDiscountForLargeOrder() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", false);
            BigDecimal amount = new BigDecimal("2000.00");
            boolean isSeasonalPromo = true;

            // Act
            BigDecimal discount = cut.calculateDiscount(customer, amount, isSeasonalPromo);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("360.00"));
        }
    }

    @Nested
    @DisplayName("First-time customer scenarios")
    class FirstTimeCustomerTests {

        @Test
        @DisplayName("should apply 7% discount for first-time customer")
        void shouldApplyBonusDiscountForFirstTimeCustomer() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", true);
            BigDecimal amount = new BigDecimal("100.00");
            boolean isSeasonalPromo = false;

            // Act
            BigDecimal discount = cut.calculateDiscount(customer, amount, isSeasonalPromo);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("7.00"));
        }

        @Test
        @DisplayName("should apply 10% discount for first-time customer during seasonal promotion")
        void shouldApplyBonusAndSeasonalDiscountForFirstTimeCustomer() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", true);
            BigDecimal amount = new BigDecimal("100.00");
            boolean isSeasonalPromo = true;

            // Act
            BigDecimal discount = cut.calculateDiscount(customer, amount, isSeasonalPromo);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("10.00"));
        }
    }

    @Nested
    @DisplayName("Combined discount scenarios")
    class CombinedDiscountTests {

        @Test
        @DisplayName("should apply 17% for first-time customer with premium order")
        void shouldApplyAllDiscountsExceptSeasonal() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", true);
            BigDecimal amount = new BigDecimal("2000.00");
            boolean isSeasonalPromo = false;

            // Act
            BigDecimal discount = cut.calculateDiscount(customer, amount, isSeasonalPromo);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("340.00"));
        }

        @Test
        @DisplayName("should apply 20% for first-time customer with premium order during seasonal promotion")
        void shouldApplyAllDiscounts() {
            // Arrange
            Customer customer = new Customer("1", "John Doe", true);
            BigDecimal amount = new BigDecimal("2000.00");
            boolean isSeasonalPromo = true;

            // Act
            BigDecimal discount = cut.calculateDiscount(customer, amount, isSeasonalPromo);

            // Assert
            assertThat(discount).isEqualByComparingTo(new BigDecimal("400.00"));
        }
    }

    @ParameterizedTest(name = "should calculate {2}% discount for amount={0}, firstTime={1}")
    @CsvSource({
        "100.00, false, 5.00, false",
        "100.00, true, 7.00, false",
        "2000.00, false, 300.00, false",
        "2000.00, true, 340.00, false",
        "100.00, false, 8.00, true",
        "100.00, true, 10.00, true",
        "2000.00, false, 360.00, true",
        "2000.00, true, 400.00, true"
    })
    void shouldCalculateCorrectDiscountForAllScenarios(
            String amount, boolean isFirstTime, String expectedDiscount, boolean isSeasonalPromo) {
        // Arrange
        Customer customer = new Customer("1", "John Doe", isFirstTime);
        BigDecimal orderAmount = new BigDecimal(amount);

        // Act
        BigDecimal discount = cut.calculateDiscount(customer, orderAmount, isSeasonalPromo);

        // Assert
        assertThat(discount).isEqualByComparingTo(new BigDecimal(expectedDiscount));
    }
}
