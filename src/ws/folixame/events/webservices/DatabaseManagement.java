package ws.folixame.events.webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**  
 * @description this class will hold functions for database management
 * @author Amaia Eskisabel Azpiazu, Gonzalo Fernández Naveira, Jorge Yagüe París 
*/  

public class DatabaseManagement {
	
	/** 
	  * @description initialize the class to manage connections with the database
	*/
	
	public DatabaseManagement() {}
	
	/** 
	  * @description opens the connection with the database
	  * @param none 
	  * @return connection - connection with the database
	  * @throws DatabaseException - connection failed
	*/
	
	public Connection dbConnection() throws DatabaseException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC driver connector is missing");
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://156.35.95.49:3306/fm_folixame","folixameadmin", "folixando");
		} catch (SQLException e) {
			throw new DatabaseException("Connection failed");
		}
		return connection;
	}
	
	/** 
	  * @description close the connection with the database
	  * @param connection - parameter with the database connection
	  * @return void
	  * @throws DatabaseException - Could not close connection
	*/
	
	public void closeConnection(Connection connection) throws DatabaseException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException("Could not close connection");
		}
	}
}
