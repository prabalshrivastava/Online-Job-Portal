package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import models.Candidate;
import models.IndustryType;
import models.Experience;

public class ShowExperienceDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";
		if(candidate != null){
			nextPage = "experience_details.jsp";
			session.setAttribute("experience",Experience.getExperienceObject(candidate));
			session.setAttribute("experiences",Experience.collectExperiences(candidate));
		}else{
			System.out.println("session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}
