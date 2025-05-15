Feature: As a customer, I want to receive an invoice.

  Scenario: Receive invoice after placing an order
    Given I have successfully placed an order
    When the order confirmation is completed
    Then the system should print an invoice showing the order details and total amount

  Scenario: Request a reprint of a previous invoice
    Given I have a history of past orders
    When I request to view a past order invoice
    Then the system should print the requested invoice on the screen

  Scenario: View invoice summary before payment
    Given I am on the checkout page with items in my cart
    When I review the final order
    Then the system should print an invoice summary including items, taxes, and total payable amount

