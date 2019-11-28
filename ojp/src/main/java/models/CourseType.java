package models;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import utilities.Db;

public class CourseType{
	private Integer		courseTypeId;
	private String		courseType;


	//Constructors
	public CourseType(){
	}

	public CourseType(Integer courseTypeId,String courseType){
		setCourseTypeId(courseTypeId);
		setCourseType(courseType);
	}

	//methods 
	//@overrride toString()
	public String toString(){
		return "{'courseTypeId' : '" + courseTypeId + "' , 'courseType' : '" + courseType + "'}";
	}
	







	public static CourseType getCourseTypeObject(Integer courseTypeId,String courseType){


		CourseType courseTypeObject = null;
		String gcto = "inside getCourseTypeObject()--->";
		
		String _courseTypeId = "",_courseType = "";
		Boolean _cti = false,_ct = false;
		
		if(courseTypeId != null){
			_courseTypeId = " course_type_id = ? ";
			_cti = true;
		}

		if(courseType != null){
			_courseType = " course_type = ? ";
			_ct = true;

			if(_cti)
				_courseType = " and " + _courseType;
		}

		if(!_cti && !_ct)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select course_type_id,course_type from course_types where " + _courseTypeId + _courseType;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_cti)
				ps.setInt(++i,courseTypeId);
			
			if(_ct)
				ps.setString(++i,courseType);


//			System.out.println(gcto + "preparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();


			if(rs.next()){
//				System.out.println(gcto + "courseTypeObject : " + (courseTypeObject = new CourseType(rs.getInt(1),rs.getString(2))));
				courseTypeObject = new CourseType(rs.getInt(1),rs.getString(2));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return courseTypeObject;
	}

	public static ArrayList collectAllCourseType(){
		String cact = "inside collectAllCourseType()-->";
		ArrayList courseTypes = new ArrayList();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select course_type_id,course_type from course_types";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(cact + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				courseTypes.add(new CourseType(rs.getInt(1),rs.getString(2)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return courseTypes;
	}


		

	//getter setters
	public void setCourseTypeId(Integer courseTypeId){
		this.courseTypeId = courseTypeId;
	}
	public Integer getCourseTypeId(){
		return courseTypeId;
	}

	public void setCourseType(String courseType){
		this.courseType = courseType;
	}
	public String getCourseType(){
		return courseType;
	}


}