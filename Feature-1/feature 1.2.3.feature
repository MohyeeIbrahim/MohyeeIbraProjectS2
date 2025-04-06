Feature: Store and Retrieve Customer Order History for Trend Analysis
  As a system administrator,
  I want to store and retrieve customer order history
  so that I can analyze trends and improve service offerings.

Background:
  Given system administrator is logged in
Scenario:
  Given customer 100 exists in the system
  When  a new order is placed by customer 100
  Then  the system should store the order in the customer's history

  Scenario: Retrieving customer order history
    Given customer order exist in the order list
    When  system administrator request order history for a customer 100
    Then  the system should retrieve and display the list of orders

  Scenario: Handling retrieval for a customer with no order history
    Given customer 200 has never placed an order
    When  I request their order history
    Then  the system should display a message "No past orders found"