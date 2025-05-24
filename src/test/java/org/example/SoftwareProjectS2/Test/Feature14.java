package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.InventoryManager;
import org.example.KitchenManager;
import org.example.SupplierManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Feature14 {
    private SupplierManager supplierManager;
    private KitchenManager kitchenManager;
    private InventoryManager inv;
    private Map<String, Double> latestPrices;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    @Before
    public void setUp() {
        inv=new InventoryManager();
        supplierManager = new SupplierManager();
        kitchenManager = new KitchenManager(inv,supplierManager);
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));  }


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
    //4th scenario

    @Given("the supplier manager is initialized")
    public void the_supplier_manager_is_initialized() {
        assertNotNull(supplierManager);

    }

    @Given("the following prices exist for {string}:")
    public void the_following_prices_exist_for(String ingredient, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> prices = dataTable.asMaps();
        for (Map<String, String> row : prices) {
            supplierManager.addOrUpdatePrice(
                    ingredient,
                    row.get("Supplier"),
                    Double.parseDouble(row.get("Price"))
            );
        }
    }

    @When("the kitchen manager fetches prices for {string}")
    public void the_kitchen_manager_fetches_prices_for(String ingredient) {
        kitchenManager.fetchPricesForIngredient(ingredient);
    }


    @Then("print prices for {string} in the format:")
    public void print_prices_for_in_the_format(String string, String docString) {
        String expectedOutput = docString.trim();
        String actualOutput = outContent.toString().trim();
    }
    //5th scenario

    @Then("the system should print {string}")
    public void the_system_should_print(String expectedMessage) {
        String actualOutput = outContent.toString().trim();
    }



}
