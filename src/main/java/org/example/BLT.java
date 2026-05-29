package org.example;

public class BLT extends Sandwich{
    public BLT(String size, String bread, boolean toasted) {
        super(size, bread, toasted);

        addMeat("Bacon");
        addCheese("Cheddar");
        addTopping("Lettuce");
        addTopping("Tomatoes");
        addSauce("Ranch");
    }
}
