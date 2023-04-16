package database;

import java.sql.*;

public class Test {

    private static final String CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/sql_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "07064783267";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            /* Create table if not exists */
            statement.execute("CREATE TABLE IF NOT EXISTS contacts" + "(ID INT NOT NULL AUTO_INCREMENT, name VARCHAR(50), phone VARCHAR(50), email VARCHAR(50), PRIMARY KEY(ID) )");


            /* Insert into table normal SQL way */
            insertData(connection, "contacts", "Ronaldo", "+2349060801044", "ronaldo@gmail.com");

            /* Updating data in the database SQL way */
            updateData(connection);


            /* Delete row from contacts table */
            deleteRecord(connection, 10);

            /* Fetch all data from contacts table */
            String selectQuery = "SELECT * FROM contacts";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            getAllData(resultSet);
            resultSet.close();


            /* Closing Connections*/
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteRecord(Connection connection, Integer id) throws SQLException {
        String deleteQuery = "DELETE FROM contacts WHERE ID = ?";
        PreparedStatement deletePreparedStatement = connection.prepareStatement(deleteQuery);
        deletePreparedStatement.setInt(1, id);
        int deletedRowCount = deletePreparedStatement.executeUpdate();
        if (deletedRowCount > 0) System.out.println("Row deleted successfully.");
        else System.out.println("No rows were deleted.");
        deletePreparedStatement.close();
    }

    private static void updateData(Connection connection) throws SQLException {
        String updateQuery = "UPDATE contacts SET name = ?, phone = ?, email = ? WHERE ID = ?";
        PreparedStatement updatePreparedStatement = connection.prepareStatement(updateQuery);
        updatePreparedStatement.setString(1, "Alim");
        updatePreparedStatement.setString(2, "+4419044343990");
        updatePreparedStatement.setString(3, "alim@gmail.com");
        updatePreparedStatement.setInt(4, 2); // Assuming the ID of the row to be updated is 1
        int updatedRowsCount = updatePreparedStatement.executeUpdate();
        System.out.println(updatedRowsCount > 0 ? "Row updated successfully." : "No rows were updated.");
        updatePreparedStatement.close();
    }


    /* Method to print the fetch data from the database */
    private static void getAllData(ResultSet resultSet) throws SQLException {
        /* Print column headers */
        System.out.printf("%-5s %-20s %-15s %-30s%n", "ID", "Name", "Phone", "Email");
        System.out.println("-".repeat(60));

        /* Process the result set */
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            System.out.printf("%-5d %-20s %-15s %-30s%n", id, name, phone, email);
        }
    }


    /* Inserting data into table  */
    private static void insertData(Connection connection, String tableName, String name, String phone, String email) throws SQLException {
        /* Inserting into table */
        String insertQuery = "INSERT INTO " + tableName + "(name, phone, email) VALUES (?, ?, ?)";
        PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery);
        insertPreparedStatement.setString(1, name);
        insertPreparedStatement.setString(2, phone);
        insertPreparedStatement.setString(3, email);
        int insertedRowCount = insertPreparedStatement.executeUpdate();
        System.out.println(insertedRowCount > 0 ? "Row inserted successfully." : "No rows were inserted.");
        insertPreparedStatement.close();
    }

}
