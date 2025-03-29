package org.example;

import java.util.List;

public class Customer {
    int customerId;
    String customerName;
    String password;
    String  dietaryPreference;
    String  allergies;
    // List <Order> orderHistory;

    public Customer(Integer customerId,String customerName, String password,String dietaryPreference,String allergies) {
        this.customerName=customerName;
        this.password=password;
        this.customerId=customerId;
        this.dietaryPreference=dietaryPreference;
        this.allergies=allergies;
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
}
