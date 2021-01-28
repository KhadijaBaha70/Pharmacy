package DbOutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    private static String User="system";
    private static String Password="Sanfoura1998";
    private static String SQconn="jdbc:oracle:thin:@localhost:1521:ORCL";
    /* cette classe permet d'eatablir une connection avec notre base de donnees*/

    public static Connection getConnection() throws SQLException
    {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection(SQconn,User,Password);
        }catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
