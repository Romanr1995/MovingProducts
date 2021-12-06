package ru.consulting.moving;

import ru.consulting.model.Moving;
import ru.consulting.model.Warehouse;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MovingWriterImpl implements MovingWriter {

    @Override
    public void writeMoving(String filename, List<List<Moving>> movings) {

        try (BufferedWriter buffer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {

            buffer.write("При перемещении товаров с одного склада на другой с уменьшением " +
                    "средней цены товаров на обоих складах возможны следующе наборы вариантов:\n\n");

            int number = 1;
            for (List<Moving> movingSet : movings) {
                if (movingSet.isEmpty()) {
                    continue;
                }
                buffer.write(number++ + ")");
                buffer.write(movingSet.get(0).toString());
                Warehouse whereFromWarehouse = movingSet.get(0).getWhereFromWarehouse();
                Warehouse whereWarehouse = movingSet.get(0).getWhereWarehouse();
                List<BigDecimal> collectPrice = new ArrayList<>();

                for (Moving moving : movingSet) {
                    buffer.write("-" + moving.getProduct().getNameProduct() + "\n");
                    collectPrice.add(moving.getProduct().getPrice());
                }
                BigDecimal averagePriceProductsWhereFromAfterMovings = whereFromWarehouse.getAveragePriceProducts(collectPrice, 1);
                BigDecimal averagePriceProductsWhereAfterMovings = whereWarehouse.getAveragePriceProducts(collectPrice, 0);
                String format = String.format("\nСредняя цена после перемещения:\n" +
                                "%s = %.2f\n" +
                                "%s = %.2f\n", whereFromWarehouse.getNameWarehouse(),
                        averagePriceProductsWhereFromAfterMovings,
                        whereWarehouse.getNameWarehouse(),
                        averagePriceProductsWhereAfterMovings);
                buffer.write(format);
                buffer.write("\n");
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
