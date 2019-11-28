package models;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import utilities.Db;

public class Contact{

	//member variables
	private	Integer		contactId;
	private	ContactType	contactType;
	private	User		user;
	private	String		contact;

	//constructors
	public Contact(Integer contactId , ContactType contactType , User user , String contact){
		setContactId(contactId);
		setContactType(contactType);
		setUser(user);
		setContact(contact);
	}
	
	public Contact(){
	}


	//methods()
	//@override toString()
	public String toString(){
		return "{'contactId' : " + contactId + " , 'contactType' : " + contactType + " , 'user' : " +user + " , 'contact' : '" + contact + "'}";
	}
	
	public static Contact getContactObject(Integer contactId , ContactType contactType , User user , String contact){
		String gco = "inside getContactObject()-->";
		Contact contactObject = null;
		String _contactId = ""  , _contactType = "" , _contact = "" , _user = "";
		Boolean _ci = false , _ct = false , _c = false , _u = false;

		if(contactId != null){
			_contactId = " contact_id = ? ";
			_ci = true;
		}

		if(contactType != null){
			_contactType = " contact_type_id = ? ";
			_ct = true;
			if(_ci)
				_contactType = " and " + _contactType;
		}

		if(user != null){
			_user = " user_id = ? ";
			_u = true;
			if(_ci || _ct)
				_user = " and " + _user;
		}

		if(contact != null){
			_contact = "contact = ? " ;
			_c = true ;

			if(_ci || _ct || _u)
				_contact = " and " + _contact ; 
		}

		if(!_ci && !_ct && !_u && !_c)
			return contactObject;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select contact_id ,  contact_type_id ,  user_id , contact from contacts where " + _contactId + _contactType + _user + _contact;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;

			if(_ci)
				ps.setInt(++i , contactId);

			if(_ct)
				ps.setInt(++i , contactType.getContactTypeId());

			if(_u)
				ps.setInt(++i , user.getUserId());

			if(_c)
				ps.setString(++i , contact);

//			System.out.println(gco + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
//				System.out.println(gco + (contactObject = new Contact(rs.getInt(1) , ContactType.getContactTypeObject(rs.getInt(2) , null) , user , rs.getString(4))));
				contactObject = new Contact(rs.getInt(1) , ContactType.getContactTypeObject(rs.getInt(2) , null) , user , rs.getString(4));
			}


		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}

		return contactObject;
	}

	public static Contact updateContact(ContactType contactType , User user , String contact , Boolean isAjaxRequest){
		String uc = "inside updateContact()-->";
		Contact contactObject = null;
		String query = "";
		
		//this is written to avoid null pointer exception
		if(contactType == null)
			contactType = new ContactType(null,null);

		if(user == null)
			return null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);


			Boolean isUpdation = isAjaxRequest || getContactObject(null,contactType,user,null) != null;

			if(isUpdation)
				query = "update contacts set contact_type_id = ? , user_id = ? , contact = ? where user_id = ? and contact_Type_id = ? " ;
			else
				query = "insert into contacts(contact_type_id , user_id , contact) value(? , ? , ?)";

			PreparedStatement ps = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);

			ps.setObject(1 , contactType.getContactTypeId());
			ps.setInt(2 , user.getUserId());
			ps.setObject(3 , contact);

			if(isUpdation){
				ps.setInt(4 , user.getUserId());
				ps.setObject(5 , contactType.getContactTypeId());
			}

			System.out.println(uc + "PreparedStatement : " + ps);
			System.out.println(uc + "Rows Effected : " + ps.executeUpdate());
			ResultSet contactId = ps.getGeneratedKeys();

			if(contactId.next()){
				System.out.println(contactObject = new Contact(contactId.getInt(1) , contactType , user , contact));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return contactObject;
	}
	

	public static ArrayList<Contact> collectContacts(ContactType contactType,User user){
		String cc = "inside collectContacts()--->";
		ArrayList<Contact> contacts = null;

		String _contactType = "";
		String _user = "";

		Boolean _ct = false,_u = false;

		if(contactType != null){
			_contactType = "  where contact_type_id = ? ";
		}

		if(user != null){
			_user = " where user_id = ? ";
			_u = true;

			if(_ct)
				_user = " and user_id = ? ";
		}

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select contact_id,contact_type_id,user_id,contact from contacts " + _contactType + _user;
			PreparedStatement ps = connection.prepareStatement(query);

			int i = 0;
			if(_ct)
				ps.setObject(++i,contactType.getContactTypeId());
			if(_u)
				ps.setObject(++i,user.getUserId());

			System.out.println(cc + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			contacts = new ArrayList<Contact>();
			while(rs.next()){
				contacts.add(new Contact(rs.getInt(1),ContactType.getContactTypeObject(rs.getInt(2),null),User.getUserObject(null,null,new User(rs.getInt(3),null,null,null,null)),rs.getString(4)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return contacts;
	}
		

	//getter setters
	public void setContactId(Integer contactId){
		this.contactId=contactId;
	}
	public Integer getContactId(){
		return contactId;
	}

	public void setContactType(ContactType contactType){
		this.contactType=contactType;
	}
	public ContactType getContactType(){
		return	contactType;
	}

	public void setUser(User user){
		this.user=user;
	}
	public User getUser(){
		return user;
	}

	public void setContact(String contact){
		this.contact=contact;
	}
	public String getContact(){
		return contact;
	}
	
}