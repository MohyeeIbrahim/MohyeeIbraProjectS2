  Feature: As a kitchen manager,
    I want to track ingredient stock levels in real time
    so that I can prevent shortages and ensure continuous operations.

  Scenario: Identify ingredient running low
  Given the stock for "Tomatoes" is 3 units
  When the kitchen manager checks the inventory
  Then "Tomatoes" should be marked as "Low Stock"

  Scenario: Restock an ingredient manually
  Given "Cheese" stock is low
  When the kitchen manager restocks "Cheese" with 10 units
  Then the stock level of "Cheese" should be updated to reflect the new quantity

    Scenario: View current stock levels
      Given the stock system is up to date
      When the kitchen manager views the inventory
      Then the system should display the quantity of each ingredient in stock

    Scenario: Add new ingredient with custom threshold
      Given the kitchen manager adds "Olive Oil" with 10 units and a threshold of 10
      When the kitchen manager checks the stock status of "Olive Oil"
      Then "Olive Oil" should marked as "Sufficient"