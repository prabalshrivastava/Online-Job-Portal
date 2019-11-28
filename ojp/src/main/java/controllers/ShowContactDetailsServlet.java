package controllers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.Candidate;
import models.Contact;
import models.ContactType;
import models.UserAddress;


public class ShowContactDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		String nextPage = "showlogin.do";

		if(candidate!=null){
			nextPage = "contact_details.jsp";
			ArrayList contacts = new ArrayList(9);

			/*
			PERMANENT_ADDRESS = 1;        
			LOCAL_ADDRESS = 2;            
			MOBILE_NUMBER = 3;            
			ALTERNATE_MOBILE_NUMBER = 4;  
			LANDLINE_NUMBER = 5;          
			ALTERNATE_LANDLINE_NUMBER = 6;
			EMAIL = 7;
			ALTERNATE_EMAIL = 8;			
			*/

			contacts.add(new Contact(null,null,null,null));

			contacts.add(ContactType.PERMANENT_ADDRESS,UserAddress.getUserAddressObject(null,candidate,ContactType.getContactTypeObject(ContactType.PERMANENT_ADDRESS,null),null,null));
			contacts.add(ContactType.LOCAL_ADDRESS,UserAddress.getUserAddressObject(null,candidate,ContactType.getContactTypeObject(ContactType.LOCAL_ADDRESS,null),null,null));
		
			
			contacts.add(ContactType.MOBILE_NUMBER,Contact.getContactObject(null,ContactType.getContactTypeObject(ContactType.MOBILE_NUMBER,null),candidate,null));
			contacts.add(ContactType.ALTERNATE_MOBILE_NUMBER,Contact.getContactObject(null,ContactType.getContactTypeObject(ContactType.ALTERNATE_MOBILE_NUMBER,null),candidate,null));
			contacts.add(ContactType.LANDLINE_NUMBER,Contact.getContactObject(null,ContactType.getContactTypeObject(ContactType.LANDLINE_NUMBER,null),candidate,null));
			contacts.add(ContactType.ALTERNATE_LANDLINE_NUMBER,Contact.getContactObject(null,ContactType.getContactTypeObject(ContactType.ALTERNATE_LANDLINE_NUMBER,null),candidate,null));
			contacts.add(ContactType.EMAIL,Contact.getContactObject(null,ContactType.getContactTypeObject(ContactType.EMAIL,null),candidate,null));
			contacts.add(ContactType.ALTERNATE_EMAIL,Contact.getContactObject(null,ContactType.getContactTypeObject(ContactType.ALTERNATE_EMAIL,null),candidate,null));

			
			session.setAttribute("contacts",contacts);

			
			session.setAttribute("PERMANENT_ADDRESS",ContactType.PERMANENT_ADDRESS);
			session.setAttribute("LOCAL_ADDRESS",ContactType.LOCAL_ADDRESS);

			session.setAttribute("MOBILE_NUMBER",ContactType.MOBILE_NUMBER);
			session.setAttribute("ALTERNATE_MOBILE_NUMBER",ContactType.ALTERNATE_MOBILE_NUMBER);
			session.setAttribute("LANDLINE_NUMBER",ContactType.LANDLINE_NUMBER);
			session.setAttribute("ALTERNATE_LANDLINE_NUMBER",ContactType.ALTERNATE_LANDLINE_NUMBER);
			session.setAttribute("EMAIL",ContactType.EMAIL);
			session.setAttribute("ALTERNATE_EMAIL",ContactType.ALTERNATE_EMAIL);
			

		}else{
			System.out.println("session expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}
	