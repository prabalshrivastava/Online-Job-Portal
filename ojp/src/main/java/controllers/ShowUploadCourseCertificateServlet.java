package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.File;
import models.Candidate;

public class ShowUploadCourseCertificateServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		
		String succs = "inside ShowUploadCourseCertificateServlet--->";
		String nextPage = "showlogin.do";

		if(candidate != null){
			nextPage = "uploadcoursecertificate.jsp";
		}else{
			System.out.println(succs + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		try{
			doPost(request,response);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(ServletException e){
			e.printStackTrace();
		}
	}
}
