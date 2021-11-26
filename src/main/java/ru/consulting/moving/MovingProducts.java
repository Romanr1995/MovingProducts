package ru.consulting.moving;

import ru.consulting.model.Moving;
import ru.consulting.model.Product;
import ru.consulting.model.Warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MovingProducts {

    public static List<Moving> findMovements(Collection<Warehouse> warehouses) {

        List<Moving> movings = new ArrayList<>();

        for (Warehouse warehouseWhereFrom : warehouses) {

            BigDecimal averagePriceProductsWhereFrom = warehouseWhereFrom.getAveragePriceProducts();

            for (Warehouse warehouseWhere : warehouses) {

                BigDecimal averagePriceProductsWhere = warehouseWhere.getAveragePriceProducts();

                if (averagePriceProductsWhereFrom.compareTo(averagePriceProductsWhere) < 0) {

                    List<Moving> movingFiltr = warehouseWhereFrom.getProductInWarehouse().stream()
                            .filter(product -> product.getPrice().compareTo(warehouseWhereFrom.getAveragePriceProducts()) > 0)
                            .filter(product -> product.getPrice().compareTo(averagePriceProductsWhere) < 0)
                            .map(product -> new Moving(warehouseWhereFrom.getNameWarehouse(), warehouseWhere.getNameWarehouse(),
                                    product.getNameProduct(), averagePriceProductsWhereFrom, averagePriceProductsWhere))
                            .collect(Collectors.toList());
                    movings.addAll(movingFiltr);
                }
            }
        }
        return movings;
    }

    public static String toStringMovings(List<Moving> movings) {

        StringBuilder printMovings = new StringBuilder();

        for (Moving moving : movings) {
            printMovings.append(moving + "\n");
        }

        return String.format("При перемещении товара с одного склада на другой с уменьшением " +
                "средней цены товаров на обоих складах возможны следующе варианты:\n%s", printMovings);
    }
}
