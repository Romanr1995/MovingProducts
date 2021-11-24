package ru.consulting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MovingProducts {

    public static List<Moving> comparisonAveragePrice(List<Warehouse> warehouses) {

        List<Moving> movings = new ArrayList<>();

        for (int i = 0; i < warehouses.size(); i++) {

            Warehouse warehouse1 = warehouses.get(i);
            BigDecimal averagePriceProducts1 = warehouse1.getAveragePriceProducts();
            String nameWarehouse1 = warehouse1.getNameWarehouse();

            for (int j = 0; j < warehouses.size(); j++) {

                Warehouse warehouse2 = warehouses.get(j);
                BigDecimal averagePriceProducts2 = warehouse2.getAveragePriceProducts();
                String nameWarehouse2 = warehouse2.getNameWarehouse();

                if (averagePriceProducts1.compareTo(averagePriceProducts2) < 0) {
                    String productsMaxPrice = warehouse1.getProductsMaxPrice();
                    Moving moving = new Moving(nameWarehouse1, nameWarehouse2, productsMaxPrice,
                            averagePriceProducts1, averagePriceProducts2);
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
