package ed.wgu.zamzow.software_ii.database;

import java.sql.*;
import java.util.Properties;
import java.sql.DriverManager;



public class DBConnect {

    private static final String DBURL = "jdbc:mysql://jmzsoft.com:3306/jmzsoft3_scheduler";
    private static final String username = "jmzsoft3_mazwoz";
    private static final String pass = "TazMan$$00";

    public static Connection ConnectToDB() throws Exception{

        Connection con = null;

        Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", pass);


        con = DriverManager.getConnection(DBURL, connectionProps);
        return con;
    }

    public static void CloseConnection(Connection con) throws Exception {
        con.close();
    }
}
