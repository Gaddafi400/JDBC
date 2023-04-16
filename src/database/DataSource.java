package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/sql_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "07064783267";
    private Connection connection;


    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        dataSource.open();

    }

    /* Open connection */
    public Boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
            return false;
        }
    }

    /* Close connection */
    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
    }

}
