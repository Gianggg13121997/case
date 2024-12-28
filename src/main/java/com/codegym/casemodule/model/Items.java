package com.codegym.casemodule.model;

public class Items {
    private Product product;

    private int quantity;
    private int price;
    private String description;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Items(Product product, int quantity) {
        super();
        this.product = product;
        this.quantity = quantity;
    }

    public Items() {
        super();
    }

}