package org.example;

public class Meal {
    private int mealId;
    private String name;
    private boolean available;


    public Meal(int id, String name,boolean available) {
        this.mealId = id;
        this.name = name;
        this.available=available;
    }
    public String getName() {
        return name;
    }
    public int getId(){
        return mealId;
    }
    public boolean isAvailable(){
        return available;
    }


}
