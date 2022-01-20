package ru.consulting.data;

import ru.consulting.loading.ProductsLoaderImpl;
import ru.consulting.model.Product;
import ru.consulting.model.Warehouse;

import java.sql.*;
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
            PreparedStatement selectProducts =
                    connection.prepareStatement("SELECT name, weight, price FROM products WHERE id_warehouse=?");
            Statement statementWarehouse = connection.createStatement();
            ResultSet resultSetWarehouse = statementWarehouse.executeQuery("SELECT * FROM warehouses");
            while (resultSetWarehouse.next()) {
                Warehouse warehouse = new Warehouse(resultSetWarehouse.getString(2));
                List<Product> products = getProducts(selectProducts, resultSetWarehouse.getInt(1));
                warehouse.addProductInWarehouse(products);
                warehouses.putIfAbsent(resultSetWarehouse.getString(2),
                        warehouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductsLoaderImpl.printWarehousesWithProducts(warehouses);
    }

    private List<Product> getProducts(PreparedStatement selectProducts, int idWarehouse) throws SQLException {
        List<Product> products = new ArrayList<>();
        selectProducts.setInt(1, idWarehouse);
        ResultSet resultSet = selectProducts.executeQuery();
        while (resultSet.next()) {
            products.add(new Product(resultSet.getString(1), resultSet.getDouble(2),
                    resultSet.getBigDecimal(3)));
        }
        return products;
    }
}
