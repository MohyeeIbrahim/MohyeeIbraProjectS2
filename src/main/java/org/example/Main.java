package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CustomerManager customerManager = new CustomerManager();
        ChefManager chefManager = new ChefManager(customerManager);
        InventoryManager inventoryManager = new InventoryManager();
        SupplierManager supplierManager = new SupplierManager();
        KitchenManager kitchenManager = new KitchenManager(chefManager);
        CustomMealService customMealService = new CustomMealService();
        SubstitutionService substitutionService = new SubstitutionService();
        MealDeliveryReminderSystem reminderSystem = new MealDeliveryReminderSystem();


        SystemAdministrator admin = new SystemAdministrator(customerManager);


        Customer customer1 = new Customer(1, "John Doe", "password123", "Vegetarian", "Peanuts");
        Customer customer2 = new Customer(2, "Jane Smith", "secure456", "Vegan", "Shellfish");
        customerManager.addCustomer(customer1);
        customerManager.addCustomer(customer2);


        Chef chef1 = new Chef(101, "Gordon Ramsay", "masterchef");
        chef1.addExpertise("Italian");
        chef1.addExpertise("French");

        Chef chef2 = new Chef(102, "Jamie Oliver", "healthyfood");
        chef2.addExpertise("Vegan");
        chef2.addExpertise("Vegetarian");

        chefManager.addChef(chef1);
        chefManager.addChef(chef2);


        Meal pasta = new Meal(1, "Pasta Carbonara", 12.99, true);
        Meal salad = new Meal(2, "Caesar Salad", 8.99, true);
        Meal burger = new Meal(3, "Veggie Burger", 10.99, true);


        List<Meal> order1Meals = new ArrayList<>();
        order1Meals.add(pasta);
        order1Meals.add(salad);
        Order order1 = new Order(1001, LocalDate.now(), order1Meals);
        customer1.addOrder(order1);

        List<Meal> order2Meals = new ArrayList<>();
        order2Meals.add(burger);
        order2Meals.add(salad);
        Order order2 = new Order(1002, LocalDate.now().minusDays(1), order2Meals);
        customer2.addOrder(order2);


        System.out.println("\n=== Kitchen Operations ===");
        kitchenManager.assignTask("Italian");
        kitchenManager.assignTask("Vegan");


        System.out.println("\nChef Notifications:");
        for (Chef chef : chefManager.getAllChefs()) {
            System.out.println(chef.getChefName() + ": " + chef.getNotifications());
        }


        System.out.println("\n=== Inventory Management ===");
        inventoryManager.addIngredient("Tomato", 20, 5);
        inventoryManager.addIngredient("Basil", 3, 5); // Low stock
        System.out.println("Tomato stock status: " + inventoryManager.getStockStatus("Tomato"));
        System.out.println("Basil stock status: " + inventoryManager.getStockStatus("Basil"));
        System.out.println("Restock suggestions: " + inventoryManager.getRestockSuggestions());


        System.out.println("\n=== Supplier Management ===");
        supplierManager.addOrUpdatePrice("Tomato", "Fresh Farms", 1.99);
        supplierManager.addOrUpdatePrice("Tomato", "Organic Co", 2.49);
        supplierManager.addOrUpdatePrice("Tomato", "Local Market", 1.79);
        System.out.println("Best tomato supplier: " + supplierManager.getBestSupplier("Tomato") +
                " at $" + supplierManager.getBestPrice("Tomato"));


        System.out.println("\n=== Custom Meal Service ===");
        List<String> ingredients = List.of("tomato", "basil", "mozzarella");
        System.out.println("Creating custom meal with: " + ingredients);
        System.out.println(customMealService.createCustomMealRequest(1, ingredients));


        System.out.println("\n=== Ingredient Substitution ===");
        CustomMealRequest request = new CustomMealRequest(1, 1, List.of("butter", "sugar"));
        System.out.println("Original ingredients: " + request.getIngredients());
        substitutionService.applySubstitution(request, chefManager, 101);
        System.out.println("Updated ingredients: " + request.getIngredients());


        System.out.println("\n=== Delivery Reminders ===");
        reminderSystem.setCurrentTime("2023-12-01 10:00 AM");
        reminderSystem.scheduleDelivery("2023-12-01 12:00 PM", false);
        reminderSystem.scheduleDelivery("2023-12-02 01:00 PM", true);
        System.out.println("Reminders: " + reminderSystem.sendReminders(2));


        System.out.println("\n=== Financial Reporting ===");
        try {
            Path reportPath = admin.exportFinancialReportToPdf(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
            System.out.println("Generated financial report at: " + reportPath);

            FinancialReportService.Report report = admin.reportService.generateReport(
                    LocalDate.now().getMonthValue(), LocalDate.now().getYear());
            System.out.println("\nFinancial Summary:");
            System.out.println("Revenue: $" + report.getRevenue());
            System.out.println("Expenses: $" + report.getExpenses());
            System.out.println("Profit: $" + report.getNetProfit());
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }

        System.out.println("\n=== Customer Order History ===");
        System.out.println("Customer 1 order history:\n" + admin.getCustomerOrderHistory(1));
    }
}