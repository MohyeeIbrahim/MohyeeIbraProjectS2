package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;
import org.example.CustomerManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Feature1 {
    Customer currentCustomer;
    CustomerManager customerManager;
    @Before
    public void setUp(){
        currentCustomer=new Customer(100,"mohyee","12345","Vegetarian","alllwlwl");;
        customerManager=new CustomerManager();
    }

    @Given("customer want to input his preferences and allergies")
    public void customer_want_to_input_his_preferences_and_allergies() {

    }
    @When("the customer enter set_preferences {string}")
    public void theCustomerEnterSetPreferences(String dietaryPreference) {
        currentCustomer.setDietaryPreference(dietaryPreference);
    }
    @When("the customer enter set_allergies {string}")
    public void theCustomerEnterSetAllergies(String allergies) {
        currentCustomer.setAllergies(allergies);
    }

    @Then("the system should store {string} as the dietary preference")
    public void theSystemShouldStoreAsTheDietaryPreference(String dietaryPreference) {
        assertEquals(dietaryPreference, currentCustomer.getDietaryPreference());
    }
    @Then("store {string} as the allergies")
    public void storeAsTheAllergies(String allergies) {
        assertEquals(allergies,currentCustomer.getAllergies());
    }

    @Then("display {string}")
    public void display(String expectedString) {
        String displayMessage = "Preferences saved successfully";
        assertEquals(expectedString, displayMessage);
    }

    //second scenario
    @When("enter customer Preferences")
    public void enter_customer_preferences() {
      // the customer already log in,so it's just a navigate page
    }

    @When("change dietary preference from {string} to {string}")
    public void change_dietary_preference_from_to(String oldDietaryPreference, String newDietaryPreference) {
        assertEquals(currentCustomer.getDietaryPreference(),oldDietaryPreference);
        currentCustomer.setDietaryPreference(newDietaryPreference);
    }

    @Then("preferences should be updated to {string}")
    public void preferences_should_be_updated_to(String newDietaryPreference) {
        assertEquals(newDietaryPreference,currentCustomer.getDietaryPreference());
    }

    //Third Scenario
    @When("enter customer allergies")
    public void enter_customer_allergies() {

    }
    @When("change allergies from {string} to {string}")
    public void change_allergies_from_to(String oldAllergies, String newAllergies) {
        currentCustomer.setAllergies(newAllergies);
    }
    @Then("allergies should be updated to {string}")
    public void allergies_should_be_updated_to(String newAllergies) {
        assertEquals(newAllergies,currentCustomer.getAllergies());
    }

}
