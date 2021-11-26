package ru.consulting.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        List<BigDecimal> collect = productInWarehouse.stream()
                .map(product -> product.getPrice())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (collect.isEmpty()) {
            return new BigDecimal(0);
        } else {
            return collect.stream().reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(new BigDecimal(collect.size()), 2, RoundingMode.HALF_DOWN);
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

    public List<Product> getProductInWarehouse() {
        return productInWarehouse;
    }
}
