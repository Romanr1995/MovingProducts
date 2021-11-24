package ru.consulting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MovingProducts {

    public static List<Moving> findMovements(List<Warehouse> warehouses) {

        List<Moving> movings = new ArrayList<>();

        for (int i = 0; i < warehouses.size(); i++) {

            Warehouse warehouseWhereFrom = warehouses.get(i);
            BigDecimal averagePriceProductsWhereFrom = warehouseWhereFrom.getAveragePriceProducts();
            String nameWarehouseWhereFrom = warehouseWhereFrom.getNameWarehouse();

            for (int j = 0; j < warehouses.size(); j++) {

                Warehouse warehouseWhere = warehouses.get(j);
                BigDecimal averagePriceProductsWhere = warehouseWhere.getAveragePriceProducts();
                String nameWarehouseWhere = warehouseWhere.getNameWarehouse();

                if (averagePriceProductsWhereFrom.compareTo(averagePriceProductsWhere) < 0) {
                    String productsMaxPrice = warehouseWhereFrom.getProductsMaxPrice();

                    Moving moving = new Moving(nameWarehouseWhereFrom, nameWarehouseWhere, productsMaxPrice,
                            averagePriceProductsWhereFrom, averagePriceProductsWhere);
                    movings.add(moving);
                }
            }
        }
        return movings;
    }

    public static String printMovings(List<Moving> movings) {

        StringBuilder printMovings = new StringBuilder();

        for (Moving moving : movings) {
            printMovings.append(moving + "\n");
        }

        return String.format("При перемещении товара с одного склада на другой с уменьшением " +
                "средней цены товаров на обоих складах возможны следующе варианты:\n%s", printMovings);
    }
}
