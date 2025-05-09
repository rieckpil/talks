package de.rieckpil.talks.discount;

import org.junit.jupiter.api.DisplayName;

/**
 * This class demonstrates how mutation testing works by showing examples of
 * potential mutations that PIT might introduce and whether our tests would catch them.
 */
@DisplayName("Mutation Testing Examples")
public class MutationTestingExample {

    /**
     * Example 1: Poor Test with High Coverage but Low Mutation Score
     *
     * Consider the poor test: shouldCalculateDiscountForPremiumOrder()
     *
     * POTENTIAL MUTATION 1: Change "totalAmount.compareTo(PREMIUM_THRESHOLD) >= 0" to "totalAmount.compareTo(PREMIUM_THRESHOLD) > 0"
     *
     * The poor test would still pass if this mutation were introduced because:
     * - It tests with amount=2000.00 which is > 1000.00, not equal to
     * - It doesn't test the boundary case of exactly 1000.00
     * - It only checks that the discount is > 100.00, not the exact amount
     *
     * MUTATION SURVIVING = Business logic bug introduced!
     */

    /**
     * Example 2: Poor Test with Weak Assertions
     *
     * Consider the poor test: shouldCalculateDiscountWithSeasonalPromotion()
     *
     * POTENTIAL MUTATION 2: Change "discountRate.add(SEASONAL_DISCOUNT)" to "discountRate"
     * (Remove the addition of seasonal discount)
     *
     * The poor test would still pass if this mutation were introduced because:
     * - It only asserts that the result is greater than zero
     * - It doesn't verify the actual calculation or that the seasonal discount was applied
     *
     * MUTATION SURVIVING = Business logic bug introduced!
     */

    /**
     * Example 3: Good Test that Would Kill Mutations
     *
     * Consider the good test: shouldApplyStandardAndSeasonalDiscountForRegularCustomer()
     *
     * POTENTIAL MUTATION 3: Change "discountRate.add(SEASONAL_DISCOUNT)" to "discountRate"
     * (Remove the addition of seasonal discount)
     *
     * The good test would FAIL if this mutation were introduced because:
     * - It verifies the exact calculated amount (8.00) that includes the seasonal discount
     * - Without the seasonal discount, the result would be 5.00, failing the assertion
     *
     * MUTATION KILLED = Bug detected!
     */

    /**
     * Example 4: Boundary Testing
     *
     * The good tests include:
     * - Tests for exactly 1000.00 (the boundary)
     * - Tests for values just below and above boundaries
     * - Tests for all combinations of conditions
     *
     * POTENTIAL MUTATION 4: Change ">=" to ">"
     *
     * The good test with parameterized cases would catch this immediately by failing
     * when testing the boundary condition.
     *
     * MUTATION KILLED = Bug detected!
     */
}
