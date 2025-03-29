package org.example;
import java.util.*;
public class ChefManager {
    private List<Chef> chefs; // List to store multiple chefs
    private Chef currentChef; // Currently logged-in chef

    public ChefManager() {
        chefs = new ArrayList<Chef>();
    }

    // Add a new chef to the list
    public void addChef(Chef chef) {
        chefs.add(chef);
    }

    // Log in a chef by their ID
    public void loginChef(int chefId) {
        for (Chef chef : chefs) {
            if (chef.getChefId() == chefId) {
                currentChef = chef;
                return;
            }
        }
        throw new IllegalArgumentException("Chef with ID " + chefId + " not found.");
    }

    // Get the currently logged-in chef
    public Chef getCurrentChef() {
        if (currentChef == null) {
            throw new IllegalStateException("No chef is currently logged in.");
        }
        return currentChef;
    }

    // Get a chef by their ID
    public Chef getChefById(int chefId) {
        for (Chef chef : chefs) {
            if (chef.getChefId() == chefId) {
                return chef;
            }
        }
        throw new IllegalArgumentException("Chef with ID " + chefId + " not found.");
    }
}
