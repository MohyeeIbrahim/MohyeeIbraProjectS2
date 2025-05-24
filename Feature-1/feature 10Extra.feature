Feature: Shopping Cart Management
  As a customer
  I want to manage my shopping cart
  So I can review meals before checkout

  Scenario: Add an available meal to cart
    Given I have an empty cart
    When I add an available "Vegan Burger" with price 15.00
    Then the cart should contain:
      | Meal        | Price |
      | Vegan Burger | 15.00 |
    And the response should be "Vegan Burger added to cart"

  Scenario: Add an unavailable meal
    Given I have an empty cart
    When I add an unavailable "Pizza" with price 12.00
    Then the cart should be empty
    And the response be "Pizza is not available"

  Scenario: Add a null meal
    Given I have an empty cart
    When I add a null meal
    Then the cart be empty
    And the response should "Cannot add null meal"

  Scenario: Clear a populated cart
    Given I have a cart with:
      | Meal        | Price |
      | Vegan Burger | 15.00 |
    When I clear the cart
    Then the cart should be empty without anything

  Scenario: Add duplicate meals
    Given I have empty cart
    When I add "Vegan Burger" twice
    Then the cart should contain 2 items
    And the total should be 30.00

