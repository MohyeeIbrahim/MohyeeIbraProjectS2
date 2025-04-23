package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.InventoryManager;
import org.example.KitchenManager;
import org.example.SupplierManager;

import static org.junit.Assert.*;

public class Feature15 {
    private InventoryManager inventoryManager;
    private SupplierManager supplierManager;
    private KitchenManager kitchenManager;
    private String lastOrderedIngredient;
    private String selectedSupplier;
    private double selectedPrice;
    @Before
    public void setUp(){
        inventoryManager = new InventoryManager();
        supplierManager = new SupplierManager();
        kitchenManager = new KitchenManager(supplierManager);
    }
    //1st scenario
    @Given("the current stock of {string} is below the critical threshold")
    public void the_current_stock_of_is_below_the_critical_threshold(String ingredient) {
        inventoryManager.addIngredient(ingredient, 2, 5);  // Stock = 2, Threshold = 5
        supplierManager.addOrUpdatePrice(ingredient, "SupplierX", 3.50);
        assertTrue(inventoryManager.isLowStock(ingredient));
        lastOrderedIngredient = ingredient;
    }

    @When("the system performs an automatic stock check")
    public void the_system_performs_an_automatic_stock_check() {
            selectedSupplier = supplierManager.getBestSupplier(lastOrderedIngredient);
            selectedPrice = supplierManager.getBestPrice(lastOrderedIngredient);
    }

    @Then("it should generate a purchase order for {string} from the preferred supplier")
    public void it_should_generate_a_purchase_order_for_from_the_preferred_supplier(String ingredient) {
        assertEquals(lastOrderedIngredient, ingredient);
        assertNotNull("Supplier should be selected", selectedSupplier);
        assertTrue("Price must be positive", selectedPrice > 0);

    }
    //2nd scenario
    @Given("the system has auto-generated a purchase order")
    public void the_system_has_auto_generated_a_purchase_order() {
        lastOrderedIngredient = "Tomato";
        supplierManager.addOrUpdatePrice(lastOrderedIngredient,"SupplierX", 1.50);
        selectedSupplier = supplierManager.getBestSupplier(lastOrderedIngredient);
        selectedPrice = supplierManager.getBestPrice(lastOrderedIngredient);
        String notification = "Auto-Order placed for " + lastOrderedIngredient + " with SupplierX "+selectedSupplier + " at "+selectedPrice + "$";
        kitchenManager.addNotification(notification);
        assertNotNull(selectedSupplier);
    }

    @When("the order is successfully placed with the supplier")
    public void the_order_is_successfully_placed_with_the_supplier() {
        assertFalse( kitchenManager.getNotifications().isEmpty());
    }

    @Then("the kitchen manager should receive a notification with the order details")
    public void the_kitchen_manager_should_receive_a_notification_with_the_order_details() {
        assertTrue(kitchenManager.hasNotificationContaining("Auto-Order placed for " + lastOrderedIngredient));
    }
    //3rd Scenario
    @Given("the stock of {string} is critically low")
    public void the_stock_of_is_critically_low(String ingredient) {
        inventoryManager.addIngredient(ingredient, 1, 4);
        assertTrue(inventoryManager.isLowStock(ingredient));
        lastOrderedIngredient = ingredient;
    }
    @Given("multiple suppliers offer different real-time prices")
    public void multiple_suppliers_offer_different_real_time_prices() {
        supplierManager.addOrUpdatePrice(lastOrderedIngredient, "SupplierA", 2.10);
        supplierManager.addOrUpdatePrice(lastOrderedIngredient, "SupplierB", 1.90);
        supplierManager.addOrUpdatePrice(lastOrderedIngredient, "SupplierC", 2.30);
    }

    @When("the system generates an automatic order")
    public void the_system_generates_an_automatic_order() {
        selectedSupplier = supplierManager.getBestSupplier(lastOrderedIngredient);
        selectedPrice = supplierManager.getBestPrice(lastOrderedIngredient);
    }

    @Then("it should select the supplier with the lowest available price")
    public void it_should_select_the_supplier_with_the_lowest_available_price() {
        assertEquals("SupplierB", selectedSupplier);
        assertEquals(1.90,
                selectedPrice,0.001);
    }


}
