Feature: As a system,
  I want to automatically suggest restocking when ingredients are low
  so that kitchen managers can take action promptly.

Scenario: Auto-suggest restock when level is below threshold
Given the stock "Milk" is 2 units
And the threshold for "Milk" is 5 units
When the system monitors inventory
Then it should suggest restocking "Milk"

  Scenario: Generate restock report for all low-stock items
    Given "Oil" has 1 unit and "Sugar" has 2 units
    And their thresholds are 5 and 4 respectively
    When the system runs the restock check
    Then it should generate a report suggesting restocking for "Oil" and "Sugar"

Scenario: Do not suggest restock when stock is sufficient
Given  stock for "Flour" is 15 units
And the threshold for "Flour" 10 units
When the system monitors inventory
Then it should not suggest restocking "Flour"

