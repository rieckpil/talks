package de.rieckpil.talks.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("OrderProcessor Unit Tests")
class OrderProcessorTest {

    @Mock
    private InventoryService inventoryService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private OrderProcessor cut;

    @Nested
    @DisplayName("When processing orders")
    class ProcessOrderTests {

        @Test
        @DisplayName("should reject order when no items are present")
        void shouldRejectOrderWhenNoItemsArePresent() {
            // Arrange
            Order order = new Order("customer-123", Collections.emptyList());

            // Act
            OrderResult result = cut.processOrder(order);

            // Assert
            assertThat(result.status()).isEqualTo(OrderStatus.REJECTED);
            assertThat(result.message()).contains("must contain at least one item");

            verify(inventoryService, never()).checkAvailability(anyString(), anyInt());
            verify(paymentGateway, never()).processPayment(anyString(), any());
        }

        @Test
        @DisplayName("should reject order when inventory is not available")
        void shouldRejectOrderWhenInventoryIsNotAvailable() {
            // Arrange
            OrderItem item = new OrderItem("product-1", 5, BigDecimal.valueOf(10.00));
            Order order = new Order("customer-123", List.of(item));

            when(inventoryService.checkAvailability("product-1", 5)).thenReturn(false);

            // Act
            OrderResult result = cut.processOrder(order);

            // Assert
            assertThat(result.status()).isEqualTo(OrderStatus.REJECTED);
            assertThat(result.message()).contains("not available");

            verify(paymentGateway, never()).processPayment(anyString(), any());
            verify(inventoryService, never()).reserveItems(anyString(), anyInt());
        }

        @Test
        @DisplayName("should return payment failed when payment processing fails")
        void shouldReturnPaymentFailedWhenPaymentProcessingFails() {
            // Arrange
            OrderItem item = new OrderItem("product-1", 5, BigDecimal.valueOf(10.00));
            Order order = new Order("customer-123", List.of(item));

            when(inventoryService.checkAvailability("product-1", 5)).thenReturn(true);
            when(paymentGateway.processPayment(eq("customer-123"), any())).thenReturn("");

            // Act
            OrderResult result = cut.processOrder(order);

            // Assert
            assertThat(result.status()).isEqualTo(OrderStatus.PAYMENT_FAILED);

            verify(inventoryService, never()).reserveItems(anyString(), anyInt());
            verify(notificationService, never()).sendOrderConfirmation(anyString(), anyString());
        }

        @Test
        @DisplayName("should complete order successfully when all conditions are met")
        void shouldCompleteOrderSuccessfullyWhenAllConditionsAreMet() {
            // Arrange
            OrderItem item1 = new OrderItem("product-1", 5, BigDecimal.valueOf(10.00));
            OrderItem item2 = new OrderItem("product-2", 2, BigDecimal.valueOf(25.50));
            Order order = new Order("customer-123", List.of(item1, item2));

            when(inventoryService.checkAvailability("product-1", 5)).thenReturn(true);
            when(inventoryService.checkAvailability("product-2", 2)).thenReturn(true);
            when(paymentGateway.processPayment(eq("customer-123"), eq(BigDecimal.valueOf(101.00)))).thenReturn("tx-123");

            // Act
            OrderResult result = cut.processOrder(order);

            // Assert
            assertThat(result.status()).isEqualTo(OrderStatus.COMPLETED);
            assertThat(result.message()).isEqualTo("tx-123");

            verify(inventoryService).reserveItems("product-1", 5);
            verify(inventoryService).reserveItems("product-2", 2);
            verify(notificationService).sendOrderConfirmation(eq("customer-123"), anyString());
        }
    }
}
