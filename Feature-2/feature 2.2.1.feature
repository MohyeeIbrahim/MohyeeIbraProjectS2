Feature: Alternative ingredient suggestions
  As a customer,
  I want the system to suggest alternative ingredients if an ingredient is unavailable or does not fit my dietary restrictions
  So that I can enjoy my meal without compromising my health

  Scenario: Suggest alternative for an unavailable ingredient
    Given a customer viewing a recipe that includes "milk"
    And  "milk" is currently unavailable in the system
    When customer request alternative ingredients
    Then the system should suggest "almond milk", "soy milk", and "oat milk" as alternatives

  Scenario: No suitable alternative available
    Given customer allergic to "nuts"
    And customer viewing a recipe that includes "peanut butter"
    When customer request alternative ingredients
    And the system cannot find a nut-free alternative
    Then the system should notify me that no suitable alternative is available