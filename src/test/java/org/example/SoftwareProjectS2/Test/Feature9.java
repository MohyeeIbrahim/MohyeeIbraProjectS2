package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Feature9 {
    private CustomMealRequest mealRequest;
    private AlertService alertService;
    private SubstitutionService substitutionService;
    private List<String> updatedIngredients;

    @Before
    public void setUp(){
        ChefManager chefManager = new ChefManager();
        Chef chef = new Chef(1, "Gordon Ramsay", "1234");
        chefManager.addChef(chef);
        alertService = new AlertService();
        substitutionService = new SubstitutionService();
    }
    //1st scenario
    @Given("a custom meal request has triggered an ingredient substitution")
    public void a_custom_meal_request_has_triggered_an_ingredient_substitution() {
        List<String> ingredients = Arrays.asList("chicken", "butter", "salt");
        mealRequest = new CustomMealRequest(101, 1001, ingredients);
    }
    @When("the substitution is applied automatically by the system")
    public void the_substitution_is_applied_automatically_by_the_system() {
        substitutionService.applySubstitution(mealRequest, alertService);
    }

    @Then("chef should receive an alert stating: {string}")
    public void chef_should_receive_an_alert_stating(String expectedAlert) {
        assertEquals(expectedAlert, alertService.getLastAlert());
    }

    //2nd Scenario
    @Given("the chef has received an alert for an ingredient substitution")
    public void the_chef_has_received_an_alert_for_an_ingredient_substitution() {
        List<String> ingredients = Arrays.asList("butter", "salt");
        mealRequest = new CustomMealRequest(102, 1002, ingredients);
        substitutionService.applySubstitution(mealRequest, alertService);
    }

    @When("the chef review the substitution details")
    public void the_chef_review_the_substitution_details() {
        updatedIngredients = substitutionService.applySubstitution(mealRequest, alertService);
    }

    @Then("the system should update the final recipe accordingly")
    public void the_system_should_update_the_final_recipe_accordingly() {
        List<String> expected = Arrays.asList("olive oil", "salt");
        assertEquals(expected, updatedIngredients);
    }

}
