package org.example;

public class ChefOrderService {
    private ChefManager chefManager;
    private CustomerManager customerManager;
    public ChefOrderService(ChefManager chefManager, CustomerManager customerManager) {
        this.chefManager = chefManager;
        this.customerManager = customerManager;
    }
    public String viewCustomerOrderHistory(int chefId, int customerId) {
        try {
            Customer customer = customerManager.getCustomerById(customerId);
            // Get and verify orders
            String history = customer.getFormattedOrderHistory();
            return history.isEmpty() ? "No orders found" : history;
        } catch (IllegalArgumentException e) {
            return "Customer not found ";
        }
    }
}
