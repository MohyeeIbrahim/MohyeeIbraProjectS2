package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.CustomerManager;
import org.example.FinancialReportService;
import org.example.SystemAdministrator;
import java.nio.file.Path;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Month;

import static org.junit.Assert.*;

public class Feature17 {
    private FinancialReportService reportService;
    private FinancialReportService.Report report;
    private SystemAdministrator administrator;
    private int reportMonth;
    private int reportYear;
    private String expectedFilename;
    private Path pdfPath;
    private int month;
    private int year;
    @Before
    public void setUp() {
        reportService = new FinancialReportService();
        report = null;
        reportMonth = 0;
        reportYear = 0;
        administrator = new SystemAdministrator(new CustomerManager());
        pdfPath = null;
        month = 0;
        year = 0;
    }

    @Given("the system administrator is on the main menu")
    public void the_system_administrator_is_on_the_main_menu() {
        System.out.println("Admin is on main menu.");
    }

    @When("the administrator selects Generate Financial Report")
    public void the_administrator_selects_generate_financial_report() {
        System.out.println("Admin selects Generate Financial Report.");
    }

    @When("enters {int} for month and {int} for year")
    public void enters_for_month_and_for_year(Integer month, Integer year) {
        reportMonth = month;
        reportYear = year;
        report = reportService.generateReport(month, year);
        System.out.println("Generated report for " + month + "/" + year);
    }

    @Then("the system should generate the financial report for March {int}")
    public void the_system_should_generate_the_financial_report_for_march(Integer expectedYear) {
        System.out.println("Expected report for March " + expectedYear);
        System.out.println("Actual month: " + reportMonth);
        System.out.println("Actual year: " + reportYear);
    }

    @Then("display total revenue, expenses, and net profit for that period")
    public void display_total_revenue_expenses_and_net_profit_for_that_period() {
        System.out.println(report.formatReport(reportMonth, reportYear));
    }

    // Scenario 2





    @Given("a financial report for {string} has been generated")
    public void a_financial_report_for_has_been_generated(String period) {
        String[] parts = period.split(" ");
        this.month = Month.valueOf(parts[0].toUpperCase()).getValue();
        this.year = Integer.parseInt(parts[1]);
    }

    @When("the administrator selects {string} from the main menu")
    public void the_administrator_selects_from_the_main_menu(String option) throws Exception {
        assertEquals("Export as PDF", option);
        this.pdfPath = administrator.exportFinancialReportToPdf(month, year);
    }

    @Then("the system should generate a PDF file")
    public void the_system_should_generate_a_pdf_file() throws IOException {
        assertNotNull("PDF file should exist", pdfPath);
        assertTrue("File should exist", Files.exists(pdfPath));
        assertTrue("File should not be empty", Files.size(pdfPath) > 0);
    }

    @Then("the file should be named {string}")
    public void the_file_should_be_named(String expectedFilename) {
        String actualFilename = pdfPath.getFileName().toString();
        assertEquals("Filename should match exactly", expectedFilename, actualFilename);
    }
}
