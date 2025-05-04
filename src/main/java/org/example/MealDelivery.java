package org.example;

import java.time.LocalDateTime;

public class MealDelivery {
    private final LocalDateTime deliveryTime;
    private final boolean cancelled;

    public MealDelivery(LocalDateTime deliveryTime, boolean cancelled) {
        this.deliveryTime = deliveryTime;
        this.cancelled = cancelled;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
