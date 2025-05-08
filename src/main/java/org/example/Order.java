package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Order {
    private  int orderId;
    private  LocalDate date;
    private  List<Meal> meals;

    public Order(int id, LocalDate date, List<Meal> meals) {
        this.orderId = id;
        this.date = date;
        this.meals = new ArrayList<>(meals);
    }

    public LocalDate getDate() {
        return date;
    }
    public List<Meal> getMeals() {
        return new ArrayList<Meal>(meals);
    }

    public String formatForDisplay(int orderNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(orderNumber).append(". [").append(date).append("] ");

        List<String> mealNames = new ArrayList<String>();
        for (Meal meal : meals) {
            mealNames.add(meal.getName());
        }
        sb.append(String.join(", ", mealNames));
        return sb.toString();
    }

}
