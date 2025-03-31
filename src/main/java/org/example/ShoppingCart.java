package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private List<Meal> items = new ArrayList<Meal>();

    public void addItem(Meal meal) {
        if (meal.isAvailable()) {
            items.add(meal);
        }
    }

    public List<Meal> getItems() { return Collections.unmodifiableList(items); }
    public int getItemCount() { return items.size(); }
}

