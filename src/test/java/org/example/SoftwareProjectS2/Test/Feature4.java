package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Feature4 {
    private Chef chef;
    private ChefManager chefManager;
    private CustomerManager customerManager;
    private String displayedOrders;
    private String Result;
    Customer customer;
    private Exception caughtException;

    @Before
    public void setup() {
        chef = new Chef(1, "Chef John", "1234");
        customerManager = new CustomerManager();  // Create first
        chefManager = new ChefManager(customerManager);  // Inject dependency
        chefManager.addChef(chef);
    }

    @Given("the customer {int} has placed meal orders in the past")
    public void the_customer_has_placed_meal_orders_in_the_past(Integer customerId) {
        customer = new Customer(customerId, "Customer", "pass", "None", "None");

        List<Meal> meals1 = List.of(
                new Meal(101, "Vegan Burger", 9, true),
                new Meal(102, "Sweet Potato Fries", 5, true)
        );
        customer.addOrder(new Order(1, LocalDate.now().minusDays(1), meals1));
        customerManager.addCustomer(customer);
    }

    @When("chef enters view_order_history for customerId {int}")
    public void chef_enters_view_order_history_for_customer_id(Integer customerId) {
        displayedOrders = chefManager.viewCustomerOrderHistory(customerId);
    }

    @Then("the system should display the list of past meal orders for customerId {int}")
    public void the_system_should_display_the_list_of_past_meal_orders_for_customer_id(Integer customerId) {

        assertNotNull("Expected order history but got null", displayedOrders);

        Customer customer = customerManager.getCustomerById(customerId);
        List<Order> orders = customer.getOrderHistory();

        StringBuilder expectedOutputBuilder = new StringBuilder();
        expectedOutputBuilder.append("Customer past orders:"+"\n");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            expectedOutputBuilder.append(order.formatForDisplay(i + 1));
            if (i < orders.size() - 1) expectedOutputBuilder.append("\n");
        }
        String expectedOutput = expectedOutputBuilder.toString();

        String normalizedExpected = expectedOutput.replace("\r\n", "\n").trim();
        String normalizedActual = displayedOrders.replace("\r\n", "\n").trim();

        assertEquals(normalizedExpected, normalizedActual);
    }

    // Second Scenario
    @Given("the customer {int} has not placed any meal orders")
    public void the_customer_has_not_placed_any_meal_orders(Integer customerId) {
        Customer customer = new Customer(customerId, "Ali", "12345678", "Vegetarian", "None");
        customerManager.addCustomer(customer);
        assertTrue(customer.getOrderHistory().isEmpty());
    }

    @When("the chef enters view_order_history for customerId {int}")
    public void the_chef_enters_view_order_history_for_cutomer_id(Integer customerId) {
        Result = chefManager.viewCustomerOrderHistory(customerId);
    }

    @Then("the system display {string}")
    public void the_system_display(String expected) {
        assertEquals(expected, Result);
    }
    //3rd new scenario

    @Given("customer ID {int} is not registered in the system")
    public void customer_id_is_not_registered_in_the_system(Integer customerId) {
        assertNull(customerManager.getCustomerById(customerId));
    }
    @When("the chef requests order history for customer {int}")
    public void the_chef_requests_order_history_for_customer(Integer customerId) {
        try {
            Result = chefManager.viewCustomerOrderHistory(customerId);
        } catch (Exception e) {
            caughtException = e;
        }
    }
    @Then("system display {string}")
    public void system_display(String expectedMessage) {
        assertEquals(expectedMessage, Result);
        assertNull(caughtException);
    }

}