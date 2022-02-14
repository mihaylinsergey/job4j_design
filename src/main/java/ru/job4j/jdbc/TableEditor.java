package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void executeTable(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
                statement.execute(query);
        }
    }

    public void createTable(String tableName) throws SQLException {
        String query = String.format(
                "create table if not exists %s (%s, %s);", tableName,
                "id serial primary key",
                "name text"
        );
        executeTable(query);
    }

    public void dropTable(String tableName) throws SQLException {
        String query = String.format(
                "drop table %s;", tableName
        );
        executeTable(query);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String query = String.format(
                "alter table %s add %s %s;", tableName,
                columnName, type
        );
        executeTable(query);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String query = String.format(
                "alter table %s drop %s;", tableName,
                columnName
        );
        executeTable(query);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String query = String.format(
                "alter table %s rename column %s to %s;", tableName,
                columnName, newColumnName
        );
        executeTable(query);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("./data/app.properties")) {
            properties.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
          tableEditor.createTable("Cars");
          System.out.println(getTableScheme(connection, "Cars"));
          tableEditor.addColumn("Cars", "Count", "int");
          System.out.println(getTableScheme(connection, "Cars"));
          tableEditor.dropColumn("Cars", "Model");
          System.out.println(getTableScheme(connection, "Cars"));
          tableEditor.renameColumn("Cars", "Count", "Model");
          System.out.println(getTableScheme(connection, "Cars"));
          tableEditor.dropTable("Cars");
        }
    }
}