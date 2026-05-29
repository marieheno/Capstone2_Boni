package org.example;

import java.util.Arrays;
import java.util.List;

public class DrinkSize extends MenuItem{
    public DrinkSize(String name, String flavor) {
        super(name);
    }

    @Override
    public List<String> getItems(){
        return Arrays.asList("Small", "Medium", "Large");
    }
}
