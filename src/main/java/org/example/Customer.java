package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Customer {
    private int customerId;
    private String customerName;
    String password;
    String dietaryPreference;
    String allergies;
    List<Order> orderHistory ;
    private ShoppingCart cart;

    public Customer(Integer customerId,String customerName, String password,String dietaryPreference,String allergies) {
        this.customerName=customerName;
        this.password=password;
        this.customerId=customerId;
        this.dietaryPreference=dietaryPreference;
        this.allergies=allergies;
        this.orderHistory = new ArrayList<Order>();
        this.cart=new ShoppingCart();
    }
    public int getCustomerId() {
            return customerId;
    }

    public void setDietaryPreference(String dietaryPreference) {
        if (dietaryPreference == null||dietaryPreference.trim().isEmpty()||!dietaryPreference.matches("[a-zA-Z]+")) {
            System.out.println("Error: Please enter valid dietary preferences");
            return;
        }
        this.dietaryPreference=dietaryPreference;
    }

    public String getDietaryPreference() {
        if (dietaryPreference == null || dietaryPreference.isEmpty())
            return "No dietary preferences available for this customer";
        return dietaryPreference;
    }

    public void setAllergies(String allergies) {
        this.allergies=allergies;
    }
    public String getAllergies() {
        return allergies;
    }
    public void addOrder(Order order) {
        orderHistory.add(order);
    }
    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }
    public String getFormattedOrderHistory() {
        if (orderHistory.isEmpty()) {
            return "No past orders found";
        }

        StringBuilder sb = new StringBuilder("Customer past orders:\n");
        int orderNumber = 1;
        List<Order> sortedOrders = new ArrayList<>(orderHistory);
        sortedOrders.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));

        for (Order order : sortedOrders) {
            sb.append(order.formatForDisplay(orderNumber++)).append("\n");
        }
        return sb.toString().trim();
    }

    public Optional<Meal> findMealInHistory(int selectedMealId) {
        for (Order order : orderHistory) {
            for (Meal meal : order.getMeals()) {
                if (meal.getId() == selectedMealId) {
                    return Optional.of(meal);
                }
            }
        }
        return Optional.empty();
    }

    public ShoppingCart getCart() {
        return this.cart;
    }


    public String getName() {
        return customerName;
    }
}
