package org.example;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Random;

public class FinancialReportService {
    private final CustomerManager customerManager;
    private final Random random = new Random();

    public FinancialReportService(CustomerManager customerManager) {
        if (customerManager == null) {
            throw new IllegalArgumentException("CustomerManager cannot be null");
        }
        this.customerManager = customerManager;
    }

    public static class Report {
        private final double revenue;
        private final double expenses;
        private final double netProfit;
        private final int totalOrders;
        private final int totalMeals;

        public Report(double revenue, double expenses, int totalOrders, int totalMeals) {
            this.revenue = revenue;
            this.expenses = expenses;
            this.netProfit = revenue - expenses;
            this.totalOrders = totalOrders;
            this.totalMeals = totalMeals;
        }

        public double getRevenue() { return revenue; }
        public double getExpenses() { return expenses; }
        public double getNetProfit() { return netProfit; }
        public int getTotalOrders() { return totalOrders; }
        public int getTotalMeals() { return totalMeals; }
    }

    public Report generateReport(int month, int year) {
        validateMonthYear(month, year);

        double revenue = 0.0;
        int totalOrders = 0;
        int totalMeals = 0;

        for (Customer customer : customerManager.getAllCustomers()) {
            for (Order order : customer.getOrderHistory()) {
                LocalDate orderDate = order.getDate();
                if (orderDate.getMonthValue() == month && orderDate.getYear() == year) {
                    totalOrders++;
                    for (Meal meal : order.getMeals()) {
                        revenue += meal.getPrice();
                        totalMeals++;
                    }
                }
            }
        }

        random.setSeed((long) (month + 1) * year);
        double expenses = 500 + random.nextDouble() * 200;

        return new Report(
                roundToTwoDecimals(revenue),
                roundToTwoDecimals(expenses),
                totalOrders,
                totalMeals
        );
    }

    private void validateMonthYear(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1-12");
        }
        if (year < 2000 || year > Year.now().getValue()) {
            throw new IllegalArgumentException("Invalid year");
        }
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100) / 100.0;
    }
}