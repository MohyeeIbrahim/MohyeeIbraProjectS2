Feature: Create Custom Meal Request
  As a customer, I want to select ingredients and customize my meal
  so that I can order meals according to my taste and dietary needs.

#  Background:
#    Given the customer is logged in

  Scenario: Successfully creating a custom meal request with valid ingredients
    When the customer chooses to create a custom meal
    And they select the following ingredients: "chicken", "broccoli", "rice"
    Then they should see a confirmation message "Your custom meal request has been submitted"

  Scenario: Failing to create a custom meal request due to missing ingredient selection
    When the customer attempts to submit a custom meal without ingredients
    Then they should see an error message "Please select at least one ingredient"