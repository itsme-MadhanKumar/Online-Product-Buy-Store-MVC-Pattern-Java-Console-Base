package Util;

import java.sql.*;
public class Database_connection
{
    private static final String URL = "jdbc:mysql://localhost:3306/ONLINE_PURCHASE";
    private static final String USER = "root";
    private static final String PSW = "Universe9952@";
    protected static Connection conn = null;
    public Connection connect() throws SQLException
    {
        if(conn==null)
        {
            return conn  = DriverManager.getConnection(URL,USER,PSW);
        }
        return conn;
    }
}
