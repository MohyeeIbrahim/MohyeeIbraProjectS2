package org.example;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ShoppingCart {
    private List<Meal> items = new ArrayList<Meal>();
    public void addItem(Meal meal) {
        if (meal == null) {
            System.out.println("Cannot add null meal");
            return;
        }
        if (meal.isAvailable()) {
            items.add(meal);
            System.out.println(meal.getName() + " added to cart");
        } else {
            System.out.println(meal.getName() + " is not available");
        }
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


