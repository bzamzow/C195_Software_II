package ed.wgu.zamzow.software_ii.database;

import java.sql.*;
import java.util.Properties;
import java.sql.DriverManager;



public class DBConnect {

    //private static final String DBURL = "jdbc:mysql://163.47.101.126:3306/scheduler";
    private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/scheduler";
    private static final String username = "sqlUser";
    private static final String pass = "Passw0rd!";

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
