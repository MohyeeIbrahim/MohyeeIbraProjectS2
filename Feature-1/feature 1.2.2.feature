Feature: Access Customer Order History for Personalized Meal Plans
  As a chef, I want to access customers’ order history so that I can suggest personalized meal plans.

  Background:
    Given the chef is logged in
  Scenario: Viewing a customer’s past orders
    Given the customer 100 has placed meal orders in the past
    When the chef enters  view_order_history for customerId 100
    Then the system should display the list of past meal orders for customerId 100

  Scenario: No order history available for a customer
    Given the customer 200 has not placed any meal orders
    When the chef enters  view_order_history for cutomerId 200
    Then the system display "No past orders found"

#  Scenario: Suggesting a personalized meal plan
#    Given the chef has accessed the order history for customerId 100
#    When the chef enters create_meal_plan for customerId 100
#    Then the system should generate a personalized meal plan
#    And the meal plan should be saved and visible to the customer