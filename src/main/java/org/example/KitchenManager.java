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
    public KitchenManager(InventoryManager inventoryManager, SupplierManager supplierManager) {
        this.inventoryManager = inventoryManager;
        this.supplierManager = supplierManager;
    }
    public void assignTask(String taskType) {
        List<Chef> chefs = chefManager.getAllChefs();
        Chef suitableChef = null;
        for (Chef chef : chefs) {
            if (chef.hasExpertise(taskType) &&
                    (suitableChef == null || chef.getTaskCount() < suitableChef.getTaskCount())) {
                suitableChef = chef;
            }
        }
        if (suitableChef != null) {
            suitableChef.addTask();
            suitableChef.addNotification("New task assigned: " + taskType);
            System.out.println("Assigned " + taskType + " task to Chef " + suitableChef.getChefName());
        } else {
            System.out.println("No suitable chef available");
        }
    }
    public Map<String, Double> showSortedIngredientPrices(String ingredientName) {
        if (supplierManager == null) {
            System.out.println("SupplierManager not available.");
            return new HashMap<>();
        }
        List<Map.Entry<String, Double>> sortedList = supplierManager.getSortedPrices(ingredientName);
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : sortedList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    private void generatePurchaseOrder(String ingredient, String supplier, double price) {
        int threshold = inventoryManager.getThreshold(ingredient);
        int currentStock = inventoryManager.getIngredientQuantity(ingredient);
        int orderQuantity = threshold * 2 - currentStock;
        inventoryManager.restockIngredient(ingredient, orderQuantity);
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
    public void fetchPricesForIngredient(String ingredientName) {

        Map<String, Double> prices = supplierManager.getPricesForIngredient(ingredientName);

        if (prices == null || prices.isEmpty()) {
            System.out.println("No prices found for: " + ingredientName);
            return;
        }

        System.out.println("Prices for " + ingredientName + ":");
        List<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(prices.entrySet());
        sortedEntries.sort(Map.Entry.comparingByKey());

        for (Map.Entry<String, Double> entry : sortedEntries) {
            String supplier = entry.getKey();
            Double price = entry.getValue();
            System.out.printf("- %s: $%.2f%n", supplier, price);
        }

    }
    public void autoOrderLowStockIngredients() {
        List<String> lowStockIngredients = inventoryManager.getLowStockIngredients();
        if (lowStockIngredients.isEmpty()) {
            System.out.println("All ingredients are sufficiently stocked");
            return;
        }
        for (String ingredient : lowStockIngredients) {
            String bestSupplier = supplierManager.getBestSupplier(ingredient);
            double bestPrice = supplierManager.getBestPrice(ingredient);
            if (bestSupplier != null) {
                generatePurchaseOrder(ingredient, bestSupplier, bestPrice);
                notifyKitchenManager(ingredient, bestSupplier, bestPrice);
            }
            else {
                System.out.println("No supplier found for: " + ingredient);
            }
        }
    }

}
