package ru.consulting.model;

import java.util.List;

public class Moving {

    private Warehouse whereFromWarehouse;
    private Warehouse whereWarehouse;
    private List<Product> product;

    public Moving(Warehouse whereFromWarehouse, Warehouse whereWarehouse, List<Product> product) {
        this.whereFromWarehouse = whereFromWarehouse;
        this.whereWarehouse = whereWarehouse;
        this.product = product;
    }


    public Warehouse getWhereFromWarehouse() {
        return whereFromWarehouse;
    }

    public Warehouse getWhereWarehouse() {
        return whereWarehouse;
    }

    public List<Product> getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return String.format("Откуда: %s\nКуда: %s\nСредняя цена до перемещения:\n" +
                        "%s = %.2f\n" +
                        "%s = %.2f\n" +
                        "\nСписок перемещенных товаров: \n",
                whereFromWarehouse.getNameWarehouse(), whereWarehouse.getNameWarehouse(),
                whereFromWarehouse.getNameWarehouse(),
                whereFromWarehouse.getAveragePriceProducts(whereFromWarehouse.getProductInWarehouse()),
                whereWarehouse.getNameWarehouse(),
                whereWarehouse.getAveragePriceProducts(whereWarehouse.getProductInWarehouse()));
    }
}
