package ru.consulting;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DistributionSystem {

    private final static Map<String, Warehouse> warehouses = new HashMap<>();

    public static void downloadFiles() {
        String path = "C:\\Users\\rnozdrachev\\Desktop\\Java\\test1.txt";

        try (BufferedReader buffer = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                // считываем остальные строки в цикле

                String[] productLine = line.split(",");
                String nameProduct = productLine[0];
                BigDecimal weightProduct = BigDecimal.valueOf(Double.valueOf(productLine[1]));
                BigDecimal priceProduct = BigDecimal.valueOf(Double.valueOf(productLine[2]));
                String nameWarehouse = productLine[3];
                Product product = new Product(nameProduct, weightProduct, priceProduct);
                if (warehouses.containsKey(nameWarehouse)) {
                    Warehouse exist = warehouses.get(nameWarehouse);
                    exist.setProductInWarehouse(product);
                } else {
                    warehouses.put(nameWarehouse, new Warehouse(product));
                }
            }
            System.out.println(warehouses);

        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        downloadFiles();
        Warehouse warehouse = warehouses.get("второй склад");
        warehouse.printProductNaneAndAveragePrice();
    }
}
