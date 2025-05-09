package org.example;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    private final int chefId;
    private final String chefName;
    private  String password;
    List<String> expertise;
    Integer assignedTasks;
    List<String> notifications;
    private List<String> substitutionAlerts = new ArrayList<>();

    public Chef(int chefId, String chefName,String password) {
          this.chefId=chefId;
          this.chefName=chefName;
          this.password=password;
          expertise=new ArrayList<String>();
          assignedTasks=0;
          notifications=new ArrayList<String>();
    }

    public int getChefId() {
    return chefId;
    }
    public String getChefName(){
        return chefName;
    }
    public void addTask() {
        assignedTasks++;
    }
    public int getTaskCount() {
        return assignedTasks;
    }
    public boolean hasExpertise(String taskType) {
        return expertise.contains(taskType);
    }
    public void addExpertise(String expertises){
        expertise.add(expertises);
    }

    public void setTaskCount(Integer taskCount) {
        this.assignedTasks=taskCount;
    }
    public void addNotification(String message) {
        notifications.add(message);
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void addSubstitutionAlert(String alert) {
        substitutionAlerts.add(alert);
    }

    public List<String> getSubstitutionAlerts() {
        return substitutionAlerts;
    }

}
