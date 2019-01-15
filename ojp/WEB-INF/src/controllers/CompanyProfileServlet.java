package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Company;
import models.IndustryType;
import models.Branch;
import models.PinCode;

public class CompanyProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");


		String cps = "CompanyProfileServlet--->";
		String nextPage = "showlogin.do";

		System.out.println(cps + "company : " + company);

		if(company != null){
			nextPage = "showbranch.do";



			//user details :
			String userName = null ,email = null,password = null;
			System.out.println(cps + "userName : " + (userName = request.getParameter("username")));
			System.out.println(cps + "email : " + (email = request.getParameter("email")));
			System.out.println(cps + "password : " + (password = request.getParameter("password")));
			
			//company details : 
			String logoPath = null, website = null, mission = null, vision = null, history = null, awards = null, aboutUs = null,_industryType = null;
			Integer industryType = null;
			System.out.println(cps + "logoPath : " + (logoPath = request.getParameter("logopath")));
			System.out.println(cps + "website : " + (website = request.getParameter("website")));
			System.out.println(cps + "mission : " + (mission = request.getParameter("mission")));
			System.out.println(cps + "vision : " + (vision = request.getParameter("vision")));
			System.out.println(cps + "history : " + (history = request.getParameter("history")));
			System.out.println(cps + "awards : " +  (awards = request.getParameter("awards")));
			System.out.println(cps + "industryType : " + (_industryType = request.getParameter("industrytype")));
			try{
				if(_industryType != null){
					System.out.println(cps + "parsed industryType : " + (industryType = Integer.parseInt(_industryType)));
				}
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("branch.do").include(request,response);
			System.out.println("After updating User Details Company : " + company.updateUser(company.getUserId(),userName,email,password,company.getUserType()));
			System.out.println("After updaing Company Profile Details : " + company.updateCompanyProfile(logoPath,website,mission,vision,history,awards,aboutUs,IndustryType.getIndustryTypeObject(industryType,null)));

		}else{
			System.out.println(cps + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}