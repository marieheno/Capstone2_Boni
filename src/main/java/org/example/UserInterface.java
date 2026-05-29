package org.example;

import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private Order order;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void display() {
        boolean running = true;

        while (running) {

            System.out.println("\n=== DELI-cious ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    startOrder();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void startOrder() {
        order = new Order();

        boolean ordering = true;

        while (ordering) {

            System.out.println("\n=== ORDER MENU ===");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add BLT");
            System.out.println("3) Add Philly Cheese Steak");
            System.out.println("4) Add Drink");
            System.out.println("5) Add Chips");
            System.out.println("6) Checkout");
            System.out.println("0) Cancel Order");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addCustomSandwich();
                    break;
                case 2:
                    order.addSandwich(new BLT("8", "White", true));
                    System.out.println("BLT Added!");
                    break;
                case 3:
                    order.addSandwich(new PhillyCheeseSteak("4", "White", true));
                    System.out.println("Philly Cheese Steak Added!");
                    break;
                case 4:
                    addDrink();
                    break;
                case 5:
                    addChips();
                    break;
                case 6:
                    if (!order.hasItems()) {
                        System.out.println("Order is empty.");
                        break;
                    }
                    System.out.println(order);
                    ReceiptFileManager.saveReceipt(order);
                    ordering = false;
                    break;
                case 0:
                    ordering = false;
                    System.out.println("Order Cancelled.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addCustomSandwich() {
        Bread bread = new Bread("Bread");

        bread.showItems();
        int breadChoice = scanner.nextInt();
        scanner.nextLine();

        String selectedBread = bread.chooseItem(breadChoice);



        System.out.println("\nChoose Size:");
        System.out.println("1) 4 inch");
        System.out.println("2) 8 inch");
        System.out.println("3) 12 inch");

        int sizeChoice = scanner.nextInt();
        scanner.nextLine();

        String size;

        switch (sizeChoice) {
            case 1:
                size = "4";
                break;
            case 2:
                size = "8";
                break;
            default:
                size = "12";
        }
        System.out.println("\nToasted? (yes/no)");

        String toastedInput = scanner.nextLine();

        boolean toasted =
                toastedInput.equalsIgnoreCase("yes");
        Sandwich sandwich =
                new Sandwich(size, selectedBread, toasted);


        Meat meat = new Meat("Meat");

        meat.showItems();
        int meatChoice = scanner.nextInt();
        scanner.nextLine();

        String selectedMeat =
                meat.chooseItem(meatChoice);

        sandwich.addMeat(selectedMeat);
        System.out.println("Extra Meat? (yes/no)");

        String extraMeat =
                scanner.nextLine();

        if (extraMeat.equalsIgnoreCase("yes")) {
            sandwich.addExtraMeat(selectedMeat);
        }

        Cheese cheese = new Cheese("Cheese");
        cheese.showItems();

        int cheeseChoice = scanner.nextInt();
        scanner.nextLine();

        String selectedCheese = cheese.chooseItem(cheeseChoice);

        sandwich.addCheese(selectedCheese);
        System.out.println("Extra Cheese? (yes/no)");

        String extraCheese =
                scanner.nextLine();

        if (extraCheese.equalsIgnoreCase("yes")) {
            sandwich.addExtraCheese(selectedCheese);
        }

        System.out.println("\nDo you want:");
        System.out.println("1) One Topping");
        System.out.println("2) Multiple Toppings");
        System.out.println("0) No Toppings");

        int toppingOption = scanner.nextInt();
        scanner.nextLine();

        Topping topping = new Topping("Toppings");

        switch (toppingOption) {

            case 1:
                topping.showItems();
                int toppingChoice = scanner.nextInt();
                scanner.nextLine();

                sandwich.addTopping(topping.chooseItem(toppingChoice));
                break;
            case 2:
                boolean addingToppings = true;

                while (addingToppings) {

                    topping.showItems();
                    System.out.println("0) Done");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 0) {
                        addingToppings = false;
                    }
                    else {
                        sandwich.addTopping(topping.chooseItem(choice));

                        System.out.println("Topping Added!");
                    }
                }
                break;
        }

        System.out.println("\nDo you want:");
        System.out.println("1) One Sauce");
        System.out.println("2) Multiple Sauces");
        System.out.println("0) No Sauce");

        int sauceOption = scanner.nextInt();
        scanner.nextLine();

        Sauce sauce = new Sauce("Sauces");

        switch (sauceOption) {
            case 1:
                sauce.showItems();
                int sauceChoice = scanner.nextInt();
                scanner.nextLine();
                sandwich.addSauce(sauce.chooseItem(sauceChoice));
                break;
            case 2:
                boolean addingSauces = true;

                while (addingSauces) {
                    sauce.showItems();

                    System.out.println("0) Done");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 0) {
                        addingSauces = false;
                    }
                    else {
                        sandwich.addSauce(sauce.chooseItem(choice));
                        System.out.println("Sauce Added!");
                    }
                }
                break;
        }
        order.addSandwich(sandwich);
        System.out.println("Sandwich Added!");
    }

    private void addDrink() {

        DrinkSize size = new DrinkSize("Drink Sizes", "Coke");

        size.showItems();
        int sizeChoice = scanner.nextInt();
        scanner.nextLine();

        String selectedSize = size.chooseItem(sizeChoice);
        System.out.println("Enter Flavor:");
        String flavor = scanner.nextLine();

        Drink drink = new Drink(selectedSize, flavor);

        order.addDrink(drink);
        System.out.println("Drink Added!");
    }

    private void addChips() {
        ChipsMenu menu = new ChipsMenu("Chips");

        menu.showItems();
        int choice = scanner.nextInt();
        scanner.nextLine();

        String chipType = menu.chooseItem(choice);

        Chips chips = new Chips(chipType);
        order.addChips(chips);
        System.out.println("Chips Added!");
    }
}
