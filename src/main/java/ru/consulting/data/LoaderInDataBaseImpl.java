package ru.consulting.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.*;

import static ru.consulting.data.ConnectionManager.getConnection;
import static ru.consulting.loading.Checker.checkingParameterValuesFromFile;

public class LoaderInDataBaseImpl implements LoaderInDataBase {

    @Override
    public void loadProductsInDataBase(String fileName) throws IOException {
        Connection connection = getConnection();

        try (BufferedReader buffer = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            int countLine = 0;
            connection.setAutoCommit(false);
            try {
                PreparedStatement preparedStatementFindWarehouse =
                        connection.prepareStatement("SELECT id FROM warehouses WHERE name = ?");
                PreparedStatement preparedStatementWarehouses =
                        connection.prepareStatement("INSERT INTO warehouses (name) VALUES (?)",
                                Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatementProducts =
                        connection.prepareStatement("INSERT INTO products (name, weight, price, id_warehouse) " +
                                "VALUES (?, ?, ?, ?)");
                while ((line = buffer.readLine()) != null) {
                    countLine++;
                    String[] productLine = line.split(",");

                    if (checkingParameterValuesFromFile(productLine, countLine)) {

                        preparedStatementFindWarehouse.setString(1, productLine[3].trim().toUpperCase());
                        int idWarehouse = getIdWarehouse(preparedStatementFindWarehouse, preparedStatementWarehouses,
                                productLine[3].trim().toUpperCase());
                        saveProducts(preparedStatementProducts, productLine[0].trim(), Double.parseDouble(productLine[1]),
                                new BigDecimal(productLine[2]), idWarehouse);
                        connection.commit();
                    }
                }
            } catch (SQLException e) {
                try {
                    System.out.println("Ошибка при записи в базу данных");
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
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
    }

//    private int getIdWarehouse(PreparedStatement selectPstm, PreparedStatement insertPstm,
//                               String nameWarehouse) throws SQLException {
//        selectPstm.setString(1, nameWarehouse);
//        ResultSet resultSet = selectPstm.executeQuery();
//        if (!resultSet.next()) {
//            insertPstm.setString(1, nameWarehouse);
//            insertPstm.executeUpdate();
//        }
//        resultSet = selectPstm.executeQuery();
//        resultSet.next();
//
//        return resultSet.getInt(1);
//    }

    private int getIdWarehouse(PreparedStatement selectPstm, PreparedStatement insertPstm,
                               String nameWarehouse) throws SQLException {
        selectPstm.setString(1, nameWarehouse);
        ResultSet resultSet = selectPstm.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            insertPstm.setString(1, nameWarehouse);
            if (insertPstm.executeUpdate() == 0) {
                throw new SQLException("Не удалось внести объект, строки не затронуты");
            }
            if (insertPstm.getGeneratedKeys().next()) {
                return insertPstm.getGeneratedKeys().getInt(1);
            } else {
                throw new SQLException("Id Warehouse не получен");
            }
        }
    }

    private void saveProducts(PreparedStatement preparedStatement, String nameProduct, double weight,
                              BigDecimal price, int idWarehouse) throws SQLException {
        preparedStatement.setString(1, nameProduct);
        preparedStatement.setDouble(2, weight);
        preparedStatement.setBigDecimal(3, price);
        preparedStatement.setInt(4, idWarehouse);
        preparedStatement.executeUpdate();
    }

    public static void deleteDataBaseProducts() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM products");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
