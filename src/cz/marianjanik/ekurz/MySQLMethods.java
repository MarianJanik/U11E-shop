package cz.marianjanik.ekurz;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLMethods implements GoodsMethods {

    private static final String MY_URL = "JDBC:MySQL://Localhost:3306/e-shop";
    private static final String MY_USER = "userJava";
    private static final String MY_PASSWORD = "Test1234!";

    @Override
    public Item loadItemById(Integer id) throws SQLException {
        String commandSQL = "SELECT * FROM table_items WHERE id=" + id;
        Statement statement = MySQLMethods.mySQLConnectionAndStatement();
        ResultSet resultSet = statement.executeQuery(commandSQL);
        resultSet.next();
        return getItem(resultSet);
    }

    @Override
    public void deleteAllOutOfStockItems() throws SQLException {
        Statement statement = MySQLMethods.mySQLConnectionAndStatement();
        statement.executeUpdate("DELETE FROM table_items WHERE numberInStock=0");
    }

    @Override
    public List<Item> loadAllAvailableItems() throws SQLException {
        List <Item> itemList = new ArrayList<>();
        Statement statement = MySQLMethods.mySQLConnectionAndStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM table_items WHERE numberInStock>0");
        while (resultSet.next()) {
            Item item = getItem(resultSet);
            itemList.add(item);
        }
        return itemList;
    }

    @Override
    public void saveItem(Item item) throws SQLException {
        Statement statement = MySQLMethods.mySQLConnectionAndStatement();
        statement.executeUpdate("INSERT INTO table_items (partNo,serialNo,name,description,numberInStock,price) VALUES ('"
                + item.getPartNo() + "','" + item.getSerialNo() + "','" + item.getName() + "','"
                + item.getDescription() + "'," + item.getNumberInStock() + "," + item.getPrice() + ")");
    }

    @Override
    public void updatePrice(Integer id, BigDecimal newPrice) throws SQLException {
        String commandSQL = "UPDATE table_items SET price=" + newPrice + " WHERE id=" + id;
        Statement statement = MySQLMethods.mySQLConnectionAndStatement();
        statement.executeUpdate(commandSQL);
    }

    private static Statement mySQLConnectionAndStatement() throws SQLException {
        Connection dataBaseConnection = DriverManager.getConnection(MY_URL,MY_USER,MY_PASSWORD);
        Statement statement = dataBaseConnection.createStatement();
        return statement;
    }

    private static Item getItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId((resultSet.getInt(1)));
        item.setPartNo(resultSet.getString(2));
        item.setSerialNo(resultSet.getString(3));
        item.setName(resultSet.getString(4));
        item.setDescription(resultSet.getString(5));
        item.setNumberInStock(resultSet.getInt(6));
        item.setPrice(resultSet.getBigDecimal(7));
        return item;
    }
}
