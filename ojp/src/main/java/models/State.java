package models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
import utilities.Db;

public class State{

	//member variables
	private Integer stateId;
	private String	state;
	private Country	country;		
	
	public State(){

	}
	public State(Integer stateId,String state,Country country){
		//System.out.println("inside state constructor-->stateId : "+stateId);
		setStateId(stateId);
		setState(state);
		setCountry(country);
	}

	//methods
	//@override
	public String toString(){
		return "{'stateId' : " + stateId + ",'state' : '" + state + "','country' : " + country + "} ";
	}

	//collectAllStates is used by the Listeners for collecting ArrayList of the states
	public static ArrayList collectAllStates(){

		String cas = "inside collectAllStates-->";
		ArrayList<State> states = new ArrayList<State>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select state_id,state,country_id from states";
			PreparedStatement ps = connection.prepareStatement(query);
			//System.out.println(cas+"presparedStatement :"+ps);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				State state = new State(rs.getInt(1),rs.getString(2),Country.getCountryObject(rs.getInt(3),null));
				//System.out.println(cas+"stateId : "+rs.getInt(1));
				//System.out.println(cas+state);
				states.add(state);
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return states;
	}
	
	
	
	//pass any of one parameter and you'll get the state Object
	public static State getStateObject(Integer stateId,String state,Country country){

		String gso = "inside getStateObject()-->";
		String _stateId = "",_state = "",_country = "";
		State stateObject = null;
		Boolean _si = false,_s = false , _c = false;
		
		
//		System.out.println(gso + "stateId : " + stateId + "\tstate : " + state + "\tcountry : " + country);
		if(stateId == null && state == null && country == null){
			System.out.println("stateId,state,country passed to getStateObject is null");
			return stateObject;

		}else{

			if(stateId != null){
				_stateId ="state_id = ? ";
				_si = true;
			}
			
			if(state != null){
				_state = "state = ? ";

				if(_si)
					_state = " and " + _state;

				_s = true;
			}
			
			if(country != null && country.getCountryId() != null){
				_country = "country_id = ? ";

				if(_s || _si)
					_country = " and " + _country;

				_c = true;

			}
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
				String query = "select state_id,state,country_id from states where "+_stateId + _state + _country ;
				PreparedStatement ps = connection.prepareStatement(query);
		
				int i = 0;
				if(stateId != null)
					ps.setInt(++i,stateId);

				if(state != null)
					ps.setString(++i,state);

				if(country != null && country.getCountryId() != null)
					ps.setInt(++i,country.getCountryId());
		
				//System.out.println(gso + "PreparedStatement : " + ps);				
				ResultSet rs = ps.executeQuery();

				if(rs.next()){
					//System.out.println(gso + (stateObject = new State(rs.getInt(1),rs.getString(2),Country.getCountryObject(rs.getInt(3),null))));
					stateObject = new State(rs.getInt(1),rs.getString(2),Country.getCountryObject(rs.getInt(3),null));
				}

				connection.close();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		
			return stateObject;
		}
	}
	
	//getter setters
	public void setStateId(Integer stateId){
		this.stateId=stateId;
	}
	public Integer getStateId(){
		return stateId;
	}

	public void setState(String state){
		this.state = state;
	}
	public String getState(){
		return state;
	}

	public void setCountry(Country  country){
		this.country = country;
	}
	public Country getCountry(){
		return country;
	}
}