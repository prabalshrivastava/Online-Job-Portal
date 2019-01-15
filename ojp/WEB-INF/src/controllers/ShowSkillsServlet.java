package controllers	;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;

import models.Candidate;
import models.CandidateSkill;

public class ShowSkillsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String sks = "ShowSkillsServlet-->";
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";

		if(candidate != null){
			nextPage = "skills.jsp";
			session.setAttribute("candidateskills" , CandidateSkill.collectCandidateSkills(null,null,null,candidate,null));
		}else{
			System.out.println(sks + "session expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}