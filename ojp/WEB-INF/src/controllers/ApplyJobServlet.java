package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import models.Candidate;
import models.Company;
import models.Applicant;
import models.Job;

public class ApplyJobServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";
		String ajs = "inside ApplyJobServlet()--->";
		

		if(candidate != null){
			String _jobId = null;
			Integer jobId = null;

			System.out.println(ajs + "_jobId : " + (_jobId = request.getParameter("jobd")));
			try{
				System.out.println(ajs + "jobId : " + (jobId = Integer.parseInt(_jobId)));
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
//			Job job,Candidate candidate,Status status,Date applyDate
			Applicant.updateApplicant(Job.getJobObject(jobId),candidate,null,new java.sql.Date(new java.util.Date().getTime()));

		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}
