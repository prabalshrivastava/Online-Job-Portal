package models;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;


import utilities.DbConnect;
import utilities.Db;




public class Organization{

	//member variables
	private	Integer				organizationId;
	private	OrganizationType	organizationType;
	private	String				organization;

	//constructors
	public Organization(Integer organizationId,OrganizationType organizationType,String organization){
		setOrganizationId(organizationId);
		setOrganizationType(organizationType);
		setOrganization(organization);
	}
	
	//methods
	//@override toString()
	public String toString(){
		return "{'organizationId' : " + organizationId + " , 'organizationType' : " + organizationType + ",'organization' : '" + organization + "'}";
	}


	public static Organization getOrganizationObject(Integer organizationId,OrganizationType organizationType,String organization){
		String goo = "inside getOrganizationObject()--->";
		Organization organizationObject = null;

		String _organization = "",_organizationId = "",_organizationType = "" ;
		Boolean _o = false,_oi = false, _ot = false;


		if(organizationId != null){
			_organizationId = " organization_id = ? " ;
			_oi = true;
		}

		if(organizationType != null){
			_organizationType = " organization_type_id = ? ";
			_ot = true;

			if(_oi)
				_organizationType = " and " + _organizationType;
		}

		if(organization != null){
			_organization = " organization = ? " ;
			_o = true;

			if(_oi || _ot)
				_organization = " and " + _organization;
		}

		if(!_oi && !_ot &&!_o)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select organization_id,organization_type_id,organization from organization where " + _organizationId + _organizationType + _organization;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_oi)
				ps.setInt(++i,organizationId);

			if(_ot)
				ps.setInt(++i,organizationType.getOrganizationTypeId());


			if(_o)
				ps.setString(++i,organization);

			//System.out.println(goo + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				//System.out.println(goo + "OrganizationObject : " + (organizationObject = new Organization(rs.getInt(1),OrganizationType.getOrganizationTypeObject(rs.getInt(2),null),rs.getString(1))));
				organizationObject = new Organization(rs.getInt(1),OrganizationType.getOrganizationTypeObject(rs.getInt(2),null),rs.getString(1));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return organizationObject;
	}

	public static ArrayList collectAllOrganizations(){
		String cao = "inside collectAllOrganizations()-->";
		ArrayList organizations = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select organization_id,organization_type_id,organization from organization";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(cao + "PreparedStatament : " + ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				organizations.add(new Organization(rs.getInt(1),OrganizationType.getOrganizationTypeObject(rs.getInt(2),null),rs.getString(3)));
			}
			connection.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return organizations;
	}

	//getter setters
	public void setOrganizationId(Integer organizationId){
		this.organizationId=organizationId;
	}
	public Integer getOrganizationId(){
		return organizationId;
	}

	public void setOrganizationType(OrganizationType organizationType){
		this.organizationType=organizationType;
	}
	public OrganizationType getOrganizationTypeId(){
		return organizationType;
	}

	public void setOrganization(String organization){
		this.organization=organization;
	}
	public String getOrganization(){
		return organization;
	}
}