package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;


import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import models.UserType;
import models.User;
import models.Candidate;

public class UploadResumeServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String urs = "inside UploadResumeServlet-->";
		System.out.println("\n\n\n\n");
		HttpSession session = request.getSession();
		//System.out.println(urs);		
		User user = (User)session.getAttribute("user");
		String nextPage = "showlogin.do";
		String fileName = null;
		String uploadType = "/resume/";
		String uploadFolder = null;
		String resumeFilePath = null;

		String url = null;
		System.out.println(urs + "URL recieved : " + (url = request.getRequestURL().toString()));
		



		if(user != null){
				
			//isMultiPartContent(HttpServletRequest) of ServletFileUpload class
			//checks if the request is of multipart type or not
			if(ServletFileUpload.isMultipartContent(request)){
				
				System.out.println(urs + "MultiPart request");
				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				//child of FileItemFactory for passing into ServletFileUpload(FileItemFactory) constructor

				ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
				//ServletFileUpload(FileItemFactory) creates an initialised 
				//version of ServletFileUpload object
				try{
					List<FileItem> fileItems = null;
					System.out.println(urs + "fileItems : " + (fileItems = servletFileUpload.parseRequest(request)));
					//parseRequest(request) of ServletFileUpload breaks the request headers into
					//FileItem objects creates the List Object and wraps these items in it
					//returns the List object
					for(FileItem fileItem : fileItems){
						//enhanced for loop for iterating 
						//is isFormField() tests every request header whether it is 
						//upload field or not returns true if it is not an upload field
						if(fileItem.getFieldName().equals("uploadtype")){
							//we have checked two times for uploadtype because we want to make it design independent
							System.out.println(urs + "uploadType recieved from the client : " + (uploadType = fileItem.getString()));

							if(uploadType == null || uploadType.equals(""))
								System.out.println(urs + "uploadType sent from resume page : " + (uploadType = "/resume/"));
							else
								System.out.println(urs + "uploadType sent from an iframe : " + (uploadType = "/" + uploadType + "/"));
						}
					}

						for(FileItem fileItem : fileItems){
						if(fileItem.isFormField()){
							//formfield is a normal field
						}else{
							//formfield is a file upload field

							fileName = fileItem.getName();
							//getName() of FileItem gets the name of the file from 
							//the client's machine or name provided by the browser request
							String contextPath = getServletContext().getRealPath("WEB-INF/uploads/" + user.getEmail() + uploadType);
							//getRealPath() of ServletContext class returns the absolute context path
							//of the application
							System.out.println(urs + "does upload directory alredy exists :" + !(new File(contextPath)).mkdirs());
							//creates non existing directory
							File file = new File(contextPath,fileName);
							System.out.println(urs + "file path for resume :" + file);
							fileItem.write(file);
							//write(File) writes the file into the filesystem in the specified path
							System.out.println(urs + "file uploaded successfully");
							try{
								((Candidate)user).updateOtherDetails(null,null,null,null,null,resumeFilePath = fileName);
							}
							catch(ClassCastException e){
								e.printStackTrace();
							}
							nextPage = "showprojects.do";
						}
					}
				}
				catch(FileUploadException e){
					//parseRequest() of ServletFileUpload throws FileUploadException
					e.printStackTrace();
				}
				catch(Exception e){
					//write method of FileItem throws Exception
					e.printStackTrace();
				}
			}
		}else{
			System.out.println("Session expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
//		response.getWriter().write("successfully uploaded " + fileName);
	}
}