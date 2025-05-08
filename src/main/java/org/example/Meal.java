package org.example;

public class Meal {
    private final int mealId;
    private final String name;
    private final double price;
    private final boolean available;

    public Meal(int id, String name, double price, boolean available) {
        this.mealId = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return mealId;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

}
