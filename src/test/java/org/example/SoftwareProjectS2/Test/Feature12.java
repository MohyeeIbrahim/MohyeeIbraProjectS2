package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.InventoryManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Feature12 {
    private InventoryManager inventoryManager;
    int beforeStock;
    @Before
    public void setUp(){
        inventoryManager = new InventoryManager();
    }
    //1st scenario
    @Given("the stock for {string} is {int} units")
    public void the_stock_for_is_units(String ingredient, Integer stock) {
        inventoryManager.addIngredient(ingredient, stock);
    }
    @When("the kitchen manager checks the inventory")
    public void the_kitchen_manager_checks_the_inventory() {
        //no need for anything just visual
    }
    @Then("{string} should be marked as {string}")
    public void should_be_marked_as(String ingredient, String status) {
        String actualStatus = inventoryManager.getStockStatus(ingredient);
        assertEquals(status, actualStatus);
    }
//2nd Scenario
    @Given("{string} stock is low")
    public void stock_is_low(String ingredient) {
         inventoryManager.addIngredient(ingredient, 2);
         beforeStock=inventoryManager.getIngredientQuantity(ingredient);
    }

    @When("the kitchen manager restocks {string} with {int} units")
    public void the_kitchen_manager_restocks_with_units(String ingredient, Integer quantity) {

        inventoryManager.restockIngredient(ingredient, quantity);
    }

    @Then("the stock level of {string} should be updated to reflect the new quantity")
    public void the_stock_level_of_should_be_updated_to_reflect_the_new_quantity(String ingredient) {
        int updatedStock = inventoryManager.getIngredientQuantity(ingredient);
        assertTrue(beforeStock<updatedStock);
        assertTrue(updatedStock > 0);

    }
    //3rd Scenario
    @Given("the stock system is up to date")
    public void the_stock_system_is_up_to_date() {
        inventoryManager.addIngredient("Tomato", 10);
        inventoryManager.addIngredient("Cheese", 5);
        inventoryManager.addIngredient("Mushroom", 2);
    }

    @When("the kitchen manager views the inventory")
    public void the_kitchen_manager_views_the_inventory() {
        //no need test it will be just an input
    }

    @Then("the system should display the quantity of each ingredient in stock")
    public void the_system_should_display_the_quantity_of_each_ingredient_in_stock() {
        assertEquals(10, inventoryManager.getIngredientQuantity("Tomato"));
        assertEquals(5, inventoryManager.getIngredientQuantity("Cheese"));
        assertEquals(2, inventoryManager.getIngredientQuantity("Mushroom"));
    }

    //4th sceanrio
    @Given("the kitchen manager adds {string} with {int} units and a threshold of {int}")
    public void the_kitchen_manager_adds_with_units_and_a_threshold_of(String ingredient, Integer quantity, Integer threshold) {
        inventoryManager.addIngredient(ingredient, quantity);
        inventoryManager.setThreshold(ingredient,threshold);
    }
    @When("the kitchen manager checks the stock status of {string}")
    public void the_kitchen_manager_checks_the_stock_status_of(String string) {
    //nothing to do
    }
    @Then("{string} should marked as {string}")
    public void should_marked_as(String ingredient, String expectedStatus) {
        String actualStatus = inventoryManager.getStockStatus(ingredient);
        assertEquals(actualStatus, expectedStatus);
    }


}
