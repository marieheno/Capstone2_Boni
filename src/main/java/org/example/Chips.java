package org.example;

public class Chips {

    private String type;

    public Chips(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return 1.50;
    }

    @Override
    public String toString() {

        return "\nChips" + "\nType: " + type + "\nPrice: $1.50";
    }
}
