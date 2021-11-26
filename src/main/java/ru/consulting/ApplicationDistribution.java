package ru.consulting;

import ru.consulting.loading.ProductsLoader;
import ru.consulting.loading.ProductsLoaderImpl;
import ru.consulting.model.Moving;
import ru.consulting.model.Warehouse;
import ru.consulting.moving.MovingProducts;
import ru.consulting.moving.MovingWriter;
import ru.consulting.moving.MovingWriterImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApplicationDistribution {

    public static void main(String[] args) {

        try {
            ProductsLoader loader = new ProductsLoaderImpl();
            Map<String, Warehouse> loadProducts = loader.loadProducts(args[0]);
            ProductsLoaderImpl.printWarehousesWithProducts(loadProducts);

            List<Moving> allMovings = MovingProducts.findMovements(loadProducts.values());
            String stringMovings = MovingProducts.toStringMovings(allMovings);
            System.out.println(stringMovings);
            try {
                MovingWriter movingWriter = new MovingWriterImpl();
                movingWriter.writeMoving(args[1], stringMovings);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Невозможно вывести перемещения во внешний файл," +
                        "так как не задан путь к файлу в параметрах");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Неверно задано имя файла: " + args[0]);
        } catch (ArrayIndexOutOfBoundsException | IOException exception) {
            System.out.println("Необходимо задать путь к файлу в параметры при запуске программы!");
        }

    }
}
