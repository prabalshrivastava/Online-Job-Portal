package controllers;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.File;

import models.Candidate;
import models.CandidateSkill;
import models.Qualification;

public class DownloadCandidateFileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String dcfs = "inside DownloadServlet-->";
		System.out.println("\n\n\n\n");
		HttpSession session = request.getSession();
		Candidate candidate = (Candidate)session.getAttribute("user");

		InputStream is = null;
		OutputStream os = null;
		String path = null,requestedResource = null,resource = null,fileName = null;


		if(candidate != null){
			System.out.println(dcfs + "requestedResource : " + (requestedResource = request.getParameter("resource")));
			
			if(requestedResource != null){
				System.out.print(dcfs + "resource :");
				if(requestedResource.equals("profilepicpath")){
					response.setContentType("image/png");
					System.out.print(resource =  "/profile pic/" + (fileName = candidate.getProfilePicPath()));
				}else if(requestedResource.equals("resumefilepath")){
					response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
					System.out.print(resource = "/resume/" + (fileName = candidate.getResumeFilePath()));
				}else if(requestedResource.equals("certificates")){
					//CANDIDATE	SKILL CERTIFICATES
					response.setContentType("");
					System.out.print(resource = "/certificates/" + (fileName = CandidateSkill.getCandidateSkillObject(null,null,null,null,candidate,null).getCertificate()));
				}else if(requestedResource.equals("coursecertificate")){
					//QUALIFICATIONS CERTIFICATE
					response.setContentType("");
					System.out.print(resource = "/coursecertificate/" + (fileName = Qualification.getQualificationObject(null,null,candidate,null,null,null,null).getCertificatePath()));
				}
				response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			}
			
			System.out.println(dcfs + "path : " + (path = "WEB-INF/uploads/" + candidate.getEmail() + resource));
			System.out.println(dcfs + "inputStream :" + (is = getServletContext().getResourceAsStream(path)));
			System.out.println(dcfs + "outputStream :" + (os = response.getOutputStream()));

			byte[] arr = new byte[1024];
			while(is.read(arr)!= -1)
				os.write(arr);

			os.flush();
			os.close();
		}	
	}
}
