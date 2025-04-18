Feature: As a kitchen manager,
  I want to assign tasks to chefs based on their workload and expertise
  so that I can ensure balanced workloads and efficiency.

Scenario: Assign task to chef with matching expertise and lowest workload
Given chef "Ali" has expertise in "Italian" having 3 tasks
And chef "Omar" has expertise in "Italian" having 1 task
When the manager assigns a new "Italian" task
Then the task should be assigned to chef "Omar"

Scenario: Do not assign task to chef with unmatched expertise
Given chef "Sara" has expertise in "Desserts"
When the manager try to assigns a "Seafood" task to "sara"
Then the task should not be assigned to "Sara"

