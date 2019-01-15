package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Candidate;
import models.CandidateSkill;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;



public class UploadCertificateServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		
		String ucs = "inside UploadCertificateServlet--->";
		String nextPage = "showlogin.do";

		if(candidate != null){
			nextPage = "showuploadcertificate.do";
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

			String fieldName = null;
			String fieldValue = null;
			String contextPath = null;
			String uploadPath = null;

			System.out.println(ucs + "contextPath : " + (contextPath = getServletContext().getRealPath("")));
			System.out.println(ucs + "uploadPath : " + (uploadPath = "/WEB-INF\\uploads\\" + candidate.getEmail() + "/certificates"));
			//BECAUSE \ IS ESCAPE SEQUENCE WE USE \\ OR /

			File file = new File(contextPath,uploadPath);
			System.out.println(ucs + "file : " + file);

			if(ServletFileUpload.isMultipartContent(request)){
				System.out.println(ucs + "Request is multiPart");
				List<FileItem> fileItems = null;				
				try{
					System.out.println(ucs + "fileItems : " + (fileItems = servletFileUpload.parseRequest(request)));
				}
				catch(FileUploadException e){
					e.printStackTrace();
				}
				try{
					for(FileItem fileItem : fileItems){
						System.out.println(ucs + "fieldName : " + (fieldName = fileItem.getFieldName()));

						if(fileItem.isFormField()){
							System.out.println(ucs + "fieldValue : " + (fieldValue = fileItem.getString()));
						}else{
							System.out.println(ucs + "Now Uploading File");
							try{
								System.out.println(ucs + "Does Directory '" + file +  "' is non-Existent : " + file.mkdirs());
								File completeFile = null;
								System.out.println(ucs + "completeFile : " + (completeFile = new File(file.getAbsolutePath(),fileItem.getName())));
								fileItem.write(completeFile);
								System.out.println(ucs + "file Uploaded Successfully");
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
				catch(NullPointerException e){
					e.printStackTrace();
				}
			}else{
				System.out.println(ucs + "Request is not MultiPart");
			}
		}else{
			System.out.println(ucs + "session Expired");
		}
		System.out.println(ucs + "Redirecting to NextPage");
		request.getRequestDispatcher(nextPage).forward(request,response);
	}	
}