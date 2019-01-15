package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import models.User;
import utilities.Db;
import java.util.ArrayList;

public class Company extends User{

	//members variables
	private	Integer			companyId;
	private String			logoPath;
	private String			website;
	private IndustryType	industryType;
	private String			mission;
	private String			vision;
	private String			aboutUs;
	private String			history;
	private String			awards;
	//private User			user;
	//because we access User using inheritance we have removed its instance

	//constructors
	public Company(){
	}
	public Company(Integer companyId){
		setCompanyId(companyId);
	}
	public Company(String email,String password){
		super(email,password);
	}
	public Company(String userName,String email,String password,UserType userType){
		super(userName,email,password,userType);
		System.out.println("comapnyId = "+companyId);
	}
	public Company(Integer companyId,String logoPath,String website,IndustryType industryType,String mission,String vision,String aboutUs,String history,String awards){
		setCompanyId(companyId);
		setLogoPath(logoPath);
		setWebsite(website);
		setIndustryType(industryType);
		setMission(mission);
		setVision(vision);
		setAboutUs(aboutUs);
		setHistory(history);
		setAwards(awards);
	}



	//methods
	public String toString(){
		String user = "'userId' : " + getUserId() + " , 'userName' : '" + getUserName() + "' , 'email' : '" + getEmail() + "' , 'password' : '" + getPassword() + "' , 'userType' : " + getUserType() + "'";
		String company = "'companyId' : " + companyId + " , 'logoPath' : '" +logoPath + "' , 'website' : '" + website + "' , 'industryType' : " + industryType + " , 'mission' : '" + mission + "' , 'vision' : '" + vision + "' , 'aboutUs' : '" + aboutUs + "' , 'history' : '" + history + "' , 'awards' : '" + awards + "'";
		return "{'company' : '" + super.getUserName() + "' , 'user' : " + super.toString() + " , " + company + "}";
	}

	public Company updateCompanyProfile(String logoPath,String website,String mission,String vision,String history,String awards,String aboutUs,IndustryType industryType){
		String ucp = "inside updateCompanyProfile--->";
		String _logoPath = "" ,_website = "" , _mission = "" , _vision = "" , _history = "" , _awards = "" , _aboutUs = "" , _industryType = "";
		Boolean _lp , _w , _m , _v , _h , _a , _au, _it;
		_lp = _w = _m = _v = _h = _a = _au = _it = false;
		
		if(logoPath != null){
			_logoPath = " logo_path = ? ";
			_lp = true;
		}
		if(website != null){
			_website = " website = ? " ;
			_w = true;
			if(_lp)
				_website = " , " + _website; 
		}
		if(mission != null){
			_mission = " mission = ? ";
			_m = true;
			if(_lp || _w)
				_mission = " , " + _mission ;
		}
		if(vision != null){
			_vision = " vision = ? ";
			_v = true;
			if(_lp || _w || _m)
				_vision = " , " + _vision;
		}
		if(history != null){
			_history = " history = ? ";
			_h = true;
			if(_lp || _w || _m || _v)
				_history = " , " + _history;
		}
		if(awards != null){
			_awards = " awards = ? ";
			_a = true;
			if(_lp || _w || _m || _v || _h)
				_awards = " , " + _awards;
		}
		if(aboutUs != null){
			_aboutUs = " about_us = ? ";
			_au = true;
			if(_lp || _w || _m || _v || _h || _a)
				_aboutUs = " , " + _aboutUs;
		}
		if(industryType != null){
			_industryType = " industry_type_id = ? ";
			_it = true;
			if(_lp || _w || _m || _v || _h || _a || _au)
				_industryType = " , " + _industryType;
		}
		
		if(!_lp && !_w && !_m && !_v && !_h && !_a && !_au && !_it)
			return null;


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "update companies set " + _logoPath + _website + _mission + _vision + _history + _awards + _aboutUs + _industryType +  " where company_id = ? || user_id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_lp)
				ps.setObject(++i,logoPath);
			if(_w)
				ps.setObject(++i,website);
			if(_m)
				ps.setObject(++i,mission);
			if(_v)
				ps.setObject(++i,vision);
			if(_h)
				ps.setObject(++i,history);
			if(_a)
				ps.setObject(++i,awards);
			if(_au)
				ps.setObject(++i,aboutUs);
			if(_it)
				ps.setObject(++i,industryType.getIndustryTypeId());

			ps.setObject(++i,this.companyId);
			ps.setObject(++i,this.getUserId());

			System.out.println(ucp + "PreparedStatement : " + ps);
			System.out.println(ucp + "Rows Effected : " + ps.executeUpdate());
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return this;
	}
	


