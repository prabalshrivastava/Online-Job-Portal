package models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.Db;
import java.util.ArrayList;

public class Designation{
	//member variables
	private Integer designationId;
	private String	designation;
	
	//constructors
	public Designation(Integer designationId,String designation){
		setDesignationId(designationId);
		setDesignation(designation);
	}

	//methods
	//@overrride toString()
	public String toString(){
		return " {'designationId' : " + designationId + " , 'designation' : '" + designation + "'} ";
	}
	public static Designation getDesignationObject(Integer designationId,String designation){
		
		Designation designationObject = null;
		String _designationId = "",_designation = "",and = "",where = " where ";
		String gdo = "inside getDesignationObject()-->";
		
		if(designation == null && designationId == null){
			and = "";
			where = "";
			return null;
		}else if(designation != null && designationId != null){
			and = " and ";
			where = " where ";
		}
		
		if(designation != null){
			_designation = " designation = '" + designation + "'";
		}

		if(designationId != null){
			_designationId = " designation_id = " + designationId ;
		}
		/*
		System.out.println(gdo+"designationId : "+designationId);
		System.out.println(gdo+"designation : "+designation);
		System.out.println(gdo+"where : "+where);
		System.out.println(gdo+"and : "+and);
		*/
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select designation_id, designation from designations " + where + _designation + and + _designationId;
			PreparedStatement ps = connection.prepareStatement(query);
			//System.out.println(gdo+"PreparedStatement : "+ps+"\n");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				designationObject = new Designation(rs.getInt(1),rs.getString(2));
			}
			
			connection.close();

		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return designationObject;
	}


	public static ArrayList<Designation> collectAllDesignation(){

		String cad = "inside collectAllDesignation()-->";
		ArrayList<Designation> designations = new ArrayList<Designation>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select designation_id,designation from designations";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(cad+"preparedStatement : "+ps);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				designations.add(new Designation(rs.getInt(1),rs.getString(2)));
//				System.out.println(cad+"designationId : "+rs.getInt(1)+"\tdesignation : "+rs.getString(2));
			}

			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return designations;
	}

	//getter setters
	public void setDesignationId(Integer designationId){
		this.designationId=designationId;
	}

	public Integer getDesignationId(){
		return designationId;
	}

	public void setDesignation(String designation){
		this.designation=designation;
	}
	public String getDesignation(){
		return designation;
	}
}