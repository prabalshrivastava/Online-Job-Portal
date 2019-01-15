package models;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

import utilities.Db;

public class UserType{

	//member variables
	private Integer userTypeId;
	private String	userType;


	public static final Integer CANDIDATE = getUserTypeObject(null,"candidate").getUserTypeId();
	public static final Integer COMPANY = getUserTypeObject(null,"company").getUserTypeId();

	static{
		System.out.println("CANDIDATE : " + CANDIDATE);
		System.out.println("COMPANY : " + COMPANY);
	}


	/*
		public static final	Integer CANDIDATE = 1;
		public static final	Integer COMPANY = 2;
	*/


	
	public UserType(){
	}
	public UserType(Integer userTypeId){
		this.userTypeId = userTypeId;
	}
	public UserType(Integer userTypeId,String userType){
		this(userTypeId);
		setUserType(userType);
	}

	//methods()
	public String toString(){
		return "{'userTypeId' : " + userTypeId + ",'userType' : '" + userType + "'}";
	}

	public static Boolean isCandidate(User user){
		if(user instanceof Candidate){
			System.out.println("user is a candidate");
			return true;
		}else{
			System.out.println("user is a company");
			return false;
		}
	}
	
	public static UserType getUserTypeObject(Integer userTypeId,String userType){
		String guto = "inside getUserTypeObject()--->";
		String _userTypeId = "",_userType = "";
		Boolean _uti = false , _ut = false;
		UserType userTypeObject = null;

		if(userTypeId != null){
			_uti = true;
			_userTypeId = " user_type_id = ? " ;
		}
		if(userType != null){
			_ut = true;
			_userType = " user_type = ? ";
			if(_uti)
				_userType = " and " + _userType;
		}

		if(!_uti && !_ut)
			return null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select user_type_id,user_type from user_types where " + _userTypeId + _userType;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			
			if(_uti)
				ps.setInt(++i,userTypeId);


			if(_ut)
				ps.setString(++i,userType);
			
			//System.out.println(guto + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				//System.out.println(guto + (userTypeObject = new UserType(rs.getInt(1),rs.getString(2))));
				userTypeObject = new UserType(rs.getInt(1),rs.getString(2));
			}
			connection.close();
		}

		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return userTypeObject;
	}

	
	//getter setters
	
	public void setUserTypeId(Integer userTypeId){
		this.userTypeId=userTypeId;
	}
	public Integer getUserTypeId(){
		return userTypeId;
	}

	public void setUserType(String userType){
		this.userType = userType;
	}
	public String getUserType(){
		return userType;
	}
}