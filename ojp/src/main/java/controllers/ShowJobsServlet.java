package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import models.Company;
import models.Job;

public class ShowJobsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String sjs = "inside ShowJobsServlet--->";
		String nextPage = "showlogin.do";

		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");

		if(company != null){
			nextPage = "jobs.jsp";
		}else{
			System.out.println(sjs + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}