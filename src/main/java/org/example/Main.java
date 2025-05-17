package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*private static CustomerManager customerManager = new CustomerManager();
    private static ChefManager chefManager = new ChefManager(customerManager);
    private static InventoryManager inventoryManager = new InventoryManager();
    private static SupplierManager supplierManager = new SupplierManager();
    private static KitchenManager kitchenManager = new KitchenManager(chefManager);
    private static CustomMealService customMealService = new CustomMealService();
    private static SubstitutionService substitutionService = new SubstitutionService();
    private static SystemAdministrator admin = new SystemAdministrator(customerManager);
    private static Scanner scanner = new Scanner(System.in);*/

    public static void main(String[] args) {
        /*initializeSampleData();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Meal Delivery System ===");
            System.out.println("1. Customer Operations");
            System.out.println("2. Chef Operations");
            System.out.println("3. Kitchen Operations");
            System.out.println("4. Inventory Management");
            System.out.println("5. Supplier Management");
            System.out.println("6. Financial Reports");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    chefMenu();
                    break;
                case 3:
                    kitchenMenu();
                    break;
                case 4:
                    inventoryMenu();
                    break;
                case 5:
                    supplierMenu();
                    break;
                case 6:
                    financialMenu();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("Exiting system...");
        scanner.close();
    }

    private static void initializeSampleData() {

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


        inventoryManager.addIngredient("Tomato", 20, 5);
        inventoryManager.addIngredient("Basil", 3, 5);
        inventoryManager.addIngredient("Mozzarella", 15, 5);


        supplierManager.addOrUpdatePrice("Tomato", "Fresh Farms", 1.99);
        supplierManager.addOrUpdatePrice("Tomato", "Organic Co", 2.49);
        supplierManager.addOrUpdatePrice("Tomato", "Local Market", 1.79);
    }

    private static void customerMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Customer Operations ===");
            System.out.println("1. View Order History");
            System.out.println("2. Create Custom Meal Request");
            System.out.println("3. Reorder Meal");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter customer ID: ");
                    int custId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(customerManager.getCustomerById(custId).getFormattedOrderHistory());
                    break;
                case 2:
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter ingredients (comma separated): ");
                    String[] ingredients = scanner.nextLine().split(",");
                    List<String> ingredientList = new ArrayList<>();
                    for (String ingredient : ingredients) {
                        ingredientList.add(ingredient.trim());
                    }
                    System.out.println(customMealService.createCustomMealRequest(customerId, ingredientList));
                    break;
                case 3:
                    System.out.print("Enter customer ID: ");
                    int cId = scanner.nextInt();
                    System.out.print("Enter meal ID to reorder: ");
                    int mealId = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = customerManager.getCustomerById(cId);
                    System.out.println(customerManager.reorderMeal(customer, mealId));
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void chefMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Chef Operations ===");
            System.out.println("1. View All Chefs");
            System.out.println("2. View Chef Notifications");
            System.out.println("3. View Customer Order History");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nAll Chefs:");
                    for (Chef chef : chefManager.getAllChefs()) {
                        System.out.println(chef.getChefId() + ": " + chef.getChefName() +
                                " (Expertise: " + chef.expertise + ")");
                    }
                    break;
                case 2:
                    System.out.print("Enter chef ID: ");
                    int chefId = scanner.nextInt();
                    scanner.nextLine();
                    Chef chef = chefManager.getAllChefs().stream()
                            .filter(c -> c.getChefId() == chefId)
                            .findFirst()
                            .orElse(null);
                    if (chef != null) {
                        System.out.println("Notifications for Chef " + chef.getChefName() + ":");
                        chef.getNotifications().forEach(System.out::println);
                    } else {
                        System.out.println("Chef not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter chef ID: ");
                    int cId = scanner.nextInt();
                    System.out.print("Enter customer ID: ");
                    int custId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(chefManager.viewCustomerOrderHistory(cId, custId));
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void kitchenMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Kitchen Operations ===");
            System.out.println("1. Assign Task to Chef");
            System.out.println("2. View Low Stock Alerts");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter task type: ");
                    String taskType = scanner.nextLine();
                    kitchenManager.assignTask(taskType);
                    break;
                case 2:
                    System.out.println("\nLow Stock Alerts:");
                    inventoryManager.getRestockSuggestions().forEach(System.out::println);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void inventoryMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Inventory Management ===");
            System.out.println("1. Add Ingredient");
            System.out.println("2. Check Stock Level");
            System.out.println("3. Restock Ingredient");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ingredient name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter threshold (optional, press enter to skip): ");
                    String thresholdInput = scanner.nextLine();
                    if (thresholdInput.isEmpty()) {
                        inventoryManager.addIngredient(name, quantity);
                    } else {
                        int threshold = Integer.parseInt(thresholdInput);
                        inventoryManager.addIngredient(name, quantity, threshold);
                    }
                    System.out.println("Ingredient added.");
                    break;
                case 2:
                    System.out.print("Enter ingredient name: ");
                    String ingredient = scanner.nextLine();
                    System.out.println("Stock status: " +
                            inventoryManager.getStockStatus(ingredient));
                    break;
                case 3:
                    System.out.print("Enter ingredient name: ");
                    String item = scanner.nextLine();
                    System.out.print("Enter amount to add: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    inventoryManager.restockIngredient(item, amount);
                    System.out.println("Ingredient restocked.");
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void supplierMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Supplier Management ===");
            System.out.println("1. Add/Update Supplier Price");
            System.out.println("2. View Best Supplier for Ingredient");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ingredient name: ");
                    String ingredient = scanner.nextLine();
                    System.out.print("Enter supplier name: ");
                    String supplier = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    supplierManager.addOrUpdatePrice(ingredient, supplier, price);
                    System.out.println("Price updated.");
                    break;
                case 2:
                    System.out.print("Enter ingredient name: ");
                    String item = scanner.nextLine();
                    String bestSupplier = supplierManager.getBestSupplier(item);
                    if (bestSupplier != null) {
                        System.out.println("Best supplier for " + item + ": " + bestSupplier +
                                " at $" + supplierManager.getBestPrice(item));
                    } else {
                        System.out.println("No suppliers found for " + item);
                    }
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void financialMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Financial Reports ===");
            System.out.println("1. Generate Monthly Report");
            System.out.println("2. View Financial Summary");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter month (1-12): ");
                    int month = scanner.nextInt();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Path reportPath = admin.exportFinancialReportToPdf(month, year);
                        System.out.println("Report generated at: " + reportPath);
                    } catch (IOException e) {
                        System.out.println("Error generating report: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter month (1-12): ");
                    int m = scanner.nextInt();
                    System.out.print("Enter year: ");
                    int y = scanner.nextInt();
                    scanner.nextLine();
                    FinancialReportService.Report report = admin.reportService.generateReport(m, y);
                    System.out.println("\nFinancial Summary:");
                    System.out.println("Revenue: $" + report.getRevenue());
                    System.out.println("Expenses: $" + report.getExpenses());
                    System.out.println("Profit: $" + report.getNetProfit());
                    System.out.println("Total Orders: " + report.getTotalOrders());
                    System.out.println("Total Meals: " + report.getTotalMeals());
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }*/
    }
}