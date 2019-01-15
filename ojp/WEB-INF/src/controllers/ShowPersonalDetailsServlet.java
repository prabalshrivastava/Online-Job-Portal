package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import models.Candidate;

import java.io.IOException;

public class ShowPersonalDetailsServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		doGet(request,response);
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";

		if(candidate!=null){
			nextPage = "personal_details.jsp";
		}else{
			System.out.println("session expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}