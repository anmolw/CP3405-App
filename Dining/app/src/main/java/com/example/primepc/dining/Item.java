package com.example.primepc.dining;

public class Item {

    private int id;
    private String name;
    private String price;

    Item(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
