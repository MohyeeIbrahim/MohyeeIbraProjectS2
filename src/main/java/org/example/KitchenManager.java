package org.example;

import java.util.*;

public class KitchenManager {
    private ChefManager chefManager;
    private SupplierManager supplierManager;
    private InventoryManager inventoryManager;
    private List<String> notifications = new ArrayList<>();


    public KitchenManager(ChefManager chefManager) {
        this.chefManager = chefManager;
    }

    public KitchenManager(SupplierManager supplierManager) {
        this.supplierManager = supplierManager;
    }

    public KitchenManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void assignTask(String taskType) {
        List<Chef> chefs = chefManager.getAllChefs();
        Chef suitableChef = null;

        for (Chef chef : chefs) {
            if (chef.hasExpertise(taskType)) {
                if (suitableChef == null || chef.getTaskCount() < suitableChef.getTaskCount()) {
                    suitableChef = chef;
                }
            }
        }

        if (suitableChef != null) {
            suitableChef.addTask(); // Increase task count
            suitableChef.addNotification("New task assigned: " + taskType);
            System.out.println("Assigned " + taskType + " task to Chef " + suitableChef.getChefName());
        } else {
            System.out.println("No suitable chef available");
        }
    }

    // Fetch and display prices for a given ingredient
    public void fetchPricesForIngredient(String ingredientName) {
        if (supplierManager == null) {
            System.out.println("SupplierManager not available.");
            return;
        }

        var prices = supplierManager.getPricesForIngredient(ingredientName);
        if (prices.isEmpty()) {
            System.out.println("No prices found for: " + ingredientName);
        } else {
            System.out.println("Prices for " + ingredientName + ":");
            prices.forEach((supplier, price) ->
                    System.out.println("- " + supplier + ": $" + price));
        }
    }

    public Map<String, Double> showSortedIngredientPrices(String ingredientName) {
        if (supplierManager == null) {
            System.out.println("SupplierManager not available.");
            return new HashMap<>();
        }

        List<Map.Entry<String, Double>> sortedList = supplierManager.getSortedPrices(ingredientName);

        // Convert the sorted list into a LinkedHashMap to preserve order
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : sortedList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    public void autoOrderLowStockIngredients() {
        List<String> lowStockIngredients = inventoryManager.getRestockSuggestions();

        for (String ingredient : lowStockIngredients) {
            String bestSupplier = supplierManager.getBestSupplier(ingredient);
            double bestPrice = supplierManager.getBestPrice(ingredient);
            if (bestSupplier != null) {
                generatePurchaseOrder(ingredient, bestSupplier, bestPrice);
                notifyKitchenManager(ingredient, bestSupplier, bestPrice);
            }
        }
    }
    private void generatePurchaseOrder(String ingredient, String supplier, double price) {
        System.out.println("Auto-order placed for: " + ingredient + " from " + supplier + " at $" + price);
    }
    private void notifyKitchenManager(String ingredient, String supplier, double price) {
        System.out.println("Kitchen manager notified: Order placed for " + ingredient + " from " + supplier + " at $" + price);
    }
    public void addNotification(String message) {
        notifications.add(message);
    }
    public List<String> getNotifications() {
        return notifications;
    }
    public boolean hasNotificationContaining(String ingredient) {
        for (String msg : notifications) {
            if (msg.contains(ingredient)) {
                return true;
            }
        }
        return false;
    }
}
