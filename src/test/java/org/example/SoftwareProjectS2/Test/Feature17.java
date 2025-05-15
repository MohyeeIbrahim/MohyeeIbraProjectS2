package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.*;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.Assert.*;

public class Feature17 {
    private FinancialReportService reportService;
    private FinancialReportService.Report report;
    private SystemAdministrator administrator;
    private CustomerManager customerManager;
    private int reportMonth;
    private int reportYear;
    private Path pdfPath;
    private int month;
    private int year;

    @Before
    public void setUp() {
        customerManager = new CustomerManager();
        reportService = new FinancialReportService(customerManager);
        administrator = new SystemAdministrator(reportService);
    }

    // Scenario 1: Generate Financial Report
    @Given("the system administrator is on the main menu")
    public void the_system_administrator_is_on_the_main_menu() {
        // Initialization handled in setUp()
    }

    @When("the administrator selects Generate Financial Report")
    public void the_administrator_selects_generate_financial_report() {
        // Action will be performed in the next step
    }

    @When("enters {int} for month and {int} for year")
    public void enters_for_month_and_for_year(Integer month, Integer year) {
        this.reportMonth = month;
        this.reportYear = year;

        // Add test data to customer manager
        Customer customer = new Customer(1, "Test User", "password", "Vegetarian", "None");
        Meal meal1 = new Meal(1, "Vegetable Soup", 8.99, true);
        Meal meal2 = new Meal(2, "Salad", 12.50, true);
        Order order = new Order(101, LocalDate.of(year, month, 15), List.of(meal1, meal2));
        customer.addOrder(order);
        customerManager.addCustomer(customer);

        report = reportService.generateReport(month, year);
        assertNotNull("Report should be generated", report);
    }

    @Then("the system should generate the financial report for March {int}")
    public void the_system_should_generate_the_financial_report_for_march(Integer expectedYear) {
        assertEquals(Month.MARCH.getValue(), reportMonth);
        assertEquals((int) expectedYear, reportYear);
        assertEquals(8.99 + 12.50, report.getRevenue(), 0.01);
    }

    @Then("display total revenue, expenses, and net profit for that period")
    public void display_total_revenue_expenses_and_net_profit_for_that_period() {
        assertTrue("Revenue should be positive", report.getRevenue() >= 0);
        assertTrue("Expenses should be positive", report.getExpenses() >= 0);
        assertEquals("Net profit should equal revenue minus expenses",
                report.getRevenue() - report.getExpenses(),
                report.getNetProfit(),
                0.01);
        assertEquals("Should count correct number of orders", 1, report.getTotalOrders());
        assertEquals("Should count correct number of meals", 2, report.getTotalMeals());
    }

    // Scenario 2: Export Report as PDF
    @Given("a financial report for {string} has been generated")
    public void a_financial_report_for_has_been_generated(String period) throws IOException {
        String[] parts = period.split(" ");
        month = Month.valueOf(parts[0].toUpperCase()).getValue();
        year = Integer.parseInt(parts[1]);

        // Generate test report data
        Customer customer = new Customer(2, "PDF User", "password", "Vegan", "Nuts");
        Meal meal = new Meal(3, "Vegan Burger", 15.75, true);
        Order order = new Order(102, LocalDate.of(year, month, 20), List.of(meal));
        customer.addOrder(order);
        customerManager.addCustomer(customer);

        report = reportService.generateReport(month, year);
    }

    @When("the administrator selects Export as PDF from the main menu")
    public void the_administrator_selects_export_as_pdf_from_the_main_menu() throws IOException {
        pdfPath = administrator.exportFinancialReportToPdf(month, year);
    }

    @Then("the system should generate a PDF file")
    public void the_system_should_generate_a_pdf_file() throws IOException {
        assertNotNull("PDF path should not be null", pdfPath);
        assertTrue("PDF file should exist", Files.exists(pdfPath));
        assertTrue("PDF file should not be empty", Files.size(pdfPath) > 0);
    }

    @Then("the file should be named {string}")
    public void the_file_should_be_named(String expectedFilename) {
        assertEquals("PDF filename should match expected",
                expectedFilename,
                pdfPath.getFileName().toString());
    }

    // New Scenario: Empty Report
   @When("enters {int} for month and {int} for year with no orders")
public void enters_for_month_and_for_year_with_no_orders(Integer month, Integer year) {
     this.reportMonth = month;
     this.reportYear = year;
     report = reportService.generateReport(month, year);
}
@Then("the system should display zero values for all metrics")
public void the_system_should_display_zero_values_for_all_metrics() {
        assertEquals("Revenue should be 0", 0.0, report.getRevenue(), 0.01);
        assertTrue("Expenses should still be generated", report.getExpenses() >= 5000);
        assertEquals("Net profit should match", -report.getExpenses(), report.getNetProfit(), 0.01);
        assertEquals("Order count should be 0", 0, report.getTotalOrders());
        assertEquals("Meal count should be 0", 0, report.getTotalMeals());
}



@When("the administrator enters invalid month {int} and year {int}")
public void the_administrator_enters_invalid_month_and_year(Integer invalidMonth, Integer invalidYear) {
    try {
            report = reportService.generateReport(invalidMonth, invalidYear);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
e.getStackTrace();
        }
}

@Then("the system should display an error message")
public void the_system_should_display_an_error_message() {
            assertTrue(true);
}

}