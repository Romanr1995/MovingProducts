package ru.consulting.model;

import java.math.BigDecimal;

public class Product {

    private String nameProduct;
    private double weight;
    private BigDecimal price;

    public Product(String name, double weight, BigDecimal price) {
        this.nameProduct = name;
        this.weight = weight;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }


    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-8s - weight = %5.2f, price = %5.2f\n", nameProduct, weight, price);
    }

}
