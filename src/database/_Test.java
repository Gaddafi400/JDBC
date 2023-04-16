//package database;
//
//import java.sql.*;
//
//public class _Test {
//
//    private static final String CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/sql_store";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "07064783267";
//
//
//    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
//            Statement statement = connection.createStatement();
//            /* Create table if not exists */
//            statement.execute("CREATE TABLE IF NOT EXISTS contacts" + "(ID INT NOT NULL AUTO_INCREMENT, name VARCHAR(50), phone VARCHAR(50), email VARCHAR(50), PRIMARY KEY(ID) )");
//
//
//            /* Insert into table normal SQL way */
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Peter', '+2347064783267', 'peter@gmail.com')");
//            /* Inserting into table using prepared statement */
//            String insertQuery = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";
//            PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery);
//            insertPreparedStatement.setString(1, "Qaddafi");
//            insertPreparedStatement.setString(2, "+2349060801044");
//            insertPreparedStatement.setString(3, "Qamar@gmail.com");
//            int insertedRowCount = insertPreparedStatement.executeUpdate();
//            System.out.println(insertedRowCount > 0 ? "Row inserted successfully." : "No rows were inserted.");
//
//            /* Updating data in the database SQL way */
//            statement.execute("UPDATE  contacts SET phone='+2345318161390' WHERE id=1");
//            /* Updating data in the database using prepared statement */
//            String updateQuery = "UPDATE contacts SET name = ?, phone = ?, email = ? WHERE ID = ?";
//            PreparedStatement updatePreparedStatement = connection.prepareStatement(updateQuery);
//            updatePreparedStatement.setString(1, "Alim");
//            updatePreparedStatement.setString(2, "+4419044343990");
//            updatePreparedStatement.setString(3, "alim@gmail.com");
//            updatePreparedStatement.setInt(4, 2); // Assuming the ID of the row to be updated is 1
//            int updatedRowsCount = updatePreparedStatement.executeUpdate();
//            System.out.println(updatedRowsCount > 0 ? "Row updated successfully." : "No rows were updated.");
//
//            /* Delete data in the database SQL way */
//            statement.execute("DELETE FROM contacts WHERE id=13");
//            /* Delete row from contacts table */
//            String deleteQuery = "DELETE FROM contacts WHERE ID = ?";
//            PreparedStatement deletePreparedStatement = connection.prepareStatement(deleteQuery);
//            deletePreparedStatement.setInt(1, 13); // Assuming the ID of the row to be deleted is 1
//            int deletedRowCount = deletePreparedStatement.executeUpdate();
//            if (deletedRowCount > 0) System.out.println("Row deleted successfully.");
//            else System.out.println("No rows were deleted.");
//
//            /* Fetch all data from contacts table */
//            String selectQuery = "SELECT * FROM contacts";
//            ResultSet resultSet = statement.executeQuery(selectQuery);
//            printFetchData(resultSet);
//
//
//            /* Closing Connections*/
//            updatePreparedStatement.close();
//            insertPreparedStatement.close();
//            deletePreparedStatement.close();
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    /* Method to print the fetch data from the database */
//    private static void printFetchData(ResultSet resultSet) throws SQLException {
//        /* Print column headers */
//        System.out.printf("%-5s %-20s %-15s %-30s%n", "ID", "Name", "Phone", "Email");
//        System.out.println("-".repeat(60));
//
//        /* Process the result set */
//        while (resultSet.next()) {
//            int id = resultSet.getInt("ID");
//            String name = resultSet.getString("name");
//            String phone = resultSet.getString("phone");
//            String email = resultSet.getString("email");
//            System.out.printf("%-5d %-20s %-15s %-30s%n", id, name, phone, email);
//        }
//    }
//}
