Feature: Input Dietary Preferences and Allergies
  As a customer,
  I want to input my dietary preferences and allergies
  so that the system can recommend appropriate meals and prevent unwanted ingredients.

  Background:
    Given  the customer with id 100 is logged in

  Scenario: Successfully adding dietary preferences and allergies
    When the customer enter set_preferences "meatLover"
    And the customer enter set_allergies "peanut"
    Then the system should store "meatLover" as the dietary preference
    And store "peanut" as the allergies
    And display "Preferences saved successfully"

 Scenario: Updating existing dietary preferences
    When enter customer Preferences
    And  change dietary preference from "Vegetarian" to "Vegan"
    Then preferences should be updated to "Vegan"

  Scenario: Updating existing allergies
    When enter customer allergies
    And  change allergies from "peanut" to "soy"
    Then allergies should be updated to "soy"


