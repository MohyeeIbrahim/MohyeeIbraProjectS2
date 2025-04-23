package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.KitchenManager;
import org.example.SupplierManager;

import java.util.Map;

import static org.junit.Assert.*;

public class Feature14 {
    private SupplierManager supplierManager;
    private KitchenManager kitchenManager;
    private Map<String, Double> latestPrices;

    @Before
    public void setUp() {
        supplierManager = new SupplierManager();
        kitchenManager = new KitchenManager(supplierManager);
    }

    // 1st Scenario
    @Given("the kitchen manager is on the ingredient ordering")
    public void the_kitchen_manager_is_on_the_ingredient_ordering() {
    }

    @When("they search for {string}")
    public void they_search_for(String ingredient) {
        supplierManager.addOrUpdatePrice(ingredient,"SupplierA", 2.50);
        supplierManager.addOrUpdatePrice(ingredient,"SupplierB" , 2.30);
        supplierManager.addOrUpdatePrice(ingredient,"SupplierC", 2.60);
        latestPrices = kitchenManager.showSortedIngredientPrices(ingredient);
    }

    @Then("the system should fetch and display the latest price from all integrated suppliers")
    public void the_system_should_fetch_and_display_the_latest_price_from_all_integrated_suppliers() {
        assertNotNull(latestPrices);
        assertEquals(3, latestPrices.size());
        assertTrue(latestPrices.containsKey("SupplierA"));
        assertEquals(2.30, latestPrices.get("SupplierB"), 0.001);
    }

    // 2nd Scenario
    @Given("the kitchen manager has selected an ingredient")
    public void the_kitchen_manager_has_selected_an_ingredient() {
        supplierManager.addOrUpdatePrice( "Onions","SupplierX", 1.10);
        supplierManager.addOrUpdatePrice("Onions","SupplierY", 1.05);
    }

    @When("the system retrieves real-time pricing data")
    public void the_system_retrieves_real_time_pricing_data() {
        latestPrices = kitchenManager.showSortedIngredientPrices("Onions");
    }

    @Then("the system should display prices from all available suppliers for comparison")
    public void the_system_should_display_prices_from_all_available_suppliers_for_comparison() {
        assertEquals(2, latestPrices.size());
        assertTrue(latestPrices.containsKey("SupplierX"));
        assertEquals(1.05, latestPrices.get("SupplierY"), 0.001);
    }

    // 3rd Scenario
    @Given("the kitchen manager is viewing the supplier ingredient list")
    public void the_kitchen_manager_is_viewing_the_supplier_ingredient_list() {
        supplierManager.addOrUpdatePrice( "Salt","SupplierA", 0.90);
        supplierManager.addOrUpdatePrice("Salt","SupplierB",  0.85);
        supplierManager.addOrUpdatePrice("Salt","SupplierC",  0.95);
    }

    @When("they apply the {string} filter")
    public void they_apply_the_filter(String filter) {
        if (filter.equalsIgnoreCase("Sort by Price: Low to High")) {
            latestPrices = kitchenManager.showSortedIngredientPrices("Salt");
        }
    }

    @Then("the system should sort the list of ingredients based on current real-time prices")
    public void the_system_should_sort_the_list_of_ingredients_based_on_current_real_time_prices() {
        assertEquals(3, latestPrices.size());
        assertEquals(0.85, latestPrices.entrySet().iterator().next().getValue(), 0.001);
    }
}
