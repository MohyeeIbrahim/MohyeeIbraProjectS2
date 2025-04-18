package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Chef;
import org.example.ChefManager;
import org.example.KitchenManager;

import java.util.*;

import static org.junit.Assert.*;

public class Feature10 {
    private ChefManager chefManager;
    private KitchenManager kitchenManager;
    private Map<String, Chef> chefMap;

@Before
public void setUp(){
    chefManager = new ChefManager();
    kitchenManager = new KitchenManager(chefManager);
    chefMap = new HashMap<String,Chef>();
}

    @Given("chef {string} has expertise in {string} having {int} tasks")
    public void chef_has_expertise_in_having_tasks(String chefName, String expertise, Integer taskCount) {
        Chef chef = new Chef(chefManager.getAllChefs().size() + 1, chefName, "password");
        chef.addExpertise(expertise);
        chef.setTaskCount(taskCount);
        chefManager.addChef(chef);
        chefMap.put(chefName, chef);

    }

    @Given("chef {string} has expertise in {string} having {int} task")
    public void chef_has_expertise_in_having_task(String chefName, String expertise, Integer taskCount) {
        chef_has_expertise_in_having_tasks(chefName, expertise, taskCount);
        assertTrue(chefMap.size()==2);
    }

    @When("the manager assigns a new {string} task")
    public void the_manager_assigns_a_new_task(String taskType) {
        kitchenManager.assignTask(taskType);
    }

    @Then("the task should be assigned to chef {string}")
    public void the_task_should_be_assigned_to_chef(String expectedChefName) {
        Chef chef = chefMap.get(expectedChefName);
        assertNotNull("Expected chef not found", chef);
        assertTrue("Chef should have at least one task", chef.getTaskCount() > 0);
    }
    //2nd scenario
    @Given("chef {string} has expertise in {string}")
    public void chef_has_expertise_in(String chefName, String expertise) {
        Chef chef = new Chef(chefManager.getAllChefs().size() + 1, chefName, "password");
        chef.addExpertise(expertise);
        chefManager.addChef(chef);
        chefMap.put(chefName, chef);
    }


    @When("the manager try to assigns a {string} task to {string}")
    public void the_manager_try_to_assigns_a_task_to(String taskType, String chefName) {
      kitchenManager.assignTask(taskType);
    }

    @Then("the task should not be assigned to {string}")
    public void the_task_should_not_be_assigned_to(String chefName) {
        Chef chef = chefMap.get(chefName);
        assertNotNull("Chef should exist", chef);
        assertTrue("Chef should not have been assigned a task", chef.getTaskCount() == 0);
    }


    }
