package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.util.List;
import java.io.IOException;
import java.io.File;

import models.Candidate;

/*
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
*/
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.*;



public class UploadCourseCertificateServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String uccs = "inside UploadCourseCertificateServlet--->";
		String nextPage = "showlogin.do";
		
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		
		if(candidate != null){
			nextPage="showuploadcoursecertificate.do";

			String fieldName = null,fieldValue = null,fileName = null,uploadPath = null;
			File filePath = null;
			List<FileItem> fileItems = null;

			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
			try{
				fileItems = servletFileUpload.parseRequest(request);
			}
			catch(FileUploadException e){
				e.printStackTrace();
			}

			System.out.println(uccs + "uploadPath : " + (uploadPath = getServletContext().getRealPath("/WEB-INF\\uploads\\" + candidate.getEmail() + "/coursecertificate/")));

			if(ServletFileUpload.isMultipartContent(request)){
				System.out.println(uccs + "Request is MultiPart");
				for(FileItem fileItem : fileItems){

					System.out.print(uccs + "fieldName : " + (fieldName = fileItem.getFieldName()));
					
					if(fileItem.isFormField()){
						System.out.println(uccs + "fieldValue : " + (fieldValue = fileItem.getString()));						
					}else{
						System.out.println(uccs + "fileName : " + (fileName = fileItem.getName()));
						System.out.println(uccs + "new directed created : " + new File(uploadPath).mkdirs());
						System.out.println(uccs + "filePath : " + (filePath = new File(uploadPath,fileName)));
						try{
							fileItem.write(filePath);
							System.out.println(uccs + "File Uploaded Successfully");
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}else{
				System.out.println(uccs + "Request is Not MultiPart");
			}
		}else{
			System.out.println(uccs + "Session Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}