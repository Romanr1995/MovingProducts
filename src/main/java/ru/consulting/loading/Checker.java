package ru.consulting.loading;

import java.math.BigDecimal;

public class Checker {

    public static boolean checkingParameterValuesFromFile(String[] valuesFromFile, int countLine) {

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
            System.out.println("В строке " + countLine + " не верно задано " +
                    "количество аргументов. " + "Должно быть 4 аргумента,перечисленных через ,\n");
            return false;
        }
    }

    public static boolean isCorrectWeight(String weight, String nameProduct, int countLine) {
        String weightWithoutSpaces = weight.trim();

        try {
            double weightProduct = Double.parseDouble(weightWithoutSpaces);
            if (new BigDecimal(weightWithoutSpaces).scale() > 3) {
                System.out.println("Ошибка в строке " + countLine + ".Значение weight " +
                        "должно содержать не более трех знаков после \".\".\n");
                return false;
            }
            if (weightProduct <= 0) {
                System.out.println("Ошибка в строке " + countLine + ".Значение weight " +
                        "должно быть > 0.\n");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            if (weightWithoutSpaces.isEmpty()) {
                System.out.println("В строке " + countLine + " значение weight не должно быть пустым.\n");
            } else {
                System.out.println("Ошибка в строке " + countLine + ".Для " +
                        nameProduct + " значение weight = " + weight + " недопустимо.\n");
            }
            return false;
        }
    }

    public static boolean isCorrectPrice(String price, String nameProduct, int countLine) {
        String priceWithoutSpaces = price.trim();
        try {
            BigDecimal priceProduct = new BigDecimal(priceWithoutSpaces);
            if (priceProduct.scale() > 2) {
                System.out.println("Ошибка в строке " + countLine + ".Значение price " +
                        "должно содержать не более двух знаков после \".\".\n");
                return false;
            }
            if (priceProduct.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Ошибка в строке " + countLine + ".Значение price " +
                        "должно быть > 0.\n");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            if (priceWithoutSpaces.isEmpty()) {
                System.out.println("В строке " + countLine + " значение price не должно быть пустым.\n");
            } else {
                System.out.println("Ошибка в строке " + countLine + ".Для " +
                        nameProduct + " значение price = " + price + " недопустимо.\n");
            }
            return false;
        }
    }

    public static boolean isCorrectNameProduct(String nameProduct, int countLine) {
        String nameProductWithoutSpaces = nameProduct.trim();

        if (nameProductWithoutSpaces.length() == 0) {
            System.out.println("В строке " + countLine +
                    " значение nameProduct не должно быть пустым.\n");
            return false;
        }
        return true;
    }

    public static boolean isCorrectNameWarehouse(String nameWarehouse, int countLine) {
        String nameWarehouseWithoutSpaces = nameWarehouse.trim();

        if (nameWarehouseWithoutSpaces.length() == 0) {
            System.out.println("В строке " + countLine +
                    " значение nameWarehouse не должно быть пустым.\n");
        }
        return true;
    }
}
