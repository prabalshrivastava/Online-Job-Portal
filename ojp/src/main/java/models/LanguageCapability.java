package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utilities.Db;

public class LanguageCapability{
	//member variables
	private Integer languageCapabilityId;
	private String	languageCapability;

	//CONSTANTS
	public static final Integer READ = getLanguageCapabilityObject(null,"read").getLanguageCapabilityId();
	public static final Integer WRITE = getLanguageCapabilityObject(null,"write").getLanguageCapabilityId();
	public static final Integer SPEAK = getLanguageCapabilityObject(null,"speak").getLanguageCapabilityId();
	public static final Integer READ_WRITE = getLanguageCapabilityObject(null,"read/write").getLanguageCapabilityId();
	public static final Integer READ_SPEAK = getLanguageCapabilityObject(null,"read/speak").getLanguageCapabilityId();
	public static final Integer WRITE_SPEAK = getLanguageCapabilityObject(null,"write/speak").getLanguageCapabilityId();
	public static final Integer READ_WRITE_SPEAK =	getLanguageCapabilityObject(null,"read/write/speak").getLanguageCapabilityId();

	/*
		public static final Integer READ = 16;
		public static final Integer WRITE = 17;
		public static final Integer SPEAK = 18;
		public static final Integer READ_WRITE = 19;
		public static final Integer READ_SPEAK = 20;
		public static final Integer WRITE_SPAEK = 21;
		public static final Integer READ_WRITE_SPEAK =	22;
	*/

	static{
		System.out.println("\n\nREAD : " + READ);
		System.out.println("WRITE : " + WRITE);
		System.out.println("SPEAK : " + SPEAK);
		System.out.println("READ_WRITE : " + READ_WRITE);
		System.out.println("READ_SPEAK : " + READ_SPEAK);
		System.out.println("WRITE_SPEAK : " + WRITE_SPEAK);
		System.out.println("READ_WRITE_SPEAK : " + READ_WRITE_SPEAK + "\n\n");
	}

	//constructors
	public LanguageCapability(Integer languageCapabilityId,String languageCapability){
		setLanguageCapabilityId(languageCapabilityId);
		setLanguageCapability(languageCapability);
	}

	//methods()
	//@override toString()
	public String toString(){
		return "{'languageCapabilityId' : " + languageCapabilityId + " , 'languageCapability' : '" + languageCapability + "'}";
	}
	


	public static LanguageCapability getLanguageCapabilityObject(Integer languageCapabilityId,String languageCapability){
		LanguageCapability languageCapabilityObject = null;	
		String glco = "inside getLanguageCapabilityObject()-->";
		String _languageCapabilityId = "";
		String _languageCapability = "";

		Boolean _lci = false;
		Boolean _lc = false;

		if(languageCapabilityId != null){
			_languageCapabilityId = " language_capability_id = ? ";

			_lci = true;
		}
		if(languageCapability != null){
			_languageCapability = " language_capability = ? ";

			if(_lci)
				_languageCapability = " and " + _languageCapability;

			_lc = true;
		}
		if(!_lci && !_lc)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select language_capability_id,language_capability from language_capabilities where " + _languageCapabilityId + _languageCapability ;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_lci)
				ps.setInt(++i,languageCapabilityId);
			
			if(_lc)
				ps.setString(++i,languageCapability);

			//System.out.println(glco + "PreparedStatement : " + ps);

			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				languageCapabilityObject = new LanguageCapability(rs.getInt(1),rs.getString(2));
			}

			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return languageCapabilityObject;
	}


	//getter setters
	public void setLanguageCapabilityId(Integer languageCapabilityId){
		this.languageCapabilityId=languageCapabilityId;
	}
	public Integer getLanguageCapabilityId(){
		return languageCapabilityId;
	}

	public void setLanguageCapability(String languageCapability){
		this.languageCapability=languageCapability;
	}
	public String getLanguageCapability(){
		return languageCapability;
	}
}