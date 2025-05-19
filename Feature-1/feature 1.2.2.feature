Feature:
  As a chef,
  I want to access customers’ order history
  so that I can suggest personalized meal plans.

  Scenario: Viewing a customer’s past orders
    Given the customer 100 has placed meal orders in the past
    When  chef enters view_order_history for customerId 100
    Then the system should display the list of past meal orders for customerId 100

  Scenario: No order history available for a customer
    Given the customer 200 has not placed any meal orders
    When the chef enters view_order_history for customerId 200
    Then the system display "No past orders found"

  Scenario: Customer does not exist
    Given customer ID 999 is not registered in the system
    When the chef requests order history for customer 999
    Then system display "Customer not found"
