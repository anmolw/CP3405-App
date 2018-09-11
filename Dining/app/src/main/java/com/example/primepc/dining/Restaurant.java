package com.example.primepc.dining;

import java.util.ArrayList;

public class Restaurant {

    private int id;
    private String name;
    private String thumbnail;
    private ArrayList<Item> items = new ArrayList<>();

    Restaurant(int id, String name, String thumbnail) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    void addItem(Item item) {
        items.add(item);
    }
}
