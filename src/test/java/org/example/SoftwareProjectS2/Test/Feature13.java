package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.InventoryManager;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Feature13 {
    private InventoryManager inventoryManager;
    private List<String> restockSuggestions;
    @Before
    public void setUp(){
        inventoryManager = new InventoryManager();
    }
    //1st scenario
    @Given("the stock {string} is {int} units")
    public void the_stock_is_units(String ingredient, Integer quantity) {
        inventoryManager.addIngredient(ingredient, quantity);
    }

    @Given("the threshold for {string} is {int} units")
    public void the_threshold_for_is_units(String ingredient, Integer threshold) {
        inventoryManager.setThreshold(ingredient, threshold);
    }

    @When("the system monitors inventory")
    public void the_system_monitors_inventory() {
        restockSuggestions = inventoryManager.getRestockSuggestions();
    }

    @Then("it should suggest restocking {string}")
    public void it_should_suggest_restocking(String ingredient) {
        assertTrue("Expected restock suggestion for: " + ingredient, restockSuggestions.contains(ingredient));
    }
    //2nd scenario
    @Given("{string} has {int} unit and {string} has {int} units")
    public void has_unit_and_has_units(String ingredient1, Integer quantity1, String ingredient2, Integer quantity2) {
        inventoryManager.addIngredient(ingredient1, quantity1);
        inventoryManager.addIngredient(ingredient2, quantity2);
    }
    @Given("their thresholds are {int} and {int} respectively")
    public void their_thresholds_are_and_respectively(Integer threshold1, Integer threshold2) {
        inventoryManager.setThreshold("Oil", threshold1);
        inventoryManager.setThreshold("Sugar", threshold2);
    }

    @When("the system runs the restock check")
    public void the_system_runs_the_restock_check() {
        restockSuggestions = inventoryManager.getRestockSuggestions();
    }

    @Then("it should generate a report suggesting restocking for {string} and {string}")
    public void it_should_generate_a_report_suggesting_restocking_for_and(String ingredient1, String ingredient2) {
        assertTrue("Expected restock suggestion for: " + ingredient1, restockSuggestions.contains(ingredient1));
        assertTrue("Expected restock suggestion for: " + ingredient2, restockSuggestions.contains(ingredient2));
    }
    //3rd scenario
    @Given("stock for {string} is {int} units")
    public void stock_for_is_units(String ingredient, Integer quantity) {
        inventoryManager.addIngredient(ingredient, quantity);
    }

    @Given("the threshold for {string} {int} units")
    public void the_threshold_for_units(String ingredient, Integer threshold) {
        inventoryManager.setThreshold(ingredient, threshold);

    }

    @Then("it should not suggest restocking {string}")
    public void it_should_not_suggest_restocking(String ingredient) {
        restockSuggestions = inventoryManager.getRestockSuggestions();
        assertFalse("Ingredient should not be suggested for restocking: " + ingredient,
                restockSuggestions.contains(ingredient));
    }

}
