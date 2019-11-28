package utilities;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import javax.naming.*;

public class ConnectionPool{
	public static Connection getConnection(){
		Connection connection = null;

		try{
			InitialContext initialContext = new InitialContext();
			Context context = (Context)initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource)context.lookup("jdbc/app");
			connection = dataSource.getConnection();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(NamingException e){
			e.printStackTrace();
		}
		return connection;
	}
}