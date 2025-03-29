package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Chef;
import org.example.ChefManager;
import org.example.Customer;
import org.example.CustomerManager;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Feature2 {

    private ChefManager chefManager;
    private CustomerManager customerManager;
    private Customer customer;
    private String mealPlan;





    //1st scenario  -- |>>>>

    @Given("the chef is logged in")
    public void the_chef_is_logged_in() {
        chefManager=new ChefManager();
        chefManager.addChef(new Chef(1, "Chef John", "1234"));

        chefManager.loginChef(1);
    }

    @Given("the customer {int} has provided dietary preferences and allergies")
    public void the_customer_has_provided_dietary_preferences_and_allergies(Integer customerId) {
        customerManager = new CustomerManager();
        customer = new Customer(customerId, "mohyee", "12345", "Vegetarian", "None");
        customerManager.addCustomer(customer);
    }

    @When("the chef enters view_preferences {int}")
    public void the_chef_enters_view_preferences(Integer customerId) {
        customer = customerManager.getCustomerById(customerId);
    }



    @Then("the system should display {string}")
    public void the_system_should_display(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("Vegetarian", customer.getDietaryPreference());
        assertEquals("None", customer.getAllergies());
         System.out.println("Dietary Preference: " + customer.getDietaryPreference() + ", Allergies: " + customer.getAllergies());
    }


    //2ndscenario  -- |>>>>

    @Given("the customer {int} has dietary preferences {string} and allergies {string}")
    public void the_customer_has_dietary_preferences_and_allergies(Integer customerId, String dietaryPreference, String allergies) {
        customerManager = new CustomerManager();
        customer = new Customer(customerId, "ibra", "12345", dietaryPreference, allergies);
        customerManager.addCustomer(customer);
    }

    @Given("the chef has viewed the customer's dietary preferences")
    public void the_chef_has_viewed_the_customer_s_dietary_preferences() {
        chefManager = new ChefManager();
        Chef chef = new Chef(2, "Chef ibra","8888");
        chefManager.addChef(chef);
        chefManager.loginChef(2);
        System.out.println("Chef has viewed dietary preferences for customer " + customer.getCustomerId());
    }

    @When("the chef creates a meal plan for customer {int}")
    public void the_chef_creates_a_meal_plan_for_customer(Integer customerId) {
        // Simulate meal plan creation based on customer preferences and allergies
        String dietaryPreference = customer.getDietaryPreference();
        String allergies = customer.getAllergies();

        mealPlan = "Meal Plan for Customer " + customerId + ": " +
                "Dietary Preference: " + dietaryPreference + ", " +
                "Excluded Allergens: " + allergies;
    }

    @Then("the system should generate a meal plan excluding {string}")
    public void the_system_should_generate_a_meal_plan_excluding(String allergen) {
        assertTrue(mealPlan.contains("Excluded Allergens: " + allergen));
        System.out.println("Meal plan excludes " + allergen);
    }

    @Then("the meal plan should include {string} meals")
    public void the_meal_plan_should_include_meals(String dietaryPreference) {
        assertTrue(mealPlan.contains("Dietary Preference: " + dietaryPreference));
        System.out.println("Meal plan includes " + dietaryPreference + " meals");
    }

//2nd scenario

}
