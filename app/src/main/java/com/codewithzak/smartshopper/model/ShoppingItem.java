package com.codewithzak.smartshopper.model;

public final class ShoppingItem {

    private String name;

    private double count;

    private boolean archived;

    public ShoppingItem(final String name) {
        this.name = name;
        this.count = 1;
    }

    public ShoppingItem(final String name, final double count) {
        this.name = name;
        this.count = count;
    }

    public ShoppingItem(final String name, final boolean archived) {
        this(name);
        this.archived = archived;
    }

    public String getName() {
        return name;
    }

    public double getCount() {
        return count;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
