Feature: As a chef,
  I want to receive an alert when an ingredient substitution is applied
  so that I can approve or adjust the final recipe.

  Scenario: Receiving an alert for an automatic ingredient substitution
    Given a custom meal request has triggered an ingredient substitution
    When the substitution is applied automatically by the system
    Then chef should receive an alert stating: "Ingredient substitution applied: 'butter' replaced with 'olive oil'"

  Scenario: Reviewing and approving or modifying the substitution
    Given the chef has received an alert for an ingredient substitution
    When the chef review the substitution details
    Then the system should update the final recipe accordingly