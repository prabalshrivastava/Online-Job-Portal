package controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import models.Skill;
import models.Branch;
import models.Course;
import models.Company;
import models.Department;
import models.Designation;

import models.Job;
import models.JobScoreCriteria;
import models.JobSkill;
import models.JobShift;
import models.JobQualification;

public class PostNewJobServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");
		String nextPage = "showlogin.do";
		String pnjs = "inside PostNewJobServlet--->";
		
		//data for job
		String jobTitle = null,jobDescription = null;

		String _branch = null,_department = null,_designation = null,_noOfVacancies = null;
		Integer branch = null, department = null, designation = null, noOfVacancies = null;

		String _launchDate = null,_applyLastDate = null;
		Date	launchDate = null, applyLastDate = null;

		String _experienceMin = null ,_experienceMax = null;
		Integer experienceMin = null , experienceMax = null;

		String _ctcMin = null,_ctcMax = null;
		Integer ctcMin = null, ctcMax = null;
		
		//data for JobScoreCriteria
		String _sscScore = null,_hscDiplomaScore = null,_graduation = null;
		Integer sscScore = null, hscDiplomaScore = null, graduation = null;

		//data for JobSkill
		String skills = null;

		//data for JobShift
		String _startHour = null,_endHour = null;
		Time startHour = null , endHour = null;

		//data for JobQualification
		String _course = null;
		Integer course = null;

		Job jobObject = null;
		


		if(company != null){
			System.out.println(pnjs + "jobTitle : " + (jobTitle = request.getParameter("jobtitle")));
			System.out.println(pnjs + "_branch : " + (_branch = request.getParameter("branch")));
			System.out.println(pnjs + "_department : " + (_department = request.getParameter("department")));
			System.out.println(pnjs + "_designation : " + (_designation = request.getParameter("designation")));
			System.out.println(pnjs + "_noOfVacancies : " + (_noOfVacancies = request.getParameter("noofvacancies")));
			System.out.println(pnjs + "_lanuchDate : " + (_launchDate = request.getParameter("launchdate")));
			System.out.println(pnjs + "_applyLastDate : " + (_applyLastDate = request.getParameter("applylastdate")));
			System.out.println(pnjs + "jobDescription : " + (jobDescription = request.getParameter("jobdescription")));
			System.out.println(pnjs + "_experienceMin : " + (_experienceMin = request.getParameter("experiencemin")));
			System.out.println(pnjs + "_experienceMax : " + (_experienceMax = request.getParameter("experiencemax")));
			System.out.println(pnjs + "_ctcMin : " + (_ctcMin = request.getParameter("ctcmin")));
			System.out.println(pnjs + "_ctcMax : " + (_ctcMax = request.getParameter("ctcmax")));
			System.out.println(pnjs + "_sscScore : " + (_sscScore = request.getParameter("sscscore")));
			System.out.println(pnjs + "_hscDiplomaScore : " + (_hscDiplomaScore = request.getParameter("hscdiplomascore")));
			System.out.println(pnjs + "_graduation : " + (_graduation = request.getParameter("graduation")));
			System.out.println(pnjs + "skills : " + (skills = request.getParameter("skills")));
			System.out.println(pnjs + "_startHour : " + (_startHour = request.getParameter("starthour")));
			System.out.println(pnjs + "_endHour : " + (_endHour = request.getParameter("endhour")));
			System.out.println(pnjs + "_course : " + (_course = request.getParameter("course")));


			try{
				if(_branch != null)
					System.out.println(pnjs + "Parsed branch : " + (branch = Integer.parseInt(_branch)));
				if(_department != null)
					System.out.println(pnjs + "Parsed department : " + (department = Integer.parseInt(_department)));
				if(_designation != null)
					System.out.println(pnjs + "Parsed designation : " + (designation = Integer.parseInt(_designation)));
				if(_noOfVacancies != null)
					System.out.println(pnjs + "Parsed noOfVacancies : " + (noOfVacancies = Integer.parseInt(_noOfVacancies)));
				if(_launchDate != null)
					System.out.println(pnjs + "Parsed launchDate : " + (launchDate = Date.valueOf(_launchDate)));
				if(_applyLastDate != null)
					System.out.println(pnjs + "Parsed applyLastDate : " + (applyLastDate = Date.valueOf(_applyLastDate)));
				if(_experienceMin != null)
					System.out.println(pnjs + "Parsed experienceMin : " + (experienceMin = Integer.parseInt(_experienceMin)));
				if(_experienceMax != null)
					System.out.println(pnjs + "Parsed experienceMax : " + (experienceMax = Integer.parseInt(_experienceMax)));
				if(_ctcMax != null)
					System.out.println(pnjs + "Parsed ctcMax : " + (ctcMax = Integer.parseInt(_ctcMax)));
				if(_ctcMin != null)
					System.out.println(pnjs + "Parsed ctcMin : " + (ctcMin = Integer.parseInt(_ctcMin)));
				if(_sscScore != null)
					System.out.println(pnjs + "Parsed sscScore : " + (sscScore = Integer.parseInt(_sscScore)));
				if(_hscDiplomaScore != null)
					System.out.println(pnjs + "Parsed hscDiplomaScore : " + (hscDiplomaScore = Integer.parseInt(_hscDiplomaScore)));
				if(_graduation != null)
					System.out.println(pnjs + "Parsed graduation : " + (graduation = Integer.parseInt(_graduation)));
				if(_startHour != null)
					System.out.println(pnjs + "Parsed startHour : " + (startHour = Time.valueOf(_startHour + ":00")));
				if(_endHour != null)
					System.out.println(pnjs + "Parsed endHour : " + (endHour = Time.valueOf(_endHour + ":00")));
				if(_course != null)
					System.out.println(pnjs + "Parsed course : " + (course = Integer.parseInt(_course)));
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}
			catch(NumberFormatException e){
				e.printStackTrace();
				//numberFormatException is child of illegalArgumentException 
				//so it must be caught earlier
			}
			catch(IllegalArgumentException e){
				e.printStackTrace();
			}

			jobObject = Job.updateJobDetails(jobTitle,Branch.getBranchObject(branch),Department.getDepartmentObject(department,null),Designation.getDesignationObject(designation,null),noOfVacancies,launchDate,applyLastDate,jobDescription,experienceMin,experienceMax,ctcMin,ctcMax);
			JobScoreCriteria.updateJobScoreCriteria(jobObject,sscScore,hscDiplomaScore,graduation);
			JobSkill.updateJobSkill(jobObject,Skill.getSkillObject(1,null));
			JobShift.updateJobShift(jobObject,startHour,endHour);
			JobQualification.updateJobQualification(jobObject,Course.getCourseObject(course,null));


		}else{
			System.out.println(pnjs + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}