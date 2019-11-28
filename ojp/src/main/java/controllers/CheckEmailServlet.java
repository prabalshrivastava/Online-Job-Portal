package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import models.User;

public class CheckEmailServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		System.out.println("\n\n\n\n");
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String userName = User.checkEmail(email);
		System.out.println("inside CheckEmailServlet-->userName :\t\t"+userName);
		if(userName!=null){
			response.getWriter().write(userName);
			System.out.println("inside CheckEmailServlet-->if(userName!=null) :\t\ttrue");
		}
	}
}