package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Company;
import models.Branch;

public class ShowBranchServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");
		String sbs = "ShowBranchServlet--->";
		String nextPage = "showlogin.do";

		if(company != null){
			nextPage = "branch.jsp";
			session.setAttribute("branches",Branch.collectBranches(company));
		}else{
			System.out.println(sbs + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}
