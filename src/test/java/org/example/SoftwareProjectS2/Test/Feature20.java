package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.InventoryManager;
import org.example.KitchenManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Feature20 {
    private InventoryManager inventoryManager;
    private KitchenManager kitchenManager;

    @Before
    public void setUp(){
        inventoryManager = new InventoryManager();
        kitchenManager = new KitchenManager(inventoryManager);
    }
    @Given("the stock level for Sugar is {double} kg")
    public void the_stock_level_for_sugar_is_kg(Double quantity) {
        inventoryManager.addIngredient("Sugar", quantity.intValue());
    }

    @Given("the reorder threshold for Sugar is {int} kg")
    public void the_reorder_threshold_for_sugar_is_kg(Integer threshold) {
        inventoryManager.setThreshold("Sugar", threshold);

    }

    @When("the system checks stock levels")
    public void the_system_checks_stock_levels() {
        //nothing here
    }

    @Then("display this {string}")
    public void display_this(String expectedAlert) {
        List<String> suggestions = inventoryManager.getRestockSuggestions();
        String actualAlert = suggestions.get(0);
        assertEquals(expectedAlert, actualAlert);
    }
    //2nd Scenario
    @Given("the stock level for Tomatoes is {int} kg and the threshold is {int} kg")
    public void the_stock_level_for_tomatoes_is_kg_and_the_threshold_is_kg(Integer quantity, Integer threshold) {
        inventoryManager.addIngredient("Tomatoes", quantity, threshold);

    }

    @Given("the stock level for Lettuce is {double} kg and the threshold is {int} kg")
    public void the_stock_level_for_lettuce_is_kg_and_the_threshold_is_kg(Double quantity, Integer threshold) {
        inventoryManager.addIngredient("Lettuce", quantity.intValue(), threshold);

    }

    @Then("display the following alerts:")
    public void display_the_following_alerts(String docString) {
        List<String> suggestions = inventoryManager.getRestockSuggestions();
        String[] expectedAlerts = docString.strip().split("\n");

        for (String expected : expectedAlerts) {
            String ingredient = expected.replace("Low stock alert for: ", "").trim();
            assertTrue( suggestions.contains(ingredient));
        }
    }
    //3rd Scenario

    @Given("the stock level for Cheese is {double} kg and the threshold is {int} kg")
    public void the_stock_level_for_cheese_is_kg_and_the_threshold_is_kg(Double quantity, Integer threshold) {
        inventoryManager.addIngredient("Cheese", quantity.intValue(), threshold);
    }

    @When("the kitchen manager enters  reorder Cheese 5kg")
    public void the_kitchen_manager_enters_reorder_cheese_5kg() {
        inventoryManager.restockIngredient("Cheese", 5);
        String status = inventoryManager.getStockStatus("Cheese");
        kitchenManager.addNotification("Cheese stock updated." + status);
    }

    @Then("display the following {string}")
    public void display_the_following(String expectedNotification) {
        List<String> notifications = kitchenManager.getNotifications();
        assertTrue(notifications.contains(expectedNotification));
    }

}
