package de.rieckpil.workshop.order;

import java.math.BigDecimal;

public record OrderItem(String productId, int quantity, BigDecimal price) {
}