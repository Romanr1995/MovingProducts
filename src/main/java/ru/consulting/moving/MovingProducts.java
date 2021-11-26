package ru.consulting.moving;

import ru.consulting.model.Moving;
import ru.consulting.model.Warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovingProducts {

    public static Optional<List<Moving>> findMovements(Collection<Warehouse> warehouses) {

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
        return Optional.of(movings);
    }

    public static String toStringMovings(List<Moving> movings) {

        StringBuilder printMovings = new StringBuilder();

        for (Moving moving : movings) {
            printMovings.append(moving + "\n");
        }

        return String.format("При перемещении товара с одного склада на другой с уменьшением " +
                "средней цены товаров на обоих складах возможны следующе варианты:\n%s", printMovings);
    }

    public static List<String> searchForCombinationsOfNumbers(List<Integer> data) {

        List<String> result = new ArrayList<>();
        if (data.size() > 0) {

            result.addAll(searchForCombinationsOfNumbers(data.subList(0, data.size() - 1)));

            List<String> newData = new ArrayList<>();
            for (int i1 = 0; i1 < result.size(); i1++) {
                String s = "" + result.get(i1) + data.get(data.size() - 1);
                newData.add(s);
            }
            result.addAll(newData);
        } else {
            result.add("");
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(searchForCombinationsOfNumbers(List.of(2, 6, 10)));
    }
}
