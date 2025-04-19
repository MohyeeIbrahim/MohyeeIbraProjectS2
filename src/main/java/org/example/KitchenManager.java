package org.example;

import java.util.List;

public class KitchenManager {
    private ChefManager chefManager;

    public KitchenManager(ChefManager chefManager) {
        this.chefManager = chefManager;
    }
    public void assignTask(String taskType) {
        List<Chef> chefs = chefManager.getAllChefs();
        Chef suitableChef = null;

        for (Chef chef : chefs) {
            if (chef.hasExpertise(taskType)) {
                if (suitableChef == null || chef.getTaskCount() < suitableChef.getTaskCount()) {
                    suitableChef = chef;
                }
            }
        }
        if (suitableChef != null) {
            suitableChef.addTask(); // Increase task count
            suitableChef.addNotification("New task assigned: " + taskType);
            System.out.println("Assigned " + taskType + " task to Chef " + suitableChef.getChefName());
        } else {
            System.out.println("No suitable chef available" );
        }
    }
}
