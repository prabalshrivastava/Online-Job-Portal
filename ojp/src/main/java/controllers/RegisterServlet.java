package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import java.io.IOException;
import java.sql.DriverManager;

import models.UserType;
import models.User;
import models.Candidate;

public class RegisterServlet extends HttpServlet{
	Boolean flag = true;
	String msg = "",nextpage = null;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		try{
			HttpSession hs = request.getSession();
			System.out.println("registerservlet");
			String userName		=	request.getParameter("username");
			String email		=	request.getParameter("email");
			String password		=	request.getParameter("password");
			String _userTypeId	=	request.getParameter("usertypeid");
			if(email.trim().equals("")){
				msg += "email is empty <br />";
				flag = false;
			}
			if(userName.trim().equals("")){
				msg += "user name is empty <br />";
				flag = false;
			}	
			if(password.trim().equals("")){
				msg += "password is empty <br/ >";
				flag = false;
			}
			if(flag){
				Integer userTypeId = Integer.parseInt(_userTypeId);

				request.setAttribute("msg",msg);
				UserType userType = UserType.getUserTypeObject(userTypeId,null);
				if(User.registerUser(userName,email,password,userType) != null){
					flag = true;
				}else{
					flag = false;
				}
			}
		}
		catch(NumberFormatException n){
			msg += "please select user Type <br />";
			flag = false;
			n.printStackTrace();
		}
		request.setAttribute("msg",msg);
		if(flag){
			nextpage = "showlogin.do";
		}else{
			nextpage = "showregister.do";
		}
		request.getRequestDispatcher(nextpage).forward(request,response);
	}
}