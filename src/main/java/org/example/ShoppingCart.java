package org.example;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ShoppingCart {
    private List<Meal> items = new ArrayList<Meal>();
    public String addItem(Meal meal) {
        if (meal == null) {
            return "Cannot add null meal";
        }
        if (!meal.isAvailable()) {
            return meal.getName() + " is not available";
        }
        items.add(meal);
        return meal.getName() + " added to cart";
    }

    public List<Meal> getItems() { return Collections.unmodifiableList(items); }
    public int getItemCount() { return items.size(); }

    public double getTotal() {
        double result=0.0;
        for(Meal m:items)
            result+=m.getPrice();
        return result;
    }
    public void clear() {
        items.clear();
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }
}


