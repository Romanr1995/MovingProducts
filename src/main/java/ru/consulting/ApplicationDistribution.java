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
import java.util.Optional;

public class ApplicationDistribution {

    public static void main(String[] args) {

        try {
            if (args.length == 2) {
                ProductsLoader loader = new ProductsLoaderImpl();
                Optional<Map<String, Warehouse>> loadProducts = loader.loadProducts(args[0]);
                if (loadProducts.isPresent()) {
                    ProductsLoaderImpl.printWarehousesWithProducts(loadProducts.get());
                    List<List<Moving>> allMovings = MovingProducts.findMovements(loadProducts.get().values());

                    MovingWriter movingWriter = new MovingWriterImpl();
                    movingWriter.writeMoving(args[1], allMovings);
                }
            } else {
                System.out.println("При запуске программы необходимо в параметры указать:" +
                        " путь к файлу из которого необходимо получить данные " +
                        "и путь в к файлу куда необходимо вывести результаты.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Неверно задано имя файла: " + args[0]);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
