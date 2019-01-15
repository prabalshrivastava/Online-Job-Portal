package controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ShowRegisterServlet extends HttpServlet{
		public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
			System.out.println("\n\n\n\n");
			HttpSession hs=request.getSession();
			System.out.println("inside ShowRegisterServlet");

			RequestDispatcher rs=request.getRequestDispatcher("register.jsp");
			rs.forward(request,response);
		}
}