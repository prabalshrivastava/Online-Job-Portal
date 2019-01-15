package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Candidate;
import models.Job;
import models.JobSkill;
import models.JobScoreCriteria;
import models.JobQualification;
import models.JobScoreCriteria;
import models.JobShift;


public class ShowJobDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";
		String sjd = "inside ShowJobDetatilsServlet--->";
		String _jobId = null;
		Integer jobId = null;
		Job jobObject = null;

		if(candidate != null){
			nextPage = "jobs.jsp";
			System.out.println(sjd + "_jobId : " + (_jobId = request.getParameter("jobid")));

			try{
				System.out.println(sjd + "jobId : " + (jobId = Integer.parseInt(_jobId)));
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
			session.setAttribute("jobdetails",jobObject = Job.getJobObject(jobId));
			session.setAttribute("jsc",JobScoreCriteria.getJobScoreCriteriaObject(null,jobObject,null,null,null));
			session.setAttribute("jsk",JobSkill.getJobSkillObject(null,jobObject,null));
			session.setAttribute("jqu",JobQualification.getJobQualificationObject(null,jobObject,null));
			session.setAttribute("jsh",JobShift.getJobShiftObject(null,jobObject,null,null));
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}