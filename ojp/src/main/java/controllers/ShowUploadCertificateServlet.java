package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;

import models.Candidate;


public class ShowUploadCertificateServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");

		String nextPage = "showlogin.do";
		String sucs = "inside ShowUploadCertificateServlet--->";
		System.out.println(sucs);

		if(candidate != null){
			nextPage="uploadcertificate.jsp";
			System.out.println(sucs);
		}else{
			System.out.println(sucs + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		doGet(request,response);
	}
}
