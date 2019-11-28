package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.util.Arrays;
import java.io.IOException;

import models.Skill;
import models.Candidate;
import models.CandidateSkill;


public class SkillsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		CandidateSkill candidateSkill = null;

		String ss = "inside SkillsServlet-->";
		String nextPage = "showlogin.do";
		String code = null;
		Boolean isAjaxRequest = null;

		if(candidate!=null){
			nextPage = "showuploadresume.do";

			String[] versions = null ,durations = null ,_skills = null,certificates = null;
			Integer[] skills = null;

			System.out.println(ss + "skill : " + Arrays.deepToString(_skills = request.getParameterValues("skill")));
			System.out.println(ss + "version : " + Arrays.deepToString(versions = request.getParameterValues("version")));
			System.out.println(ss + "duration : " + Arrays.toString(durations = request.getParameterValues("duration")));
			System.out.println(ss + "certificates : " + Arrays.toString(certificates = request.getParameterValues("certificate")));
			System.out.println(ss + "code : " + (code = request.getParameter("code")));
			
			try{
				if(code != null)
					System.out.println(ss + "isAjaxRequest : " + (isAjaxRequest = code.equals("1")));
				else
					System.out.println(ss + "isAjaxRequest : " + (isAjaxRequest = false));

				if(_skills != null){
					skills = new Integer[_skills.length];
				}

				System.out.print(ss + "Parsed skills : ");
				int i = 0;
				for(String skill : _skills){
					System.out.println(skills[i]);
					try{
						System.out.println((skills[i++] = Integer.parseInt(skill)) + "--");
					}
					catch(NumberFormatException e){
						e.printStackTrace();
					}
				}
				System.out.println(ss + "parsed skills : " + Arrays.toString(skills));

				i = 0;
				for(Integer skill : skills){
					//System.out.println(ss + "CandidateSkill.updateCandidateSkill(Skill.getSkillObject(" + skill + ",null)," + versions[i] + "," + durations[i] + "," + candidate + ");");
					//System.out.println(ss + "duration[" + i + "] : " + durations[i] + " , version[" + i + "] : " + versions[i] + " , skills[" + i + "] : " + skills[i]);
					candidateSkill = CandidateSkill.updateCandidateSkill(Skill.getSkillObject(skill,null),versions[i],durations[i],candidate,certificates[i]);
					i++;
				}
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}
		}else{
			System.out.println(ss+"session expired");
		}
		if(isAjaxRequest)
			response.getWriter().write(candidateSkill.getCandidateSkillId().toString());
		else
			request.getRequestDispatcher(nextPage).forward(request,response);
	}
}