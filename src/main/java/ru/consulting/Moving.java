package ru.consulting;

import java.math.BigDecimal;

public class Moving {

    private String warehouse1;
    private String warehouse2;
    private String product;
    private BigDecimal averagePrice1;
    private BigDecimal getAveragePrice2;

    public Moving(String warehouse1, String warehouse2, String product, BigDecimal averagePrice1,
                  BigDecimal getAveragePrice2) {
        this.warehouse1 = warehouse1;
        this.warehouse2 = warehouse2;
        this.product = product;
        this.averagePrice1 = averagePrice1;
        this.getAveragePrice2 = getAveragePrice2;
    }

    public String getWarehouse1() {
        return warehouse1;
    }

    public String getWarehouse2() {
        return warehouse2;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getAveragePrice1() {
        return averagePrice1;
    }

    public BigDecimal getGetAveragePrice2() {
        return getAveragePrice2;
    }

    @Override
    public String toString() {
        return String.format("С %s на %s product - %s\naveragePrice %s = %.2f,averagePrice %s = %.2f\n",
                warehouse1, warehouse2, product, warehouse1, averagePrice1, warehouse2, getAveragePrice2);
    }
}
