Feature: View Past Meal Orders
As a customer, I want to view my past meal orders so that I can reorder meals I liked.


  Background:
    Given the customer is logged in

  Scenario: View past meal orders successfully
    When I select View Past Orders from the main menu
    Then the system should display a list of customer past meal orders

  Scenario: Reorder a previously liked meal
    And customer have past meal orders in his history
    When customer select Reorder Meal from the main menu
    And customer enter the meal ID from his past orders
    Then the system should add the selected meal to his new order

  Scenario: No past meal orders available
    And I have not placed any orders before
    When I select View Past Orders from the main menu
 Then the system should display a message saying "No past orders found"