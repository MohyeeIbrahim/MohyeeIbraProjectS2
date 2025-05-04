package org.example;

import java.util.Random;

public class FinancialReportService {
    private  CustomerManager customerManager;

    public FinancialReportService(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
    public static class Report {
        public final double revenue;
        public final double expenses;
        public final double netProfit;

        public Report(double revenue, double expenses) {
            this.revenue = revenue;
            this.expenses = expenses;
            this.netProfit = revenue - expenses;
        }

        public double getRevenue() {
            return revenue;
        }

        public double getExpenses() {
            return expenses;
        }

        public double getNetProfit() {
            return netProfit;
        }
    }

    public Report generateReport(int month, int year) {
        Random random = new Random((month + 1) * year);
        double revenue = 10000 + random.nextDouble() * 50000;
        double expenses = 5000 + random.nextDouble() * 20000;
        return new Report(revenue, expenses);
    }
}