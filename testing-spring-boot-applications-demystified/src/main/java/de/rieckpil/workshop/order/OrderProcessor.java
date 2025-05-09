package de.rieckpil.workshop.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderProcessor {

    private final InventoryService inventoryService;
    private final NotificationService notificationService;
    private final PaymentGateway paymentGateway;

    public OrderProcessor(InventoryService inventoryService, 
                         NotificationService notificationService,
                         PaymentGateway paymentGateway) {
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
        this.paymentGateway = paymentGateway;
    }

    public OrderResult processOrder(Order order) {
        // Validate order has items
        if (order.items().isEmpty()) {
            return new OrderResult(OrderStatus.REJECTED, "Order must contain at least one item");
        }

        // Check inventory availability
        for (OrderItem item : order.items()) {
            boolean available = inventoryService.checkAvailability(item.productId(), item.quantity());
            if (!available) {
                return new OrderResult(OrderStatus.REJECTED, 
                    "Product " + item.productId() + " is not available in requested quantity");
            }
        }

        // Process payment
        String transactionId = paymentGateway.processPayment(
            order.customerId(), calculateTotalAmount(order));
        
        if (transactionId == null || transactionId.isEmpty()) {
            return new OrderResult(OrderStatus.PAYMENT_FAILED, "Payment processing failed");
        }

        // Reserve inventory
        for (OrderItem item : order.items()) {
            inventoryService.reserveItems(item.productId(), item.quantity());
        }

        // Send confirmation
        notificationService.sendOrderConfirmation(order.customerId(), generateOrderConfirmation(order, transactionId));

        return new OrderResult(OrderStatus.COMPLETED, transactionId);
    }

    private BigDecimal calculateTotalAmount(Order order) {
        return order.items().stream()
            .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String generateOrderConfirmation(Order order, String transactionId) {
        return "Order " + UUID.randomUUID() + " confirmed with transaction ID: " + transactionId;
    }
}