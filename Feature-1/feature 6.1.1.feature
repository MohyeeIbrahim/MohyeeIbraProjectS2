Feature: As a customer,
  I want to receive reminders shortly before my meal delivery
  So that I can be prepared to receive it

  Scenario: Customer receives a reminder 1 hour before delivery
    Given the current time is "2025-06-10 09:00 AM"
    And the customer has a confirmed meal delivery scheduled for "2025-06-10 10:00 AM"
    When the system sends reminders 1 hour before delivery
    Then the customer should receive a notification with the message "Reminder: Your meal delivery is scheduled for 2025-06-10 10:00 AM."

  Scenario: No reminder is sent for a cancelled order
    Given current time is "2025-06-11 08:00 AM"
    And the customer has a cancelled meal delivery scheduled for "2025-06-11 09:00 AM"
    When the system send reminders 1 hour before delivery
    Then the customer should not receive any notification for that order

  Scenario: Customer has multiple upcoming deliveries and receives reminders for confirmed ones
    Given the time is "2025-06-12 07:00 AM"
    And  customer has a confirmed meal delivery scheduled for "2025-06-12 08:00 AM"
    And the customer has a confirmed meal delivery scheduled "2025-06-12 09:00 AM"
    And the customer  cancelled meal delivery scheduled for "2025-06-12 10:00 AM"
    When system sends reminders 1 hour before delivery
    Then customer should receive a notification with the message "Reminder: Your meal delivery is scheduled for 2025-06-12 08:00 AM."
    And the customer should not yet receive a notification for the 9:00 AM delivery
    And the customer should not receive any notification for the cancelled order
