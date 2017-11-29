package com.githup.dszentgy.beadando.model;

public class Newspaper extends Item {
    private final NewspaperCategory newspaperCategory;

    public Newspaper(String type, String name, int price, int quantity, NewspaperCategory newspaperCategory) {
        super(type, name, price, quantity);
        this.newspaperCategory = newspaperCategory;
    }

    public NewspaperCategory getNewspaperCategory() {
        return newspaperCategory;
    }
}
