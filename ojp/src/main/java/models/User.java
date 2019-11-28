package models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import utilities.Db;

public class User{

	//member variables
	private Integer		userId;
	private String		userName;
	private String		email;
	private String		password;
	private UserType	userType;
	public	static	Integer	keys;
	public	static	Integer keys1;
	public	static	Integer candidateOrCompanyId;

	//constructors
	public User(){
	}
	public User(String email,String password){
		//setEmail(email);
		//setPassword(password);	
		this.email		=	email;
		this.password	=	password;
	}
	public User(String userName,String email,String password,UserType userType){
		this(email,password);
		this.userName	=	userName;
		this.userType	=	userType;
	}
	public User(Integer userId,String userName,String email,String password,UserType userType){
		this(userName,email,password,userType);
		this.userId = userId;
	}

	//methods
	//@override 
	public String toString(){
		return "{'userId' : " + userId + ",'userName' : '" + userName + "','email' : '" + email + "','password' : '" + password + "','userType' : " + userType + "}";
	}




	public void checkIfUserDetailsIsNull(String userName,String email,String password){
		// User details
		if(userName == null || userName.equals("")){
			userName = getUserName();
			System.out.println("no new updation in userName :" + userName);
		}else {
			System.out.println("userName updated from " + getUserName() + " to " + userName);
		}
		
		if(email == null || email.equals("")){
			email = getEmail();
			System.out.println("no new updation in email :" + email);
		}else{
			System.out.println("email updated from " +  getEmail() + " to " + email );
		}

		if(password == null || password.equals("")){
			password = getPassword();
			System.out.println("no new updation in password :" + password);
		}else{
			System.out.println("password updated from " +  getPassword() + " to " + password);
		}
		System.out.println("new userName	:\t\t" + userName);
		System.out.println("new password	:\t\t" + password);
		System.out.println("new email		:\t\t" + email);
	}
	
	public void setUserDataIntoObject(Integer userId,String userName,String email,String password,UserType userType){
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}


	public Boolean updateUser(Integer userId,String userName,String email,String password,UserType userType){
		
		String uu = "inside updateUser()--->";
		//this.User(userId,userName,email,password,userType);
		//constructor call is not allowed from inside of a method?
		//this and super can only be written inside constructors

//		System.out.println(uu + this);
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "update users set user_name = ?,email = ?,password = ?,user_type_id = ? where user_id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,userName);
			ps.setString(2,email);
			ps.setString(3,password);
			ps.setInt(4,userType.getUserTypeId());
			ps.setInt(5,userId);
//			System.out.println(uu + "PreparedStatement : " + ps + "\n" + uu + "rows effected : " + ps.executeUpdate());

			setUserDataIntoObject(userId,userName,email,password,userType);
			flag = true;
			connection.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	public static User getUserObject(String userName,String email,User user){
		String guo = "inside getUserObject()--->";
		User userObject = null;
		
		if(userName == null && email == null && user == null)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select user_id,user_name,email,password,user_type_id from users where  user_name = ? || user_id = ? || email = ?";
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
//			if(userName != null)
				ps.setObject(++i,userName);

			if(user != null)
				ps.setObject(++i,user.getUserId());
			else
				ps.setObject(++i,null);

//			if(email != null)
				ps.setObject(++i,email);
			
//			System.out.println(guo + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
//				System.out.println(guo + (userObject = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),UserType.getUserTypeObject(rs.getInt(5),null))));
				userObject = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),UserType.getUserTypeObject(rs.getInt(5),null));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return userObject;
	}


	public static String checkEmail(String email){
		String userName = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "select user_name from users where email=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userName = rs.getString(1);
			}
			return userName;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();	
		}
		return userName;
	}




	public static User registerUser(String userName,String email,String password,UserType userType){
		boolean flag = false;
		User userObject = null;

		String ru = "inside registerUser()--->";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query = "insert into users(user_name,email,password,user_type_id) value (?,?,?,?)"; 
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,userName);
			ps.setString(2,email);
			ps.setString(3,password);
			ps.setInt(4,userType.getUserTypeId());

			System.out.println(ru + ps.executeUpdate());
			System.out.println(ru + "PreparedStatement : " + ps);

			ResultSet keys = ps.getGeneratedKeys();


			if(keys.next()){

				
				if(userType.getUserTypeId().equals(UserType.CANDIDATE)){
					userObject = new Candidate();
					query =	"insert into candidates(user_id) value (?)";
				}else{
					userObject = new Company();
					query = "insert into companies(user_id) value (?)";
				}	

				userObject.setUserId(keys.getInt(1));
				System.out.println(ru + "userId : " + userObject.getUserId());

				PreparedStatement ps1 = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				System.out.println(ru + "PreparedStatement : " + ps);

				ps1.setInt(1,userObject.getUserId());
				ps1.executeUpdate();

				ResultSet keys1 = ps1.getGeneratedKeys();
				System.out.println(ru + ps1);
			}
			connection.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return userObject;
	}





	public static User loginUser(String email,String password){
	
		User user = null;
		//boolean flag = false;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?useSSL=false&user=root&password=" + Db.dbpass);
			String query="select user_name,user_type_id,user_id from users where email=? && password=?";
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,email);
			ps.setString(2,password);
			System.out.println("inside loginUser() method :" + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				System.out.println("user_name = " + rs.getString(1));
				System.out.println("user_type_id = " + rs.getInt(2));
				System.out.println("user_id = " + rs.getInt(3));
				String userName = rs.getString(1);
				Integer userTypeId = rs.getInt(2);
				Integer userId = rs.getInt(3);


				UserType userType = new UserType();
				userType.setUserTypeId(userTypeId);

				//updating UserType object by fetching data from user_types
				String query1="select user_type from user_types where user_type_id = ?";
				PreparedStatement ps1 = connection.prepareStatement(query1);
				ps1.setInt(1,userTypeId);
				System.out.println("inside loginUser() method :" + ps);
				ResultSet rs1 = ps1.executeQuery();

				if(rs1.next()){
					userType.setUserType(rs1.getString(1));
				}				
				
				if(userTypeId == 1 ){
					//candidate has logged in
					Candidate candidate = Candidate.getCandidatePersonalDetailsIntoObject(userId);
					user = (User)candidate;
					System.out.println("candidate has logged in " + candidate);
				}else{
					//company has logged in
					user = (User)new Company();	
					System.out.println("company has logged in " + user);
				}

				user.setUserName(userName);
				user.setEmail(email);
				user.setPassword(password);
				user.setUserType(userType);
				user.setUserId(userId);

				
				/*	//alternative
				if(userTypeId == 1){
					//candidate has logged in
					Candidate candidate = new Candidate();
					candidate.setUserName(rs.getString(1));
					candidate.setEmail(email);
					candidate.setPassword(password);
					candidate.setUserType(userType);
					user = candidate;
				}else{
					//comapany has logged in
					Comapany company = new Company();
					company.setUserName(rs.getString(1));
					company.setEmail(email);
					company.setPassword(password);
					company.setUserType(userType);
					user = company;
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
		catch(NullPointerException e){
			e.printStackTrace();
		}

		return user;
	}











	//getter setter
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getUserId(){
		return userId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return userName;
	}

	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return email;
	}

	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	
	public void setUserType(UserType userType){
		this.userType = userType;
	}
	public UserType getUserType(){
		return userType;
	}
}

