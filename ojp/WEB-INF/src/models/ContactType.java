package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.util.ArrayList;
import utilities.Db;

public class ContactType{

	//member variables
	private Integer	contactTypeId;
	private String	contactType;

	//final constant variables
	/*
		public static final Integer PERMANENT_ADDRESS = 1;
		public static final Integer LOCAL_ADDRESS = 2;
		public static final Integer MOBILE_NUMBER = 3; 
		public static final Integer ALTERNATE_MOBILE_NUMBER = 4;
		public static final Integer	LANDLINE_NUMBER = 5;
		public static final Integer ALTERNATE_LANDLINE_NUMBER = 6;
		public static final Integer	EMAIL = 7;
		public static final Integer ALTERNATE_EMAIL = 8;
	*/
	public static final Integer PERMANENT_ADDRESS = ContactType.getContactTypeObject(null,"Permanent Address").getContactTypeId();
	public static final Integer LOCAL_ADDRESS  = ContactType.getContactTypeObject(null,"Local Address").getContactTypeId();
	public static final Integer MOBILE_NUMBER  = ContactType.getContactTypeObject(null,"Mobile Number").getContactTypeId();
	public static final Integer ALTERNATE_MOBILE_NUMBER= ContactType.getContactTypeObject(null,"Alternate Mobile Number").getContactTypeId(); 
	public static final Integer	LANDLINE_NUMBER  = ContactType.getContactTypeObject(null,"Landline Number").getContactTypeId();
	public static final Integer ALTERNATE_LANDLINE_NUMBER  = ContactType.getContactTypeObject(null,"Alternate Landline Number").getContactTypeId();
	public static final Integer EMAIL	= ContactType.getContactTypeObject(null,"Email").getContactTypeId();
	public static final Integer	ALTERNATE_EMAIL = ContactType.getContactTypeObject(null,"Alternate Email").getContactTypeId();



	static{
		System.out.println("\n\n\n");
		System.out.println("PERMANENT_ADDRESS : " + PERMANENT_ADDRESS);
		System.out.println("LOCAL_ADDRESS : " + LOCAL_ADDRESS);
		System.out.println("MOBILE_NUMBER : " + MOBILE_NUMBER);
		System.out.println("ALTERNATE_MOBILE_NUMBER : " + ALTERNATE_MOBILE_NUMBER);
		System.out.println("LANDLINE_NUMBER : " + LANDLINE_NUMBER);
		System.out.println("ALTERNATE_LANDLINE_NUMBER : " + ALTERNATE_LANDLINE_NUMBER);
		System.out.println("EMAIL : " + EMAIL);
		System.out.println("ALTERNATE_EMAIL : " + ALTERNATE_EMAIL);
		System.out.println("\n\n\n");
	}








	//constructors
	public ContactType(Integer contactTypeId,String contactType){
		setContactTypeId(contactTypeId);
		setContactType(contactType);
	}
	


	//methods()
	//@override toString()
	public String toString(){
		return "{'contactTypeId' : " + contactTypeId + " , contactType : '" + contactType + "'}";
	}

	//method can also be used to collect all constants
	public static ArrayList collectAllContactTypes(){
		
		ArrayList contactTypes = new ArrayList();
		String iactc = "inside initAllContactTypeConstacts()-->";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select contact_type_id,contact_type from contact_types";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println("PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();


			while(rs.next()){
				contactTypes.add(new ContactType(rs.getInt(1),rs.getString(2)));


				/*
				String contactType = rs.getString(2).trim();
				switch(contactType){
					case "Alternate Landline Number" :	
						ALTERNATE_LANDLINE_NUMBER = rs.getInt(1);
						break;
					case "Alternate Email" :
						ALTERNATE_EMAIL = rs.getInt(1);
						break;
					case "Alternate Mobile Number" : break;
						ALTERNATE_MOBILE_NUMBER = rs.getInt(1);
					case "Email" :
						EMAIL = rs.getInt(1);
						break;
					case "Landline Number" :
						LANDLINE_NUMBER = rs.getInt(1);
						break;
					case "Local Address" :
						LOCAL_ADDRESS = rs.getInt(1);
						break;
					case "Mobile Number" :
						MOBILE_NUMBER = rs.getInt(1);
						break;
					case "Permanent Address" :
						PERMANENT_ADDRESS = rs.getInt(1);
						break;
				}
				*/
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return contactTypes;
	}


	public static ContactType getContactTypeObject(Integer contactTypeId , String contactType){
		
		String gcto = "inside getContactTypeObject()-->";
		String contact_type = "";
		String contact_type_id = "";
		String and = " ", where = " where ";
		ContactType contactTypeObject = null;

		if(contactType != null && contactTypeId != null){
			and = " and ";
		}else if(contactTypeId == null && contactType == null){
			return contactTypeObject;
		}

		if(contactTypeId != null)
			contact_type_id = " contact_type_id = " + contactTypeId;

		if(contactType != null)
			contact_type = " contact_type = '" + contactType + "'";

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select contact_type_id,contact_type from contact_types " + where + contact_type_id + and + contact_type;
			PreparedStatement ps = connection.prepareStatement(query);
//			System.out.println(gcto + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
//				System.out.println(contactTypeObject = new ContactType(rs.getInt(1),rs.getString(2)));
				contactTypeObject = new ContactType(rs.getInt(1),rs.getString(2));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return contactTypeObject;
	}
	

	//getter setters
	public void setContactTypeId(Integer contactTypeId){
		this.contactTypeId=contactTypeId;
	}
	public Integer getContactTypeId(){
		return contactTypeId;
	}

	public void setContactType(String contactType){
		this.contactType=contactType;
	}
	public String setContactType(){
		return contactType;
	}
}