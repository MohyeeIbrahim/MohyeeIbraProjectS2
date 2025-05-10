package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Feature19 {

    @Before
    public void setUp(){

    }
    @Given("a cooking task {string} is scheduled at {string}")
    public void a_cooking_task_is_scheduled_at(String string, String string2) {

    }

    @When("the system sends a reminder {int} minutes before the scheduled time")
    public void the_system_sends_a_reminder_minutes_before_the_scheduled_time(Integer int1) {

    }

    @Then("the chef should see the console output {string}")
    public void the_chef_should_see_the_console_output(String string) {

    }
    //2nd Scenario
    @Given("a new cooking task {string} is assigned to the chef for {string}")
    public void a_new_cooking_task_is_assigned_to_the_chef_for(String string, String string2) {

    }

    @When("the system processes the task assignment")
    public void the_system_processes_the_task_assignment() {

    }

    @Then("the chef should see {string}")
    public void the_chef_should_see(String string) {

    }

    //3rd Scenario
    @Given("a cooking task {string} originally scheduled at {string} is rescheduled to {string}")
    public void a_cooking_task_originally_scheduled_at_is_rescheduled_to(String string, String string2, String string3) {

    }

    @When("the system processes the task update")
    public void the_system_processes_the_task_update() {

    }

}
