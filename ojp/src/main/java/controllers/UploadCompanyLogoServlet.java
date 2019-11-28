package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.File;
import java.util.List;
import models.Company;


import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileItem;



public class UploadCompanyLogoServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String nextPage = "showlogin.do";
		String ucls = "inside UploadCompanyLogoServlet--->";

		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("user");
		
		String fileName = null,fieldValue = null,uploadPath = null,fieldName = null;
		File filePath = null;
		List<FileItem> fileItems = null;

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		
		System.out.println(ucls + (uploadPath = getServletContext().getRealPath("/WEB-INF\\uploads/" + company.getEmail() + "/companylogo")));
		try{
			System.out.println(ucls + (fileItems = servletFileUpload.parseRequest(request)));
		}
		catch(FileUploadException e){
			e.printStackTrace();
		}
		if(company != null){
			nextPage = "showuploadcompanylogo.do";
			if(ServletFileUpload.isMultipartContent(request)){
				for(FileItem fileItem: fileItems){
					System.out.println(ucls + "request is MultiPart");
					System.out.println(ucls + "fieldName : " + (fieldName = fileItem.getFieldName()));

					if(fileItem.isFormField()){
						System.out.println(ucls + "fieldValue : " + (fieldValue = fileItem.getString()));
					}else{
						System.out.println(ucls + "fileName : " + (fileName = fileItem.getName()));
						System.out.println(ucls + "filePath : " + (filePath = new File(uploadPath,fileName)));
						System.out.println(ucls + "Directory Already Exists : " + new File(uploadPath).mkdirs());
						try{
							fileItem.write(filePath);
							System.out.println(ucls + "File Uploaded Successfully");
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}else{
				System.out.println(ucls + "request is not MultiPart");
			}
		}else{
			System.out.println(ucls + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}
