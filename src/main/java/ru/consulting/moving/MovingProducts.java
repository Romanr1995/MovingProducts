package ru.consulting.moving;

import ru.consulting.model.Moving;
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

    public static List<List<Moving>> searchForCombinationsOfMovings(List<Moving> data) {

        List<List<Moving>> result = new ArrayList<>();

        if (data.size() > 0) {

            result.addAll(searchForCombinationsOfMovings(data.subList(0, data.size() - 1)));

            List<List<Moving>> newData = new ArrayList<>();
            for (List<Moving> value : result) {
                List<Moving> s = new ArrayList<>(value);
                s.add(data.get(data.size() - 1));
                newData.add(s);
            }
            result.addAll(newData);
        } else {
            result.add(new ArrayList<>());
        }

        return result;
    }

}
