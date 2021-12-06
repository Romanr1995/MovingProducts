package ru.consulting.model;

import java.math.BigDecimal;
import java.util.Objects;

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
        return String.format("%-8s - weight = %5.2f, price = %5.2f\n",nameProduct,weight,price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.weight, weight) == 0 && Objects.equals(nameProduct, product.nameProduct) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameProduct, weight, price);
    }
}
