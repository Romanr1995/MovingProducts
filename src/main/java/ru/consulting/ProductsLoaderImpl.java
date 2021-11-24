package ru.consulting;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductsLoaderImpl implements ProductsLoader {

    @Override
    public Map<String, Warehouse> loadProducts(String filename) {
        Map<String, Warehouse> warehouses = new HashMap<>();
        try (BufferedReader buffer = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
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
                Warehouse exist = warehouses.get(nameWarehouse);
                exist.addProductInWarehouse(product);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Неверно задано имя файла: " + filename);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return warehouses;
    }

    public static void printWarehousesWithProducts(Map<String, Warehouse> warehouseMap) {

        for (Map.Entry entryWarehouses : warehouseMap.entrySet()) {
            Warehouse warehouse = (Warehouse) entryWarehouses.getValue();
            System.out.println(warehouse);
        }
        System.out.println();
    }

    public boolean checkingParameterValuesFromFile(String[] valuesFromFile, int countLine) {

        if (valuesFromFile.length == 4) {
            String nameProduct = valuesFromFile[0];
            String weight = valuesFromFile[1];
            String price = valuesFromFile[2];
            String nameWarehouse = valuesFromFile[3];

            boolean correctWeight = isCorrectWeight(weight, nameProduct, countLine);
            boolean correctPrice = isCorrectPrice(price, nameProduct, countLine);
            boolean correctNameProduct = isCorrectNameProduct(nameProduct, countLine);
            boolean correctNameWarehouse = isCorrectNameWarehouse(nameWarehouse, countLine);
            return correctWeight && correctPrice && correctNameProduct && correctNameWarehouse;
        } else {
            try {
                throw new RuntimeException("В строке " + countLine + " не верно задано " +
                        "количество аргументов. " + "Должно быть 4 аргумента,перечисленных через ,\n");
            } catch (RuntimeException runtimeException) {
                System.out.println(runtimeException.getMessage());
                return false;
            }
        }
    }

    public boolean isCorrectWeight(String weight, String nameProduct, int countLine) {
        String WeightWithoutSpaces = weight.trim();
        try {
            double weightProduct = Double.parseDouble(WeightWithoutSpaces);
            if (weightProduct <= 0) {
                throw new RuntimeException("Ошибка в строке " + countLine + ".Значение weight " +
                        "должно быть > 0.\n");
            }
            return true;
        } catch (NumberFormatException e) {
            if (WeightWithoutSpaces.isEmpty()) {
                System.out.println("В строке " + countLine + " значение weight не должно быть пустым.");
            } else {
                System.out.println("Ошибка в строке " + countLine + ".Для " +
                        nameProduct + " значение weight = " + weight + " недопустимо.\n");
            }
            return false;
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return false;
        }
    }

    public boolean isCorrectPrice(String price, String nameProduct, int countLine) {
        String priceWithoutSpaces = price.trim();
        try {
            BigDecimal priceProduct = new BigDecimal(priceWithoutSpaces);
            if (priceProduct.compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Ошибка в строке " + countLine + ".Значение price " +
                        "должно быть > 0.\n");
            }
            return true;
        } catch (NumberFormatException e) {
            if (priceWithoutSpaces.isEmpty()) {
                System.out.println("В строке " + countLine + " значение price не должно быть пустым.");
            } else {
                System.out.println("Ошибка в строке " + countLine + ".Для " +
                        nameProduct + " значение price = " + price + " недопустимо.\n");
            }
            return false;
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return false;
        }
    }

    public boolean isCorrectNameProduct(String nameProduct, int countLine) {
        String nameProductWithoutSpaces = nameProduct.trim();
        try {
            if (nameProductWithoutSpaces.length() == 0) {
                throw new RuntimeException("В строке " + countLine +
                        " значение nameProduct не должно быть пустым.");
            }
            return true;
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return false;
        }
    }

    public boolean isCorrectNameWarehouse(String nameWarehouse, int countLine) {
        String nameWarehouseWithoutSpaces = nameWarehouse.trim();
        try {
            if (nameWarehouseWithoutSpaces.length() == 0) {
                throw new RuntimeException("В строке " + countLine +
                        " значение nameWarehouse не должно быть пустым.");
            }
            return true;
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return false;
        }
    }
}
