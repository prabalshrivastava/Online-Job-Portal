package models;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.io.IOException;
import utilities.Db;
import models.UserType;
public class Candidate extends User{

	//properties or member variables

	private Integer	candidateId;
	private Date	dateOfBirth;
	private Boolean	gender;			//male is true and female is false
	private String	hobbies;
	private Boolean	maritalStatus;	//married is true unamrried is false		
	private Country	country;
	private String	intrest;
	private String	profilePicPath;
	private String	resumeFilePath;
	private Date	resumeUploadDate;
	private String	strengths;
	private String	weaknesses;
	private String	curricularActivity;
	private String	fatherName;
	private String	motherName;
	//private User	user; 
	//we are already accessing User using inheritance
	//constructors
	public Candidate(){
	}

	public Candidate(String email,String password){
		super(email,password);
	}

	public Candidate(String userName,String email,String password,UserType userType){
		super(userName,email,password,userType);
	}

	public Candidate(Integer candidateId,Date dateOfBirth,Boolean gender,Boolean maritalStatus,Country country,String profilePicPath,String fatherName,String motherName){
		setCandidateId(candidateId);
		setDateOfBirth(dateOfBirth);
		setGender(gender);
		setMaritalStatus(maritalStatus);
		setCountry(country);
		setProfilePicPath(profilePicPath);
		setFatherName(fatherName);
		setMotherName(motherName);
	}

	public Candidate(Integer candidateId){
		setCandidateId(candidateId);
	}



	//methods
	//@override 
	public String toString(){
		return "{'user' : " + super.toString() + " , 'candidateId' : " + candidateId + " , 'dateOfBirth' : '" + dateOfBirth + "' , 'gender' : '" + gender + "' , 'hobbies' : '" + hobbies + "' , 'maritalStatus' : '" + maritalStatus + "' , 'country' : " + country + " , 'intrest' : '" + intrest + "' , 'profilePicPath' : '" + profilePicPath + "' , 'resumeFilePath' : '" + resumeFilePath + "' , 'resumeUploadDate' : '" + resumeUploadDate + "' , 'strengths' : '" + strengths + "' , 'weaknesses' : '" + weaknesses + "' , 'curricularActivity' : '" + curricularActivity + "' , 'fatherName' : '" + fatherName + "' , 'motherName' : '" + motherName + "'}";
	}

	public static Candidate getCandidateObject(Candidate candidate,User user){
		String gco = "inside getCandidateObject()-->";
		String _candidate = "",_user = "";
		Boolean _c,_u;
		_c = _u = false;

		if(candidate != null){
			_candidate = " candidate_id = ? ";
			_c = true;
		}

		if(user != null){
			_user = " user_id = ? ";
			_u = true;

			if(_c)
				_user = " and " + _user;	//both candidate and user exists
			else
				candidate = new Candidate();	
				//this is the case when candidate was sent null and we only have userId
				//so we create a new Object and return it to the user
		}

		if(!_u && !_c)
			return null;


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select  candidate_id , date_of_birth , gender , hobbies ,user_id , marital_status , country_id , intrests, profile_pic_path,resume_file_path , resume_upload_date , strengths,weaknesses ,curricular_activity , father_name ,mother_name from candidates where " + _candidate + _user;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_c)
				ps.setInt(++i,candidate.getCandidateId());
			
			if(_u)
				ps.setInt(++i,user.getUserId());


//			System.out.println(gco + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				candidate.setCandidateId(rs.getInt(1));
				candidate.setDateOfBirth(rs.getDate(2));
				candidate.setGender(rs.getBoolean(3));
				candidate.setHobbies(rs.getString(4));
				candidate.setUserId(rs.getInt(5));
				candidate.setMaritalStatus(rs.getBoolean(6));
				candidate.setCountry(Country.getCountryObject(rs.getInt(7),null));
				candidate.setIntrest(rs.getString(8));
				candidate.setProfilePicPath(rs.getString(9));
				candidate.setResumeFilePath(rs.getString(10));
				candidate.setResumeUploadDate(rs.getDate(11));
				candidate.setStrengths(rs.getString(12));
				candidate.setWeaknesses(rs.getString(13));
				candidate.setCurricularActivity(rs.getString(14));
				candidate.setFatherName(rs.getString(15));
				candidate.setMotherName(rs.getString(16));

				User userObject = User.getUserObject(null,null,new User(rs.getInt(5),null,null,null,null));
				candidate.setUserId(userObject.getUserId());
				candidate.setUserName(userObject.getUserName());
				candidate.setEmail(userObject.getEmail());
				candidate.setPassword(userObject.getPassword());
				candidate.setUserType(userObject.getUserType());

//				System.out.println("full fledged candidate Object : " + candidate);
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return candidate;
	}

