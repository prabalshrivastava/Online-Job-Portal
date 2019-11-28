package models;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Types;

import java.io.IOException;
import utilities.Db;
import java.util.ArrayList;

public class Country{
	//member variables
	private Integer countryId;
	private String	country;
	
	//constructors
	public Country(Integer countryId,String country){
		this.countryId = countryId;
		this.country = country;
	}


	//methods
	//@override
	public String toString(){
		return "{'countryId' : " + countryId + " , 'country' : '" + country + "'} ";
	}

	public static Integer getCountryId(String country){
		Integer countryId = null;
		return countryId;
	}
	public static ArrayList collectAllCountries(){
		//this is for call inside the listeners
		ArrayList<Country> countries = new ArrayList<Country>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select country_id,country from countries";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			//System.out.print("Countries List :");
			while(rs.next()){
				countries.add(new Country(rs.getInt(1),rs.getString(2)));
				//System.out.print("\t:"+rs.getString(1));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}

		return countries;
	}



	public static Country getCountryObject(Integer countryId,String country){

		String gco = "inside getCountryObject()-->";
		String _country = "",_countryId = "";
		Boolean _c = false;
		Country countryObject = null;

//		System.out.print(gco+"country : "+country);
//		System.out.println("\t\tcountryId : "+countryId);
	

		if(country != null){
			_country = "country = ? ";
			_c = true;
		}

		if(countryId != null){
			_countryId = "country_id = ? ";

			if(_c)
				_countryId = " and " + _countryId;
		}else{
			if(!_c)
				return countryObject;
		}


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select country_id,country from countries where "+ _country + _countryId;
			//System.out.println("query for country \t\t"+query);
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(country != null)
				ps.setString(++i,country);

			if(countryId != null)
				ps.setInt(++i,countryId);

//			System.out.println(gco+"PreparedStatement :"+ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				countryObject = new Country(rs.getInt(1),rs.getString(2));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
//		System.out.println(gco+countryObject);
		return countryObject;	
	}
	

	//getter setters
	
	public void setCountryId(Integer countryId){
		this.countryId=countryId;
	}
	public Integer getCountryId(){
		return countryId;
	}

	public void setCountry(String country){
		this.country=country;
	}
	public String getCountry(){
		return country;
	}
}
