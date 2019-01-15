package models;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import utilities.Db;

public class Language{
	//member variables
	private Integer languageId;
	private	String	language;

	//constructor
	public Language(Integer languageId,String language){
		setLanguageId(languageId);
		setLanguage(language);
	}
	
	//methods
	//@override toString()
	public String toString(){
		return "{'languageId' : " + languageId + " , 'language' : '" + language + "'}";
	}
	



	public static Language getLanguageObject(Integer languageId,String language){
		String glo = "inside getLanguageObject()-->";
		Language languageObject = null;
		String _languageId = "" , _language = "";
		Boolean _li,_l;
		_li = _l = false;
		
		if(languageId != null){
			_languageId = "language_id = ? " ;
			_li = true;
		}

		if(language != null){
			_language = "language = ? ";
			_l = true;

			if(_li)
				_language = " and " + _language;
		}

		if(!_li && !_l)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select language_id,language from languages where " + _languageId + _language;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_li)
				ps.setInt(++i,languageId);

			if(_l)
				ps.setString(++i,language);


			//System.out.println(glo + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				languageObject = new Language(rs.getInt(1),rs.getString(2));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return languageObject;
	}

	public static ArrayList<Language> collectAllLanguage(){
		String cal = "inside collectAllLanguage()-->";
		ArrayList<Language> languages = new ArrayList<Language>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select language_id,language from languages";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(cal + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				languages.add(new Language(rs.getInt(1),rs.getString(2)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return languages;
	}
	
	//getter setters
	public void setLanguageId(Integer languageId){
		this.languageId=languageId;
	}
	public Integer getLanguageId(){
		return languageId;
	}

	public void setLanguage(String language){
		this.language=language;
	}
	public String getLanguage(){
		return language;
	}
}