	public Candidate updateOtherDetails(String strengths,String weaknesses,String hobbies,String curricularActivities,String intrest,String resumeFilePath){

		String uod = "inside updateOtherDetails()--->";
		String _strengths = "",_weaknesses = "",_hobbies = "";
		String _curricularActivities = "",_intrest = "",_resumeFilePath = "";
		Boolean _s , _w , _h , _ca , _i , _rfp;
		_s = _w = _h = _ca = _i = _rfp = false;


		if(strengths != null){
			_strengths = " strengths = ? ";
			_s = true;
			this.strengths = strengths;
		}

		if(weaknesses != null){
			_weaknesses = " weaknesses = ? ";
			_w = true;

			if(_s)
				_weaknesses = "," + _weaknesses;

			this.weaknesses = weaknesses;
		}
	
		if(hobbies != null){
			_hobbies = " hobbies = ? ";
			_h = true;

			if(_s || _w)
				_hobbies = "," + _hobbies;

			this.hobbies = hobbies;
		}

		if(curricularActivities != null){
			_curricularActivities = "  curricular_activity = ? ";
			_ca =  true;

			if(_s || _w || _ca)
				_curricularActivities = "," + _curricularActivities		;

			this.curricularActivity = curricularActivities;
		}

		if(intrest != null){
			_intrest = " intrests = ? ";
			_i = true;

			if(_s || _w || _ca || _i)
				_intrest = "," + _intrest;

			this.intrest = intrest;
		}
		if(resumeFilePath != null){
			_resumeFilePath = " resume_file_path = ? , resume_upload_date = ? ";
			_rfp = true;
			if(_s || _w || _ca || _i)
				_resumeFilePath = "," + _resumeFilePath;
			this.resumeFilePath = resumeFilePath;
			this.resumeUploadDate = new java.sql.Date(new java.util.Date().getTime());
		}
		
		if(!_s && !_w && !_ca && !_i && !_rfp)
			return this;
		
		try{
			//com.jdbc.mysql.Driver.class;
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "update candidates set " + _strengths + _weaknesses + _hobbies + _curricularActivities + _intrest + _resumeFilePath + " where candidate_id = ?";
			System.out.println(uod + query);
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			
			if(_s)
				ps.setObject(++i,strengths);
			
			if(_w)
				ps.setObject(++i,weaknesses);

			if(_h)
				ps.setObject(++i,hobbies);

			if(_ca)
				ps.setObject(++i,curricularActivities);

			if(_i)
				ps.setObject(++i,intrest);

			if(_rfp){
				ps.setObject(++i,resumeFilePath);
				ps.setObject(++i,this.resumeUploadDate);
			}

			ps.setInt(++i,this.candidateId);
				
			System.out.println(uod + "PreparedStatement : " + ps);
			System.out.println(uod + "RowsEffected : " + ps.executeUpdate());
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



	public Boolean updatePersonalDetails(Integer userId,String userName,String email,String password,String fatherName,String motherName,String profilePicPath,String country,Date dateOfBirth,Boolean gender,Boolean maritalStatus){
		System.out.println("inside candidate\t\tupdatePersonalDetails :\t\t"+this);
		Boolean flag = false,flag1=false;
		if(email == null && password == null && userName == null){
			System.out.println("no new data to update in users table");
		}else{
			//checkIfUserDetailsIsNull(userName,email,password);
			// User details
			if(userName == null || userName.equals("")){
				userName = getUserName();
			}else {
				System.out.println("userName updated from " + getUserName()+" to "+userName);
			}
			
			if(email == null || email.equals("")){
				email = getEmail();
			}else{
				System.out.println("email updated from "+ getEmail()+" to "+email);
			}

			if(password == null || password.equals("")){
				password = getPassword();
			}else{
				System.out.println("password updated from " + getPassword() + " to " +password);
			}
			System.out.println("new userName :\t\t"+userName);
			System.out.println("new password :\t\t"+password);
			System.out.println("new email :\t\t"+email);
			flag1 = updateUser(userId,userName,email,password,getUserType());
			//flag = super(userId,userName,email,password,getUserType());
			//because constructor was not allowed to be called from inside the method of the same class
			//(in this case User) we are calling the constructor here
			//and that too is not allowed :( 
		}
		if(fatherName == null && motherName == null && profilePicPath == null && country == null &&  dateOfBirth == null && gender == null && maritalStatus ==null){
			System.out.println("no new data to be updated in candidates table");
		}else{
			try{
				if(fatherName == null || fatherName.equals("")){
					fatherName = this.fatherName;
				}else{
					System.out.println("fatherName updated from "+this.fatherName+" to "+fatherName);
				}

				if(motherName == null || motherName.equals("")){
					motherName = this.motherName;
				}else{
					System.out.println("motherName updated from "+this.motherName+" to "+motherName);
				}

				if(profilePicPath == null || profilePicPath.equals("")){
					profilePicPath = this.profilePicPath;
				}else{
					System.out.println("profilePicPath updated from "+this.profilePicPath+" to "+profilePicPath);
				}

				if(country == null || country.equals("")){
					country = this.country.getCountry();
				}else{
					if(this.country == null || this.country.getCountry()==null){
						System.out.println("country updated from null to "+country);
	//					System.out.println("country updated from "+this.country == null ? " null ": (this.country.getCountry() == null ? " null " :this.country.getCountry())+" to "+ country);
					}else{
						System.out.println("country updated from " + this.country + " to "+country);
					}
				}

				if(dateOfBirth == null){
					dateOfBirth = this.dateOfBirth;
				}else{
					System.out.println("dateOfBirth updated from " + this.dateOfBirth+" to "+dateOfBirth);
				}

				if(gender == null){
					gender = this.gender;
				}else{
					System.out.println("gender updated from "+this.gender +" to "+gender);
				}

				if(maritalStatus ==  null){
					maritalStatus = this.maritalStatus;
				}else{
					System.out.println("maritalStatus updated from "+this.maritalStatus+" to "+maritalStatus);
				}




				Class.forName("com.mysql.jdbc.Driver");
				Connection	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
				int i = 3 ;
				String	query = "update candidates set father_name = ? , mother_name = ? , profile_pic_path = ? , country_id = ?, date_of_birth =?, gender =?, marital_status =? where user_id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				System.out.println("Query :\t\t"+query);
				
				if(fatherName == null || fatherName.equals(""))
					ps.setNull(1,Types.VARCHAR);
				else
					ps.setString(1,fatherName);					
				
				if(motherName == null || motherName.equals(""))
					ps.setNull(2,Types.VARCHAR);
				else
				 	ps.setString(2,motherName);
				
				
				if(profilePicPath == null || profilePicPath.equals("") )
					ps.setNull(3,Types.VARCHAR);
				else
					ps.setString(3,profilePicPath);
				
				Integer countryId = Country.getCountryObject(null,country).getCountryId();
				if(countryId == null) 
					ps.setNull(4,Types.INTEGER);
				else
					ps.setInt(4,countryId);

				if(dateOfBirth == null)
					ps.setNull(5,Types.DATE);
				else
					ps.setDate(5,dateOfBirth);
				
				if(gender == null)
					ps.setNull(6,Types.BOOLEAN);	
				else
					ps.setObject(6,gender);

				if(maritalStatus == null)
					ps.setNull(7,Types.BOOLEAN);
				else
					ps.setObject(7,maritalStatus);

				ps.setInt(8,getUserId());

				System.out.println("preparedStatement :\t\t"+ps);				
				ps.executeUpdate();	
				flag = true;
				connection.close();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return flag && flag1;
	}


	public static Candidate getCandidatePersonalDetailsIntoObject(Integer userId){
		Candidate candidate = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select candidate_id,date_of_birth,gender,marital_status,country_id,profile_pic_path,father_name,mother_name from candidates where user_id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,userId);
			System.out.println("setting data retrieved into object "+ ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				candidate = new Candidate(rs.getInt(1),rs.getDate(2),(Boolean)rs.getObject(3),(Boolean)rs.getObject(4),Country.getCountryObject(rs.getInt(5),null),rs.getString(6),rs.getString(7),rs.getString(8));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return candidate;
	}

	

	//getter setters
	public void setCandidateId(Integer candidateId){
		this.candidateId = candidateId;
	}
	public Integer getCandidateId(){
		return candidateId;
	}

	public void setDateOfBirth(Date dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfBirth(){
		return dateOfBirth;
	}

	public void setGender(Boolean gender){
		this.gender = gender;
	}
	public Boolean getGender(){
		return gender;
	}

	public void setHobbies(String hobbies){
		this.hobbies = hobbies;
	}
	public String getHobbies(){
		return hobbies;
	}
	public void setMaritalStatus(Boolean maritalStatus){
		this.maritalStatus = maritalStatus;
	}
	public Boolean getMaritalStatus(){
		return maritalStatus;
	}

	public void setCountry(Country country){
		this.country = country;
	}
	public Country getCountry(){
		return country;
	}

	public void setIntrest(String intrest){
		this.intrest = intrest;
	}
	public String getIntrest(){
 		return intrest;
	}

	public void setProfilePicPath(String profilePicPath){
		this.profilePicPath = profilePicPath;
	}
	public String getProfilePicPath(){
		return profilePicPath;
	}

	public void setResumeFilePath(String resumeFilePath){
		this.resumeFilePath = resumeFilePath;
	}
	public String getResumeFilePath(){
		return resumeFilePath;
	}
	
	public void setResumeUploadDate(Date resumeUploadDate){
		this.resumeUploadDate = resumeUploadDate;
	}
	public Date getResumeUploadDate(){
		return resumeUploadDate;
	}

	public void setStrengths(String strengths){
		this.strengths = strengths;
	}
	public String getStrengths(){
		return strengths;
	}

	public void setWeaknesses(String weaknesses){
		this.weaknesses = weaknesses;
	}
	public String getWeaknesses(){
		return weaknesses;
	}

	public void setCurricularActivity(String curricularActivity){
		this.curricularActivity = curricularActivity;
	}
	public String getCurricularActivity(){
		return curricularActivity;
	}

	public void setFatherName(String fatherName){
		this.fatherName = fatherName;
	}
	public String getFatherName(){
		return fatherName;
	}

	public void setMotherName(String motherName){
		this.motherName = motherName;
	}
	public String getMotherName(){
		return motherName;
	}

}