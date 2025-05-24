package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Meal;
import org.example.ShoppingCart;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Feature21Extra {
    private ShoppingCart cart;
    private String responseMessage;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
        responseMessage = "";
    }

    // Scenario 1:
    @Given("I have an empty cart")
    public void i_have_an_empty_cart() {
        assertTrue(cart.isEmpty());
    }

    @When("I add an available {string} with price {double}")
    public void i_add_an_available(String mealName, double price) {
        Meal meal = new Meal(1, mealName, price, true);
        responseMessage = cart.addItem(meal);
    }

    @Then("the cart should contain:")
    public void the_cart_should_contain(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedMeals = dataTable.asMaps();
        List<Meal> actualMeals = cart.getItems();

        assertEquals(expectedMeals.size(), actualMeals.size());
        for (int i = 0; i < expectedMeals.size(); i++) {
            assertEquals(expectedMeals.get(i).get("Meal"), actualMeals.get(i).getName());
            assertEquals(Double.parseDouble(expectedMeals.get(i).get("Price")),
                    actualMeals.get(i).getPrice(), 0.001);
        }
    }

    @Then("the response should be {string}")
    public void the_response_should_be(String expectedMessage) {
        assertEquals(expectedMessage, responseMessage);
    }

    // Scenario 2: Add unavailable meal
    @When("I add an unavailable {string} with price {double}")
    public void i_add_an_unavailable(String mealName, double price) {
        Meal meal = new Meal(2, mealName, price, false);
        responseMessage = cart.addItem(meal);
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        assertTrue(cart.isEmpty());
    }

    @Then("the response be {string}")
    public void the_response_be(String expectedMessage) {
        assertEquals(expectedMessage, responseMessage);
    }

    // Scenario 3: Add null meal
    @When("I add a null meal")
    public void i_add_a_null_meal() {
        responseMessage = cart.addItem(null);
    }

    @Then("the cart be empty")
    public void the_cart_be_empty() {
        assertTrue(cart.isEmpty());
    }

    @Then("the response should {string}")
    public void the_response_should(String expectedMessage) {
        assertEquals(expectedMessage, responseMessage);
    }

    // Scenario 4: Clear cart
    @Given("I have a cart with:")
    public void i_have_a_cart_with(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> meals = dataTable.asMaps();
        for (Map<String, String> meal : meals) {
            cart.addItem(new Meal(1, meal.get("Meal"),
                    Double.parseDouble(meal.get("Price")), true));
        }
        assertFalse(cart.isEmpty());
    }

    @When("I clear the cart")
    public void i_clear_the_cart() {
        cart.clear();
    }

    @Then("the cart should be empty without anything")
    public void the_cart_should_be_empty_without_anything() {
        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getItemCount());
    }

    // Scenario 5: Add duplicate meals
    @Given("I have empty cart")
    public void i_have_empty_cart() {
        assertTrue(cart.isEmpty());
    }

    @When("I add {string} twice")
    public void i_add_twice(String mealName) {
        Meal meal = new Meal(1, mealName, 15.00, true);
        cart.addItem(meal);
        cart.addItem(meal);
    }

    @Then("the cart should contain {int} items")
    public void the_cart_should_contain_items(int count) {
        assertEquals(count, cart.getItemCount());
    }

    @Then("the total should be {double}")
    public void the_total_should_be_(double total) {
        assertEquals(total, cart.getTotal(), 0.001);
    }
}
