package de.rieckpil.talks.order;

import java.util.List;

public record Order(String customerId, List<OrderItem> items) {
}
