package org.example;

import java.util.Arrays;
import java.util.List;

public class Topping extends MenuItem{
    public Topping(String name) {
        super(name);
    }

    @Override
    public List<String> getItems() {

        return Arrays.asList("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapenos",
                "Cucumbers", "Pickles", "Guacamole", "Mushrooms");
    }
}
