Feature:As a chef,
  I want to receive notifications about my assigned cooking tasks
  so that I can prepare meals on time.
Scenario: Chef receives notification after task assignment
Given chef "Mona" has expertise in "Baking"
When the manager assigns a "Baking" task to her
Then she should receive a notification "New task assigned: Baking"

Scenario: Notification includes task type and urgency
Given chef "Kareem" is assigned an "Appetizer" task marked as "Urgent"
Then he should receive a notification "Urgent task assigned: Appetizer"

Scenario: No notification sent if task assignment fails
Given no chef has expertise in "Sushi"
When the manager tries to assign a "Sushi" task
Then no notification should be sent to any chef
