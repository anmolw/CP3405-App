package com.example.primepc.dining;


import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class PostOrder {

    static String json;

    @SuppressLint("UseSparseArrays")
    private
    Map<Integer, Integer> itemQuantity = new HashMap<>();

    void countItems() {
        for (Item item : DynamicFoodlistActivity.cart) {
            if (!itemQuantity.containsKey(item.getId())) {
                itemQuantity.put(item.getId(), 1);
            } else {
                itemQuantity.put(item.getId(), itemQuantity.get(item.getId()) + 1);
            }
        }
        toJson();
    }

    private void toJson() {
        JsonObject restaurant = new JsonObject();
        restaurant.addProperty("restaurant", RestaurantsActivity.chosenRestaurant);
        JsonArray items = new JsonArray();

        for (int k : itemQuantity.keySet()) {
            JsonObject temporaryItem = new JsonObject();
            temporaryItem.addProperty("item", k);
            temporaryItem.addProperty("quantity", itemQuantity.get(k));
            items.add(temporaryItem);
        }

        restaurant.add("items", items);

        Gson gson = new Gson();
        json = gson.toJson(restaurant);
        System.out.println(json + "i am from post order");
        HttpPost hp = new HttpPost();
        hp.execute();
    }
}
