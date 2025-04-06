package com.example.ttt.product;

public class ProductDetails {
    public final String name;
    public final String imageUrl;
    public final String description;
    public final double price;

    public ProductDetails(String name, String imageUrl, String description, double price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nImage: %s\nDescription: %s\nPrice: $%.2f", name, imageUrl, description, price);
    }
}
