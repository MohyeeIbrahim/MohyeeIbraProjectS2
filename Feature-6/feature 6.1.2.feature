Feature: Chef task reminders and notifications

  Scenario: Chef receives a reminder for an upcoming cooking task
    Given a cooking task "Prepare Pasta" is scheduled at "6:00 PM"
    When the system sends a reminder 30 minutes before the scheduled time
    Then the chef should see the console output "Reminder: Your cooking task 'Prepare Pasta' is scheduled at 6:00 PM."

  Scenario: Chef receives an immediate notification upon task assignment
    Given a new cooking task "Grill Chicken" is assigned to the chef for "7:00 PM"
    When the system processes the task assignment
    Then the chef should see "New task assigned: Grill Chicken at 7:00 PM."

  Scenario: Chef receives an updated notification when a task is rescheduled
    Given a cooking task "Bake Bread" originally scheduled at "5:00 PM" is rescheduled to "5:30 PM"
    When the system processes the task update
    Then the chef should see "Task Update: Bake Bread rescheduled to 5:30 PM."
