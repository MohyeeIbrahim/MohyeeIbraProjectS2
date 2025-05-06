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

    public List<Chef> getAllChefs() {
        return chefs;
    }
}
