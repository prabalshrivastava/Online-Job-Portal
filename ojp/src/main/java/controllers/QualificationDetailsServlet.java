package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Qualification;
import models.Candidate;
import models.Organization;
import models.Course;


import java.sql.Date;
import java.util.Arrays;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class QualificationDetailsServlet extends HttpServlet{
	String qd = "inside QualificationDetailsServlet-->";

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String nextPage = "showlogin.do";
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		

		String _count = null;
		Integer count = null;
		String[] _course = null,_organization = null,_passingYear = null,_score = null,certificatePath = null;
		Integer[] course = null,organization = null;
		Date[] passingYear = null;
		Float[] score = null;

		Boolean isAjaxRequest = false;
		String code;

		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();

		if(candidate != null){
			nextPage = "showothers.do";

			System.out.println(qd + "code : " +(code = request.getParameter("code")));
			System.out.println(qd + "_count : " + (_count = request.getParameter("rowcount")));

			System.out.println(qd + "_course : " + Arrays.toString( _course = request.getParameterValues("course")));
			System.out.println(qd + "_organization : " + Arrays.toString(_organization = request.getParameterValues("organization")));
			System.out.println(qd + "_passingYear : " + Arrays.toString(_passingYear = request.getParameterValues("passingyear")));
			System.out.println(qd + "_score : " + Arrays.toString(_score = request.getParameterValues("score")));
			System.out.println(qd + "certificatePath : " + Arrays.toString(certificatePath = request.getParameterValues("certificatepath")));


			try{
				if(code != null)
					System.out.println(qd + "isAjaxRequest : " + (isAjaxRequest = code.equals("1")));


				if(_count != null){
					System.out.println(qd + "Parsed count : " + (count = Integer.parseInt(_count)));//recieved rowCount
					System.out.println(qd + "parsed no of elements = count + 1 : " +  ++count);	//because we are getting rowCount && no of elements = rowCount

					course = new Integer[count];
					organization = new Integer[count];
					passingYear = new Date[count];
					score = new Float[count];
				}else{
					System.out.println(qd + "count is NULL");
				}
				


				certificatePath = new String[count];
				ArrayList <Qualification> qualifications = new ArrayList<Qualification>();
				for(int i=0;i<count;i++){
					System.out.println( "\n" + qd + "course[" + i + "] : " + (course[i] = Integer.parseInt(_course[i])));
					System.out.println(qd + "organization[" + i + "] : " + (organization[i] = Integer.parseInt(_organization[i])));
					System.out.println(qd + "passingYear[" + i + "] : "  + (passingYear[i] = Date.valueOf(_passingYear[i])));
					System.out.println(qd + "score[" + i + "] : " + (score[i] = Float.parseFloat(_score[i])));
					System.out.println(qd + "course[" + i + "] : " + (Course.getCourseObject(course[i],null)));
					System.out.println(qd + "organization[" + i + "] : " + (Organization.getOrganizationObject(organization[i],null,null)) + "\n");
					Qualification qualificationObject = null;
					System.out.println(qd + "qualificationObject : " + (qualificationObject = Qualification.updateQualificationDetails(null,Course.getCourseObject(course[i],null),candidate,Organization.getOrganizationObject(organization[i],null,null),passingYear[i],score[i],certificatePath[i])));

			
					jo.put("id_" + i , qualificationObject.getQualificationId());
					ja.put(i,jo);
				}


				//System.out.print(qd + "Qualification.updateQualificationDetails(null," + Course.getCourseObject(course[0],null) + "," + candidate + "," + Organization.getOrganizationObject(organization[0],null,null) + "," + passingYear[0] + "," + score[0] + "," + certificatePath[0] + ");");
				System.out.print(qd + "Qualification.updateQualificationDetails(null," + Course.getCourseObject(course[0],null) + ",");
				System.out.print(candidate + ",");
				System.out.print(Organization.getOrganizationObject(organization[0],null,null) + ",");
				System.out.print(passingYear[0] + ",");
				System.out.print(score[0] + ",");
				System.out.print(certificatePath[0] + ");");



				System.out.println(qd + "Parsed Array : course : " + Arrays.deepToString(course));
				System.out.println(qd + "Parsed Array : organization : " + Arrays.deepToString(organization));
				System.out.println(qd + "Parsed Array : passingYear : " + Arrays.deepToString(passingYear));
				System.out.println(qd + "parsed Array : score : " + Arrays.deepToString(score));
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
			catch(IllegalArgumentException e){
				e.printStackTrace();
			}
			catch(JSONException e){
				e.printStackTrace();
			}
		}else{
			System.out.println(qd + "Session Expired");
		}
		if(isAjaxRequest)
			response.getWriter().write(ja.toString());
		else
			request.getRequestDispatcher(nextPage).forward(request,response);
	}
}