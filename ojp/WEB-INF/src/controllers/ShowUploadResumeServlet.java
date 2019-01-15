package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;

import models.Candidate;
public class ShowUploadResumeServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		System.out.println("\n\n");
		System.out.println("inside ShowUploadResumeServlet");

		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";

		if(candidate!=null){
			nextPage = "upload_your_resume.jsp";
		}else{
			System.out.println("session expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}	
}
