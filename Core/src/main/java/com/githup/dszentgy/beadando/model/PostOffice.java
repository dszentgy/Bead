package com.githup.dszentgy.beadando.model;

public class PostOffice extends Item{
    private final PostOfficeCategory postOfficeCategory;

    public PostOffice(String type, String name, int price, int quantity, PostOfficeCategory postOfficeCategory) {
        super(type, name, price, quantity);
        this.postOfficeCategory = postOfficeCategory;
    }

    public PostOfficeCategory getPostOfficeCategory() {
        return postOfficeCategory;
    }

}
