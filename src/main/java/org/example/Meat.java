package org.example;

import java.util.Arrays;
import java.util.List;

public class Meat extends MenuItem{
    public Meat(String name) {
        super(name);
    }

    @Override
    public List<String> getItems() {

        return Arrays.asList("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon");
    }
}
