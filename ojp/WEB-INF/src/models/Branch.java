package models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;

import utilities.Db;



public class Branch{

	//member variables
	private Integer		branchId;
	private String		branchName;
	private	String		address;
	private	PinCode		pinCode;
	private	String		contactPerson;
	private String		contactNumber;
	private	String		email;
	private	Company		company;
	
	//constructors()

	public Branch(){

	}

	public Branch(Integer branchId){
		setBranchId(branchId);
	}

	public Branch(Integer branchId,String branchName,String address,PinCode pinCode,String contactPerson,String contactNumber,String email,Company company){
		setBranchId(branchId);
		setBranchName(branchName);
		setAddress(address);
		setPinCode(pinCode);
		setContactPerson(contactPerson);
		setContactNumber(contactNumber);
		setEmail(email);
		setCompany(company);
	}

	//methods()
	//@override 
	public String toString(){
		return "{'branchId' :  " + branchId + " , 'branchName' : '" + branchName + "' , 'address' : '" + address + "' , 'pinCode' : " + pinCode + " , 'contactPerson' : '" + contactPerson + "' , 'contactNumber' : '" + contactNumber + "' , 'email' : '" + email + "' , 'company' : " + company + "}";
	}
	public static Branch updateBranchDetails(String branchName,String address,PinCode pinCode,String contactPerson,String contactNumber,String email,Company company){
		String ubd = "inside updateBranchDetails()--->";
		Branch branchObject = null;
		
		/*
		String _branchName = "",_address = "",_pinCode = "",_contactPerson = "",_contactNumber="",_email = "",_company = "";
		Boolean _bn = false , _a = false , _pc = false , _cp = false ,_cn = false ,_e = false , _c = false;
		*/
		String query = null;

		if(branchName != null && address != null && pinCode != null && contactPerson != null && email != null && company != null){
		}else{
			return null;
		}
		System.out.println("\n\n\n\n\n" + ubd);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			query = "insert into branches(branch_name,address,pin_code_id,contact_person,contact_number,email,company_id) values (?,?,?,?,?,?,?) on duplicate key update branch_name = values(branch_name),address = values(address),pin_code_id = values(pin_code_id),contact_person = values(contact_person),contact_number = values(contact_number),email = values(email),company_id = values(company_id)";
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			int i = 0;			
			ps.setObject(++i,branchName);
			ps.setObject(++i,address);
			ps.setObject(++i,pinCode.getPinCodeId());
			ps.setObject(++i,contactPerson);
			ps.setObject(++i,contactNumber);
			ps.setObject(++i,email);
			ps.setObject(++i,company.getCompanyId());

			System.out.println(ubd + "PreparedStatement : " + ps);
			System.out.println(ubd + "Rows Effeceted : " + ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				System.out.println(ubd + "Branch Details inserted ---->BranchObject : " +  (branchObject = new Branch(rs.getInt(1),branchName,address,pinCode,contactPerson,contactNumber,email,company)));
			}else{
				System.out.println(ubd + "Branch Details Updated ----->BranchObject : " +  (branchObject = getBranchObject(null,branchName,null,null,null,null,null,company)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}

		System.out.println("\n\n\n\n" + ubd + branchObject);
		return branchObject;
	}
		
	public static Branch getBranchObject(Integer branchId){
		return getBranchObject(branchId,null,null,null,null,null,null,null);
	}

	public static Branch getBranchObject(Integer branchId,String branchName,String address,PinCode pinCode,String contactPerson,String contactNumber,String email,Company company){
		Branch branchObject = null;
		String gbo = "inside getBranchObject()--->";
		String _branchId = "",_branchName = "",_address = "",_pinCode = "",_contactPerson = "",_contactNumber = "",_email = "",_company = "";
		Boolean _bi,_bn,_a,_pc,_cp,_cn,_e,_c;
		_bi = _bn = _a = _pc = _cp = _cn = _e = _c = false;
		
		if(branchId != null){
			_branchId = " branch_id = ? ";
			_bi = true;
		}

		if(branchName != null){
			_branchName = " branch_name = ? ";
			_bn = true;
			if(_bi)
				_branchName = " and " + _branchName;
		}
		if(address != null){
			_address = " address = ? " ;
			_a = true;
			if(_bn)
				_address = " and " + _address;
		}
		if(pinCode != null){
			_pinCode = " pin_code_id = ? ";
			_pc = true;
			if(_bi || _bn || _a)
				_pinCode = " and " + _pinCode;
		}
		if(contactPerson != null){
			_contactPerson = " contact_person = ? ";
			_cp = true;
			if(_bi || _bn || _a || _pc)
				_contactPerson = " and " + _contactPerson;
		}
		if(contactNumber != null){
			_contactNumber = " contact_number = ? " ;
			_cn = true;
			if(_bi || _bn || _a || _pc || _cp)
				_contactNumber = " and " + _contactNumber;
		}
		if(email != null){
			_email = " email = ? ";
			_e = true;
			if(_bi || _bn || _a || _pc || _cp || _cn)
				_email = " and " + _email;
		}
		if(company != null){
			_company = " company_id = ? ";
			_c = true;
			if(_bi || _bn || _a || _pc || _cp || _cn || _e)
				_company = " and " + _company;
		}

		if(!_bi && !_bn && !_a && !_pc && !_cp && !_cn && !_e && !_c)
			return null;


		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select branch_id,branch_name,address,pin_code_id,contact_person,contact_number,email,company_id from branches where " + _branchId  + _branchName + _address + _pinCode + _contactPerson + _contactNumber + _email + _company;
			PreparedStatement ps = connection.prepareStatement(query);
			int i = 0;
			if(_bi)
				ps.setInt(++i,branchId);
			if(_bn)
				ps.setString(++i,branchName);
			if(_a)
				ps.setString(++i,address);
			if(_pc)
				ps.setInt(++i,pinCode.getPinCodeId());
			if(_cp)
				ps.setString(++i,contactPerson);
			if(_cn)
				ps.setString(++i,contactNumber);
			if(_e)
				ps.setString(++i,email);
			if(_c)
				ps.setInt(++i,company.getCompanyId());

//			System.out.println(gbo + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
//				System.out.println(gbo + "branchObject : " +  (branchObject = new Branch(rs.getInt(1),rs.getString(2),rs.getString(3),PinCode.getPinCodeObject(rs.getInt(4),null,null),rs.getString(5),rs.getString(6),rs.getString(7),Company.getCompanyObject(new Company(rs.getInt(8)),null))));
				branchObject = new Branch(rs.getInt(1),rs.getString(2),rs.getString(3),PinCode.getPinCodeObject(rs.getInt(4),null,null),rs.getString(5),rs.getString(6),rs.getString(7),Company.getCompanyObject(new Company(rs.getInt(8)),null));
			}

			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return branchObject;
	}



	public static ArrayList<Branch> collectBranches(Company company){
		//if you pass company null then this method returns collection of all the branches
		ArrayList<Branch> branches = null;
		String cb = "inside collectBranch()--->";
		String _company = "";
		Boolean _c = false;

		if(company != null){
			_c = true;
			_company = " where company_id = ? " ;
		}
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password=" + Db.dbpass);
			String query = "select branch_id,branch_name,address,pin_code_id,contact_person,contact_number,email,company_id from branches " + _company;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(company != null)
				ps.setObject(++i,company.getCompanyId());

			System.out.println(cb + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			branches = new ArrayList<Branch>();
			while(rs.next()){
				branches.add(new Branch(rs.getInt(1),rs.getString(2),rs.getString(3),PinCode.getPinCodeObject(rs.getInt(4),null,null),rs.getString(5),rs.getString(6),rs.getString(7),Company.getCompanyObject(new Company(rs.getInt(8)),null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return branches;
	}




	//getter setters
	public void setBranchId(Integer branchId){
		this.branchId = branchId;
	}
	public Integer getBranchId(){
		return branchId;
	}

	public void setBranchName(String branchName){
		this.branchName = branchName;
	}
	public String getBranchName(){
		return branchName;
	}

	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return address;
	}

	public void setPinCode(PinCode pinCode){
		this.pinCode = pinCode;
	}
	public PinCode getPinCode(){
		return pinCode;
	}

	public void setContactPerson(String contactPerson){
		this.contactPerson = contactPerson;
	}
	public String getContactPerson(){
		return contactPerson;
	}

	public void setContactNumber(String contactNumber){
		this.contactNumber = contactNumber;
	}
	public String getContactNumber(){
		return contactNumber;
	}

	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return email;
	}

	public void setCompany(Company company){
		this.company = company;
	}
	public Company getCompany(){
		return company;
	}
}