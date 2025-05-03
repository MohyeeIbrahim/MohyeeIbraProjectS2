package org.example;

public class FinancialReportService {
    public static class Report {
        public double revenue;
        public double expenses;
        public double netProfit;

        public Report(double revenue, double expenses) {
            this.revenue = revenue;
            this.expenses = expenses;
            this.netProfit = revenue - expenses;
        }

        public String formatReport(int month, int year) {
            return String.format(
                    "Financial Report for %02d/%d\nTotal Revenue: $%.2f\nTotal Expenses: $%.2f\nNet Profit: $%.2f",
                    month, year, revenue, expenses, netProfit
            );
        }
    }

    public Report generateReport(int month, int year) {
        double revenue = 10000 + (month * 100);   // Dummy revenue formula
        double expenses = 5000 + (month * 50);    // Dummy expense formula
        return new Report(revenue, expenses);
    }
}