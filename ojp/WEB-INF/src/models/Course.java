package models;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import utilities.Db;

public class Course{
	//member variable
	private Integer	courseId;
	private	String	course;
	private CourseType courseType;

	//constructors
	public Course(Integer courseId,String course,CourseType courseType){
		setCourseId(courseId);
		setCourse(course);
		setCourseType(courseType);
	}
	
	//methods
	//@override toString()
	public String toString(){
		return "{'courseId' : " + getCourseId() + " , 'course' : '" + getCourse() + "' , 'courseType' : " + courseType + "}";
	}

	public static Course getCourseObject(Integer courseId,String course){
		String gco = "inside getCourseObject()--->";
		Course courseObject = null;
		String _courseId = "", _course = "";
		Boolean _ci = false,_c = false;

		if(courseId != null){
			_courseId = " course_id = ? ";
			_ci = true;
		}

		if(course != null){
			_course = " course = ? ";
			_c = true;
			if(_ci)
				_course = " and " + _course;
		}

		if(!_ci && !_c)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select course_id, course,course_type_id from courses where " +_courseId + _course;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_ci)
				ps.setInt(++i,courseId);

			if(_c)
				ps.setString(++i,course);



//			System.out.println(gco + "PreparedStataement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				//System.out.println(gco + "courseObject : " + (courseObject = new Course(rs.getInt(1),rs.getString(2),CourseType.getCourseTypeObject(rs.getInt(3),null))));
				courseObject = new Course(rs.getInt(1),rs.getString(2),CourseType.getCourseTypeObject(rs.getInt(3),null));
			}
			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return courseObject;
	}

	public static ArrayList collectAllCourses(){
		ArrayList courses = new ArrayList();
		String cac = "inside collectAllCourses()-->";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select course_id,course,course_type_id from courses";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(cac + "PreparedStataement : " + ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				courses.add(new Course(rs.getInt(1),rs.getString(2),CourseType.getCourseTypeObject(rs.getInt(3),null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return courses;
	}
	
	//getter setters
	public void setCourseId(Integer courseId){
		this.courseId=courseId;
	}
	public Integer getCourseId(){
		return courseId;
	}

	public void setCourse(String course){
		this.course=course;
	}
	public String getCourse(){
		return course;
	}

	public void setCourseType(CourseType courseType){
		this.courseType = courseType;
	}
	public CourseType getCourseType(){
		return courseType;
	}
}