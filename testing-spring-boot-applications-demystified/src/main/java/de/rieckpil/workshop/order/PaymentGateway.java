package de.rieckpil.workshop.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentGateway {

    public String processPayment(String customerId, BigDecimal amount) {
        // In a real application, this would call a payment provider
        // For demo purposes, return a transaction ID
        return UUID.randomUUID().toString();
    }
}