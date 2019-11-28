package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import models.Company;

public class JobsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");
		String js = "inside JobsSerlvet--->";
		String nextPage = "showlogin.do";
		if(company != null){
			
		}else{
			System.out.println(js + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}