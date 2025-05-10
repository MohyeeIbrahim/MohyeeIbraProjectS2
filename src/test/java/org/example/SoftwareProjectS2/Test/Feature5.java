package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Feature5 {
    private SystemAdministrator admin;
    private CustomerManager customerManager;
    private Customer customer;
    private Order order;
    private String displayedMessage;
    private String adminOrderHistoryResponse;

    @Before
    public void setUp() {
        customerManager=new CustomerManager();
        admin=new SystemAdministrator(customerManager);
        customer=new Customer(100, "Test User", "password", "Vegetarian", "None");
    }

    @Given("customer {int} exists in the system")
    public void customer_exists_in_the_system(Integer customerId) {
        customerManager.addCustomer(customer);
    }
    @When("a new order is placed by customer {int}")
    public void a_new_order_is_placed_by_customer(Integer customerId) {
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(1, "Burger",13 ,true));
        order = new Order(101, LocalDate.now(), meals);
        customer.addOrder(order);
    }
    @Then("the system should store the order in the customer's history")
    public void the_system_should_store_the_order_in_the_customer_s_history() {
        List<Order> history = customer.getOrderHistory();
        assertFalse("Order history should not be empty", history.isEmpty());
        assertTrue("Order should be in history", history.contains(order));
    }
    //2nd scenario
    @Given("customer {int} has never placed an order")
    public void customer_has_never_placed_an_order(Integer customerId) {
        customer = new Customer(customerId, "New User", "password", "Vegan", "None");
        customerManager.addCustomer(customer);
    }
    @When("I request their order history")
    public void i_request_their_order_history() {
        displayedMessage = customer.getFormattedOrderHistory();

    }
    @Then("the system should display a message {string}")
    public void the_system_should_display_a_message(String string) {
        assertEquals(string, displayedMessage);
    }

    //3rd scenario
    @Given("customer order exist in the order list")
    public void customer_order_exist_in_the_order_list() {
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(1, "Burger",10,true));
        customer.addOrder(new Order(101, LocalDate.now(), meals));
        customerManager.addCustomer(customer);
    }
    @When("system administrator request order history for a customer {int}")
    public void system_administrator_request_order_history_for_a_customer(Integer customerId) {
        adminOrderHistoryResponse = admin.getCustomerOrderHistory(customerId);
    }
    @Then("the system should retrieve and display the list of orders")
    public void the_system_should_retrieve_and_display_the_list_of_orders() {
        assertNotNull(adminOrderHistoryResponse);
        assertFalse(adminOrderHistoryResponse.trim().isEmpty());

        assertNotEquals("Order history must not show 'no orders'",
                "No past orders found", adminOrderHistoryResponse.trim());

        assertTrue(adminOrderHistoryResponse.contains("Burger"));
        assertTrue(adminOrderHistoryResponse.contains(LocalDate.now().toString()));
    }
    }


