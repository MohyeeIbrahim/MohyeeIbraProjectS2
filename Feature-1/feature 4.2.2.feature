Feature: As a system,
  I want to automatically generate purchase orders when stock levels are critically low
  so that supplies are replenished without manual intervention.
Scenario: System detects critically low stock level
Given the current stock of "Olive Oil" is below the critical threshold
When the system performs an automatic stock check
Then it should generate a purchase order for "Olive Oil" from the preferred supplier

Scenario: Notify kitchen manager after automatic order
Given the system has auto-generated a purchase order
When the order is successfully placed with the supplier
Then the kitchen manager should receive a notification with the order details

Scenario: System chooses supplier with best price for auto-order
Given the stock of "Flour" is critically low
And multiple suppliers offer different real-time prices
When the system generates an automatic order
Then it should select the supplier with the lowest available price

