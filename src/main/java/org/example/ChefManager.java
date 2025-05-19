package org.example;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChefManager {
    private List<Chef> chefs;
    private CustomerManager customerManager;
    private static final Logger logger = LoggerFactory.getLogger(ChefManager.class);



    public ChefManager() {
        chefs = new ArrayList<>();
    }
    public ChefManager(CustomerManager customerManager) {
        this();
        this.customerManager=customerManager;
    }


    public void addChef(Chef chef) {
        chefs.add(chef);
    }

    public List<Chef> getAllChefs() {
        return chefs;
    }

    public void sendAlertToChef(int chefId, String message) {
        for (Chef chef : chefs) {
            if (chef.getChefId() == chefId) {
                chef.addSubstitutionAlert(message);
                logger.warn("Alert to Chef {}!: {}", chef.getChefName(), message);
                return;
            }
        }
        logger.warn("Chef with ID {} not found", chefId);
    }
    public String viewCustomerOrderHistory(int customerId) {
        try {
            Customer customer = customerManager.getCustomerById(customerId);
            String history = customer.getFormattedOrderHistory();
            return history.isEmpty() ? "No orders found" : history;
        } catch (Exception e) {
            return "Customer not found";
        }
    }


}
