Feature: As a kitchen manager,
  I want to receive alerts when stock levels are low
  so that I can reorder before running out of ingredients.

  Scenario: Low stock alert is triggered for a specific ingredient
    Given the stock level for Sugar is 2 kg
    And the reorder threshold for Sugar is 3 kg
    When the system checks stock levels
    Then display this "Alert: Low stock for Sugar - only 2 kg remaining. Please reorder soon."

  Scenario: Multiple low stock alerts are triggered for different ingredients
    Given the stock level for Tomatoes is 2 kg and the threshold is 5 kg
    And the stock level for Lettuce is 1 kg and the threshold is 2 kg
    When the system checks stock levels
    Then display the following alerts:
      """
      Alert: Low stock for Tomatoes - only 2 kg remaining. Please reorder soon.
      Alert: Low stock for Lettuce - only 1 kg remaining. Please reorder soon.
      """

  Scenario: Kitchen manager reorders an ingredient after receiving a low stock alert
    Given the stock level for Cheese is 1 kg and the threshold is 2 kg
    When the system checks stock levels
    And the kitchen manager enters  reorder Cheese 5kg
    Then display the following "Cheese stock updated.Sufficient"
