package com.githup.dszentgy.beadando.model;

public class Item {

    private final String type;
    private final String name;
    private final int price;
    private final int quantity;

    public Item(String type, String name, int price, int quantity) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getEntityPrice() {
        return (double) getPrice() / getQuantity();
    }

}


