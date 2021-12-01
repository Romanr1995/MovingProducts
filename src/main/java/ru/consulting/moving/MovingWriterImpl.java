package ru.consulting.moving;

import ru.consulting.model.Moving;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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
                for (Moving moving : movingSet) {
                    buffer.write(moving.toString());
                }
                buffer.write("\n");
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
