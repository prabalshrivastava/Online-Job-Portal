package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import models.Candidate;
import models.ContactType;
import models.Contact;
import models.UserAddress;
import models.PinCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ContactDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException,ServletException{

		HttpSession session = request.getSession();

		Candidate candidate = (Candidate)session.getAttribute("user");
		String cds = "inside ContactDetailsServlet-->";
		String nextPage = "showlogin.do";

		String permanentAddress = null ,  localAddress = null;
		String country = null , city = null ,  state = null;
		String _pinCode = null , _lpinCode = null;
		String mobile , amobile;
		String landline , alandline;
		String email , aemail;
		Integer pinCode = null,lpinCode = null;
		Boolean isAjaxRequest = false;

		if(candidate!=null){
			
			System.out.println(cds + "Permanent Address : " + (permanentAddress = request.getParameter("permanentaddress")));
			System.out.println(cds + "Local Address : " + (localAddress = request.getParameter("localaddress")));
			System.out.println(cds + "Mobile Number : " + (mobile = request.getParameter("mobile")));
			System.out.println(cds + "Alternate Mobile : " + (amobile = request.getParameter("amobile")));
			System.out.println(cds + "Landline Number : " + (landline = request.getParameter("landline")));
			System.out.println(cds + "Alternate Landline : " + (alandline = request.getParameter("alandline")));
			System.out.println(cds + "Email ID : " + (email = request.getParameter("email")));
			System.out.println(cds + "Alternate Email : " + (aemail = request.getParameter("aemail")));

			System.out.println(cds + "Permanent Pincode : " + (_pinCode = request.getParameter("pincode")));
			System.out.println(cds + "Local Pincode : " + (_lpinCode = request.getParameter("lpincode")));

			try{
				System.out.println(cds + "isAjaxRequest : " + (isAjaxRequest = request.getParameter("code").equals("1")));
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}


			try{
				pinCode = Integer.parseInt(_pinCode);
				lpinCode = Integer.parseInt(_lpinCode);
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}


			ArrayList contacts = new ArrayList(9);
			Contact contact[] = new Contact[9];				//storing nothing in 0,1,2
			UserAddress userAddress[] = new UserAddress[3];	//storing nothing in 0



			
			//avoiding indexOutOfBoundsException
			for(Object c : contacts)
				c = new Object();

			contacts.add(0,new Contact());



			//avoiding ArrayIndexOutOfBoundsException
			for(UserAddress uA : userAddress)
				uA = new UserAddress();

			for(Contact c : contact)
				c = new Contact();


			


			if(permanentAddress != null || pinCode != null)
				System.out.println(cds + (userAddress[ContactType.PERMANENT_ADDRESS] = UserAddress.updateUserAddress(candidate,ContactType.getContactTypeObject(ContactType.PERMANENT_ADDRESS,null),PinCode.getPinCodeObject(null,_pinCode,null),permanentAddress, isAjaxRequest)));

			if(localAddress != null || lpinCode != null)
				System.out.println(cds + (userAddress[ContactType.LOCAL_ADDRESS] = UserAddress.updateUserAddress(candidate,ContactType.getContactTypeObject(ContactType.LOCAL_ADDRESS,null),PinCode.getPinCodeObject(lpinCode,null,null),localAddress, isAjaxRequest)));

			if(mobile != null)
				System.out.println(cds + (contact[ContactType.MOBILE_NUMBER] = Contact.updateContact(ContactType.getContactTypeObject(ContactType.MOBILE_NUMBER,null),candidate,mobile, isAjaxRequest)));

			if(amobile != null)
				System.out.println(cds + (contact[ContactType.ALTERNATE_MOBILE_NUMBER] = Contact.updateContact(ContactType.getContactTypeObject(ContactType.ALTERNATE_MOBILE_NUMBER,null),candidate,amobile, isAjaxRequest)));

			if(landline != null)
				System.out.println(cds + (contact[ContactType.LANDLINE_NUMBER] = Contact.updateContact(ContactType.getContactTypeObject(ContactType.LANDLINE_NUMBER,null),candidate,landline, isAjaxRequest)));

			if(alandline != null)
				System.out.println(cds + (contact[ContactType.ALTERNATE_LANDLINE_NUMBER] = Contact.updateContact(ContactType.getContactTypeObject(ContactType.ALTERNATE_LANDLINE_NUMBER,null),candidate,alandline, isAjaxRequest)));

			if(email != null)
				System.out.println(cds + (contact[ContactType.EMAIL] = Contact.updateContact(ContactType.getContactTypeObject(ContactType.EMAIL,null),candidate,email, isAjaxRequest)));

			if(aemail != null)
				System.out.println(cds + (contact[ContactType.ALTERNATE_EMAIL] = Contact.updateContact(ContactType.getContactTypeObject(ContactType.ALTERNATE_EMAIL,null),candidate,aemail, isAjaxRequest)));
			
			System.out.println(cds + "userAddress[] : " + Arrays.deepToString(userAddress));
			System.out.println(cds + "contact[] : " + Arrays.deepToString(contact)); 

			contacts.add(ContactType.PERMANENT_ADDRESS,userAddress[ContactType.PERMANENT_ADDRESS]);
			contacts.add(ContactType.LOCAL_ADDRESS,userAddress[ContactType.LOCAL_ADDRESS]);

			contacts.add(ContactType.MOBILE_NUMBER,contact[ContactType.MOBILE_NUMBER]);
			contacts.add(ContactType.ALTERNATE_MOBILE_NUMBER,contact[ContactType.ALTERNATE_MOBILE_NUMBER]);
			contacts.add(ContactType.LANDLINE_NUMBER,contact[ContactType.LANDLINE_NUMBER]);
			contacts.add(ContactType.ALTERNATE_LANDLINE_NUMBER,contact[ContactType.ALTERNATE_LANDLINE_NUMBER]);
			contacts.add(ContactType.EMAIL,contact[ContactType.EMAIL]);
			contacts.add(ContactType.ALTERNATE_EMAIL,contact[ContactType.ALTERNATE_EMAIL]);
			

			session.setAttribute("contacts",contacts);
			
			nextPage = "showprojectdetails.do";
		}else{
			System.out.println("session expired");
		}


		if(isAjaxRequest)
			response.getWriter().write("successfully updated contacts");
		else
			request.getRequestDispatcher(nextPage).forward(request,response);
	}
}