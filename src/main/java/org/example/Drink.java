package org.example;

public class Drink {

    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public double getPrice() {
        switch (size.toLowerCase()) {

            case "small":
                return 2.00;
            case "medium":
                return 2.50;
            default:
                return 3.00;
        }
    }

    @Override
    public String toString() {

        return "\nDrink" + "\nSize: " + size + "\nFlavor: " + flavor +
                "\nPrice: $" + String.format("%.2f", getPrice());

    }
}
