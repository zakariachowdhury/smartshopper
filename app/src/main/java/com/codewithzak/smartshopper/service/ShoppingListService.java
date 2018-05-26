package com.codewithzak.smartshopper.service;

import com.codewithzak.smartshopper.model.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListService {
    private List<ShoppingItem> allItems;

    public ShoppingListService() {
        allItems = new ArrayList<>();
        allItems.add(new ShoppingItem("Bread"));
        allItems.add(new ShoppingItem("Milk", 1.5));
        allItems.add(new ShoppingItem("Egg", 6));
        allItems.add(new ShoppingItem("Banana", true));
        allItems.add(new ShoppingItem("Cookie", true));
    }

    public List<ShoppingItem> getItems(boolean archived) {
        List<ShoppingItem> currentItems = new ArrayList();
        for (ShoppingItem item: allItems) {
            if (item.isArchived() == archived) {
                currentItems.add(item);
            }
        }
        return currentItems;
    }
}
