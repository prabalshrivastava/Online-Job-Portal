package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import utilities.Db;



public class IndustryType{
	//member variables
	private	Integer		industryTypeId;
	private	String		industryType;
	


	//constructors
	public IndustryType(Integer industryTypeId,String industryType){
		setIndustryTypeId(industryTypeId);
		setIndustryType(industryType);
	}


	//methods
	//@override toString()
	public String toString(){
		return " {'industryTypeId' : " + industryTypeId + " , 'industryType' : '" + industryType + "'} ";
	}
	


	public static IndustryType getIndustryTypeObject(Integer industryTypeId , String industryType){
		
		IndustryType industryTypeObject = null;
		String gito = "inside getIndustryTypeObject-->";
		String where = " where ",and = " ",industry_type = "", industry_type_id = "";
		
		if(industryTypeId == null && industryType == null){
			where = "";
			return null;
		}else if(industryTypeId != null && industryType != null){
			and = " and ";
		}
		

		if(industryTypeId != null){
			industry_type_id = " industry_type_id = "+industryTypeId;
		}

		if(industryType != null){
			industry_type = " industry_type = '"+industryType + "'";
		}
		/*
		System.out.println(gito + "industryTypeId : " + industryTypeId);
		System.out.println(gito + "industryType : " + industryType);
		System.out.println(gito + "where : " + where);
		System.out.println(gito + "and : " + and);
		*/
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select industry_type_id,industry_type from industry_types "+where+industry_type_id+and+industry_type;
			PreparedStatement ps = connection.prepareStatement(query);
			//System.out.println(gito+"preparedStatement : "+ps+"\n");
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				industryTypeObject = new IndustryType(rs.getInt(1),rs.getString(2));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return industryTypeObject;
	}




	public static ArrayList<IndustryType> collectAllIndustryType(){
		ArrayList<IndustryType> industryTypes = new ArrayList<IndustryType>();
		String cait = "inside collectAllIndustryType()-->";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select industry_type_id,industry_type from industry_types";
			PreparedStatement ps = connection.prepareStatement(query);
	
			System.out.println(cait+"preparedStatement : "+ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				industryTypes.add(new IndustryType(rs.getInt(1),rs.getString(2)));
				//System.out.println(cait + "industryTypeId : "+rs.getInt(1)+"\tindustryType : "+rs.getString(2));
			}

			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return industryTypes;
	}


	

	//getter setters
	public void setIndustryTypeId(Integer industryTypeId){
		this.industryTypeId = industryTypeId;
	}
	public Integer getIndustryTypeId(){
		return industryTypeId;
	}
	
	public void setIndustryType(String industryType){
		this.industryType=industryType;
	}
	public String getIndustryType(){
		return industryType;
	}
}