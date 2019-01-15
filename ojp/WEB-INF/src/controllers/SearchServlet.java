package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;


import models.City;
import models.Skill;
import models.Company;
import models.JobSkill;
import models.JobShift;
import models.Candidate;
import models.IndustryType;
import models.JobQualification;
import models.userinfo.CompanyJobs;



public class SearchServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String ss = "inside SearchServlet--->";
		String nextPage = "showlogin.do";
		String _city = null,_industryType = null ,workExperience = null,_skill = null,_ctc = null,_company = null,_designation = null,_department = null;
		Integer city = null, industryType = null ,skill = null , ctc = null,companyId = null;
		if(candidate != null){
			//nextPage = "
			System.out.println(ss + "_city : " + (_city = request.getParameter("city")));
			System.out.println(ss + "_industryType : " + (_industryType = request.getParameter("industrytype")));
			System.out.println(ss + "_workExperience : " + (workExperience = request.getParameter("workexperience")));
			System.out.println(ss + "_skill : " + (_skill = request.getParameter("skill")));
			System.out.println(ss + "_ctc : " + (_ctc = request.getParameter("ctc")));
			System.out.println(ss + "_company : " + (_company = request.getParameter("company")));
			System.out.println(ss + "_designation : " + (_designation = request.getParameter("designation")));
			System.out.println(ss + "_department : " + (_department = request.getParameter("department")));
//			System.out.println(ss + "_experience : " + (_experience = request.getParameter("experience")));
//			System.out.println(ss + "_experienceMax : " + (_experienceMax = request.getParameter("experineceMax")));
//			System.out.println(ss + "_ctcMin : " + (_ctcMin = request.getParameter("ctcmin")));
//			System.out.println(ss + "
			

			try{
				if(_city != null)
					System.out.println(ss + "Parsed city : " + (city = Integer.parseInt(_city)));
				if(_industryType != null)
					System.out.println(ss + "Parsed industryType : " + (industryType = Integer.parseInt(_industryType)));
				if(_skill != null)
					System.out.println(ss + "Parsed skill : " + (skill = Integer.parseInt(_skill)));
				if(_company != null)
					System.out.println(ss + "Parsed companyId : " + (companyId = Integer.parseInt(_company)));
				if(_ctc != null)
					System.out.println(ss + "Parsed ctc : " + (ctc = Integer.parseInt(_ctc)));

				session.setAttribute("companyjobs",CompanyJobs.searchJobs(null,null,null,null,City.getCityObject(city,null,null),null,null,IndustryType.getIndustryTypeObject(industryType,null),Company.getCompanyObject(new Company(companyId),null),JobSkill.getJobSkillObject(null,null,Skill.getSkillObject(skill,null)),null,null,null));
				nextPage = "showsearch.do";
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
		}else{
			System.out.println(ss + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}