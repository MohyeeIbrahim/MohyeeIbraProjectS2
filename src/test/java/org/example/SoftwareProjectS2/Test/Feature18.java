package org.example.SoftwareProjectS2.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MealDeliveryReminderSystem;
import java.util.List;

import static org.junit.Assert.*;

public class Feature18 {

    private MealDeliveryReminderSystem reminderSystem;
    private List<String> notifications;

    @Before
    public void setUp(){
        reminderSystem = new MealDeliveryReminderSystem();
    }

    @Given("the current time is {string}")
    public void the_current_time_is(String string) {
        reminderSystem.setCurrentTime(string);

    }
    @Given("the customer has a confirmed meal delivery scheduled for {string}")
    public void the_customer_has_a_confirmed_meal_delivery_scheduled_for(String deliveryTime) {
        reminderSystem.scheduleDelivery(deliveryTime, false);
    }
    @When("the system sends reminders {int} hour before delivery")
    public void the_system_sends_reminders_hour_before_delivery(Integer int1) {
        notifications = reminderSystem.sendReminders(int1);

    }
    @Then("the customer should receive a notification with the message {string}")
    public void the_customer_should_receive_a_notification_with_the_message(String string) {
        assertNotNull("Notification list should not be null", notifications);
        assertTrue("Expected notification not found", notifications.contains(string));
    }

    //2nd Scenario
    @Given("current time is {string}")
    public void current_time_is(String string) {
        reminderSystem.setCurrentTime(string);

    }
    @Given("the customer has a cancelled meal delivery scheduled for {string}")
    public void the_customer_has_a_cancelled_meal_delivery_scheduled_for(String string) {
        reminderSystem.scheduleDelivery(string, true);
    }
    @When("the system send reminders {int} hour before delivery")
    public void the_system_send_reminders_hour_before_delivery(Integer int1) {
        notifications = reminderSystem.sendReminders(int1);
    }
    @Then("the customer should not receive any notification for that order")
    public void the_customer_should_not_receive_any_notification_for_that_order() {
        assertNotNull("Notification list should not be null", notifications);
        assertTrue("Expected no notifications, but some were found", notifications.isEmpty());
    }

    //3rd Scenario
    @Given("the time is {string}")
    public void the_time_is(String time) {
        reminderSystem.setCurrentTime(time);

    }
    @Given("customer has a confirmed meal delivery scheduled for {string}")
    public void customer_has_a_confirmed_meal_delivery_scheduled_for(String time) {
        reminderSystem.scheduleDelivery(time, false);

    }
    @Given("the customer has a confirmed meal delivery scheduled {string}")
    public void the_customer_has_a_confirmed_meal_delivery_scheduled(String time) {
        reminderSystem.scheduleDelivery(time, false);
    }
    @Given("the customer  cancelled meal delivery scheduled for {string}")
    public void the_customer_cancelled_meal_delivery_scheduled_for(String time) {
        reminderSystem.scheduleDelivery(time, true);

    }
    @When("system sends reminders {int} hour before delivery")
    public void system_sends_reminders_hour_before_delivery(Integer hoursBefore) {
        notifications = reminderSystem.sendReminders(hoursBefore);
    }
    @Then("customer should receive a notification with the message {string}")
    public void customer_should_receive_a_notification_with_the_message(String expectedMessage) {
        assertNotNull(notifications);
        assertTrue(notifications.contains(expectedMessage));
    }
    @Then("the customer should not yet receive a notification for the {int}:{int} AM delivery")
    public void the_customer_should_not_yet_receive_a_notification_for_the_am_delivery(Integer hour, Integer minute) {
        String expectedSubstring = reminderSystem.getFormattedTime(hour, minute);
        assertNotNull(notifications);
        assertFalse(reminderSystem.containsNotificationForTime(expectedSubstring, notifications));
    }
    @Then("the customer should not receive any notification for the cancelled order")
    public void the_customer_should_not_receive_any_notification_for_the_cancelled_order() {
        assertFalse(reminderSystem.containsCancelledDeliveryNotification(notifications));
    }


}
