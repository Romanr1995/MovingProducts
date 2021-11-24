package ru.consulting;

import java.util.Map;

public class ApplicationDistribution {

    public static void main(String[] args) {

        try {
            ProductsLoader loader = new ProductsLoaderImpl();
            Map<String, Warehouse> loadProducts = loader.loadProducts(args[0]);
            ProductsLoaderImpl.printWarehousesWithProducts(loadProducts);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Необходимо задать путь к файлу в параметры при запуске программы!");
        }

    }
}
