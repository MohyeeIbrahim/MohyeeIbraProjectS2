package org.example;
import java.util.*;
public class SupplierManager {
    private final Map<String, Map<String, Double>> supplierPrices;
    public SupplierManager() {
        supplierPrices = new HashMap<>();
    }
    public void addOrUpdatePrice(String ingredient, String supplier, double price) {
        supplierPrices.putIfAbsent(ingredient, new HashMap<>());
        supplierPrices.get(ingredient).put(supplier, price);
    }
    public Map<String, Double> getPricesForIngredient(String ingredient) {
        return supplierPrices.getOrDefault(ingredient, new HashMap<>());
    }
    public List<Map.Entry<String, Double>> getSortedPrices(String ingredient) {
        Map<String, Double> prices = supplierPrices.getOrDefault(ingredient, new HashMap<>());
        List<Map.Entry<String, Double>> sorted = new ArrayList<>(prices.entrySet());
        sorted.sort(Map.Entry.comparingByValue());
        return sorted;
    }
    public String getBestSupplier(String ingredient) {
        List<Map.Entry<String, Double>> sorted = getSortedPrices(ingredient);
        return sorted.isEmpty() ? null : sorted.get(0).getKey();
    }
    public double getBestPrice(String ingredient) {
        List<Map.Entry<String, Double>> sorted = getSortedPrices(ingredient);
        return sorted.isEmpty() ? -1 : sorted.get(0).getValue();
    }
}

