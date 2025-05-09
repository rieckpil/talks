package de.rieckpil.workshop.order;

public record OrderResult(OrderStatus status, String message) {
}