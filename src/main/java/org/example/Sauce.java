package org.example;

import java.util.Arrays;
import java.util.List;

public class Sauce extends MenuItem{
    public Sauce(String name) {
        super(name);
    }

    @Override
    public List<String> getItems() {

        return Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands",
                "Vinaigrette");
    }
}
