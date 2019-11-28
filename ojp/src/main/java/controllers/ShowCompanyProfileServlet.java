package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import models.Company;
import models.Branch;

public class ShowCompanyProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");
		String scps = "inside ShowCompanyProfileServlet--->";
		String nextPage = "showlogin.do";
	
		session.setAttribute("branch",Branch.getBranchObject(null,"Head Office",null,null,null,null,null,company));
	
		if(company != null){
			nextPage = "companyprofile.jsp";
		}else{
			System.out.println("session expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}