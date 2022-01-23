package ru.consulting.data;

import ru.consulting.loading.ProductsLoaderImpl;
import ru.consulting.model.Product;
import ru.consulting.model.Warehouse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.consulting.data.ConnectionManager.getConnection;

public class LoadOutDataBaseImpl implements LoadOutDataBase {

    @Override
    public void loadProductsOutDataBase() {
        Connection connection = getConnection();
        Map<String, Warehouse> warehouses = new HashMap<>();
        try {
            Statement statementProducts = connection.createStatement();
            ResultSet resultSetProducts = statementProducts
                    .executeQuery("SELECT name, weight, price, p.id_warehouse FROM products p ORDER BY p.id_warehouse, name");
            Statement statementWarehouse = connection.createStatement();
            ResultSet resultSetWarehouse = statementWarehouse
                    .executeQuery("SELECT id, name FROM warehouses ORDER BY id");
            resultSetProducts.next();
            while (resultSetWarehouse.next()) {
                Warehouse warehouse = new Warehouse(resultSetWarehouse.getString(2));
                List<Product> products = getProducts(resultSetProducts, resultSetWarehouse.getInt(1));
                warehouse.addProductInWarehouse(products);
                warehouses.putIfAbsent(resultSetWarehouse.getString(2),
                        warehouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        ProductsLoaderImpl.printWarehousesWithProducts(warehouses);
    }

    private List<Product> getProducts(ResultSet selectProducts, int idWarehouse) throws SQLException {
        List<Product> products = new ArrayList<>();
        do {
            products.add(new Product(selectProducts.getString(1), selectProducts.getDouble(2),
                    selectProducts.getBigDecimal(3)));
        } while (selectProducts.next() && selectProducts.getInt(4) == idWarehouse);
        return products;
    }
}
