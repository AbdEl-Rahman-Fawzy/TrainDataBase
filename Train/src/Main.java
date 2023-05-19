import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/train";
        String username = "root";
        String password = "Abdo@112233";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            String showTablesQuery = "SHOW TABLES";
            ResultSet tablesResult = statement.executeQuery(showTablesQuery);

            while (tablesResult.next()) {
                String tableName = tablesResult.getString(1);
                System.out.println("Table: " + tableName);
                String selectQuery = "SELECT * FROM " + tableName;
                ResultSet dataResult = statement.executeQuery(selectQuery);
                ResultSetMetaData metaData = dataResult.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (dataResult.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object value = dataResult.getObject(i);
                        System.out.println(columnName + ": " + value);
                    }
                    System.out.println();
                }
                dataResult.close();
            }

            tablesResult.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}