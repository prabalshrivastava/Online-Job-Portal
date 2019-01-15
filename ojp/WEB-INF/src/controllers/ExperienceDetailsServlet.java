package controllers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.sql.Date;

import models.Experience;
import models.Candidate;
import models.IndustryType;
import models.Department;
import models.Designation;


public class ExperienceDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";
		String es ="inside ExperienceDetailsServlet-->";

		String company = null;
		Integer ctc = null,industryType = null,designation = null,department = null;
		Date joining = null,leaving = null;
		Boolean isAjaxRequest = null;
		
		String _ctc = null,_joining = null,_leaving = null,_designation = null,_department = null,_industryType=null;
		//these are precautionary variables for avoiding exception

		if(candidate != null){
			try{
				System.out.println(es + "isAjaxRequest : " + (isAjaxRequest = request.getParameter("code").equals("1")));
			}catch(NullPointerException e){
				e.printStackTrace();
			}

			System.out.println(es + (isAjaxRequest ? "AJAX REQUEST RECIEVED \n\n" : "NORMAL REQUEST RECIEVED \n\n"));
			

			System.out.println(es + "company : " + (company = request.getParameter("company")));
			System.out.println(es + "industryType : " + (_industryType = request.getParameter("industrytype")));
			System.out.println(es + "designation : " + (_designation = request.getParameter("designation")));
			System.out.println(es + "department : " + (_department = request.getParameter("department")));
			System.out.println(es + "ctc : " + (_ctc = request.getParameter("ctc")));
			System.out.println(es + "joining : " + (_joining = request.getParameter("joining")));
			System.out.println(es + "leaving : " + (_leaving = request.getParameter("leaving")));


			try{
				if(_ctc != null)
					ctc = Integer.parseInt(_ctc);
				
				if(_designation != null)
					designation = Integer.parseInt(_designation);

				if(_department != null)
					department = Integer.parseInt(_department);

				if(_industryType != null)
					industryType = Integer.parseInt(_industryType);

				if(_joining != null)
					joining = Date.valueOf(_joining);

				if(_leaving != null)
					leaving = Date.valueOf(_leaving);
				
			}catch(NumberFormatException e){
				e.printStackTrace();
			}catch(IllegalArgumentException e){
				e.printStackTrace();
			}
			session.setAttribute("experiencedetails", Experience.insertExperience(candidate,company,IndustryType.getIndustryTypeObject(industryType,null),Department.getDepartmentObject(department,null),Designation.getDesignationObject(designation,null),joining,leaving,ctc,isAjaxRequest));
			
			/*
			//for testing getDesignationObject() method
			System.out.println(es+Designation.getDesignationObject(1,null));
			System.out.println(es+Designation.getDesignationObject(null,"Project Manager/Project Lead/Technical Architect"));
			System.out.println(es+Designation.getDesignationObject(null,null));
			System.out.println(es+Designation.getDesignationObject(21,"vice-President"));
			*/
			/*
			//for testing getDepartmentObject() method
			System.out.println(es+Department.getDepartmentObject(1,null));
			System.out.println(es+Department.getDepartmentObject(null,"customer-care"));
			System.out.println(es+Department.getDepartmentObject(4,"Research and Development"));
			System.out.println(es+Department.getDepartmentObject(null,null));

			
			//for testing getIndusttryTypeObject() method
			System.out.println(es+IndustryType.getIndustryTypeObject(1,null));
			System.out.println(es+IndustryType.getIndustryTypeObject(2,null));
			System.out.println(es+IndustryType.getIndustryTypeObject(null,"IT-Software"));
			System.out.println(es+IndustryType.getIndustryTypeObject(null,"Retail"));
			System.out.println(es+IndustryType.getIndustryTypeObject(1,"IT-Software"));
			System.out.println(es+IndustryType.getIndustryTypeObject(null,null));
			*/

			
		}else{
			System.out.println(es+"session expired");
		}
		if(isAjaxRequest)
			response.getWriter().write("text recieved from servlet --> success");
		else
			request.getRequestDispatcher(nextPage).forward(request,response);
	}
}