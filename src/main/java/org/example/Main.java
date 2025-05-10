package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ChefManager chefManager = new ChefManager();
        CustomerManager customerManager = new CustomerManager();
        SupplierManager supplierManager = new SupplierManager();
        InventoryManager inventoryManager = new InventoryManager();

        Chef alice = new Chef(1, "Alice", "pass");
        alice.addExpertise("grill");
        Chef bob = new Chef(2, "Bob", "pass");
        bob.addExpertise("salad");
        chefManager.addChef(alice);
        chefManager.addChef(bob);

        Customer cust = new Customer(100, "Eve", "pwd", "vegetarian", "nuts");
        customerManager.addCustomer(cust);
        Meal stirFry = new Meal(10, "Veggie Stir-fry", 12.5, true);
        Meal soup = new Meal(11, "Mushroom Soup", 6.0, true);
        Order order = new Order(500, LocalDate.now(), Arrays.asList(stirFry, soup));
        cust.addOrder(order);

        ChefOrderService orderService = new ChefOrderService(chefManager, customerManager);
        System.out.println("Order History for Customer 100 as viewed by Chef 1:");
        System.out.println(orderService.viewCustomerOrderHistory(1, 100));

        KitchenManager kmByChef      = new KitchenManager(chefManager);
        KitchenManager kmBySupplier  = new KitchenManager(supplierManager);
        KitchenManager kmByInventory = new KitchenManager(inventoryManager);

        kmByChef.assignTask("grill");

        AlertService alertService = new AlertService();
        CustomMealRequest req = new CustomMealRequest(1, 100, Arrays.asList("butter", "tomato"));
        SubstitutionService subs = new SubstitutionService();
        List<String> updated = subs.applySubstitution(req, alertService);
        System.out.println("Updated Ingredients: " + updated);
        System.out.println("Last Alert: " + alertService.getLastAlert());

        supplierManager.addOrUpdatePrice("tomato", "SupplierA", 2.5);
        supplierManager.addOrUpdatePrice("tomato", "SupplierB", 2.0);
        inventoryManager.addIngredient("tomato", 2, 5);

        Map<String, Double> sorted = kmBySupplier.showSortedIngredientPrices("tomato");
        System.out.println("Sorted Prices for tomato: " + sorted);

        List<String> lowStock = inventoryManager.getRestockSuggestions();
        for (String ingredient : lowStock) {
            String bestSupplier = supplierManager.getBestSupplier(ingredient);
            double bestPrice    = supplierManager.getBestPrice(ingredient);
            System.out.println("Auto-order: " + ingredient + " from " + bestSupplier + " at $" + bestPrice);
        }

        cust.getCart().addItem(stirFry);
        cust.getCart().addItem(soup);
        String invoice = InvoicePrinter.generateInvoiceSummary(cust.getCart().getItems(), 0.1);
        System.out.println(invoice);

        SystemAdministrator sysAdmin = new SystemAdministrator(customerManager);
        try {
            Path pdf = sysAdmin.exportFinancialReportToPdf(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
            System.out.println("PDF written to: " + pdf.toString());
        } catch (IOException e) {
            System.err.println("Failed to write PDF: " + e.getMessage());
        }
    }
}