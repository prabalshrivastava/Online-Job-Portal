package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;

import models.Candidate;
import models.Country;
import models.State;
import models.City;
import models.PinCode;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class LocationHandlerServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response){	
		
		String lhs = "inside LocationHandlerServlet-->";
		Integer countryId = null ,stateId = null, cityId = null , i = 0;
		String _countryId = null,_stateId = null,_cityId = null, pinCode = null;


		//arrayList will retrieve the list of objects from application scope
		ArrayList<Country> countries = null ;
		ArrayList<PinCode> pinCodes = null ;
		ArrayList<State> states = null ; 
		ArrayList<City> cities = null ;


		JSONArray ja = new JSONArray();
		JSONObject jo;

		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		
		ServletContext context = getServletContext();
		countries = (ArrayList<Country>)context.getAttribute("countries");
		states = (ArrayList<State>)context.getAttribute("states");
		cities = (ArrayList<City>)context.getAttribute("cities");
		pinCodes = (ArrayList<PinCode>)context.getAttribute("pincodes");

		if(candidate != null){
			
			
			try{
				_countryId = request.getParameter("countryId");
				_stateId = request.getParameter("stateId");
				_cityId = request.getParameter("cityId");
				pinCode = request.getParameter("pincode");
							

				if(_countryId!=null)
					countryId = Integer.parseInt(_countryId);
				
				if(_stateId!=null)
					stateId = Integer.parseInt(_stateId);
				
				if(_cityId!=null)
					cityId = Integer.parseInt(_cityId);

				System.out.println(lhs+"recieved ajax request with countryId = "+countryId);
				System.out.println(lhs+"recieved ajax request with stateId = "+stateId);
				System.out.println(lhs+"recieved ajax request with cityId = "+cityId);
				System.out.println(lhs+"recieved ajax request with pincode = "+pinCode);

			}
			catch(NumberFormatException e){
				//e.printStackTrace();		//commented to save server console space
			}



			if(countryId != null){
				i  = 0;

				for(State stateObject : states){

					jo = new JSONObject();
					if(stateObject.getCountry().getCountryId().equals(countryId)){

						try{

							System.out.println(lhs+"state = "+stateObject.getState()+"\tstateId = "+stateObject.getStateId());
							System.out.println(lhs+stateObject+" == countryId : "+countryId);

							jo.put("name",stateObject.getState());
							jo.put("id",stateObject.getStateId());
							ja.put(i++,jo);

						}
						catch(JSONException e){
							e.printStackTrace();
						}
					}
				}
			}

			if(stateId != null){
				//if only State is there we can return country and array of cities
				i=0;
				for(City city : cities){

					jo = new JSONObject();

					System.out.println(lhs+"stateId : "+stateId+"\tcity : "+city);

					if(stateId.equals(city.getState().getStateId())){
						System.out.println(lhs+"stateId : "+stateId+" == city : "+city);
						try{
							jo.put("name",city.getCity());
							jo.put("id",city.getCityId());
							ja.put(i++,jo);
							//System.out.println(lhs+" i = "+i);
						}
						catch(JSONException e){
							e.printStackTrace();
						}
					}
				}

			}

			if(cityId != null){
				//if only City is there we can return Country and State and array of pins


			}

			if(pinCode != null){
				//if only pinCode is there we can return Country,State and City

				
				i = 0;
				for(PinCode pincode : pinCodes){
					jo = new JSONObject();
					if(pincode.getPinCode().startsWith(pinCode)){
						System.out.println(pincode.getPinCode());
						try{
							jo.put("name",pincode.getPinCode());
							jo.put("id",pincode.getPinCodeId());
							ja.put(i++,jo);
						}
						catch(JSONException e){
							e.printStackTrace();
						}
					}
				}
			}



			try{
				System.out.println(lhs+ja);
				response.getWriter().write(ja.toString());
			}
			catch(IOException e){
				e.printStackTrace();
			}

		}else{
			System.out.println("Session expired");
		}
	}
}