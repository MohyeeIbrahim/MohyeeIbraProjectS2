package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Order {
    public int orderId;
    private  LocalDate date;
    private  List<Meal> meals;

    public Order(int id, LocalDate date, List<Meal> meals) {
        this.orderId = id;
        this.date = date;
        this.meals = new ArrayList<>(meals);
    }

    public LocalDate getDate() {
        return date;
    }
    public List<Meal> getMeals() {
        return new ArrayList<>(meals);
    }

    public String formatForDisplay(int orderNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(orderNumber).append(". Order #").append(orderId).append(" [").append(date).append("] ");
        List<String> mealNames = new ArrayList<String>();
        for (Meal meal : meals) {
            mealNames.add(meal.getName());
        }
        sb.append(String.join(", ", mealNames));
        return sb.toString();
    }
    public String generateInvoice(double taxRate) {
        double subtotal = calculateSubtotal();
        double taxes = subtotal * taxRate;
        double total = subtotal + taxes;

        StringBuilder invoice = new StringBuilder();
        invoice.append("=== Order #").append(orderId).append(" Invoice ===\n");
        invoice.append("Date: ").append(date).append("\n\n");
        invoice.append("Items:\n");

        for (Meal meal : meals) {
            invoice.append("- ").append(meal.getName())
                    .append(": $").append(String.format("%.2f", meal.getPrice()))
                    .append("\n");
        }

        invoice.append("\nSubtotal: $").append(String.format("%.2f", subtotal)).append("\n");
        invoice.append(String.format("Tax (%d%%): $%.2f\n", (int)(taxRate * 100), taxes));
        invoice.append("Total: $").append(String.format("%.2f", total)).append("\n");

        return invoice.toString();
    }

    private double calculateSubtotal() {
        double subtotal = 0.0;
        for (Meal meal : meals) {
            subtotal += meal.getPrice();
        }
        return subtotal;
    }

}
