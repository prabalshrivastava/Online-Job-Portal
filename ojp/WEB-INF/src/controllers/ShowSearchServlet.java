package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import models.Candidate;

public class ShowSearchServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String sss = "inside ShowSearchServlet--->";
		String nextPage = "showlogin.do";

		if(candidate != null){
			nextPage = "search.jsp";
		}else{
			System.out.println(sss + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}