package org.example;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
public class SystemAdministrator {
    private CustomerManager customerManager;
    private static final String REPORTS_DIR = "financial_reports";
    FinancialReportService reportService;

    public SystemAdministrator(CustomerManager customerManager) {
        this.customerManager = customerManager;
        this.reportService = new FinancialReportService(customerManager);
    }

    public SystemAdministrator(FinancialReportService reportService) {
        this.reportService = reportService;
    }

    public Path exportFinancialReportToPdf(int month, int year) throws IOException {
        Path dirPath = Paths.get(REPORTS_DIR);
        if (!dirPath.toFile().exists()) {
            dirPath.toFile().mkdirs();
        }

        String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String period = monthName + " " + year;
        String filename = "Financial_Report_" + monthName + "_" + year + ".pdf";
        Path filePath = Paths.get(REPORTS_DIR, filename);

        FinancialReportService.Report report = reportService.generateReport(month, year);

        try (PdfWriter writer = new PdfWriter(filePath.toString());
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new Paragraph("Financial Report - " + period)
                    .setFontSize(16)
                    .setBold());
            document.add(new Paragraph(String.format("Total Revenue: $%,.2f", report.getRevenue()))
                    .setFontSize(12));
            document.add(new Paragraph(String.format("Total Expenses: $%,.2f", report.getExpenses()))
                    .setFontSize(12));
            document.add(new Paragraph(String.format("Net Profit: $%,.2f", report.getNetProfit()))
                    .setFontSize(12)
                    .setBold());
        }

        return filePath;
    }

    public String getCustomerOrderHistory(Integer customerId) {
        Customer customer = customerManager.getCustomerById(customerId);
        return customer.getFormattedOrderHistory();
    }
}
