package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private String size;
    private String bread;
    private boolean toasted;

    private List<String> meats;
    private List<String> extraMeats;
    private List<String> cheeses;
    private List<String> extraCheeses;
    private List<String> toppings;
    private List<String> sauces;

    public Sandwich(String size, String bread, boolean toasted) {

        this.size = size;
        this.bread = bread;
        this.toasted = toasted;

        meats = new ArrayList<>();
        extraMeats = new ArrayList<>();
        cheeses = new ArrayList<>();
        extraCheeses = new ArrayList<>();
        toppings = new ArrayList<>();
        sauces = new ArrayList<>();
    }

    public void addMeat(String meat) {
        meats.add(meat);
    }

    public void addExtraMeat(String meat) {
        extraMeats.add(meat);
    }

    public void addCheese(String cheese) {
        cheeses.add(cheese);
    }

    public void addExtraCheese(String cheese) {
        extraCheeses.add(cheese);
    }

    public void addTopping(String topping) {
        toppings.add(topping);
    }

    public void addSauce(String sauce) {
        sauces.add(sauce);
    }

    public double getPrice() {
        double price;

        switch (size) {
            case "4":
                price = 5.50;
                break;
            case "8":
                price = 7.00;
                break;
            default:
                price = 8.50;
        }

        switch (size) {
            case "4":
                price += meats.size() * 1.00;
                price += extraMeats.size() * 0.50;
                price += cheeses.size() * 0.75;
                price += extraCheeses.size() * 0.30;
                break;
            case "8":
                price += meats.size() * 2.00;
                price += extraMeats.size() * 1.00;
                price += cheeses.size() * 1.50;
                price += extraCheeses.size() * 0.60;
                break;
            default:
                price += meats.size() * 3.00;
                price += extraMeats.size() * 1.50;
                price += cheeses.size() * 2.25;
                price += extraCheeses.size() * 0.90;
        }
        return price;
    }

    @Override
    public String toString() {

        String sandwichInfo = "";
        sandwichInfo += "Sandwich\n";

        sandwichInfo += String.format("%-20s $%.2f\n",
                size + "\" " + bread + " Bread",
                getPrice());
        sandwichInfo += "\n";

        for (String meat : meats) {
            sandwichInfo += String.format("%-20s $2.00\n", meat);
        }

        for (String meat : extraMeats) {
            sandwichInfo += String.format("%-20s $1.00\n", "Extra " + meat);
        }

        for (String cheese : cheeses) {
            sandwichInfo += String.format("%-20s $1.50\n", cheese);
        }

        for (String cheese : extraCheeses) {
            sandwichInfo += String.format("%-20s $0.60\n", "Extra " + cheese);
        }

        for (String topping : toppings) {
            sandwichInfo += topping + "\n";
        }

        for (String sauce : sauces) {
            sandwichInfo += sauce + "\n";
        }

        sandwichInfo += String.format("\n%-20s $%.2f\n",
                "Sandwich Total:",
                getPrice());
        sandwichInfo += "--------------------------------------\n";

        return sandwichInfo;

    }
}
