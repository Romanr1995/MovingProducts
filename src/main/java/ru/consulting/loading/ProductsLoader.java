package ru.consulting.loading;

import ru.consulting.model.Warehouse;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface ProductsLoader {

    Optional<Map<String, Warehouse>> loadProducts(String filename) throws IOException;
}
