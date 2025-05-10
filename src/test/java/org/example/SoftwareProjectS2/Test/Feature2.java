package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Chef;
import org.example.ChefManager;
import org.example.Customer;
import org.example.CustomerManager;

import static org.junit.Assert.*;

public class Feature2 {
    private Chef chef;
    private ChefManager chefManager;
    private CustomerManager customerManager;
    private Customer customer;

    //1st scenario
   @Before
    public void setUp(){
        chef=new Chef(1, "Chef John", "1234");
        chefManager=new ChefManager();
        chefManager.addChef(chef);
       customerManager = new CustomerManager();
   }

    @Given("the customer {int} has provided dietary preferences and allergies")
    public void the_customer_has_provided_dietary_preferences_and_allergies(Integer customerId) {
        customer = new Customer(customerId, "mohyee", "12345", "Vegetarian", "None");
        customerManager.addCustomer(customer);
    }

    @When("the chef enters view_preferences {int}")
    public void the_chef_enters_view_preferences(Integer customerId) {
        customer = customerManager.getCustomerById(customerId);
    }

    @Then("the system should display {string}")
    public void the_system_should_display(String string) {
        assertEquals("Vegetarian", customer.getDietaryPreference());
        assertEquals("None", customer.getAllergies());
    }
    //2nd scenario
    @Given("the customer {int} has not provided any dietary preferences")
    public void the_customer_has_not_provided_any_dietary_preferences(Integer customerId) {
       customer=new Customer(customerId,"ali","123","","penaut");
       customerManager.addCustomer(customer);
    }
    @When("the chef enter view_preferences")
    public void the_chef_enter_view_preferences() {

    }
    @Then("the display {string}")
    public void the_display(String expectedString) {
       assertEquals(expectedString,customer.getDietaryPreference());
    }



}
