package controllers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.lang.IllegalArgumentException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Enumeration;
import java.util.Collections;
import java.io.File;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import models.Candidate;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

public class PersonalDetailsServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		
		String userName = null,email = null ,password = null,fatherName = null,motherName = null,profilePicPath = null,country = null,code = null;
		Date dateOfBirth = null;
		Boolean gender = null,maritalStatus = null,flag = false,flag1 = false;
		String pds = "inside PersonalDetailsServlet-->";
		String field = null;

		System.out.println("\n\n\n\n");
		/*
		System.out.println(pds+"request URL recieved from client-->"+request.getRequestURL());
		System.out.println(pds+"request URI recieved from client-->"+request.getRequestURI());
		System.out.println(pds+request.getParameterMap());
		Enumeration parameters = request.getParameterNames();
		while(parameters.hasMoreElements()){
			System.out.println(pds+(String)parameters.nextElement());
		}
		*/

		HttpSession session  = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");
		System.out.println(pds+"candidate :\t\t"+candidate);
		
		
		if(candidate!=null){
			System.out.println(pds+"candidate object recieved from session");
			if(ServletFileUpload.isMultipartContent(request)){
				System.out.println(pds+"request is multipart");

				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

				//System.out.println(pds+PersonalDetailsServlet.getFullURL(request));
				System.out.println(pds+"payload :"+PersonalDetailsServlet.getBody(request));


				try{
					List<FileItem> fileItems = servletFileUpload.parseRequest(request);
					System.out.println(pds+"fileItems :"+fileItems);
					

					for(FileItem fileItem : fileItems){
						System.out.println(pds+"fieldname :"+fileItem.getFieldName());
						System.out.println("inside for loop");
						field = fileItem.getFieldName();
						if(fileItem.isFormField()){
							
							if(field.equals("_username")){
								userName = fileItem.getString().trim();
								System.out.println(pds+"userName :\t\t>"+userName+"<");

							}else if(field.equals("_email")){
								email = fileItem.getString().trim();
								System.out.println(pds+"email :\t\t\t>"+email+"<");

							}else if(field.equals("_password")){
								password = fileItem.getString().trim();
								System.out.println(pds+"password :\t\t>"+password+"<");

							}else if(field.equals("_dob")){
								try{
									dateOfBirth = Date.valueOf(fileItem.getString().trim());
									//valueOf() returns Date object by parsing the String passed
									//throws illegal argument exception on incorrect dateformat

								}catch(IllegalArgumentException e){
									System.out.println("\n\n\n");
									e.printStackTrace();
									System.out.println("\n\n\n");

								}
								System.out.println(pds+"dateOfBirth :\t\t>"+dateOfBirth+"<");

							}else if(field.equals("_gender")){
								String gen = fileItem.getString().toLowerCase().trim();
								gender = gen.equals("male")? true : (gen.equals("female") ? false : null);
								//male is true and female is false
								System.out.println(pds+"gender :\t\t>"+gender+"<");

							}else if(field.equals("_fname")){
								fatherName = fileItem.getString().trim();
								System.out.println(pds+"father name :\t\t>"+fatherName+"<");

							}else if(field.equals("_mname")){
								motherName = fileItem.getString().trim();
								System.out.println(pds+"mother name :\t\t>"+motherName+"<");

							}else if(field.equals("_maritalstatus")){
								String ms;								
								ms = fileItem.getString().toLowerCase().trim();		
								System.out.println(pds+"maritalstatus :\t\t>"+ms+"<");

								if(ms != null){
									ms = ms.toLowerCase();
									maritalStatus = ms.equals("married")? true :(ms.equals("unmarried")? false : null);
									//married is true unmarried is false		
									System.out.println(pds+"maritalStatus :\t\t>"+ maritalStatus+"<");
								}

							}else if(field.equals("_country")){
								country = fileItem.getString().trim();
								System.out.println(pds+"Nationality :\t\t>"+country+"<");

							}else if(field.equals("code")){
								code = fileItem.getString().trim();
								System.out.println(pds+"code :\t\t>"+code+"<");

								if(code.equals("1")){
									flag = true;
									System.out.println(pds+"ajax request recieved");
								}
							}

						}else if(field.equals("_profilepicpath")){
							profilePicPath = fileItem.getName().trim();
							System.out.println(pds+"profilePicPath :\t>"+profilePicPath+"<");

							if(profilePicPath!=null && !profilePicPath.equals("")){
								String path = getServletContext().getRealPath("/WEB-INF/uploads/"+candidate.getEmail()+"/profile pic/");
								new File(path).mkdirs();
								File filePath = new File(path,profilePicPath);
								System.out.println(pds+"filePath :\t\t>"+filePath+"<");
								fileItem.write(filePath);

							}else{
								profilePicPath = candidate.getProfilePicPath();
							}
						}else{
							System.out.println(pds+"none of the fields match inside PersonalDetailsServlet"+"<");
						}
					}//for loop end
					System.out.println("candidate.updatePersonalDetails("+candidate.getUserId()+","+userName+","+email+","+password+","+fatherName+","+motherName+","+country+")");
					flag1 = candidate.updatePersonalDetails(candidate.getUserId(),userName,email,password,fatherName,motherName,profilePicPath,country,dateOfBirth,gender,maritalStatus);

				}catch(FileUploadException e){
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		if(flag && flag1){
			System.out.println(pds+"candidate data updated using ajax request");
			response.getWriter().write("update successful");
			
		}else if(flag1){
			System.out.println(pds+"candidate update succcessful using normal request");
			request.getRequestDispatcher("displaycandidates.jsp").forward(request,response);
		}else{
			System.out.println(pds+"unable to update candidate data");
			request.getRequestDispatcher("showpersonaldetails.do").forward(request,response);
		}
	}










	public static String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}


	/*
	public static String getFullURL(HttpServletRequest request) {
		StringBuffer requestURL = request.getRequestURL();
		String queryString = request.getQueryString();

		if (queryString == null) {
			return requestURL.toString();
		} else {
			return requestURL.append('?').append(queryString).toString();
		}
	}*/

}