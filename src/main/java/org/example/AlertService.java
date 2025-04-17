package org.example;

public class AlertService {
    private String lastAlert;

    public void sendAlert(String message) {
        this.lastAlert = message;
        System.out.println("Alert to Chef !: " + message);
    }
    public String getLastAlert() {
        return lastAlert;
    }
}
