package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;

import static org.example.InvoicePrinter.generateInvoiceSummary;
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
    private Order order;
    private Meal meal1;
    private Meal meal2;
    private Order pastOrder;
    private String printedInvoice;

    private ShoppingCart cart;
    private String invoiceSummary;
    private double taxRate = 0.10; // 10% tax
    @Before
    public void setUp() {
        customerManager = new CustomerManager();
        customer = new Customer(1, "John Doe", "password123", "Vegan", "Nuts");
        customerManager.addCustomer(customer);

        // Add prices for the meals
        meal1 = new Meal(1, "Vegan Burger", 9.99, true);
        meal2 = new Meal(2, "Vegan Salad", 7.49, true);

        List<Meal> meals = Arrays.asList(meal1, meal2);

        order = new Order(1001, LocalDate.now(), meals);
        customer.addOrder(order);
    }

    @Given("I have successfully placed an order")
    public void  iHaveSuccessfullyPlacedAnOrder() {
        assertFalse(customer.getOrderHistory().isEmpty());
    }
    @When("the order confirmation is completed")
    public void the_order_confirmation_is_completed() {
        assertNotNull(order);
    }
    @Then("the system should print an invoice showing the order details and total amount")
    public void the_system_should_print_an_invoice_showing_the_order_details_and_total_amount() {
        double mealPrice = 10.0;
        double totalAmount = mealPrice * order.getMeals().size();

        StringBuilder invoice = new StringBuilder();
        invoice.append("Invoice for Customer: ").append(customer.getCustomerName()).append("\n");
        invoice.append("Order ID: ").append(order.getDate()).append("\n");
        invoice.append("Meals Ordered:\n");
        for (Meal meal : order.getMeals()) {
            invoice.append("- ").append(meal.getName()).append(" ($").append(mealPrice).append(")\n");
        }
        invoice.append("Total Amount: $").append(totalAmount).append("\n");

        assertTrue(invoice.toString().contains("Invoice for Customer"));
        assertTrue(invoice.toString().contains(meal1.getName()));
        assertTrue(invoice.toString().contains(meal2.getName()));
        assertTrue(invoice.toString().contains("Total Amount"));
    }
//2nd scenario

    @Given("I have a history of past orders")
    public void i_have_a_history_of_past_orders() {
        // Setup: create a customer and past orders
        customerManager = new CustomerManager();
        customer = new Customer(2, "Alice", "password456", "Vegetarian", "Gluten");

        Meal meal1 = new Meal(3, "Vegetarian Pizza",10, true);
        Meal meal2 = new Meal(4, "Gluten-Free Salad",15, true);
        List<Meal> meals = Arrays.asList(meal1, meal2);

        pastOrder = new Order(2001, LocalDate.now().minusDays(10), meals); // Order from 10 days ago

        customer.addOrder(pastOrder);
        customerManager.addCustomer(customer);

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

    //3rd scenario
    @Given("I am on the checkout page with items in my cart")
    public void i_am_on_the_checkout_page_with_items_in_my_cart() {
        customerManager = new CustomerManager();
        customer = new Customer(3, "Bob", "mypassword", "Normal", "None");

        Meal meal1 = new Meal(5, "Cheeseburger", 8.99, true);
        Meal meal2 = new Meal(6, "Fries", 3.49, true);

        customer.getCart().addItem(meal1);
        customer.getCart().addItem(meal2);

        customerManager.addCustomer(customer);

        cart = customer.getCart();

        assertFalse(cart.getItems().isEmpty());
    }

    @When("I review the final order")
    public void i_review_the_final_order() {
        invoiceSummary = generateInvoiceSummary(cart.getItems(), 0.10); // Tax rate 10%
    }

    @Then("the system should print an invoice summary including items, taxes, and total payable amount")
    public void the_system_should_print_an_invoice_summary_including_items_taxes_and_total_payable_amount() {
        assertTrue(invoiceSummary.contains("Invoice Summary"));
        assertTrue(invoiceSummary.contains("Cheeseburger"));
        assertTrue(invoiceSummary.contains("Fries"));
        assertTrue(invoiceSummary.contains("Subtotal"));
        assertTrue(invoiceSummary.contains("Taxes"));
        assertTrue(invoiceSummary.contains("Total Payable"));
    }





}
