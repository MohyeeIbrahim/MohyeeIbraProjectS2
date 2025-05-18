package org.example;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {
   /* static Scanner scanner = new Scanner(System.in);
    static CustomerManager customerManager = new CustomerManager();
    static ChefManager chefManager = new ChefManager(customerManager);
    static InventoryManager inventoryManager = new InventoryManager();
    static SupplierManager supplierManager = new SupplierManager();
    static KitchenManager kitchenManager = new KitchenManager(chefManager);
    static FinancialReportService reportService = new FinancialReportService(customerManager);
    static SystemAdministrator admin = new SystemAdministrator(reportService);
    static SystemAdministrator admin2=new SystemAdministrator(customerManager);
    static CustomMealService customMealService = new CustomMealService();
    static MealDeliveryReminderSystem reminderSystem = new MealDeliveryReminderSystem();
*/
    public static void main(String[] args) throws IOException {
        /*Data();

        while (true) {
            System.out.println("\n--- Cook System ---");
            System.out.println("1. Customer");
            System.out.println("2. Chef");
            System.out.println("3. Kitchen Manager");
            System.out.println("4. System Administrator");
            System.out.println("0. Exit");
            System.out.print("Choose role: ");
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
                    kitchenManagerMenu();
                    break;
                case 4:
                    systemAdminMenu();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ------------------ CUSTOMER -------------------
    public static void customerMenu() {
        Customer customer = customerManager.getCustomerById(1);

        while (true) {
            System.out.println("\n[Customer Menu]");
            System.out.println("1. Set dietary preferences");
            System.out.println("2. View past orders");
            System.out.println("3. Reorder a meal");
            System.out.println("4. Create custom meal request");
            System.out.println("5. Schedule a meal delivery");
            System.out.println("6. Get meal reminder");
            System.out.println("7. View invoice for an order");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter dietary preference: ");
                    String diet = scanner.nextLine();
                    customer.setDietaryPreference(diet);
                    break;
                case 2:
                    System.out.println(customer.getFormattedOrderHistory());
                    break;
                case 3:
                    System.out.print("Enter meal ID to reorder: ");
                    int mealId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(customerManager.reorderMeal(customer, mealId));
                    break;
                case 4:
                    System.out.print("Enter ingredients (comma-separated): ");
                    String[] parts = scanner.nextLine().split(",");
                    List<String> ingredients = Arrays.stream(parts).map(String::trim).toList();
                    if (customMealService.validateIngredients(ingredients)) {
                        System.out.println(customMealService.createCustomMealRequest(customer.getCustomerId(), ingredients));
                    } else {
                        System.out.println("Invalid ingredient combination.");
                    }
                    break;
                case 5:
                    System.out.print("Enter delivery date-time (YYYY-MM-DD HH:MM AM/PM): ");
                    String timeStr = scanner.nextLine();
                    reminderSystem.scheduleDelivery(timeStr, false);
                    break;
                case 6:
                    System.out.print("Enter current time (YYYY-MM-DD HH:MM AM/PM): ");
                    String now = scanner.nextLine();
                    reminderSystem.setCurrentTime(now);
                    List<String> reminders = reminderSystem.sendReminders(1);
                    reminders.forEach(System.out::println);
                    break;
                case 7 :
                    System.out.print("Enter Order ID to view invoice: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();
                    boolean found = false;
                    for (Order order : customer.getOrderHistory()) {
                        if (orderId == order.orderId) {
                            String invoice = order.generateInvoice(0.10); // 10% tax
                            System.out.println(invoice);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Order not found.");
                        break;
                    }
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ------------------ CHEF -------------------
    public static void chefMenu() {
        Chef chef = chefManager.getAllChefs().get(0);

        while (true) {
            System.out.println("\n[Chef Menu]");
            System.out.println("1. View customer allergies-preferences");
            System.out.println("2. View customer order history");
            System.out.println("3. View assigned tasks");
            System.out.println("4. View substitution alerts");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = customerManager.getCustomerById(customerId);
                    System.out.println("=== Dietary Info ===");
                    System.out.println("Preferences: " + customer.getDietaryPreference());
                    System.out.println("Allergies: " + customer.getAllergies());
                    break;

                case 2:
                    System.out.print("Enter customer ID: ");
                    int custId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("=== Order History ===");
                    System.out.println(chefManager.viewCustomerOrderHistory(chef.getChefId(), custId));
                    break;

                case 3:
                    chef.getNotifications().forEach(System.out::println);
                    break;
                case 4:
                    chef.getSubstitutionAlerts().forEach(System.out::println);
                    break;


                    case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ------------------ KITCHEN MANAGER -------------------
    public static void kitchenManagerMenu() {
        while (true) {
            System.out.println("\n[Kitchen Manager Menu]");
            System.out.println("1. Assign task to chef");
            System.out.println("2. Check inventory stock");
            System.out.println("3. Restock suggestions");
            System.out.println("4. Show real-time ingredient prices");
            System.out.println("5. Auto order low stock");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter task type (e.g., Italian): ");
                    String taskType = scanner.nextLine();
                    kitchenManager.assignTask(taskType);
                    break;
                case 2:
                    System.out.print("Enter ingredient: ");
                    String ing = scanner.nextLine();
                    System.out.println(inventoryManager.getStockStatus(ing));
                    break;
                case 3:
                    kitchenManager = new KitchenManager(inventoryManager);
                    kitchenManager.getNotifications().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter ingredient name: ");
                    String ingName = scanner.nextLine();
                    kitchenManager.fetchPricesForIngredient(ingName);
                    break;
                case 5:
                    kitchenManager = new KitchenManager(inventoryManager);
                    kitchenManager = new KitchenManager(supplierManager);
                    kitchenManager.autoOrderLowStockIngredients();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ------------------ SYSTEM ADMIN -------------------
    public static void systemAdminMenu() throws IOException {
        while (true) {
            System.out.println("\n[System Admin Menu]");
            System.out.println("1. Export financial report to PDF");
            System.out.println("2. View customer order history");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter month [1-12]: ");
                    int month = scanner.nextInt();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    admin.exportFinancialReportToPdf(month, year);
                    System.out.println("Report exported.");
                    break;
                case 3:
                    System.out.print("Enter customer ID: ");
                    int custId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(admin.getCustomerOrderHistory(custId));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void Data() {
        Customer c1 = new Customer(1, "Alice", "pass", "Vegan", "Milk");
        customerManager.addCustomer(c1);

        Chef chef = new Chef(10, "Bob", "pass");
        chef.addExpertise("Italian");
        chefManager.addChef(chef);

        inventoryManager.addIngredient("Tomatoes", 2);
        inventoryManager.addIngredient("Rice", 10);
        supplierManager.addOrUpdatePrice("Tomatoes", "FreshFarm", 1.5);
        supplierManager.addOrUpdatePrice("Tomatoes", "OrganicSupply", 1.2);

        Meal m = new Meal(101, "Vegan Pasta", 12.0, true);
        c1.addOrder(new Order(1, LocalDate.now().minusDays(1), List.of(m)));*/
    }
}
