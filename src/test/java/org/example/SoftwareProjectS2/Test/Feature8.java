package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;
import org.example.IngredientSuggestionService;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Feature8 {
    private Customer customer;
    private IngredientSuggestionService suggestionService;
    private String unavailableIngredient;
    private List<String> suggestedAlternatives;

    @Before
    public void setUp(){
        suggestionService = new IngredientSuggestionService();
        customer = new Customer(1, "mohyee", "123", "vegetarian", "peanuts");
    }

    @Given("a customer viewing a recipe that includes {string}")
    public void a_customer_viewing_a_recipe_that_includes(String string) {
    }
    @Given("{string} is currently unavailable in the system")
    public void is_currently_unavailable_in_the_system(String ingredient) {
        this.unavailableIngredient = ingredient;
        assertFalse(suggestionService.isAvailable(ingredient));
    }
    @When("customer request alternative ingredients")
    public void customer_request_alternative_ingredients() {
        this.suggestedAlternatives = suggestionService.suggestAlternatives(unavailableIngredient, customer);
    }
    @Then("the system should suggest {string}, {string}, and {string} as alternatives")
    public void the_system_should_suggest_and_as_alternatives(String alternative1, String alternative2, String alternative3) {
        assertTrue(suggestedAlternatives.contains(alternative1));
        assertTrue(suggestedAlternatives.contains(alternative2));
        assertTrue(suggestedAlternatives.contains(alternative3));
    }
    //2nd scenario
    private String allergy;
    private String notificationMessage;
    @Given("customer allergic to {string}")
    public void customer_allergic_to(String allergy) {
        this.allergy = allergy;
        customer = new Customer(2, "ibra", "12345", "vegetarian", allergy);  // Create customer with allergy
    }
    @Given("customer viewing a recipe that includes {string}")
    public void customer_viewing_a_recipe_that_includes(String ingredient) {
        this.unavailableIngredient = ingredient;
        assertFalse(suggestionService.isAvailable(ingredient));
    }
    @When("the system cannot find a nut-free alternative")
    public void the_system_cannot_find_a_nut_free_alternative() {
        this.suggestedAlternatives = suggestionService.suggestAlternatives(unavailableIngredient, customer);
    }
    @Then("the system should notify me that no suitable alternative is available")
    public void the_system_should_notify_me_that_no_suitable_alternative_is_available() {
        assertTrue(suggestedAlternatives.contains("No suitable alternative is available."));
    }
}
