package org.example;

public class PhillyCheeseSteak extends Sandwich{
    public PhillyCheeseSteak(String size, String bread, boolean toasted) {
        super(size, bread, toasted);

        addMeat("Steak");
        addCheese("American");
        addTopping("Peppers");
        addSauce("Mayo");
    }
}
