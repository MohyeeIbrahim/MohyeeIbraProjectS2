  Feature:As a chef,
    I want to receive notifications about my assigned cooking tasks
    so that I can prepare meals on time.
    Scenario: Only the assigned chef receives the notification
      Given chef "Ali" has expertise in "Grilling" has 2 tasks
      And chef "Layla" has expertise in "Grilling" has 1 task
      When the manager assigns a "Grilling" task
      Then "Layla" should receive a notification "New task assigned: Grilling"
      And "Ali" should not receive any notification

  Scenario: No notification sent if task assignment fails
  Given no chef has expertise in "Sushi"
  When the manager tries to assign a "Sushi" task
  Then no notification should be sent to any chef
