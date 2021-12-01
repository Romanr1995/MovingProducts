package ru.consulting.loading;

import ru.consulting.model.Product;
import ru.consulting.model.Warehouse;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.consulting.loading.Checker.checkingParameterValuesFromFile;

public class ProductsLoaderImpl implements ProductsLoader {

    @Override
    public Optional<Map<String, Warehouse>> loadProducts(String filename) throws IOException {
        Map<String, Warehouse> warehouses = new HashMap<>();
        try (BufferedReader buffer = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            int countLine = 0;
            while ((line = buffer.readLine()) != null) {

                countLine++;
                String[] productLine = line.split(",");

                String nameProduct;
                double weightProduct;
                BigDecimal priceProduct;
                String nameWarehouse;

                if (checkingParameterValuesFromFile(productLine, countLine)) {
                    nameProduct = productLine[0].trim();
                    weightProduct = Double.parseDouble(productLine[1]);
                    priceProduct = new BigDecimal(productLine[2]);
                    nameWarehouse = productLine[3].trim().toUpperCase();
                } else {
                    continue;
                }
                Product product = new Product(nameProduct, weightProduct, priceProduct);

                warehouses.putIfAbsent(nameWarehouse, new Warehouse(nameWarehouse));
                warehouses.get(nameWarehouse).addProductInWarehouse(product);

            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        return Optional.of(warehouses);
    }

    public static void printWarehousesWithProducts(Map<String, Warehouse> warehouseMap) {

        for (Map.Entry entryWarehouses : warehouseMap.entrySet()) {
            Warehouse warehouse = (Warehouse) entryWarehouses.getValue();
            System.out.println(warehouse);
        }
        System.out.println();
    }
}
