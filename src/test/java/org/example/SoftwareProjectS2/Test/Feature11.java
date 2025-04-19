package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.Chef;
import org.example.ChefManager;
import org.example.KitchenManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Feature11 {
    private ChefManager chefManager;
    private KitchenManager kitchenManager;
    private Map<String, Chef> chefMap;
    @Before
    public void setUp(){
        chefManager = new ChefManager();
        kitchenManager = new KitchenManager(chefManager);
        chefMap = new HashMap<String,Chef>();
    }
    //1st Scenario
    @Given("chef {string} has expertise in {string} has {int} tasks")
    public void chef_has_expertise_in_has_tasks(String name, String expertise, Integer taskCount) {
        Chef chef = new Chef(chefManager.getAllChefs().size() + 1, name, "pass");
        chef.addExpertise(expertise);
        chef.setTaskCount(taskCount);
        chefManager.addChef(chef);
        chefMap.put(name, chef);
    }
    @Given("chef {string} has expertise in {string} has {int} task")
    public void chef_has_expertise_in_has_task(String name, String expertise, Integer taskCount) {
        chef_has_expertise_in_has_tasks(name, expertise, taskCount);
    }
    @When("the manager assigns a {string} task")
    public void the_manager_assigns_a_task(String task) {
        kitchenManager.assignTask(task);
    }
    @Then("{string} should receive a notification {string}")
    public void should_receive_a_notification(String name, String expectedNotification) {
        Chef chef = chefMap.get(name);
        assertNotNull("Chef not found", chef);
        List<String> notifications = chef.getNotifications();
        assertTrue("Expected notification not found", notifications.contains(expectedNotification));
    }
    @Then("{string} should not receive any notification")
    public void should_not_receive_any_notification(String name) {
        Chef chef = chefMap.get(name);
        assertNotNull("Chef not found", chef);
        List<String> notifications = chef.getNotifications();
        assertTrue("Chef should not have any notifications", notifications.isEmpty());
    }
    //2nd sceanrio
    @Given("no chef has expertise in {string}")
    public void no_chef_has_expertise_in(String expertise) {

    }
    @When("the manager tries to assign a {string} task")
    public void the_manager_tries_to_assign_a_task(String taskType) {
        kitchenManager.assignTask(taskType);

    }
    @Then("no notification should be sent to any chef")
    public void no_notification_should_be_sent_to_any_chef() {
        Chef chef1 = new Chef(5,"ali","pass1");
        Chef chef2 = new Chef(6,"moha","pass2");
        chefManager.addChef(chef1);
        chefManager.addChef(chef2);

        for (Chef chef : chefManager.getAllChefs()) {
            assertTrue("No notifications should be sent", chef.getNotifications().isEmpty());
        }
    }


}
