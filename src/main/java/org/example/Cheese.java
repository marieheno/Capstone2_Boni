package org.example;

import java.util.Arrays;
import java.util.List;

public class Cheese extends MenuItem{
    public Cheese(String name) {
        super(name);
    }

    @Override
    public List<String> getItems() {

        return Arrays.asList("American", "Provolone", "Cheddar", "Swiss");
    }
}
