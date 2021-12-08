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

    public static List<List<Moving>> findMovements(Collection<Warehouse> warehouses) {

        List<List<Moving>> movings = new ArrayList<>();
        for (Warehouse warehouseWhereFrom : warehouses) {
            BigDecimal averagePriceProductsWhereFrom = warehouseWhereFrom.getAveragePriceProducts(warehouseWhereFrom.getProductInWarehouse());
            List<List<Product>> lists = searchForCombinationsOfMovings(warehouseWhereFrom.getProductInWarehouse());
            for (Warehouse warehouseWhere : warehouses) {
                BigDecimal averagePriceProductsWhere = warehouseWhere.getAveragePriceProducts(warehouseWhere.getProductInWarehouse());
                if (averagePriceProductsWhereFrom.compareTo(averagePriceProductsWhere) < 0) {

                    List<Moving> collect = lists.stream()
                            .filter(productList -> warehouseWhereFrom.getAveragePriceProducts(productList).compareTo(averagePriceProductsWhereFrom) > 0)
                            .filter(productList -> warehouseWhereFrom.getAveragePriceProducts(productList).compareTo(averagePriceProductsWhere) < 0)
                            .map(productList -> new Moving(warehouseWhereFrom, warehouseWhere,
                                    productList))
                            .collect(Collectors.toList());
                        movings.add(collect);
                }
            }
        }
        return movings;
    }


    public static List<List<Product>> searchForCombinationsOfMovings(List<Product> data) {

        List<List<Product>> result = new ArrayList<>();

        if (data.size() > 0) {

            result.addAll(searchForCombinationsOfMovings(data.subList(0, data.size() - 1)));

            List<List<Product>> newData = new ArrayList<>();
            for (List<Product> value : result) {
                List<Product> productList = new ArrayList<>(value);
                productList.add(data.get(data.size() - 1));
                newData.add(productList);
            }
            result.addAll(newData);
        } else {
            result.add(new ArrayList<>());
        }
        return result;
    }
}
