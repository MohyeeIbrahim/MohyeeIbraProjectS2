package org.example;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    public static List<Customer> customer;
    public CustomerManager(){
        customer=new ArrayList<Customer>();
    }
    public void addCustomer(Customer currentCustomer) {
        customer.add(currentCustomer);
    }


}
