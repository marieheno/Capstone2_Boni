package org.example;

import java.util.Arrays;
import java.util.List;

public class ChipsMenu extends MenuItem{
    public ChipsMenu(String name) {
        super(name);
    }

    @Override
    public List<String> getItems(){
        return Arrays.asList("Classic", "BBQ", "Cheddar", "Salt & Vinegar");
    }
}
