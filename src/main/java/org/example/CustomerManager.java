package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerManager {
    public static List<Customer> customer;
    private Customer currentCustomer;

    public CustomerManager(){
        customer=new ArrayList<Customer>();
    }

    public void addCustomer(Customer currentCustomer) {
        customer.add(currentCustomer);
    }

    public Customer getCustomerById(Integer customerId) {
        {
            for (Customer c : customer) {
                if (c.getCustomerId() == customerId) {
                    return c;
                }
            }
            throw new IllegalArgumentException("Chef with ID " + customerId + " not found.");
        }
    }
    public boolean loginCustomer(int customerId) {
        for (Customer cus : customer) {
            if (cus.getCustomerId() == customerId) {
                currentCustomer = cus;
                return true;
            }
        }
        System.out.println("Chef with ID " + customerId + " not found.");
        return false;
    }
    public String reorderMeal(int mealId) {
        Optional<Meal> mealToReorder = currentCustomer.findMealInHistory(mealId);

        if (mealToReorder.isPresent()) {
            currentCustomer.getCart().addItem(mealToReorder.get());
            return "Added " + mealToReorder.get().getName() + " to your cart";
        }
        return "Meal not found in your history";
    }
}
