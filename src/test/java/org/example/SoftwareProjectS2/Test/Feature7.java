package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.CustomMealService;
import org.example.CustomerManager;
import org.example.IngredientValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Feature7 {
    private CustomMealService customMealService;
    private List<String> selectedIngredients;
    private boolean validationResult;
    private IngredientValidator validator;
    private boolean isValid;
    private String errorMessage;

    @Before
    public void setUp() {
        customMealService = new CustomMealService();
        validator = new IngredientValidator();
        selectedIngredients = new ArrayList<>();
}
    @Given("a customer has selected a valid combination of ingredients {string}")
    public void select_valid_ingredients(String ingredients) {
        selectedIngredients = Arrays.asList(ingredients.split(",\\s*"));
    }

    @When("the system validates the selected ingredients")
    public void validate_ingredients() {
        validationResult = customMealService.validateIngredients(selectedIngredients);
    }

    @Then("the meal request should be accepted as valid")
    public void verify_validation_success() {
        assertTrue(validationResult);
    }

    //2nd scenario
    @Given("a customer has selected incompatible ingredients {string}")
    public void a_customer_has_selected_incompatible_ingredients(String inputIngredients) {
        List<String> selectedIngredients = Arrays.asList(inputIngredients.split(",\\s*"));
        IngredientValidator validator = new IngredientValidator();
        isValid = validator.isValidCombination(selectedIngredients);
        errorMessage = "The selected ingredient combination is not allowed";
        assertFalse(isValid);
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedMessage) {
        assertFalse("Expected combination to be invalid", isValid);
        assertEquals(expectedMessage, errorMessage);
    }


}


















