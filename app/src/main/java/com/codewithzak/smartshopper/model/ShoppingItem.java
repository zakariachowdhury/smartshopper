package com.codewithzak.smartshopper.model;

public final class ShoppingItem {

    private String name;

    private double count;

    private boolean checked;

    public ShoppingItem(final String name) {
        this.name = name;
        this.count = 1;
    }

    public ShoppingItem(final String name, final double count) {
        this.name = name;
        this.count = count;
    }

    public ShoppingItem(final String name, final boolean checked) {
        this(name);
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public double getCount() {
        return count;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
