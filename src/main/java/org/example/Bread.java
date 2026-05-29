package org.example;

import java.util.Arrays;
import java.util.List;

public class Bread extends MenuItem{
    public Bread(String name) {
        super(name);
    }

    @Override
    public List<String> getItems() {

        return Arrays.asList("White", "Wheat", "Rye", "Wrap");
    }
}
