package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.CustomMealService;
import org.example.Customer;
import org.example.CustomerManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Feature6 {
    private Customer customer;
    private CustomerManager customerManager;
    private CustomMealService customMealService;
    private String confirmationMessage;

@Before
public void setUp() {
    customMealService = new CustomMealService();
    customer = new Customer(1, "Test Customer", "password", "Vegetarian", "None");
    customerManager = new CustomerManager();
    customerManager.addCustomer(customer);
}

    @When("the customer chooses to create a custom meal")
    public void the_customer_chooses_to_create_a_custom_meal() {
     //no code needed
    }
    @When("they select the following ingredients: {string}, {string}, {string}")
    public void they_select_the_following_ingredients(String string, String string2, String string3) {
        List<String> ingredients = Arrays.asList(string,string2 , string3);
        confirmationMessage = customMealService.createCustomMealRequest(customer.getCustomerId(), ingredients);
    }
    @Then("they should see a confirmation message {string}")
    public void they_should_see_a_confirmation_message(String expectedMessage) {
        assertEquals(expectedMessage, confirmationMessage);
       }

    @When("the customer attempts to submit a custom meal without ingredients")
    public void submit_empty_ingredients() {
        try {
            confirmationMessage = customMealService.createCustomMealRequest(
                   customer.getCustomerId(),
                    Collections.emptyList()
            );
        } catch (IllegalArgumentException e) {
            confirmationMessage = e.getMessage();
        }
    }

    @Then("they should see an error message {string}")
    public void they_should_see_an_error_message(String expectedMessage) {
        assertEquals(expectedMessage, confirmationMessage);
    }


}
