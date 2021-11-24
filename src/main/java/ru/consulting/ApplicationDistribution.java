package ru.consulting;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationDistribution {

    public static void main(String[] args) {

        try {
            ProductsLoader loader = new ProductsLoaderImpl();
            Map<String, Warehouse> loadProducts = loader.loadProducts(args[0]);
            ProductsLoaderImpl.printWarehousesWithProducts(loadProducts);

            Collection<Warehouse> values = loadProducts.values();
            List<Warehouse> collect = values.stream().collect(Collectors.toList());

            List<Moving> movings = MovingProducts.comparisonAveragePrice(collect);
            String s = MovingProducts.printMovings(movings);
            System.out.println(s);

            MovingWriter movingWriter = new MovingWriterImpl();
            movingWriter.writeMoving(args[1],s);

        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Необходимо задать путь к файлу в параметры при запуске программы!");
        }

    }
}
