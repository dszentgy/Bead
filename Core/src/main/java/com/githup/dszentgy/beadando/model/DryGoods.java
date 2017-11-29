package com.githup.dszentgy.beadando.model;

public class DryGoods extends Item{

    private final DryGoodsCategory category;

        public DryGoods(DryGoodsCategory category, String type, String name, int price, int quantity) {
            super(type, name, price,quantity);
            this.category = category;
        }

    public DryGoodsCategory getCategory() {
        return category;
    }




}
