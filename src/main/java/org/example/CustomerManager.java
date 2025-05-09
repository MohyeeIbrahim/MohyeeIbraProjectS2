package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CustomerManager {
    public  List<Customer> customer;
    private Customer currentCustomer;
    public CustomerManager(){
        customer=new ArrayList<Customer>();
    }

    public void addCustomer(Customer currentCustomer) {
        customer.add(currentCustomer);
    }
    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    public Customer getCustomerById(Integer customerId) {
            for (Customer c : customer) {
                if (c.getCustomerId() == customerId) {
                    return c;
                }
            }
            return null;
    }

    public String reorderMeal(Customer customer, int mealId) {
        Optional<Meal> mealToReorder = customer.findMealInHistory(mealId);
        if (mealToReorder.isPresent()) {
            customer.getCart().addItem(mealToReorder.get());
            return "Added " + mealToReorder.get().getName() + " to your cart";
        }
        return "Meal not found in your history";
    }
    public List<Customer> getAllCustomers() {
        return Collections.unmodifiableList(customer);
    }
}
