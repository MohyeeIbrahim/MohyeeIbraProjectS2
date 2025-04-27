package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Feature4 {
    private Chef chef;
    private ChefManager chefManager;
    private CustomerManager customerManager;
    private ChefOrderService chefOrderService;
    private String displayedOrders;
    private String Result;



    //    @Given("the chef is logged in")
//    public void the_chef_is_logged_in() {
//        chef = new Chef(1, "Chef John", "1234");
//        chefManager = new ChefManager();
//        chefManager.addChef(chef);
//        chefOrderService = new ChefOrderService(chefManager, customerManager);
//        assertTrue(chefManager.loginChef(chef.getChefId()));
//        customerManager = new CustomerManager();
//    }
    @Before
    public void setup(){
        chef = new Chef(1, "Chef John", "1234");
        chefManager = new ChefManager();
        customerManager = new CustomerManager();
        chefOrderService = new ChefOrderService(chefManager, customerManager);
        chefManager.addChef(chef);
        assertTrue(chefManager.loginChef(chef.getChefId()));
    }

    @Given("the customer {int} has placed meal orders in the past")
    public void the_customer_has_placed_meal_orders_in_the_past(Integer customerId) {
        Customer customer = new Customer(customerId, "Customer" + customerId, "pass", "None", "None");
        List<Meal> meals1 = List.of(
                new Meal(101, "Vegan Burger", 9,true),
                new Meal(102, "Sweet Potato Fries", 5,true)
        );
        customer.addOrder(new Order(1, LocalDate.now().minusDays(1), meals1));
        customerManager.addCustomer(customer);
    }
    @When("the chef enters  view_order_history for customerId {int}")
    public void the_chef_enters_view_order_history_for_customer_id(Integer customerId) {
        displayedOrders = chefOrderService.viewCustomerOrderHistory(chef.getChefId(), customerId);
    }
    @Then("the system should display the list of past meal orders for customerId {int}")
    public void the_system_should_display_the_list_of_past_meal_orders_for_customer_id(Integer int1) {
        String expectedOutput = """
            Customer past orders:
            1. [%s] Vegan Burger, Sweet Potato Fries
            """.formatted(
                LocalDate.now().minusDays(1).toString()
        );
        assertEquals(expectedOutput.trim(), displayedOrders.trim());
    }
    //Second Scenario
    @Given("the customer {int} has not placed any meal orders")
    public void the_customer_has_not_placed_any_meal_orders(Integer customerId) {
        Customer customer = new Customer(customerId, "Ali", "12345678", "Vegetarian", "None");
        customerManager.addCustomer(customer);
        // Verify no orders exist
        assertTrue(customer.getOrderHistory().isEmpty());
    }
    @When("the chef enters  view_order_history for cutomerId {int}")
    public void the_chef_enters_view_order_history_for_cutomer_id(Integer customerId) {
     Result=chefOrderService.viewCustomerOrderHistory(chef.getChefId(), customerId);;
    }
    @Then("the system display {string}")
    public void the_system_display(String expected) {
        assertEquals(expected,Result);
    }


}
