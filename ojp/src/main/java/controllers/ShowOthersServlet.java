package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;

import models.Candidate;
import models.CandidateLanguage;

public class ShowOthersServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";
		String sos = "inside ShowOthersServlet-->";

		if(candidate != null){
			System.out.println("\n\n" + sos + Candidate.getCandidateObject(candidate,null));
			System.out.println(sos + candidate + "\n\n");
			session.setAttribute("user",candidate);
			session.setAttribute("candidatelanguage",CandidateLanguage.collectCandidateLanguages(candidate,null));

			nextPage = "others.jsp";
		}else{
			System.out.println("session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}