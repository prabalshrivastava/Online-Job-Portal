package models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import utilities.Db;
import utilities.DbConnect;


public class OrganizationType{
	//member variables
	private Integer	organizationTypeId;
	private String	organizationType;

	//constructors 
	public OrganizationType(Integer organizationTypeId , String organizationType){
		setOrganizationTypeId(organizationTypeId);
		setOrganizationType(organizationType);
	}

	//methods
	//@override toString()
	public String toString(){
		return "{'organizationTypeId' : " + getOrganizationTypeId() + ",'organizationType' : '" +getOrganizationType() + "'}";
	}
	
	public static OrganizationType getOrganizationTypeObject(Integer organizationTypeId,String organizationType){
		OrganizationType organizationTypeObject = null;
		String gotoo = "inside getOrganizationTypeObject()-->";
		
		String where = " where ";
		String and = " ";
		String organization_type_id = " organization_type_id = ? ";
		String organization_type = " organization_type = ? ";
		Boolean firstParam = false , secondParam = false;
		


		if(organizationTypeId == null){
			organization_type_id = " ";
			firstParam = true;
		}else{
			organization_type_id = " organization_type_id = ? ";
		}

		if(organizationType == null){
			organization_type = " ";
			secondParam = true;
		}else{
			organization_type = "organization_type = ? ";
		}
			
		if(firstParam && secondParam)
			return organizationTypeObject;	//ie return null if both paramters are null
		else if(!firstParam && !secondParam){
			and = " and ";
		}

		try{
			String query = "select organization_type_id,organization_type from organization_types"+where+organization_type_id+and+organization_type;
			PreparedStatement ps = DbConnect.getPs(query);
			
			int i = 0;
			if(!firstParam)
				ps.setInt(++i,organizationTypeId);
			if(!secondParam)
				ps.setString(++i,organizationType);


			//System.out.println(gotoo + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				organizationTypeObject = new OrganizationType(rs.getInt(1),rs.getString(2));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		/*
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		*/
		return organizationTypeObject;
	}
	
	public static ArrayList collectAllOrganizationTypes(){
		String caot = "inside CollectAllOrganizationTypes()-->";
		ArrayList organizationTypes = new ArrayList();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select organization_type_id,organization_type from organization_types";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(caot + "PreparedStatament : " + ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				organizationTypes.add(new OrganizationType(rs.getInt(1),rs.getString(2)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return organizationTypes;
	}



	//getter setters
	public void setOrganizationTypeId(Integer organizationTypeId){
		this.organizationTypeId=organizationTypeId;
	}
	public Integer getOrganizationTypeId(){
		return organizationTypeId;
	}

	public void setOrganizationType(String organizationType){
		this.organizationType = organizationType;
	}
	public String getOrganizationType(){
		return organizationType;
	}
}
