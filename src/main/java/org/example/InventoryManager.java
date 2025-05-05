 package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class InventoryManager {
    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Integer> thresholds = new HashMap<>();

    public void addIngredient(String name, int quantity) {
        stock.put(name, stock.getOrDefault(name, 0) + quantity);
        thresholds.putIfAbsent(name, 5);
    }

    public void addIngredient(String name, int quantity, int threshold) {
        stock.put(name, stock.getOrDefault(name, 0) + quantity);
        thresholds.put(name, threshold);
    }

    public int getIngredientQuantity(String name) {
        return stock.getOrDefault(name, 0);
    }

    public boolean isLowStock(String ingredient) {
        int stockThreshold = thresholds.getOrDefault(ingredient, 5);
        int currentStock = stock.getOrDefault(ingredient, 0);
        boolean isLow = currentStock < stockThreshold;
        return isLow;
    }
    public String getStockStatus(String ingredient) {
        int quantity = stock.getOrDefault(ingredient, 0);
        int threshold = thresholds.getOrDefault(ingredient, 5);
        return quantity < threshold ? "Low Stock" : "Sufficient";
    }
    public void restockIngredient(String name, int amount) {
        stock.put(name, stock.getOrDefault(name, 0) + amount);
    }

    public void setThreshold(String name, int threshold) {
        thresholds.put(name, threshold);
    }

    public List<String> getRestockSuggestions() {
        List<String> suggestions = new ArrayList<>();
        for (String ingredient : stock.keySet()) {
            if (isLowStock(ingredient)) {
                int quantity = stock.get(ingredient);
                suggestions.add(String.format(
                        "Alert: Low stock for %s - only %d kg remaining. Please reorder soon.",
                        ingredient, quantity
                ));
            }
        }
        return suggestions;
    }

}
