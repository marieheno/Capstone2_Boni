package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chip) {
        chips.add(chip);
    }

    public boolean hasItems() {

        return !sandwiches.isEmpty()
                || !drinks.isEmpty()
                || !chips.isEmpty();
    }

    public double getTotal() {
        double total = 0;

        for (Sandwich sandwich : sandwiches) {
            total += sandwich.getPrice();
        }

        for (Drink drink : drinks) {
            total += drink.getPrice();
        }

        for (Chips chip : chips) {
            total += chip.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {

        StringBuilder receipt = new StringBuilder();
        receipt.append("\n============= RECEIPT ===============\n");
        receipt.append(String.format("%-20s %-10s\n", "Product", "Price"));
        receipt.append("-------------------------------------------");

        for (Sandwich sandwich : sandwiches) {
            receipt.append(sandwich);
        }

        for (Drink drink : drinks) {
            receipt.append(drink);
        }

        for (Chips chip : chips) {
            receipt.append(chip);
        }

        receipt.append("\nTOTAL: $")
                .append(String.format("%.2f", getTotal()));
        receipt.append("\n==============================================\n");


        return receipt.toString();
    }
}
