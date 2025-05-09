package de.rieckpil.workshop.order;

import java.util.List;

public record Order(String customerId, List<OrderItem> items) {
}