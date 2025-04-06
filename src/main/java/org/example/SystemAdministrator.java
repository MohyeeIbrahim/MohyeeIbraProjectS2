package org.example;

public class SystemAdministrator {
    private final CustomerManager customerManager;

    public SystemAdministrator(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
    public String getCustomerOrderHistory(Integer customerId) {
        Customer customer = customerManager.getCustomerById(customerId);
        return customer.getFormattedOrderHistory();

    }
}
