package de.rieckpil.talks.order;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendOrderConfirmation(String customerId, String message) {
        // In a real application, this would send an email or SMS
    }
}
