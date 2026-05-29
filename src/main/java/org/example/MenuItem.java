package org.example;

import java.util.List;

public abstract class MenuItem {
    private String name;

    public MenuItem(String name) {
        this.name = name;
    }

    public abstract List<String> getItems();

    public void showItems() {
        System.out.println("\n" + name + ":");

        for (int i = 0; i < getItems().size(); i++) {
            System.out.println((i + 1) + ") " + getItems().get(i));
        }
    }

    public String chooseItem(int choice) {
        return getItems().get(choice - 1);
    }
}
