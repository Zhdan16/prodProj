package com.example.springzhdan.pojo;

public class ProductsList {
    String category;
    String name;
    Integer price;
    boolean available;

    public ProductsList(String category, String name, Integer price, boolean available) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