	public static Company getCompanyObject(Company company,User user){
		String gco = "inside getCompanyObject()--->";
		//Company companyObject = null;
		User userObject = null;

		if(company == null && user == null)
			return null;
			
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select company_id,logo_path,website,industry_type_id,mission,vision,about_us,history,awards,user_id from companies where company_id = ? || user_id = ?";
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(company != null)
				ps.setObject(++i,company.getCompanyId());
			else
				ps.setObject(++i,null);

			if(user != null)
				ps.setObject(++i,user.getUserId());
			else if(company != null)
				ps.setObject(++i,company.getUserId());
			else
				ps.setObject(++i,null);


//			System.out.println(gco + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				/*
				System.out.println(gco + "Incomplete Company Object : " + (company = new Company(rs.getInt(1),rs.getString(2),rs.getString(3),IndustryType.getIndustryTypeObject(rs.getInt(4),null),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9))));
				System.out.println(gco + "User Object : " + (userObject = User.getUserObject(null,null,new User(rs.getInt(10),null,null,null,null))));
				*/
				company = new Company(rs.getInt(1),rs.getString(2),rs.getString(3),IndustryType.getIndustryTypeObject(rs.getInt(4),null),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
				userObject = User.getUserObject(null,null,new User(rs.getInt(10),null,null,null,null));

				company.setUserId(userObject.getUserId());
				company.setUserName(userObject.getUserName());
				company.setEmail(userObject.getEmail());
				company.setPassword(userObject.getPassword());
				company.setUserType(userObject.getUserType());
				/*
				company.setCompanyId(rs.getInt(1));      
				company.setLogoPath(rs.getString(2));        
				company.setWebsite(rs.getString(3));          
				company.setIndustryType(IndustryType.getIndustryTypeObject(rs.getInt(4),null));
				company.setMission(rs.getString(5));          
				company.setVision(rs.getString(6));            
				company.setAboutUs(rs.getString(7));          
				company.setHistory(rs.getString(8));          				
				company.setAwards(rs.getString(9));            			
				*/

//				System.out.println(gco + "full fledged Company Object : " + company);
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return company;
	}
	
	
	


	public static ArrayList<Company> collectCompanies(){
		String cc = "inside collectCompanies()--->";
		ArrayList<Company> companies = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			//company_id,logo_path,website,industry_type_id,mission,vision,about_us,history,awards,user_id
			String query = "select company_id from companies";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println(cc + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			companies = new ArrayList<Company>();
			while(rs.next()){
				companies.add(Company.getCompanyObject(new Company(rs.getInt(1)),null));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(cc + "companies : " + companies);
		return companies;
	}
	
	//getter setters
	public void setCompanyId(Integer companyId){
		this.companyId = companyId;
	}
	public Integer getCompanyId(){
		return companyId;
	}

	public void setLogoPath(String logoPath){
		this.logoPath = logoPath;
	}
	public String getLogoPath(){
		return logoPath;
	}

	public void setWebsite(String website){
		this.website = website;
	}
	public String getWebsite(){
		return website;
	}

	public void setIndustryType(IndustryType industryType){
		this.industryType = industryType;
	}
	public IndustryType getIndustryType(){
		return industryType;
	}

	public void setMission(String mission){
		this.mission = mission;
	}
	public String getMission(){
		return mission;
	}

	public void setVision(String vision){
		this.vision = vision;
	}
	public String getVision()	{
		return vision;
	}
	
	public void setAboutUs(String aboutUs){
		this.aboutUs = aboutUs;
	}
	public String getAboutUs(){
		return aboutUs;
	}

	public void setHistory(String history){
		this.history = history;
	}
	public String getHistory(){
		return history;
	}

	public void setAwards(String awards){
		this.awards = awards;
	}
	public String getAwards(){
		return awards;
	}
		
	/*
	public void setUser(User user){
		this.user = user;
	}
	public User getUser(){
		return user;
	}
	*/
}