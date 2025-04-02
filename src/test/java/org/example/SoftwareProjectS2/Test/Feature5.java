package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;

public class Feature5 {
    private Customer customer;
    @Given("system administrator is logged in")
    public void system_administrator_is_logged_in() {

    }
    @Given("the system is actively receiving customer orders")
    public void the_system_is_actively_receiving_customer_orders() {
        customer = new Customer(100, "Test User", "pass", "None", "None");
    }
    @When("a customer {int} places an order")
    public void a_customer_places_an_order(Integer int1) {

    }
    @Then("the order details should be stored in the orders List")
    public void the_order_details_should_be_stored_in_the_orders_list() {

    }

}
