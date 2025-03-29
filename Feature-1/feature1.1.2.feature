
Feature: View Customer Dietary Preferences
  As a chef,
  I want to view customer dietary preferences
  so that I can customize meals accordingly.

  Background:
    Given the chef is logged in

  Scenario: Viewing dietary preferences for a customer
    Given the customer 100 has provided dietary preferences and allergies
    When the chef enters view_preferences 100
    Then the system should display "Dietary Preference: Vigterian, Allergies:"


#  Scenario: Viewing dietary preferences when no data is available
#    Given the customer "103" has not provided any dietary preferences
#    When the chef enters  "view_preferences 103"
#    Then the system should display "No dietary preferences available for this customer"
  Scenario: Customizing meals based on dietary preferences
    Given the customer 100 has dietary preferences "Vegetarian" and allergies "Gluten"
    And the chef has viewed the customer's dietary preferences
    When the chef creates a meal plan for customer 100
    Then the system should generate a meal plan excluding "Gluten"
    And the meal plan should include "Vegetarian" meals