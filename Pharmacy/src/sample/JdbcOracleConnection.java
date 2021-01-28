package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcOracleConnection {

    Connection conn = null;

    public Connection getConnect()
    {
        return conn;
    }
    public void connection() {

        try {
            // registers Oracle JDBC driver - though this is no longer required
            // since JDBC 4.0, but added here for backward compatibility
            Class.forName("oracle.jdbc.OracleDriver");

            // METHOD #2
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:ORCL";
            String username = "system";
            String password;
            password = "Sanfoura1998";
            conn = DriverManager.getConnection(dbURL2, username,password);
            if (conn != null) {
                System.out.println("Connected to database");
            }


        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
