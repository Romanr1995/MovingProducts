package ru.consulting;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DistributionSystem {

    private final static Map<String, Warehouse> warehouses = new HashMap<>();

    public Map<String, Warehouse> getWarehouses() {
        return warehouses;
    }

    public Warehouse getWarehouseByName(String name) {
        return warehouses.get(name);
    }

    public void downloadFileTxtAndConvertToWarehouses(String path) {

        try (BufferedReader buffer = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String line;
            while ((line = buffer.readLine()) != null) {

                String[] productLine = line.split(",");
                if (productLine.length == 4) {
                    String nameProduct = productLine[0];
                    double weightProduct;
                    try {
                        weightProduct = Double.parseDouble(productLine[1]);
                    } catch (NumberFormatException numberFormatException) {
                        System.out.println("Для " + nameProduct + " значение weight = " + productLine[1] +
                                ", недопустимо.Введите корректное значение!");
                        System.out.println();
                        continue;
                    }

                    BigDecimal priceProduct;
                    try {
                        priceProduct = new BigDecimal(productLine[2]);
                    } catch (NumberFormatException numberFormatException) {
                        System.out.println("Для " + nameProduct + " значение price = " + productLine[2] +
                                ", недопустимо.Введите корректное значение!");
                        System.out.println();
                        continue;
                    }
                    String nameWarehouse = productLine[3];

                    Product product = new Product(nameProduct, weightProduct, priceProduct);
                    if (warehouses.containsKey(nameWarehouse)) {
                        Warehouse exist = warehouses.get(nameWarehouse);
                        exist.addProductInWarehouse(product);
                    } else {
                        warehouses.put(nameWarehouse, new Warehouse(nameWarehouse, product));
                    }
                }
            }

        } catch (FileNotFoundException exception) {
            System.out.println("Неверно задано имя файла: " + path);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void printWarehousesWithProducts() {

        for (Map.Entry entryWarehouses : warehouses.entrySet()) {
            Warehouse warehouse = (Warehouse) entryWarehouses.getValue();
            System.out.println(warehouse);
        }
        System.out.println();
    }
}
