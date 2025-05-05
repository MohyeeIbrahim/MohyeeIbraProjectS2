package org.example;
import java.util.*;
public class ChefManager {
    private List<Chef> chefs;
    private Chef currentChef;


    public ChefManager() {
        chefs = new ArrayList<Chef>();
    }

    public void addChef(Chef chef) {
        chefs.add(chef);
    }
    public boolean loginChef(int chefId) {
        for (Chef chef : chefs) {
            if (chef.getChefId() == chefId) {
                currentChef = chef;
                return true;
            }
        }
        System.out.println("Chef with ID " + chefId + " not found.");
        return false;
    }

    public List<Chef> getAllChefs() {
        return chefs;
    }
}
