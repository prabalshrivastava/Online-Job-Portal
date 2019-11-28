package models;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utilities.Db;

public class Status{
	//member variables
	private	Integer statusId;
	private	String	status;
	
	//constructors
	public Status(){
	}

	public Status(Integer statusId,String status){
		setStatusId(statusId);
		setStatus(status);
	}

	//methods()
	//@override toString()
	public String toString(){
		return "{'statusId' : " + statusId + ",'status' : '" + status +  "'}";
	}

	//not yet tested
	public static Status getStatusObject(Integer statusId,String status){
		String gso = "inside getStatusObject()--->";

		String   _statusId = "" , _status = "";
		Boolean _si = false , _s = false;
		Status statusObject = null;

		if(statusId != null){
			_statusId = " status_id = ? ";
			_si = true;
		}

		if(status != null){
			_status = " status = ? ";
			_s = true;
			if(_si)
				_status = " and " + status;
		}
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhos:3306/ojp?user=rrot&password=" + Db.dbpass);
			String query = " select status_id ,status from status where " + _statusId + _status;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_si)
				ps.setObject(++i,statusId);
			if(_s)
				ps.setObject(++i,status);
			
			System.out.println(gso + "PrepsredStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(gso + "statusObject : " + (statusObject = new Status(rs.getInt(1),rs.getString(2))));
			}
			connection.close();	
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return statusObject;
	}
	
	//(Integer statusId,String status){
	public static Status updateStatus(String status){
		String us = "inside updateStatus()--->";
		Status statusObject = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "insert into status(status) value(?)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			ps.setObject(++i,status);
			System.out.println(us + "PreparedStatement : " + ps);
			System.out.println(us + "RowsEffected : " + ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				System.out.println(us + "updating statusObject : " + (statusObject = Status.getStatusObject(rs.getInt(1),null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return statusObject;
	}

	//getter setters
	public void setStatusId(Integer statusId){
		this.statusId=statusId;
	}
	public Integer getStatusId(){
		return statusId;
	}
	
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
}