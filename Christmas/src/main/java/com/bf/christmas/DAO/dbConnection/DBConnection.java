package com.bf.christmas.DAO.dbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * create and get a new Connection with the database.
 * @author gautier
 *
 */
public class DBConnection {


	/**
	 *  url of the db
	 */
	private final static String url = "jdbc:mysql://localhost:3306/christmas";
	
	/**
	 * Drive used to connect to the bd
	 */
	private final static String driver = "com.mysql.jdbc.Driver";
	
	/**
	 * userName to connect to the db 
	 */
	private final static String userName = "gautier";
	
	/**
	 * Password for the connection to the db
	 */
	private final static String password = "christmas";
	
	
	/**
	 * Allows to create a new db connection
	 * @return the new db connection from pool
	 * @throws NamingException
         * @throws SQLException 
	 */

	public static Connection getConnection() throws NamingException, SQLException {
            
            try {
                     Context initCtx = new InitialContext();

                     Context envCtx = (Context) initCtx.lookup("java:comp/env");

                     DataSource ds = (DataSource) envCtx.lookup("jdbc/christmas");
                     
                     return ds.getConnection();
                     
            } catch (NamingException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
	}
}
