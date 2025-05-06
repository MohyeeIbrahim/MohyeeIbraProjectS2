package org.example;
import java.util.*;
public class ChefManager {
    private List<Chef> chefs;

    public ChefManager() {
        chefs = new ArrayList<Chef>();
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
                System.out.println("Alert to Chef " + chef.getChefName() + "!: " + message);
                return;
            }
        }
        System.out.println("Chef with ID " + chefId + " not found.");
    }


}
