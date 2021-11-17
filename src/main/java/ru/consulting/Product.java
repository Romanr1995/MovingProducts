package ru.consulting;

import java.math.BigDecimal;

public class Product {

    private String nameProduct;
    private BigDecimal weight;
    private BigDecimal price;

    public Product(String name, BigDecimal weight, BigDecimal price) {
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
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
        return "nameProduct: " + nameProduct + ", weight: " + weight + ", price: " + price;
    }
}
