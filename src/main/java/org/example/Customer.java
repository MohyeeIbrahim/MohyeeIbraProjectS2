package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Customer {
    int customerId;
    String customerName;
    String password;
    String  dietaryPreference;
    String  allergies;
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
        return new OrderHistory(orderHistory).formatAllOrders();
    }

    public Optional<Meal> findMealInHistory(int selectedMealId) {
        return orderHistory.stream()
                .flatMap(order -> order.getMeals().stream())
                .filter(meal -> meal.getId() == selectedMealId)
                .findFirst();
    }

    public ShoppingCart getCart() {
        return this.cart;
    }

}
