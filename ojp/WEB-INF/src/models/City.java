package models;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.Db;
public class City{

	//member variables
	private Integer cityId;
	private String	city;
	private State	state;

	//constructors
	public City(Integer cityId , String city , State state){
		setCityId(cityId);
		setCity(city);
		setState(state);
	}

	//methods
	//@override
	public String toString(){
		if(this != null)
			return "{'cityId' : " + cityId + " , 'city' : '" + city + "' , 'state' : " + state + "}";
		else
			return "null";
	}
	

	public static City getCityObject(Integer cityId , String city , State state){
		City cityObject = null;
		String gco = "inside getCityObject()-->";
		String _cityId = "" , _state = "" , _city = "";
		Boolean _c = false , _ci = false , _s = false;
		
		if(cityId != null){
			_cityId = "city_id = ? ";

			_ci = true;
		}

		if(city != null){
			_city = "city = ? ";
			
			if(_ci)
				_city = " and " + _city;
			_c = true;
		}

		if(state != null && state.getStateId() != null){
			_state = " state_id = ? ";

			if(_ci || _c)
				_state = " and " + _state;
			_s = true;
		}

		if(!_ci && !_c && !_s)
			return cityObject;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select city_id , city , state_id from cities where " + _cityId + _city + _state;
			PreparedStatement ps = connection.prepareStatement(query);
			
			int i = 0;
			if(_ci)
				ps.setInt(++i , cityId);

			if(_c)
				ps.setString(++i , city);

			if(_s)
				ps.setInt(++i , state.getStateId());

//			System.out.println(gco + "PreparedStatement : " + ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
//				System.out.println(gco + (cityObject = new City(rs.getInt(1) , rs.getString(2) , State.getStateObject(rs.getInt(3) , null , null))));
				cityObject = new City(rs.getInt(1) , rs.getString(2) , State.getStateObject(rs.getInt(3) , null , null));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cityObject;
	}




	public static ArrayList collectAllCities(){
		ArrayList<City> cities = new ArrayList<City>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+Db.dbpass);
			String query = "select city_id , city , state_id from cities";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				cities.add(new City(rs.getInt(1) , rs.getString(2) , State.getStateObject(rs.getInt(3) , null , null)));
			}
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cities;
	}


	public  ArrayList collectCities(){
		
		ArrayList<City> cities = new ArrayList<City>();
		try{			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ojp?user=root&password="+utilities.Db.dbpass);
			String query = "select cities.city , cities.city_id , cities.state_id , states.state , states.state_id from cities ,  states where cities.state_id=states.state_id";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			//System.out.println("cities.city\tcities.city_id\tcities.state_id\tstates.state\tstates.state_id");
			while(rs.next()){
				
				State state = new State();
				state.setState(rs.getString(4));
				state.setStateId(rs.getInt(5));

				
				this.setCity(rs.getString(1));
				this.setCityId(rs.getInt(2));				
				this.setState(state);

				cities.add(this);

				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getInt(2)+"\t");
				System.out.print(rs.getInt(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.println(rs.getInt(5));
			}
			System.out.println("city = " + this.getCity());
			System.out.println("city_id = "+this.getCityId());
			System.out.println("state "+this.getState());
			connection.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
		return cities;
	}




	//getter setters
	public void setCityId(Integer cityId){
		this.cityId=cityId;
	}
	public Integer getCityId(){
		return cityId;
	}

	public void setCity(String city){
		this.city=city;
	}
	public String getCity(){
		return city;
	}

	public void setState(State state){
		this.state=state;
	}
	public State getState(){
		return state;
	}

}