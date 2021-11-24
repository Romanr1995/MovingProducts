package ru.consulting;

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

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-8s - weight = %5.2f, price = %5.2f\n",nameProduct,weight,price);
    }
}
