package controllers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import models.Candidate;
import models.Project;

public class ProjectDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";
		String title = null , _members = null , duration = null ,details = null ,otherDetails = null;
		Integer members = null;
		String pds = "inside ProjectDetailsServlet-->";
		Boolean isAjaxRequest = null;
		String code = null;
		

		if(candidate != null){
			//nextPage = "";
			System.out.println(pds + "code : " + (code = request.getParameter("code")));
			System.out.println(pds + "title : " + (title = request.getParameter("title")));	
			System.out.println(pds + "members : " + (_members = request.getParameter("members")));
			System.out.println(pds + "duration : " + (duration = request.getParameter("duration")));
			System.out.println(pds + "details : " + (details = request.getParameter("details")));
			System.out.println(pds + "otherdetails : " + (otherDetails = request.getParameter("otherdetails")));
			
			try{		
				System.out.println("isAjaxRequest : " + (isAjaxRequest = code.equals("1")));
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}

		
			try{
				if(_members != null)
					members = Integer.parseInt(_members);
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}

			session.setAttribute("project",Project.updateProjectDetails(title,candidate,members,duration,details,otherDetails,isAjaxRequest));

		}else{
			System.out.println(pds + "Session Expired");
		}


		if(isAjaxRequest){
			response.getWriter().write("projectDetails update was successful using ajax request");
		}else{
			request.getRequestDispatcher(nextPage).forward(request,response);
		}
	}
}