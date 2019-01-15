package models;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.ArrayList;
import utilities.Db;



public class UserAddress{

	//member variables
	private	Integer		userAddressId;
	private	User		user;
	private	ContactType	contactType;
	private	PinCode		pinCode;
	private	String		address;

	//constructors
	public UserAddress(){
	}

	public UserAddress(Integer userAddressId , User user , ContactType contactType , PinCode pinCode , String address){
		setUserAddressId(userAddressId);
		setUser(user);
		setContactType(contactType);
		setPinCode(pinCode);
		setAddress(address);
	}

	//methods()
	//@override toString()
	public String toString(){
		//return "{'userAddressId' : '" + userAddressId + "' , 'userId' : '" + user.getUserId() + "' , 'contactTypeId' : '" + contactType.getContactTypeId() + "' , 'pinCodeId' : '" + pinCode.getPinCodeId() + "' , 'address' : '" + address + "'}'"	;
		return "{'userAddressId' : '" + userAddressId + "' , 'user' : '" + user + "' , 'contactType' : " + contactType + " , 'pinCode' : " + pinCode + " , 'address' : '" + address + "'}'"	;
	}	

	public static UserAddress getUserAddressObject(Integer userAddressId , User user , ContactType contactType , PinCode pinCode , String address){
		String guao = "inside getUserAddressObject()-->";
		UserAddress userAddressObject = null;
		String _userAddressId = "" , _user = "" ,  _contactType = "" , _pinCode = "" , _address = "";
		Boolean _uai = false , _u = false , _ct = false ,  _pc = false , _a = false;

		if(userAddressId !=  null){
			_userAddressId = "user_address_id = ? ";
			_uai = true;
		}

		if(user !=  null){
			_user = "user_id = ? ";
			_u = true;

			if(_uai)
				_user = " and " + _user ; 
		}

		if(contactType !=  null){
			_contactType = "contact_type_id = ? " ;
			_ct = true;

			if(_uai || _u)
				_contactType = " and " + _contactType;
		}

		if(pinCode !=  null){
			_pinCode = "pin_code_id = ? ";
			_pc = true;

			if(_uai || _u || _ct)
				_pinCode = " and " + _pinCode;
		}
		
		if(address !=  null){
			_address = "address = ? " ;
			_a = true;
			if(_uai || _u || _ct || _pc)
				_address = " and " + _address;
		}

		if(!_uai && !_u && !_ct && !_pc &&!_a)
			return userAddressObject;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select user_address_id , user_id , contact_type_id , pin_code_id , address from user_addresses where " + _userAddressId + _user + _contactType + _pinCode + _address;
			PreparedStatement ps = connection.prepareStatement(query);
			

			int i = 0 ;
			if(_uai)
				ps.setInt(++i , userAddressId);

			if(_u)
				ps.setInt(++i , user.getUserId());

			if(_ct)
				ps.setInt(++i , contactType.getContactTypeId());

			if(_pc)
				ps.setInt(++i , pinCode.getPinCodeId());

			if(_a)
				ps.setString(++i , address);




			//System.out.println(guao + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//System.out.println(guao + (userAddressObject = new UserAddress(rs.getInt(1) , user , ContactType.getContactTypeObject(rs.getInt(3) , null) , PinCode.getPinCodeObject(rs.getInt(4) , null , null) , rs.getString(5))));
				userAddressObject = new UserAddress(rs.getInt(1) , user , ContactType.getContactTypeObject(rs.getInt(3) , null) , PinCode.getPinCodeObject(rs.getInt(4) , null , null) , rs.getString(5));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return userAddressObject;	
	}
	


	public static UserAddress updateUserAddress(User user , ContactType contactType , PinCode pinCode , String address,Boolean isAjaxRequest){
		String uua = "inside updateUserAddress()-->";
		UserAddress userAddress = null;
		String query = "";


		//this is written to avoid null pointer exception
		if(user == null)
			return null;

		if(contactType == null)
			contactType = new ContactType(null,null);

		if(pinCode == null)
			pinCode = new PinCode(null,null,null);


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);


			Boolean isUpdation = isAjaxRequest || getUserAddressObject(null,user,contactType,null,null) != null;

			if(isUpdation)
				query = "update user_addresses set user_id = ? , contact_type_id = ?, pin_code_id = ?, address = ? where user_id = ? and contact_type_id = ?" ; 			
			else
				query = "insert into user_addresses(user_id , contact_type_id , pin_code_id , address) value(? , ? , ? , ?)";


			PreparedStatement ps = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1 , user.getUserId());
			ps.setObject(2 , contactType.getContactTypeId());
			ps.setObject(3 , pinCode.getPinCodeId());
			ps.setObject(4 , address);
			
			if(isUpdation){
				ps.setInt(5,user.getUserId());
				ps.setObject(6 , contactType.getContactTypeId());
			}

			System.out.println(uua + "PreparedStatement : " + ps);
			System.out.println(uua + "Rows Effected : " + ps.executeUpdate());
			ResultSet userAddressId = ps.getGeneratedKeys();

			if(userAddressId.next()){
				System.out.println(userAddress = new UserAddress(userAddressId.getInt(1) , user , contactType , pinCode , address));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e ){
			e.printStackTrace();
		}
		return userAddress;
	}
	
	//Integer userAddressId , User user , ContactType contactType , PinCode pinCode , String address
	public static ArrayList<UserAddress> colectUserAddresses(User user,ContactType contactType,PinCode pinCode){
		String cua = "inside collectUserAddresses()--->";
		ArrayList<UserAddress> userAddresses = null;

		String _user = "",_contactType = "",_pinCode = "";
		Boolean _u = false,_ct = false,_pc = false;

		if(user != null){
			_u = true;
			_user = " where user_id = ? ";
		}
		if(contactType != null){
			_ct = true;
			_contactType = " where contact_type_id = ? ";
			if(_u)
				_contactType = " and contact_type_id = ? ";
		}
		if(pinCode != null){
			_pc = true;
			_pinCode = " where pin_code_id = ? ";
			if(_u || _ct)
				_pinCode = " and pin_code_id = ? ";
		}

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select user_address_id,user_id,contact_type_id,pin_code_id,address from user_addresses" + _user + _contactType + _pinCode;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_u)
				ps.setObject(++i,user.getUserId());
			if(_ct)
				ps.setObject(++i,contactType.getContactTypeId());
			if(_pc)
				ps.setObject(++i,pinCode.getPinCodeId());
			
			System.out.println(cua + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				userAddresses.add(new UserAddress(rs.getInt(1),User.getUserObject(null,null,new User(rs.getInt(2),null,null,null,null)),ContactType.getContactTypeObject(rs.getInt(3),null),PinCode.getPinCodeObject(rs.getInt(4),null,null),rs.getString(5)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(cua + "userAddresses : " + userAddresses);
		return userAddresses;
	}

	//getter setters
	public void setUserAddressId(Integer userAddressId){
		this.userAddressId = userAddressId;
	}
	public Integer getUserAddressId(){
		return userAddressId;
	}

	public void setUser(User user){
		this.user = user;
	}
	public User user(){
		return user;
	}

	public void setContactType(ContactType contactType){
		this.contactType = contactType;
	}
	public ContactType getContactType(){
		return contactType;
	}

	public void setPinCode(PinCode pinCode){
		this.pinCode = pinCode;
	}
	public PinCode getPinCode(){
		return pinCode;
	}

	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return address;
	}
}