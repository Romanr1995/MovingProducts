package ru.consulting;

import java.util.Map;

public interface ProductsLoader {

    Map<String, Warehouse> loadProducts(String filename);
}
