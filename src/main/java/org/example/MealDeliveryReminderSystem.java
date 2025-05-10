package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MealDeliveryReminderSystem {

    private LocalDateTime currentTime;
    private final List<MealDelivery> deliveries = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

    public void setCurrentTime(String timeStr) {
        this.currentTime = parseDateTime(timeStr);
    }

    public void scheduleDelivery(String deliveryTimeStr, boolean cancelled) {
        LocalDateTime deliveryTime = parseDateTime(deliveryTimeStr);
        if (deliveryTime != null) {
            deliveries.add(new MealDelivery(deliveryTime, cancelled));
        }
    }

    public List<String> sendReminders(int hoursBefore) {
        List<String> reminders = new ArrayList<>();

        for (MealDelivery delivery : deliveries) {
            if (delivery.isCancelled()) continue;

            LocalDateTime reminderTime = delivery.getDeliveryTime().minusHours(hoursBefore);
            if (currentTime.equals(reminderTime)) {
                String message = "Reminder: Your meal delivery is scheduled for " +
                        delivery.getDeliveryTime().format(formatter) + ".";
                reminders.add(message);
            }
        }

        return reminders;
    }

    public boolean containsNotificationForTime(String formattedTime, List<String> notifications) {
        for (String notification : notifications) {
            if (notification.contains(formattedTime)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsCancelledDeliveryNotification(List<String> notifications) {
        for (MealDelivery delivery : deliveries) {
            if (delivery.isCancelled()) {
                String formattedTime = delivery.getDeliveryTime().format(formatter);
                String expectedMessage = "Reminder: Your meal delivery is scheduled for " + formattedTime + ".";
                if (notifications.contains(expectedMessage)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getFormattedTime(int hour, int minute) {
        return String.format("%02d:%02d AM", hour, minute);
    }

    private LocalDateTime parseDateTime(String input) {
        try {
            return LocalDateTime.parse(input.replace(" ", "T"));
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e2) {
                System.err.println("Invalid date format: " + input);
                return null;
            }
        }
    }
}
