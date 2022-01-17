package ru.consulting.data;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

import static ru.consulting.loading.Checker.checkingParameterValuesFromFile;

public class LoaderInDataBaseImpl implements LoaderInDataBase {

    @Override
    public void loadProductsInDataBase(String fileName) throws IOException, ClassNotFoundException {
        try (BufferedReader buffer = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            int countLine = 0;
            try (Connection connection = getConnection()) {
                Statement stmt = connection.createStatement();
                while ((line = buffer.readLine()) != null) {
                    countLine++;
                    String[] productLine = line.split(",");

                    if (checkingParameterValuesFromFile(productLine, countLine)) {
                        ResultSet resultSet = stmt.executeQuery("SELECT id FROM warehouses WHERE name = "
                                + productLine[3].trim().toUpperCase());
                        int id_warehouse = resultSet.getInt("id");
                        if (id_warehouse == 0) {
                            PreparedStatement preparedStatementWarehouses =
                                    connection.prepareStatement("INSERT INTO warehouses (name) VALUES (?)");
                            preparedStatementWarehouses.setString(2, productLine[3].trim().toUpperCase());
                        }
                        PreparedStatement preparedStatementProducts =
                                connection.prepareStatement("INSERT INTO products (name, price, id_warehouse) " +
                                        "VALUES (?, ?, ?)");
                        preparedStatementProducts.setString(2, productLine[0].trim());
                        preparedStatementProducts.setDouble(3, Double.parseDouble(productLine[1]));
                        preparedStatementProducts.setBigDecimal(4, new BigDecimal(productLine[2]));
                        preparedStatementProducts.setInt(5, id_warehouse);

                    } else {
                        continue;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException, NoSuchMethodException{
        Class.forName("org.postgresql.Driver");
        Properties prop = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            prop.load(in);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
    }
}
