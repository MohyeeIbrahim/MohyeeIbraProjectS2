Feature: Validate Ingredient Combinations for Custom Meals
  As a system, I want to validate ingredient combinations
  so that customers do not select incompatible or unavailable ingredients.

  Scenario: Valid ingredient combination passes validation
    Given a customer has selected a valid combination of ingredients "tomato, basil, mozzarella"
    When the system validates the selected ingredients
    Then the meal request should be accepted as valid

  Scenario: Incompatible ingredient combination triggers an error
    Given a customer has selected incompatible ingredients "fish, chocolate"
    When the system validates the selected ingredients
    Then an error message "The selected ingredient combination is not allowed" should be displayed

  