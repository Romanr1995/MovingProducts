package ru.consulting;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private String nameWarehouse;
    private List<Product> productInWarehouse;

    public Warehouse(String nameWarehouse) {
        this.nameWarehouse = nameWarehouse;
        productInWarehouse = new ArrayList<>();
    }

    public String getNameWarehouse() {
        return nameWarehouse;
    }

    public void addProductInWarehouse(Product product) {
        this.productInWarehouse.add(product);
    }


    public List<Product> getProductsAboveAveragePrice() {

        List<Product> productAboveAverage = new ArrayList<>();
        BigDecimal averagePriceProducts = getAveragePriceProducts();

        for (Product product : productInWarehouse) {
            BigDecimal productPrice = product.getPrice();
            if (productPrice.compareTo(averagePriceProducts) > 0) {
                productAboveAverage.add(product);
            }
        }
        return productAboveAverage;
    }

    public BigDecimal getAveragePriceProducts() {

        BigDecimal averageProducts = BigDecimal.ZERO;
        for (Product product : productInWarehouse) {
            averageProducts = averageProducts.add(product.getPrice());
        }
        BigDecimal countProducts = new BigDecimal(productInWarehouse.size());
        if (countProducts.compareTo(BigDecimal.ZERO) == 0) {

            return new BigDecimal(0);
        } else {
            return averageProducts.divide(countProducts, 2, RoundingMode.HALF_DOWN);
        }

    }

    @Override
    public String toString() {
        StringBuilder printProducts = new StringBuilder();
        for (Product product : productInWarehouse) {
            printProducts.append(product);
        }
        return String.format("%s - averagePrice = %.2f\n%s", nameWarehouse, getAveragePriceProducts(),
                printProducts);
    }
}
