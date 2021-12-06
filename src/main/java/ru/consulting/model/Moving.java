package ru.consulting.model;

public class Moving {

    private Warehouse whereFromWarehouse;
    private Warehouse whereWarehouse;
    private Product product;

    public Moving(Warehouse whereFromWarehouse, Warehouse whereWarehouse, Product product) {
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

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return String.format("Откуда: %s\nКуда: %s\nСредняя цена до перемещения:\n" +
                        "%s = %.2f\n" +
                        "%s = %.2f\n" +
                        "\nСписок перемещенных товаров: \n",
                whereFromWarehouse.getNameWarehouse(), whereWarehouse.getNameWarehouse(),
                whereFromWarehouse.getNameWarehouse(), whereFromWarehouse.getAveragePriceProducts(),
                whereWarehouse.getNameWarehouse(), whereWarehouse.getAveragePriceProducts());
    }
}
