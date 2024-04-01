package ru.gb.lecture.jdbc;

import java.sql.*;

public class Database {
    private static final String url = "jdbc:mysql://localhost:32769";
    private static final String user = "root";
    private static final String password = "12345";

    public static void connect() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            Statement statement = con.createStatement();
            dropAndCreate(statement);
            createTable(statement);
            insertInTable(statement);
            selectFromTable(statement);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private static void dropAndCreate(Statement statement) throws SQLException {
        statement.execute("DROP SCHEMA `test` ;");
        statement.execute("CREATE SCHEMA `test` ;");
    }

    private static void createTable(Statement statement) throws SQLException {
        statement.execute("""
                CREATE TABLE `test`.`table` (
                 `id` INT NOT NULL,
                 `firstname` VARCHAR(45) NULL,
                 `lastname` VARCHAR(45) NULL,
                 PRIMARY KEY (`id`));""");
    }

    private static void insertInTable(Statement statement) throws SQLException {
        statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
                "VALUES (1,'Иванов','Иван');");
        statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
        "VALUES (2,'Петров','Пётр');");
    }

    private static void selectFromTable(Statement statement) throws SQLException {
        ResultSet set = statement.executeQuery("SELECT * FROM `test`.`table`;");
        while (set.next()){
            System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
        }
    }
}
