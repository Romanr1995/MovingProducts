package ru.consulting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationDistribution {

    public static void main(String[] args) {

        try {
            ProductsLoader loader = new ProductsLoaderImpl();
            Map<String, Warehouse> loadProducts = loader.loadProducts(args[0]);
            ProductsLoaderImpl.printWarehousesWithProducts(loadProducts);

            List<Warehouse> allWarehouses = new ArrayList<>(loadProducts.values());

            List<Moving> allMovings = MovingProducts.findMovements(allWarehouses);
            String printMovings = MovingProducts.printMovings(allMovings);
            System.out.println(printMovings);

            try {
                MovingWriter movingWriter = new MovingWriterImpl();
                movingWriter.writeMoving(args[1], printMovings);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Невозможно вывести перемещения во внешний файл," +
                        "так как не задан путь к файлу в параметрах");
            }

        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Необходимо задать путь к файлу в параметры при запуске программы!");
        }

    }
}
