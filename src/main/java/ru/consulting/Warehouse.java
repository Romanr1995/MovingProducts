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

    public BigDecimal getAveragePriceProducts() {

        BigDecimal averagePrice = BigDecimal.ZERO;
        for (Product product : productInWarehouse) {
            averagePrice = averagePrice.add(product.getPrice());
        }
        BigDecimal countProducts = new BigDecimal(productInWarehouse.size());
        if (countProducts.compareTo(BigDecimal.ZERO) == 0) {
            return new BigDecimal(0);
        } else {
            return averagePrice.divide(countProducts, 2, RoundingMode.HALF_DOWN);
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
