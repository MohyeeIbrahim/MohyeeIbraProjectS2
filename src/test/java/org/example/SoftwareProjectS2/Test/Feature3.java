package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class Feature3 {
    Customer customer;
    CustomerManager customerManager;
    String pastOrder;
    private ShoppingCart currentCart;
    private String systemResponse;

    @Given("the customer is logged in")
    public void the_customer_is_logged_in() {
        customer=new Customer(100,"ali","ssss","Vegan","None");
        customer.addOrder(new Order(1, LocalDate.now().minusDays(3),
                List.of(
                        new Meal(1, "Vegan Burger",15,true),
                        new Meal(2, "Sweet Potato Fries",5,true)
                )));
        customer.addOrder(new Order(2, LocalDate.now().minusDays(1),
                List.of(new Meal(3, "Quinoa Salad",20,true))));
        customerManager=new CustomerManager();
        customerManager.addCustomer(customer);
        assertTrue(customerManager.loginCustomer(customer.getCustomerId()));
    }

    @When("I select View Past Orders from the main menu")
    public void i_select_view_past_orders_from_the_main_menu() {
        pastOrder= customer.getFormattedOrderHistory();
    }

    @Then("the system should display a list of customer past meal orders")
    public void the_system_should_display_a_list_of_customer_past_meal_orders() {
        String expectedOutput = """
            Customer past orders:
            1. [%s] Quinoa Salad
            2. [%s] Vegan Burger, Sweet Potato Fries
            """.formatted(
                LocalDate.now().minusDays(1).toString(),
                LocalDate.now().minusDays(3).toString()
        );

        assertEquals(expectedOutput.trim(), pastOrder.trim());
    }
    //Second Scenario
    @Given("customer have past meal orders in his history")
    public void customer_have_past_meal_orders_in_his_history() {
        assertFalse(customer.getOrderHistory().isEmpty());
        this.currentCart = customer.getCart();
    }

    @When("customer select Reorder Meal from the main menu")
    public void customer_select_reorder_meal_from_the_main_menu() {
    }

    @When("customer enter the meal ID from his past orders")
    public void customer_enter_the_meal_id_from_his_past_orders() {
        this.systemResponse = customerManager.reorderMeal(2);
    }

    @Then("the system should add the selected meal to his new order")
    public void the_system_should_add_the_selected_meal_to_his_new_order() {
        // Verify meal was added to cart
        assertEquals(1, currentCart.getItemCount());
        assertEquals(2, currentCart.getItems().get(0).getId());
        // Verify system response
        assertEquals("Added Sweet Potato Fries to your cart", systemResponse);
        // Verify meal exists in history
        assertTrue(customer.findMealInHistory(1).isPresent());
    }
    //3rd scenario
    @Given("I have not placed any orders before")
    public void i_have_not_placed_any_orders_before() {
        Customer c=new Customer(1000,"mohyee","fda","meat","None");
        assertTrue(c.getOrderHistory().isEmpty());

    }
    @Then("the system should display a message saying {string}")
    public void the_system_should_display_a_message_saying(String expected) {
        Customer c=new Customer(1000,"mohyee","fda","meat","None");
        assertEquals(expected,c.getFormattedOrderHistory());

    }

}

