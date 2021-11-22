package ru.consulting;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private String nameWarehouse;
    private List<Product> productInWarehouse = new ArrayList<>();

    public Warehouse() {

    }

    public Warehouse(String nameWarehouse, Product product) {
        this.nameWarehouse = nameWarehouse;
        this.productInWarehouse.add(product);
    }

    public String getNameWarehouse() {
        return nameWarehouse;
    }

    public void addProductInWarehouse(Product product) {
        this.productInWarehouse.add(product);
    }

    public BigDecimal getAveragePriceProducts() {

        BigDecimal averagePrice = BigDecimal.ZERO;
        for (Product product : productInWarehouse) {
            BigDecimal productPrice = product.getPrice();
            BigDecimal add = averagePrice.add(productPrice);
            averagePrice = add;
        }
        BigDecimal countProducts = BigDecimal.valueOf(productInWarehouse.size());
        return averagePrice.divide(countProducts, 2, RoundingMode.HALF_DOWN);
    }

    @Override
    public String toString() {
        String printProducts = "";
        for (Product product : productInWarehouse) {
            printProducts+=product.toString();
        }
        return nameWarehouse + " - averagePrice = " + getAveragePriceProducts() + "\n" +
                printProducts;
    }
}
