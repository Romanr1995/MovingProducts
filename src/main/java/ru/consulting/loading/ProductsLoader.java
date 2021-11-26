package ru.consulting.loading;

import ru.consulting.model.Warehouse;

import java.io.IOException;
import java.util.Map;

public interface ProductsLoader {

    Map<String, Warehouse> loadProducts(String filename) throws IOException;
}
