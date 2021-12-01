package ru.consulting.model;

import java.math.BigDecimal;

public class Moving {

    private String whereFromWarehouse;
    private String whereWarehouse;
    private String product;
    private BigDecimal averagePriceWhereFrom;
    private BigDecimal getAveragePriceWhere;

    public Moving(String warehouse1, String warehouse2, String product, BigDecimal averagePrice1,
                  BigDecimal getAveragePrice2) {
        this.whereFromWarehouse = warehouse1;
        this.whereWarehouse = warehouse2;
        this.product = product;
        this.averagePriceWhereFrom = averagePrice1;
        this.getAveragePriceWhere = getAveragePrice2;
    }

    @Override
    public String toString() {
        return String.format("С %s на %s product - %s\naveragePrice %s = %.2f,averagePrice %s = %.2f\n",
                whereFromWarehouse, whereWarehouse, product, whereFromWarehouse, averagePriceWhereFrom, whereWarehouse, getAveragePriceWhere);
    }
}
