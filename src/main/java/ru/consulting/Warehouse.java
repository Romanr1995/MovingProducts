package ru.consulting;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private List<Product> productInWarehouse = new ArrayList<>();

    public Warehouse(Product product) {
        this.productInWarehouse.add(product);
    }

    public void setProductInWarehouse(Product product) {
        this.productInWarehouse.add(product);
    }

    public void printProductNaneAndAveragePrice() {
        String print = "Products: ";
        for (Product product : productInWarehouse) {
            print += product.getNameProduct() + ", ";
        }

        print += "\naveragePrice = " + getAveragePriceProducts();

        System.out.println(print);
    }

    public BigDecimal getAveragePriceProducts() {

        BigDecimal averagePrice = BigDecimal.ZERO;
        for (Product product : productInWarehouse) {
            BigDecimal productPrice = product.getPrice();
            BigDecimal add = averagePrice.add(productPrice);
            averagePrice = add;
        }
        BigDecimal countProducts = BigDecimal.valueOf(productInWarehouse.size());
        return averagePrice.divide(countProducts, 4, RoundingMode.HALF_DOWN);
    }

    @Override
    public String toString() {
        return productInWarehouse.toString();
    }
}
