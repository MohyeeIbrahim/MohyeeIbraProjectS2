package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.example.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Feature16 {
    private CustomerManager customerManager;
    private Customer customer;
    private String printedInvoice;
    private String invoiceSummary;

    @Before
    public void setUp(){
        customerManager = new CustomerManager();
        customer = new Customer(1, "ibra", "password123", "Vegan", "Nuts");
        customerManager.addCustomer(customer);
    }

    // Scenario 1:
    @Given("I have successfully placed an order")
    public void iHaveSuccessfullyPlacedAnOrder() {
        Meal meal1 = new Meal(1, "Vegan Burger", 9.99, true);
        Meal meal2 = new Meal(2, "Vegan Salad", 7.49, true);
        List<Meal> meals = Arrays.asList(meal1, meal2);

        Order order = new Order(1001, LocalDate.now(), meals);
        customer.addOrder(order);

        assertFalse(customer.getOrderHistory().isEmpty());
    }

    @When("the order confirmation is completed")
    public void the_order_confirmation_is_completed() {
        assertNotNull(customer.getOrderHistory().get(0));
    }

    @Then("the system should print an invoice showing the order details and total amount")
    public void the_system_should_print_an_invoice_showing_the_order_details_and_total_amount() {
        List<Meal> meals = customer.getOrderHistory().get(0).getMeals();
        double subtotal = meals.stream().mapToDouble(Meal::getPrice).sum();
        double taxRate = 0.10;
        double taxes = subtotal * taxRate;
        double total = subtotal + taxes;

        String invoice = InvoicePrinter.generateInvoiceSummary(meals, taxRate);

        assertTrue(invoice.contains("Vegan Burger"));
        assertTrue(invoice.contains("Vegan Salad"));
        assertTrue(invoice.contains(String.format("Subtotal: $%.2f", subtotal)));
        assertTrue(invoice.contains(String.format("Taxes (10%%): $%.2f", taxes)));
        assertTrue(invoice.contains(String.format("Total Payable: $%.2f", total)));
    }

    // Scenario 2:
    @Given("I have a history of past orders")
    public void i_have_a_history_of_past_orders() {

        Meal meal1 = new Meal(3, "Vegetarian Pizza", 10, true);
        Meal meal2 = new Meal(4, "Gluten-Free Salad", 15, true);
        List<Meal> meals = Arrays.asList(meal1, meal2);

        Order pastOrder = new Order(2001, LocalDate.now().minusDays(10), meals);
        customer.addOrder(pastOrder);

        assertFalse(customer.getOrderHistory().isEmpty());
    }

    @When("I request to view a past order invoice")
    public void i_request_to_view_a_past_order_invoice() {
        SystemAdministrator systemAdmin = new SystemAdministrator(customerManager);
        printedInvoice = systemAdmin.getCustomerOrderHistory(customer.getCustomerId());
        assertNotNull(printedInvoice);
    }

    @Then("the system should print the requested invoice on the screen")
    public void the_system_should_print_the_requested_invoice_on_the_screen() {
        assertTrue(printedInvoice.contains("Vegetarian Pizza"));
        assertTrue(printedInvoice.contains("Gluten-Free Salad"));
        assertTrue(printedInvoice.contains("Customer past orders"));
    }

    // Scenario 3:
    @Given("I am on the checkout page with items in my cart")
    public void i_am_on_the_checkout_page_with_items_in_my_cart() {

        Meal meal1 = new Meal(5, "Cheeseburger", 8.99, true);
        Meal meal2 = new Meal(6, "Fries", 3.49, true);

        customer.getCart().addItem(meal1);
        customer.getCart().addItem(meal2);

        assertFalse(customer.getCart().getItems().isEmpty());
    }

    @When("I review the final order")
    public void i_review_the_final_order() {
        double taxRate = 0.10;
        invoiceSummary = InvoicePrinter.generateInvoiceSummary(customer.getCart().getItems(), taxRate);
    }

    @Then("the system should print an invoice summary including items, taxes, and total payable amount")
    public void the_system_should_print_an_invoice_summary_including_items_taxes_and_total_payable_amount() {
        List<Meal> items = customer.getCart().getItems();
        double subtotal = items.stream().mapToDouble(Meal::getPrice).sum();
        double tax = subtotal * 0.10;
        double total = subtotal + tax;

        assertTrue(invoiceSummary.contains("Cheeseburger"));
        assertTrue(invoiceSummary.contains("Fries"));
        assertTrue(invoiceSummary.contains(String.format("Subtotal: $%.2f", subtotal)));
        assertTrue(invoiceSummary.contains(String.format("Taxes (10%%): $%.2f", tax)));
        assertTrue(invoiceSummary.contains(String.format("Total Payable: $%.2f", total)));
    }
}
