Feature: As a system administrator,
  I want to generate financial reports
  so that I can analyze revenue and track business performance.

  Scenario: Generate financial report for a specific month
    Given the system administrator is on the main menu
    When the administrator selects Generate Financial Report
    And enters 03 for month and 2025 for year
    Then the system should generate the financial report for March 2025
    And display total revenue, expenses, and net profit for that period

  Scenario: Export a financial report to PDF
    Given a financial report for "April 2025" has been generated
    When the administrator selects "Export as PDF" from the main menu
    Then the system should generate a PDF file
    And the file should be named "Financial_Report_April_2025.pdf"