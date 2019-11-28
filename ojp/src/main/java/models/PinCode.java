package models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import utilities.Db;





public class PinCode{

	//member variables

	private	Integer	pinCodeId;
	private	String	pinCode;
	private	City	city;
	
	
	//constructors
	public PinCode(Integer pinCodeId,String pinCode,City city){
		setPinCodeId(pinCodeId);
		setPinCode(pinCode);
		setCity(city);
	}



	//methods
	//@override
	public String toString(){
		return "{'pinCodeId' : " + pinCodeId + ",'pinCode' : '" + pinCode + "','city' : " + city + "}";
	}

	public static PinCode getPinCodeObject(Integer pinCodeId,String pinCode,City city){

		PinCode pinCodeObject = null;
		String gpco = "inside getPinCodeObject()-->";
		String _pinCodeId = "";
		String _pinCode = "";
		String _city = "";

		Boolean _pci = false,_pc = false,_c = false;

		if(pinCodeId != null){
			_pinCodeId = "pin_code_id = ? ";
			_pci = true;
		}

		if(pinCode != null){
			_pinCode = "pin_code = ? ";
			_pc = true;

			if(_pci)
				_pinCode = " and " + _pinCode;
		}

		if(city != null){
			_city = "city_id = ? ";
			_c = true;

			if(_pci || _pc)
				_city = " and " + _city;
		}

		if(!_pci && !_pc && !_c)
			return pinCodeObject;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select pin_code_id,pin_code,city_id from pin_codes where " + _pinCodeId + _pinCode + _city;
			PreparedStatement ps = connection.prepareStatement(query);


			int i = 0;
			if(_pci)
				ps.setInt(++i,pinCodeId);

			if(_pc)
				ps.setString(++i,pinCode);

			if(_c)
				ps.setInt(++i,city.getCityId());

			//System.out.println(gpco + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				pinCodeObject = new PinCode(rs.getInt(1),rs.getString(2),City.getCityObject(rs.getInt(3),null,null));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return pinCodeObject;
	}



	public static ArrayList<PinCode> collectAllPinCodes(){


		ArrayList<PinCode> pinCodes = new ArrayList<PinCode>();
		String cap = "inside collectAllPinCodes()-->";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select pin_code_id,pin_code,city_id from pin_codes";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
//				System.out.println(cap + "PinCodeObject : " + (pinCodes.add(new PinCode(rs.getInt(1),rs.getString(2),City.getCityObject(rs.getInt(3),null,null)))));
				pinCodes.add(new PinCode(rs.getInt(1),rs.getString(2),City.getCityObject(rs.getInt(3),null,null)));
			}
			System.out.println(cap + "preparedStatement : "+ps);
			connection.close();	
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return pinCodes;
	}
	



	//getter setters
	public void setPinCodeId(Integer pinCodeId){
		this.pinCodeId = pinCodeId;
	}
	public Integer getPinCodeId(){
		return pinCodeId;
	}

	public void setPinCode(String pinCode){
		this.pinCode=pinCode;
	}
	public String getPinCode(){
		return pinCode;
	}

	public void setCity(City city){
		this.city=city;
	}
	public City getCity(){
		return city;
	}

}