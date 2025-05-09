package de.rieckpil.talks.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class DiscountCalculator {

  private static final BigDecimal PREMIUM_THRESHOLD = new BigDecimal("1000.00");
  private static final BigDecimal STANDARD_DISCOUNT = new BigDecimal("0.05");  // 5%
  private static final BigDecimal PREMIUM_DISCOUNT = new BigDecimal("0.10");   // 10%
  private static final BigDecimal FIRST_TIME_BONUS = new BigDecimal("0.02");   // 2%
  private static final BigDecimal SEASONAL_DISCOUNT = new BigDecimal("0.03");  // 3%

  public BigDecimal calculateDiscount(Customer customer, BigDecimal totalAmount, boolean seasonalPromoActive) {
    BigDecimal discountRate = BigDecimal.ZERO;

    // Standard discount for all customers (5%)
    discountRate = discountRate.add(STANDARD_DISCOUNT);

    // Premium discount for orders over $1000 (additional 10%)
    if (totalAmount.compareTo(PREMIUM_THRESHOLD) >= 0) {
      discountRate = discountRate.add(PREMIUM_DISCOUNT);
    }

    // First-time customer bonus (additional 2%)
    if (customer.firstTimeCustomer()) {
      discountRate = discountRate.add(FIRST_TIME_BONUS);
    }

    // Seasonal promotion discount if active (additional 3%)
    if (seasonalPromoActive) {
      discountRate = discountRate.add(SEASONAL_DISCOUNT);
    }

    // Calculate discount amount
    BigDecimal discountAmount = totalAmount.multiply(discountRate);

    // Return the discount amount rounded to 2 decimal places
    return discountAmount.setScale(2, RoundingMode.HALF_UP);
  }
}
