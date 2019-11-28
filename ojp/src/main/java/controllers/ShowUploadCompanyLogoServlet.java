package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import models.Company;

public class ShowUploadCompanyLogoServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String nextPage = "showLogin.do";
		String sucls = "inside ShowUploadCompanyLogoServlet--->";

		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");
		
		if(company != null){
			nextPage = "uploadcompanylogo.jsp";
		}else{
			System.out.println(sucls + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response){
		try{
			doGet(request,response);
		}
		catch(ServletException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}