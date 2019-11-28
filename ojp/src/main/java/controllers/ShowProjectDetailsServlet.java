package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;


import models.Candidate;
import models.Project;


public class ShowProjectDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String nextPage = "showlogin.do";
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		if(candidate != null){
			nextPage = "project_details.jsp";
			//session.setAttribute("project",Project.getProjectObject(candidate));
			session.setAttribute("projects",Project.collectProjects(candidate));
		}else{
			System.out.println("session expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}
