package ed.wgu.zamzow.software_ii.database;

import java.sql.*;
import java.util.Properties;
import java.sql.DriverManager;

/**
 * Class that holds the connection data and creates the connection for all read and write
 *
 * @author Bret Zamzow
 */

public class DBConnect {

    //private static final String DBURL = "jdbc:mysql://163.47.101.126:3306/scheduler";
    private static final String DBURL = "jdbc:mysql://jmzsoft.com:3306/jmzsoft3_scheduler?enabledTLSProtocols=TLSv1.2";
    private static final String username = "jmzsoft3_mazwoz";
    private static final String pass = "TazMan00$$";

    /**
     * Method to establish a connection to the database
     * @return The connection to be used throughout the application
     * @throws Exception
     */
    public static Connection ConnectToDB() throws Exception{

        Connection con = null;

        Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", pass);


        con = DriverManager.getConnection(DBURL, connectionProps);
        return con;
    }

    /**
     * Method to close the connection
     * @param con
     * @throws Exception
     */
    public static void CloseConnection(Connection con) throws Exception {
        con.close();
    }
}
