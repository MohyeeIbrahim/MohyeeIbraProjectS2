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
    private Chef chef;
    private ChefManager chefManager;
    private CustomMealRequest mealRequest;
    private SubstitutionService substitutionService;
    private List<String> updatedIngredients;

    @Before
    public void setUp(){
        chefManager = new ChefManager();
        chef=new Chef(1, "mohyee", "1234");
        chefManager.addChef(chef);
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
        substitutionService.applySubstitution(mealRequest, chefManager, chef.getChefId());
    }

    @Then("the chef should have the following substitution alerts")
    public void the_chef_should_have_the_following_substitution_alerts(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedAlerts = dataTable.asList();
        List<String> actualAlerts = chef.getSubstitutionAlerts();
        assertEquals(expectedAlerts, actualAlerts);
    }

    //2nd Scenario
    @Given("the chef has received an alert for an ingredient substitution")
    public void the_chef_has_received_an_alert_for_an_ingredient_substitution() {
        List<String> ingredients = Arrays.asList("butter", "salt");
        mealRequest = new CustomMealRequest(102, 1002, ingredients);
        substitutionService.applySubstitution(mealRequest, chefManager, chef.getChefId());
    }

    @When("the chef review the substitution details")
    public void the_chef_review_the_substitution_details() {
        updatedIngredients = mealRequest.getIngredients();
    }

    @Then("the system should update the final recipe accordingly")
    public void the_system_should_update_the_final_recipe_accordingly() {
        List<String> expected = Arrays.asList("olive oil", "salt");
        assertEquals(expected, updatedIngredients);
    }

}
