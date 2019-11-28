package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.ArrayList;
import models.User;
import models.Candidate;
import models.Company;
import models.Organization;
import models.UserType;

public class LoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		System.out.println("\n\n\n\n");
		boolean flag = true;
		String msg = "";
		String nextPage = "login.jsp";
		String ls = "inside loginServlet-->";
		HttpSession hs = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(ls + "email :\t\t"+email);
		System.out.println(ls + "password :\t\t"+password);

		if(email.trim().equals("")){
			msg = "\nemail is empty <br />";
			flag = false;
		}
		if(password.trim().equals("")){
			msg	+= "\npassword is empty <br />";
			flag  =	false;
		}
		if(flag){


			//previously we were Instansiating User object but realized better to 
			//instantiate its childrens
			
			User user = User.loginUser(email,password);
			String userType;
			if(user!=null){
				if(user.getUserType().getUserTypeId() == UserType.CANDIDATE){
					nextPage = "showpersonaldetails.do";
					userType = "candidate";	//curently not being used
					user = (Candidate)user;
					System.out.println(ls + (user = Candidate.getCandidateObject((Candidate)user,(User)user)));
				}else{
					nextPage = "showcompanyprofile.do";
					userType = "company";	//currently not being used
					user = (Company)user;
					System.out.println(ls + (user = Company.getCompanyObject((Company)user,(User)user)));
				}
				hs.setAttribute("user",user);
				//alternative
				//hs.setAttribute(userType,user);
				//alternative
				//hs.setAttribute(user.getUserType().getUserType(),user);
				//System.out.println(user.getUserType().getUserType());
			}else{
				msg	+= "\ninvalid username or password <br/>";
			}
		}

		request.setAttribute("msg",msg);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request,response);
	}
}