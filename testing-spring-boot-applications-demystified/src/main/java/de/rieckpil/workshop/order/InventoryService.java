package de.rieckpil.workshop.order;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public boolean checkAvailability(String productId, int quantity) {
        // In a real application, this would check a database
        // For demo purposes, this is simplified
        return true;
    }

    public void reserveItems(String productId, int quantity) {
        // In a real application, this would update a database
    }
}