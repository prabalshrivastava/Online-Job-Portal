package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.Job;
import models.Branch;
import models.Company;
import models.JobShift;
import models.JobSkill;
import models.JobScoreCriteria;
import models.JobQualification;

public class ShowPostNewJobServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");
		String spnjs = "inside ShowPostNewJobServlet--->";
		String nextPage = "showlogin.do";


		ArrayList<Job> jobs = new ArrayList<Job>() , jobsInThisBranch = null;
		ArrayList<Branch> branches = null;
		ArrayList<JobShift> jobShifts = new ArrayList<JobShift>();
		ArrayList<JobSkill> jobSkills = new ArrayList<JobSkill>();
		ArrayList<JobScoreCriteria> jobScoreCriterias = new ArrayList<JobScoreCriteria>();
		ArrayList<JobQualification> jobQualifications = new ArrayList<JobQualification>();
		//ArrayList<ArrayList<Job>> jobsInAllBranches = null;

		if(company != null){
			nextPage = "post_new_job.jsp";
			
			session.setAttribute("branches",branches = Branch.collectBranches(company));
			for(Branch branch : branches){
				System.out.println(spnjs + "JobsInThisBranch : " + (jobsInThisBranch = Job.collectJobs(null,branch,null,null,null,null,null,null,null,null,null,null)));

				for(Job job : jobsInThisBranch){
					jobs.add(job);
				}
			}
			/*
			for(ArrayList<Job> job : jobsInAllBranches){
				jobs.add(job);
			}*/

			
			for(Job job : jobs){
				jobScoreCriterias.add(JobScoreCriteria.getJobScoreCriteriaObject(null,job,null,null,null));//Integer jobScoreCriteriaId,Job job,Integer sscScore,Integer hscDiplomaScore,Integer graduation
				jobShifts.add(JobShift.getJobShiftObject(null,job,null,null));//(Integer jobShiftId,Job job,Time startHour,Time endHour)
				jobQualifications.add(JobQualification.getJobQualificationObject(null,job,null));//Integer jobQualificationId,Job job,Course course
				jobSkills.add(JobSkill.getJobSkillObject(null,job,null));//Integer jobSkillId,Job job,Skill skill
			}
			session.setAttribute("jobs",jobs);
			session.setAttribute("jobscorecriterias",jobScoreCriterias);
			session.setAttribute("jobshifts",jobShifts);
			session.setAttribute("jobqualifications",jobQualifications);
			session.setAttribute("jobskills",jobSkills);
			
		}else{	
			System.out.println(spnjs + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}