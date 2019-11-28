package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;	
import java.sql.SQLException;

import java.util.ArrayList;
import utilities.Db;



public class Department{
	//member variables
	private	Integer	departmentId;
	private	String	department;




	//constructor 
	public Department(Integer departmentId,String department){
		setDepartmentId(departmentId);
		setDepartment(department);
	}
	



	//methods 
	//@override toString()
	public String toString(){
		return " {'departmentId' : " + departmentId+" , 'department' : '" + department + "'}" ;
	}	
	
	public static Department getDepartmentObject(Integer departmentId,String department){
		
		String _department = "",_departmentId = "";
		String where = " where ", and = "";
		String gdo = "inside getDepartmentObject()-->";
		
		Department departmentObject = null;

		if(department == null && departmentId == null){
			and = "";
			where = "";
			return null;
		}else if(department != null && departmentId != null){
			and = " and ";
			where = " where ";
		}

		if(department != null){
			_department = "department = '" + department + "'";
		}
		
		if(departmentId != null){
			_departmentId = "department_id = " + departmentId ;
		}
		
		/*
		System.out.println(gdo+"departmentId : "+departmentId);
		System.out.println(gdo+"department : "+department);
		System.out.println(gdo+"where : "+where);
		System.out.println(gdo+"and : "+and);
		*/
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select department_id , department from departments "+where+_departmentId+and+_department;
			PreparedStatement ps = connection.prepareStatement(query);
//			System.out.println(gdo+"preparedStatement : "+ps+"\n");
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				departmentObject = new Department(rs.getInt(1),rs.getString(2));
			}
			connection.close();

		}catch(ClassNotFoundException e){
			e.printStackTrace();

		}catch(SQLException e){
			e.printStackTrace();

		}
		return departmentObject;
	}

	
	public static ArrayList<Department> collectAllDepartment(){

		ArrayList<Department> departments = new ArrayList<Department>();
		String cad = "inside collectAllDepartments()-->";

		try{

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select department_id,department from departments";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(cad+"preparedStatement : "+ps);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				departments.add(new Department(rs.getInt(1),rs.getString(2)));
			}

			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return departments;
	}



	//getter setters
	public void setDepartmentId(Integer departmentId){
		this.departmentId=departmentId;
	}
	public Integer getDepartmentId(){
		return departmentId;
	}

	public void setDepartment(String department){
		this.department=department;
	}
	public String getDepartment(){
		return department;
	}

}