package utilities;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class DbConnect{
	public static PreparedStatement getPs(String query){
		PreparedStatement ps = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			ps = connection.prepareStatement(query);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return ps;
	}
}