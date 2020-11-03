package com.flipkart.Utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Db util.
 */
public class DBUtil {

    private static Connection connection = null;
    private static Logger logger = Logger.getLogger(DBUtil.class);


    /**
     * Gets connection to the db
     *
     * @return the connection
     */
    public static Connection getConnection() {
        if (connection != null) return connection;

        try {
            Properties prop = new Properties();
            InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream == null) throw new NullPointerException();
            prop.load(inputStream);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException | IOException | NullPointerException ex) {
            logger.error(ex.getMessage());
        }
        return connection;
    }

    /**
     * Close connection to the db
     */
    public static void closeConnection() {
        logger.info("Closing Connection...");
        try {
            if(connection != null) {
                connection.close();
            }
            else {
                logger.info("Connection closed successfully!");
            }
        }catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Close prepared statement
     *
     * @param stmt the prepared statement
     */
    public static void closeStmt(PreparedStatement stmt) {
        try{
            if(stmt!=null)
                stmt.close();
        }catch(SQLException ignored){}
    }
}
