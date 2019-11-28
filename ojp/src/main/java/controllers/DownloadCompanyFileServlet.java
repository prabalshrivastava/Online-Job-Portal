package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import models.Company;
import models.Candidate;
import models.User;

public class DownloadCompanyFileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws  IOException,ServletException{
		String dcls = "inside DownloadCompanyFileServlet--->";
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		Company company	= null;
		Candidate candidate = null;
		
		String _companyId = null;
		Integer companyId = null;

		if(user instanceof Company){
			System.out.println(dcls + "user is company : " + (company = (Company)user));
		}else if(user instanceof Candidate){
			System.out.println(dcls + "user is candidate : " + (candidate = (Candidate)user));
			System.out.println(dcls + "companyId recieved : " + (_companyId = request.getParameter("company")));

			if(_companyId != null){
				System.out.println(dcls + "parsed companyId : " + (companyId = Integer.parseInt(_companyId)));
				System.out.println(dcls + "companyObject is :" + (company = Company.getCompanyObject(new Company(companyId),null)));
			}
		}



		String path = null, fileName = null,requestedResource = null,resource = null;

		if(company != null){
			System.out.println(dcls + "requestedResource : " +  (requestedResource = request.getParameter("resource")));
			if(requestedResource != null){
				if(requestedResource.equals("logopath")){
					response.setContentType("image/png");
					System.out.println(dcls + "resource : " + (resource = "/companylogo/" + company.getLogoPath()));
				}
				else if(requestedResource.equals(""))
					System.out.println(dcls + "resource : ");
			}
			System.out.println(dcls + "path : " + (path = "WEB-INF/uploads/" + company.getEmail() + resource));

			InputStream is = null;
			OutputStream os = null;
			System.out.println(dcls + "inputStream : " + (is = getServletContext().getResourceAsStream(path)));
			System.out.println(dcls + "outputStream : " +  (os = response.getOutputStream()));
			byte[] arr = new byte[1024];
			while(is.read(arr) != -1){
				os.write(arr);
			}
			os.flush();
			os.close();
		}else{
			System.out.println(dcls + "Session Expired");
		}
	}
}