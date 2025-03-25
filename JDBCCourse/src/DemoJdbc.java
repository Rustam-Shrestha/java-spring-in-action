// Step 1: Import package
import java.sql.*;

public class DemoJdbc {
    public static void main(String[] args) throws Exception {
        /*
        Steps for JDBC:
        1. Import package
        2. Load and register driver
        3. Create connection
        4. Create statement
        5. Execute statement
        6. Process result
        7. Close
        */

        // Step 2: Load and register driver
        Class.forName("org.postgresql.Driver");

        // Step 3: Create connection
        String url = "jdbc:postgresql://localhost:5432/DemoDb";
        String userName = "postgres";
        String password = "admin";
        Connection con = DriverManager.getConnection(url, userName, password);

        System.out.println("Connection has been established");
    }
}
