package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import models.Company;
import models.Branch;
import models.PinCode;

public class BranchServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");
		String bs = "inside BranchServlet--->";
		String nextPage = "showlogin.do";
		String address,_pinCode,contactNumber,contactPerson,contactEmail,branchName;
		Integer pinCode = null;
		Boolean isHeadOffice = false;

		if(company != null){
			//branch details *******head office******
			//Head Office
			System.out.println(bs + "branchName : " + (branchName = request.getParameter("branchname")));
			System.out.println(bs + "address : " + (address = request.getParameter("address")));
			System.out.println(bs + "pinCode : " + (_pinCode = request.getParameter("pincode")));
			System.out.println(bs + "contactPerson : " + (contactPerson = request.getParameter("contactperson")));
			System.out.println(bs + "contactNumber : " + (contactNumber = request.getParameter("contactnumber")));
			System.out.println(bs + "contactEmail : " + (contactEmail = request.getParameter("contactemail")));
			try{
				if(_pinCode != null){
					System.out.println(bs + "Parsed pinCode : " + (pinCode = Integer.parseInt(_pinCode)));
				}
				if(branchName != null)
					isHeadOffice = branchName.equals("Head Office");
				//here head office means that the request came from the company profile page using
				//dispatcher include
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
//			System.out.println(Branch.updateBranchDetails(branchName,address,PinCode.getPinCodeObject(pinCode,null,null),contactPerson,contactNumber,contactEmail,company));
			System.out.println("\n\n" + bs + "After updating Branch details Branch : " + Branch.updateBranchDetails(branchName,address,PinCode.getPinCodeObject(pinCode,null,null),contactPerson,contactNumber,contactEmail,company) + "\n\n");
			
		}else{
			System.out.println(bs + "Session Expired");
		}

		if(!isHeadOffice)
			request.getRequestDispatcher(nextPage).forward(request,response);
	}
	public void hello(){
		System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>hello<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
	}
}