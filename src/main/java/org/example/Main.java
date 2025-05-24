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
    static KitchenManager kitchenManager2 = new KitchenManager(inventoryManager,supplierManager);
    static FinancialReportService reportService = new FinancialReportService(customerManager);
    static SystemAdministrator admin = new SystemAdministrator(reportService);
    static SystemAdministrator admin2=new SystemAdministrator(customerManager);
    static CustomMealService customMealService = new CustomMealService();
    static MealDeliveryReminderSystem reminderSystem = new MealDeliveryReminderSystem();*/

    public static void main(String[] args) throws IOException {
    /* Data();

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
            System.out.println("2. Set allergies");
            System.out.println("3. View past orders");
            System.out.println("4. Reorder a meal");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Clear Cart");
            System.out.println("8. Create custom meal request");
            System.out.println("9. Schedule a meal delivery");
            System.out.println("10. Get meal reminder");
            System.out.println("11. View invoice for an order");
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
                    System.out.print("Enter allergies: ");
                    String alerg = scanner.nextLine();
                    customer.setAllergies(alerg);
                    break;
                case 3:
                    System.out.println(customer.getFormattedOrderHistory());
                    break;
                case 4:
                    boolean keepOrdering = true;
                    while (keepOrdering) {
                        System.out.print("Enter meal ID to reorder: ");
                        int mealId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(customerManager.reorderMeal(customer, mealId));
                        System.out.print("Would you like to order another meal? (Y/N): ");
                        String choice = scanner.nextLine().trim().toLowerCase();
                        if (!choice.equals("y") && !choice.equals("yes")) {
                            keepOrdering = false;
                        }
                    }
                    break;
                case 5:
                    ShoppingCart cart = customer.getCart();
                    if (cart.getItems().isEmpty()) {
                        System.out.println("Your cart is empty");
                        break;
                    }
                    System.out.println("\n=== Your Cart ===");
                    int itemNum = 1;
                    for (Meal m: cart.getItems()){
                        System.out.println("Item id : " + itemNum++ +" Item name : " + m.getName()+"Item price : "+m.getPrice());
                    }
                    System.out.printf("TOTAL: $%.2f%n", cart.getTotal());
                    break;
                case 6:
                    if (customer.getCart().isEmpty()) {
                        System.out.println("Cannot checkout - cart is empty");
                        return;
                    }
                    Order newOrder = new Order(
                            generateOrderId(),
                            LocalDate.now(),
                            new ArrayList<>(customer.getCart().getItems())
                    );
                    customer.addOrder(newOrder);
                    String invoice = newOrder.generateInvoice(0.10);
                    System.out.println(invoice);
                    customer.getCart().clear();
                    System.out.println("Checkout complete! Your order is being processed.");
                        break;
                case 7:
                    customer.getCart().clear();
                    System.out.println("Cart cleared");
                    break;
                case 8:
                    System.out.print("Enter ingredients (comma-separated): ");
                    String[] parts = scanner.nextLine().split(",");
                    List<String> ingredients = Arrays.stream(parts).map(String::trim).toList();
                    if (customMealService.validateIngredients(ingredients)) {
                        System.out.println(customMealService.createCustomMealRequest(customer.getCustomerId(), ingredients));
                    } else {
                        System.out.println("Invalid ingredient combination.");
                    }
                    break;
                case 9:
                    System.out.print("Enter delivery date-time (YYYY-MM-DD HH:MM AM/PM): ");
                    String timeStr = scanner.nextLine();
                    reminderSystem.scheduleDelivery(timeStr, false);
                    break;
                case 10:
                    System.out.print("Enter current time (YYYY-MM-DD HH:MM AM/PM): ");
                    String now = scanner.nextLine();
                    reminderSystem.setCurrentTime(now);
                    List<String> reminders = reminderSystem.sendReminders(1);
                    reminders.forEach(System.out::println);
                    break;
                case 11:
                    System.out.print("Enter Order ID to view invoice: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();
                    boolean found = false;
                    for (Order order : customer.getOrderHistory()) {
                        if (orderId == order.orderId) {
                            String invoie = order.generateInvoice(0.10);
                            System.out.println(invoie);
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
                    System.out.println(" ------ Choose your way ------ ");
                    System.out.println("1. View all customer order history");
                    System.out.println("2. View specific customer");
                    int op=scanner.nextInt();
                    switch (op){
                        case 1:
                            System.out.println("=== Order History ===");
                            List<Customer> c= customerManager.getAllCustomers();
                            for(Customer c1: c){
                                System.out.println("Orders for Customer :" + customerManager.getCustomerById(c1.getCustomerId()).getName()+"\n"+ chefManager.viewCustomerOrderHistory(c1.getCustomerId()));
                            }
                            break;
                        case 2:
                            System.out.print("Enter customer ID: ");
                            int custId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("=== Order History ===");
                            System.out.println("Orders for Customer :" + customerManager.getCustomerById(custId).getName() +"\n"+ chefManager.viewCustomerOrderHistory(custId));
                            break;
                    }

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
                    System.out.print("Enter task type : ");
                    String taskType = scanner.nextLine();
                    kitchenManager.assignTask(taskType);
                    break;
                case 2:
                    System.out.print("Enter ingredient: ");
                    String ing = scanner.nextLine();
                    System.out.println(inventoryManager.getStockStatus(ing));
                    break;
                case 3:
                    List<String> suggestions = inventoryManager.getRestockSuggestions();
                    suggestions.forEach(kitchenManager2::addNotification);
                    if (kitchenManager2.getNotifications().isEmpty()) {
                        System.out.println("All ingredients are above threshold");
                    } else {
                        System.out.println("\n=== Restock Suggestions ===");
                        kitchenManager2.getNotifications().forEach(System.out::println);
                    }
                    break;
                case 4:
                    System.out.print("Enter ingredient name: ");
                    String ingName = scanner.nextLine();
                    kitchenManager2.fetchPricesForIngredient(ingName);
                    break;
                case 5:
                    kitchenManager2.autoOrderLowStockIngredients();
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
                case 2:
                    System.out.println(" ------ Choose your way ------ ");
                    System.out.println("1. View all customer order history");
                    System.out.println("2. View specific customer");
                    int op=scanner.nextInt();
                    switch (op){
                        case 1:
                            List<Customer> c= customerManager.getAllCustomers();
                            System.out.println("=== Order History ===");
                            for(Customer c1: c){
                                System.out.println("Orders for Customer :" + customerManager.getCustomerById(c1.getCustomerId()).getName() +"\n" + admin2.getCustomerOrderHistory(c1.getCustomerId()));
                            }
                            break;
                        case 2:
                            System.out.print("Enter customer ID: ");
                            int custId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("=== Order History ===");
                            System.out.println("Orders for Customer :" + customerManager.getCustomerById(custId).getName() +"\n" + admin2.getCustomerOrderHistory(custId));
                            break;
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void Data() {
        System.out.println("hi data");
        Customer c1 = new Customer(1, "Alice", "pass123", "Vegan", "Milk");
    Customer c2 = new Customer(2, "Bob", "pass456", "Keto", "Shellfish");
    Customer c3 = new Customer(3, "Charlie", "pass789", "Vegetarian", "Peanuts");
    Customer c4 = new Customer(4, "Diana", "pass101", "Gluten-Free", "Soy");

    customerManager.addCustomer(c1);
    customerManager.addCustomer(c2);
    customerManager.addCustomer(c3);
    customerManager.addCustomer(c4);

    Chef chef1 = new Chef(10, "Gordon", "chefpass1");
    chef1.addExpertise("Italian");
    chef1.addExpertise("French");
    Chef chef2 = new Chef(11, "Julia", "chefpass2");
    chef2.addExpertise("Asian");
    chef2.addExpertise("Vegetarian");
    Chef chef3 = new Chef(12, "Jamie", "chefpass3");
    chef3.addExpertise("Vegan");
    chef3.addExpertise("Gluten-Free");

    chefManager.addChef(chef1);
    chefManager.addChef(chef2);
    chefManager.addChef(chef3);

    inventoryManager.addIngredient("Tomatoes", 20);
    inventoryManager.addIngredient("Rice", 100);
    inventoryManager.addIngredient("Pasta", 50);
    inventoryManager.addIngredient("Chicken", 30);
    inventoryManager.addIngredient("Beef", 25);
    inventoryManager.addIngredient("Tofu", 40);
    inventoryManager.addIngredient("Bell Peppers", 35);
    inventoryManager.addIngredient("Onions", 60);
    inventoryManager.addIngredient("Garlic", 80);
    inventoryManager.addIngredient("Olive Oil", 15);
    inventoryManager.addIngredient("Soy Sauce", 1);
    inventoryManager.addIngredient("Gluten-Free Flour", 4);

    supplierManager.addOrUpdatePrice("Tomatoes", "FreshFarm", 1.5);
    supplierManager.addOrUpdatePrice("Tomatoes", "OrganicSupply", 1.2);
    supplierManager.addOrUpdatePrice("Rice", "GrainCo", 0.8);
    supplierManager.addOrUpdatePrice("Rice", "PremiumGrains", 1.0);
    supplierManager.addOrUpdatePrice("Pasta", "PastaMasters", 1.3);
    supplierManager.addOrUpdatePrice("Chicken", "MeatDirect", 3.5);
    supplierManager.addOrUpdatePrice("Beef", "MeatDirect", 5.0);
    supplierManager.addOrUpdatePrice("Tofu", "VeganSupplies", 2.0);
    supplierManager.addOrUpdatePrice("Bell Peppers", "VeggieFarm", 1.0);
    supplierManager.addOrUpdatePrice("Gluten-Free Flour", "SpecialtyFoods", 3.5);

    Meal m1 = new Meal(101, "Vegan Pasta", 12.0, true);
    Meal m2 = new Meal(102, "Chicken Alfredo", 15.0, false);
    Meal m3 = new Meal(103, "Beef Stir Fry", 14.5, false);
    Meal m4 = new Meal(104, "Tofu Curry", 13.0, true);
    Meal m5 = new Meal(105, "Gluten-Free Pizza", 16.0, true);
    Meal m6 = new Meal(106, "Vegetable Risotto", 11.5, true);
    Meal m7 = new Meal(107, "Keto Burger", 14.0, false);
    Meal m8 = new Meal(108, "Pad Thai", 13.5, false);

    c1.addOrder(new Order(1, LocalDate.now().minusDays(1), List.of(m1, m4)));
    c1.addOrder(new Order(2, LocalDate.now().minusDays(3), List.of(m6)));

    c2.addOrder(new Order(3, LocalDate.now().minusDays(2), List.of(m2, m7)));
    c2.addOrder(new Order(4, LocalDate.now().minusDays(5), List.of(m3)));

    c3.addOrder(new Order(5, LocalDate.now().minusDays(1), List.of(m4, m6)));
    c3.addOrder(new Order(6, LocalDate.now().minusDays(4), List.of(m8)));

    c4.addOrder(new Order(7, LocalDate.now().minusDays(2), List.of(m5)));
    c4.addOrder(new Order(8, LocalDate.now().minusDays(6), List.of(m1, m5, m6)));
    }
    private static int generateOrderId() {
        return Math.abs((int) System.currentTimeMillis() % 1000000);
    }

     */
}
}
