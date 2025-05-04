package org.example.SoftwareProjectS2.Test;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.CustomerManager;
import org.example.FinancialReportService;
import org.example.SystemAdministrator;
import java.nio.file.Path;
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
    private Path pdfPath;
    private int month;
    private int year;
    @Before
    public void setUp() {
        reportService = new FinancialReportService(new CustomerManager());
        administrator = new SystemAdministrator(reportService);
    }

    @Given("the system administrator is on the main menu")
    public void the_system_administrator_is_on_the_main_menu() {

    }

    @When("the administrator selects Generate Financial Report")
    public void the_administrator_selects_generate_financial_report() {

    }

    @When("enters {int} for month and {int} for year")
    public void enters_for_month_and_for_year(Integer month, Integer year) {
        this.reportMonth = month;
        this.reportYear = year;
        report = reportService.generateReport(month, year);
        assertNotNull("Report should be generated", report);
    }
    @Then("the system should generate the financial report for March {int}")
    public void the_system_should_generate_the_financial_report_for_march(Integer expectedYear) {
        assertEquals(Month.of(reportMonth).name(), "MARCH");
        assertEquals((int) expectedYear, reportYear);
    }

    @Then("display total revenue, expenses, and net profit for that period")
    public void display_total_revenue_expenses_and_net_profit_for_that_period() {
        assertTrue(report.getRevenue() >= 0);
        assertTrue(report.getExpenses() >= 0);
        assertEquals(report.getRevenue() - report.getExpenses(), report.getNetProfit(), 0.01);
    }

    // Scenario 2
    @Given("a financial report for {string} has been generated")
    public void a_financial_report_for_has_been_generated(String period) {
        String[] parts = period.split(" ");
        month = Month.valueOf(parts[0].toUpperCase()).getValue();
        year = Integer.parseInt(parts[1]);
        //report = reportService.generateReport(month, year);
    }

    @When("the administrator selects Export as PDF from the main menu")
    public void the_administrator_selects_export_as_pdf_from_the_main_menu() throws IOException {
        pdfPath = administrator.exportFinancialReportToPdf(month, year);

    }

    @Then("the system should generate a PDF file")
    public void the_system_should_generate_a_pdf_file() throws IOException {
        assertTrue(Files.exists(pdfPath));
        assertTrue(Files.size(pdfPath) > 0);
    }

    @Then("the file should be named {string}")
    public void the_file_should_be_named(String expectedFilename) {
        assertEquals(expectedFilename, pdfPath.getFileName().toString());

    }
}
