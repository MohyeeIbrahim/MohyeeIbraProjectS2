Feature:As a chef,
        I want to view customer dietary preferences
        so that I can customize meals accordingly.

  Scenario: Viewing dietary preferences for a customer
    Given the customer 100 has provided dietary preferences and allergies
    When the chef enters view_preferences 100
    Then the system should display "Dietary Preference: Vigterian, Allergies:"

 Scenario: Viewing dietary preferences when no data is available
    Given the customer 100 has not provided any dietary preferences
    When the chef enter view_preferences
    Then the display "No dietary preferences available for this customer"

