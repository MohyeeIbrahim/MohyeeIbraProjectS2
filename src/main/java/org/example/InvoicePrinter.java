package org.example;

import java.util.List;

public class InvoicePrinter {

    public static String generateInvoiceSummary(List<Meal> meals, double taxRate) {
        double subtotal = 0.0;
        for (Meal meal : meals) {
            subtotal += meal.getPrice();
        }
        double taxes = subtotal * taxRate;
        double total = subtotal + taxes;

        StringBuilder invoice = new StringBuilder();
        invoice.append("Invoice Summary:\n");
        invoice.append("Items:\n");
        for (Meal meal : meals) {
            invoice.append("- ").append(meal.getName())
                    .append(": $").append(String.format("%.2f", meal.getPrice()))
                    .append("\n");
        }
        invoice.append("Subtotal: $").append(String.format("%.2f", subtotal)).append("\n");
        invoice.append("Taxes (").append((int)(taxRate * 100)).append("%): $").append(String.format("%.2f", taxes)).append("\n");
        invoice.append("Total Payable: $").append(String.format("%.2f", total)).append("\n");

        return invoice.toString();
    }
}