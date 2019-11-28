<%@ page import="models.*,java.sql.*,java.util.*" %><br /><br />
<html>
	<head>
		<title>REGRESS TESTING</title>		
	</head>
		
	<body>
		<h1 style="color:red;" >THIS PAGE IS FOR TESTING PURPOSE</h1>
		
		<div>
			<%	
				User user = (User)session.getAttribute("user"); 
				//Candidate candidate = (Candidate)session.getAttribute("user");
				//Company company = (Company)session.getAttribute("user");
				
			%>
<%--
			<% company = Company.getCompanyObject(company,user); %>
			<%= company %><br /><br /><br /><br />

--%>
	
			

			<%=Company.collectCompanies()%>































			
			<%--
				***********************************************COLLECT METHODS()***************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************				
				//successful
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)   -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)-------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs(null,new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)         -------------------> <%= Job.collectJobs(null,new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",null,new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)            -------------------> <%= Job.collectJobs("jobTitle",null,new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),null,new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)                     -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),null,new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),null,2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)                      -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),null,2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),null, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)-------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),null, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, null,Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)                         -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, null,Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),null, "jobDescription", 10, 123, 928398, 389309)                         -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),null, "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), null, 10, 123, 928398, 389309)               -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), null, 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", null, 123, 928398, 389309) -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", null, 123, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, null, 928398, 389309)  -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, null, 928398, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, null, 389309)     -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, null, 389309)  %> <br /><br /><br />
				Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, null)     -------------------> <%= Job.collectJobs("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, null) %> <br /><br /><br />
				Job.collectJobs(null,null,null,null,null,null,null,null,null,null,null,null)                                                                                                                     -------------------> <%= Job.collectJobs(null,null,null,null,null,null,null,null,null,null,null,null) %>

				//successful
				Project.collectProjects(candidate)-----------><%= Project.collectProjects(candidate) %><br /> <br /> <br /> 
				Project.collectProjects(null)-----------><%= Project.collectProjects(null) %><br /> <br /> <br /> 
				Project.getProjectObject(candidate)----------><%= Project.getProjectObject(candidate) %><br /> <br /> <br /> 
				Project.getProjectObject(null)----------><%= Project.getProjectObject(null) %><br /> <br /> <br /> 


				//not yet tested
				Applicant.collectApplicants(candidate,job)----------------><%= Applicant.collectApplicants(candidate,job) %>
				Applicant.collectApplicants(candidate,job)----------------><%= Applicant.collectApplicants(candidate,job) %>
				Applicant.collectApplicants(candidate,job)----------------><%= Applicant.collectApplicants(candidate,job) %>
				Applicant.collectApplicants(candidate,job)----------------><%= Applicant.collectApplicants(candidate,job) %>
				***********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************
				//successful
				CourseType.collectCourseType()-----------<%= CourseType.collectAllCourseType() %>

				//successful
				CandidateLanguage.collectCandidateLanguageOfCandidate(candidate)<br/>			<%= CandidateLanguage.collectCandidateLanguageOfCandidate(candidate) %><br /><br /><br /><br /><br /><br /><br /><br />
				CandidateLanguage.collectCandidateLanguageOfCandidate(null)<br />     			<%= CandidateLanguage.collectCandidateLanguageOfCandidate(null) %><br /><br /><br /><br /><br /><br /><br /><br />
				
				
				
				
				##########################################################OTHER METHODS()######################################################################################################################################################################################################################################################################################################################################################################				
				//successful
				JobQualification.getJobQualificationObject(null,new Job(8),new Course(3,null,null))<%=  JobQualification.getJobQualificationObject(null,new Job(8),new Course(3,null,null)) %><br /><br /><br /><br /><br /><br />
				JobQualification.getJobQualificationObject(1,null,new Course(3,null,null))         <%=  JobQualification.getJobQualificationObject(1,null,new Course(3,null,null)) %><br /><br /><br /><br /><br /><br />
				JobQualification.getJobQualificationObject(1,new Job(8),null)                      <%=  JobQualification.getJobQualificationObject(1,new Job(8),null)%><br/>
				JobQualification.getJobQualificationObject(null,null,new Course(3,null,null))      <%=  JobQualification.getJobQualificationObject(null,null,new Course(3,null,null)) %><br /><br /><br /><br /><br /><br />
				JobQualification.getJobQualificationObject(1,null,null)                            <%=  JobQualification.getJobQualificationObject(1,null,null)%><br/><br />
				JobQualification.getJobQualificationObject(null,new Job(8),null)                   <%=  JobQualification.getJobQualificationObject(null,new Job(8),null)%><br/>
				JobQualification.getJobQualificationObject(null,null,null)                         <%=  JobQualification.getJobQualificationObject(null,null,null)%><br/>
				JobQualification.getJobQualificationObject(1,new Job(8),new Course(3,null,null))   <%=  JobQualification.getJobQualificationObject(1,new Job(8),new Course(3,null,null)) %><br /><br /><br /><br /><br /><br />

				//successful
				Branch.updateBranchDetails("Head Office", "address",PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)		<%= Branch.updateBranchDetails("head office", "address",PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)				%><br /><br /><br /><br />
				Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber","email",company)   -------------------------------------------><%= Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber","email",company)             %><br /><br /><br /><br />
				Branch.getBranchObject(1,null,"address",PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber","email",company)			-------------------------------------------><%= Branch.getBranchObject(1,null,"address",PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber","email",company)						%><br /><br /><br /><br />
				Branch.getBranchObject(1,"head office",null,PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber","email",company)		-------------------------------------------><%= Branch.getBranchObject(1,"head office",null,PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber","email",company)				 %><br /><br /><br /><br />
				Branch.getBranchObject(1,"head office","address",null,"contactPerson","contactNumber","email",company)										-------------------------------------------><%= Branch.getBranchObject(1,"head office","address",null,"contactPerson","contactNumber","email",company)												%><br /><br /><br /><br />
				Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),null,"contactNumber","email",company)			    -------------------------------------------><%= Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),null,"contactNumber","email",company)			            %><br /><br /><br /><br />
				Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),"contactPerson",null,"email",company)				-------------------------------------------><%= Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),"contactPerson",null,"email",company)					   %><br /><br /><br /><br />
				Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber",null,company)      -------------------------------------------><%= Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber",null,company)             %><br /><br /><br /><br />
				Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber","email",null)		-------------------------------------------><%= Branch.getBranchObject(1,"head office","address",PinCode.getPinCodeObject(54,null,null),"contactPerson","contactNumber","email",null)				%><br /><br /><br /><br />
		
				//successful
				CourseType.getCourseTypeObject(null,null)===============================<%=CourseType.getCourseTypeObject(null,null) %><br /><br />
				CourseType.getCourseTypeObject(null,"Diploma")=<%=CourseType.getCourseTypeObject(null,"Diploma") %><br /><br />
				CourseType.getCourseTypeObject(1,null)==================================<%=CourseType.getCourseTypeObject(1,null) %><br /><br />
				CourseType.getCourseTypeObject(1,"Diploma")====<%=CourseType.getCourseTypeObject(1,"Diploma") %><br /><br />


				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)   -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(null,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)-------------------> <%= Job.getJobObject(null,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,null,new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)         -------------------> <%= Job.getJobObject(3,null,new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",null,new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)            -------------------> <%= Job.getJobObject(3,"jobTitle",null,new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),null,new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)                     -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),null,new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),null,2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)                      -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),null,2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),null, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)-------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),null, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, null,Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)                         -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, null,Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),null, "jobDescription", 10, 123, 928398, 389309)                         -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),null, "jobDescription", 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), null, 10, 123, 928398, 389309)               -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), null, 10, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", null, 123, 928398, 389309) -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", null, 123, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, null, 928398, 389309)  -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, null, 928398, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, null, 389309)     -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, null, 389309)  %> <br /><br /><br />
				Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, null)     -------------------> <%= Job.getJobObject(3,"jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, null) %> <br /><br /><br />
				Job.getJobObject(null,null,null,null,null,null,null,null,null,null,null,null,null)                                                                                                                     -------------------> <%= Job.getJobObject(null,null,null,null,null,null,null,null,null,null,null,null,null) %>

				//successful
				<%= Job.updateJobDetails("jobTitle",new Branch(1),new Department(2,null),new Designation(2,null),2, Date.valueOf("1992-07-03"),Date.valueOf("1994-02-01"), "jobDescription", 10, 123, 928398, 389309) %>
						
								
				//successful
				Candidate.getCandidateObject(candidate,null)				----------------------------><%= 	Candidate.getCandidateObject(candidate,null)		%><br /><br /><br /><br />
				Candidate.getCandidateObject(null,user)					----------------------------><%= 	Candidate.getCandidateObject(null,user)			%><br /><br /><br /><br />
				Candidate.getCandidateObject(candidate,user)				----------------------------><%= 	Candidate.getCandidateObject(candidate,user)		%><br /><br /><br /><br />
				Candidate.getCandidateObject(null,null)					----------------------------><%= 	Candidate.getCandidateObject(null,null)			%><br /><br /><br /><br />



				Company.getCompanyObject(null,null)   >>>>>>>>>>>>>>>>>>>><%= Company.getCompanyObject(null,null) %><br /><br /><br />
				Company.getCompanyObject(null,user)   >>>>>>>>>>>>>>>>>>>><%= Company.getCompanyObject(null,user) %><br /><br /><br />
				Company.getCompanyObject(company,null)>>>>>>>>>>>>>>>>>>>><%= Company.getCompanyObject(company,null) %><br /><br /><br />
				Company.getCompanyObject(company,user)>>>>>>>>>>>>>>>>>>>><%= Company.getCompanyObject(company,user) %><br /><br /><br />


				User.getUserObject( "prabalShrivastava" , "prabalshrivastava54@gmail.com",user) ------------------------------><%= User.getUserObject( "prabalShrivastava" , "prabalshrivastava54@gmail.com",user) %><br /><br /><br />
				User.getUserObject( null , "prabalshrivastava54@gmail.com",user)                ------------------------------><%= User.getUserObject( null , "prabalshrivastava54@gmail.com",user) %><br /><br /><br />
				User.getUserObject( "prabalShrivastava" , null ,user)                           ------------------------------><%= User.getUserObject( "prabalShrivastava" , null ,user) %><br /><br /><br />
				User.getUserObject( "prabalShrivastava" , "prabalshrivastava54@gmail.com",null) ------------------------------><%= User.getUserObject( "prabalShrivastava" , "prabalshrivastava54@gmail.com",null) %><br /><br /><br />
				User.getUserObject( null , null ,user)                                          ------------------------------><%= User.getUserObject( null , null ,user) %><br /><br /><br />
				User.getUserObject( "prabalShrivastava" , null , null)                          ------------------------------><%= User.getUserObject( "prabalShrivastava" , null , null) %><br /><br /><br />
				User.getUserObject( null , "prabalshrivastava54@gmail.com",null)                ------------------------------><%= User.getUserObject( null , "prabalshrivastava54@gmail.com",null) %><br /><br /><br />
				User.getUserObject( null ,null, null)                                           ------------------------------><%= User.getUserObject( null ,null, null) %><br /><br /><br />


				
				
				//null is not at all allowed in branch table
				//so all these fail
				Branch.updateBranchDetails("branch name", "address",PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)<%= Branch.updateBranchDetails("branch name", "address",PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)		%><br /><br /><br /><br />
				Branch.updateBranchDetails(null, "address",PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)		<%= Branch.updateBranchDetails(null, "address",PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)				%><br /><br /><br /><br />
				Branch.updateBranchDetails("branch name", null,PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)	<%= Branch.updateBranchDetails("branch name", null,PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)			%><br /><br /><br /><br />
				Branch.updateBranchDetails("branch name", "address",PinCode.getPinCodeObject(null,"482001",null), null, "contactNumber", "email",company)					<%= Branch.updateBranchDetails("branch name", "address",PinCode.getPinCodeObject(null,"482001",null), null, "contactNumber", "email",company)							 %><br /><br /><br /><br />			
				//only this one gets inserted properly
				Branch.updateBranchDetails("head office", "address",PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)		<%= Branch.updateBranchDetails("head office", "address",PinCode.getPinCodeObject(null,"482001",null), "contactPerson", "contactNumber", "email",company)				%><br /><br /><br /><br />

				
				updateCompanyProfile(null,"website", "mission", "vision", "history", "awards", "aboutUs",new IndustryType(1,null))       >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile(null,"website", "mission", "vision", "history", "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile("logoPath",null, "mission", "vision", "history", "awards", "aboutUs",new IndustryType(1,null))      >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile("logoPath",null, "mission", "vision", "history", "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile("logoPath","website", null, "vision", "history", "awards", "aboutUs",new IndustryType(1,null))      >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile("logoPath","website", null, "vision", "history", "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile("logoPath","website", "mission", null, "history", "awards", "aboutUs",new IndustryType(1,null))     >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile("logoPath","website", "mission", null, "history", "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile("logoPath","website", "mission", "vision", null, "awards", "aboutUs",new IndustryType(1,null))      >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile("logoPath","website", "mission", "vision", null, "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile("logoPath","website", "mission", "vision", "history", null, "aboutUs",new IndustryType(1,null))     >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile("logoPath","website", "mission", "vision", "history", null, "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile("logoPath","website", "mission", "vision", "history", "awards", null,new IndustryType(1,null))      >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile("logoPath","website", "mission", "vision", "history", "awards", null,new IndustryType(1,null)) %><br /><br /><br />																											
				updateCompanyProfile(null,null, "mission", "vision", "history", "awards", "aboutUs",new IndustryType(1,null))            >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile(null,null, "mission", "vision", "history", "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile(null,"website", null, "vision", "history", "awards", "aboutUs",new IndustryType(1,null))            >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile(null,"website", null, "vision", "history", "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile(null,"website", "mission", null, "history", "awards", "aboutUs",new IndustryType(1,null))           >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile(null,"website", "mission", null, "history", "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile(null,"website", "mission", "vision", null , "awards", "aboutUs",new IndustryType(1,null))           >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile(null,"website", "mission", "vision", null , "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile(null,"website", "mission", "vision", "history", null, "aboutUs",new IndustryType(1,null))           >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile(null,"website", "mission", "vision", "history", null, "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
				updateCompanyProfile(null,"website", "mission", "vision", "history", "awards", null,new IndustryType(1,null))            >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile(null,"website", "mission", "vision", "history", "awards", null,new IndustryType(1,null)) %><br /><br /><br />																													
				updateCompanyProfile(null,null,null,null,null,null,null,null)                                        >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile(null,null,null,null,null,null,null,null) %><br /><br /><br />
				updateCompanyProfile("logoPath","website", "mission", "vision", "history", "awards", "aboutUs",new IndustryType(1,null)) >>>>>>>>>>>>>>>>>>>><%= company.updateCompanyProfile("logoPath","website", "mission", "vision", "history", "awards", "aboutUs",new IndustryType(1,null)) %><br /><br /><br />
																									

				UserType.getUserTypeObject(null,null)                                           <%= UserType.getUserTypeObject(null,null) %><br /><br /><br />
				UserType.getUserTypeObject(1,null)                                              <%= UserType.getUserTypeObject(1,null) %><br /><br /><br />
				UserType.getUserTypeObject(null,"candidate")                                      <%= UserType.getUserTypeObject(null,"candidate") %><br /><br /><br />
				UserType.getUserTypeObject(1,"candidate")                                         <%= UserType.getUserTypeObject(1,"candidate") %><br /><br /><br />


				<%= Qualification.updateQualificationDetails(null,new Course(1,null),candidate,new Organization(1,null,null),Date.valueOf("1994-07-07"),(float)123.0,null) %>

				Organization.getOrganizationObject(3,OrganizationType.getOrganizationTypeObject(1,null),"IB")-----------------------><%= Organization.getOrganizationObject(3,OrganizationType.getOrganizationTypeObject(1,null),"IB")	%> <br /> <br /> <br />
				Organization.getOrganizationObject(null,OrganizationType.getOrganizationTypeObject(1,null),"IB")--------------------><%= Organization.getOrganizationObject(null,OrganizationType.getOrganizationTypeObject(1,null),"IB")	%> <br /> <br /> <br />
				Organization.getOrganizationObject(3,null,"IB")---------------------------------------------------------------------><%= Organization.getOrganizationObject(3,null,"IB")							%> <br /> <br /> <br />
				Organization.getOrganizationObject(3,OrganizationType.getOrganizationTypeObject(1,null),null)-----------------------><%= Organization.getOrganizationObject(3,OrganizationType.getOrganizationTypeObject(1,null),null)	%> <br /> <br /> <br />
				Organization.getOrganizationObject(null,null,"IB")------------------------------------------------------------------><%= Organization.getOrganizationObject(null,null,"IB")							%> <br /> <br /> <br />
				Organization.getOrganizationObject(3,null,null)---------------------------------------------------------------------><%= Organization.getOrganizationObject(3,null,null)							%> <br /> <br /> <br />
				Organization.getOrganizationObject(null,OrganizationType.getOrganizationTypeObject(1,null),null)--------------------><%= Organization.getOrganizationObject(null,OrganizationType.getOrganizationTypeObject(1,null),null)	%> <br /> <br /> <br />
				Organization.getOrganizationObject(null,null,null)------------------------------------------------------------------><%= Organization.getOrganizationObject(null,null,null)							%> <br /> <br /> <br />



				Course.getCourseObject( 1 , "BE/BTECH - Aerospace Engineering")-------<%= Course.getCourseObject( 1 , "BE/BTECH - Aerospace Engineering") %><br /><br />
				Course.getCourseObject( 1 , null)-------------------------------------<%= Course.getCourseObject( 1 , null) %><br /><br />
				Course.getCourseObject( null , "BE/BTECH - Aerospace Engineering")----<%= Course.getCourseObject( null , "BE/BTECH - Aerospace Engineering") %><br /><br />
				Course.getCourseObject( null , null)----------------------------------<%= Course.getCourseObject( null , null) %><br /><br />

				

				//successful
				CandidateLanguage.getCandidateLanguageObject(Integer candidateLanguageId,Language language,LanguageCapability languageCapability,Candidate candidate)<br /><br /><br /><br /><br />
				CandidateLanguage.getCandidateLanguageObject(null,Language.getLanguageObject(1,null),LanguageCapability.getLanguageCapabilityObject(22,null),candidate)        				<%= CandidateLanguage.getCandidateLanguageObject(null,Language.getLanguageObject(1,null),LanguageCapability.getLanguageCapabilityObject(22,null),candidate) %><br /><br /><br />
				CandidateLanguage.getCandidateLanguageObject(null,null,LanguageCapability.getLanguageCapabilityObject(22,null),candidate)				     				<%= CandidateLanguage.getCandidateLanguageObject(null,null,LanguageCapability.getLanguageCapabilityObject(22,null),candidate) %><br /><br /><br />
				CandidateLanguage.getCandidateLanguageObject(null,Language.getLanguageObject(1,null),null,candidate)      										<%= CandidateLanguage.getCandidateLanguageObject(null,Language.getLanguageObject(1,null),null,candidate) %><br /><br /><br />
				CandidateLanguage.getCandidateLanguageObject(null,Language.getLanguageObject(1,null),LanguageCapability.getLanguageCapabilityObject(22,null),null)      				<%= CandidateLanguage.getCandidateLanguageObject(null,Language.getLanguageObject(1,null),LanguageCapability.getLanguageCapabilityObject(22,null),null) %><br /><br /><br />
				CandidateLanguage.getCandidateLanguageObject(null,null,null,candidate)   														<%= CandidateLanguage.getCandidateLanguageObject(null,null,null,candidate) %><br /><br /><br />
				CandidateLanguage.getCandidateLanguageObject(null,Language.getLanguageObject(1,null),null,null)    											<%= CandidateLanguage.getCandidateLanguageObject(null,Language.getLanguageObject(1,null),null,null) %><br /><br /><br />
				CandidateLanguage.getCandidateLanguageObject(null,null,LanguageCapability.getLanguageCapabilityObject(22,null),null)   									<%= CandidateLanguage.getCandidateLanguageObject(null,null,LanguageCapability.getLanguageCapabilityObject(22,null),null) %><br /><br /><br />
				CandidateLanguage.getCandidateLanguageObject(null,null,null,null)															<%= CandidateLanguage.getCandidateLanguageObject(null,null,null,null) %><br /><br /><br />
				



				//successful
				Language.getLanguageObject(null,null)    			<%= Language.getLanguageObject(null,null) %><br /><br /><br />
				Language.getLanguageObject(1,null)       			<%= Language.getLanguageObject(1,null) %><br /><br /><br />
				Language.getLanguageObject(1,"French")   			<%= Language.getLanguageObject(1,"French") %><br /><br /><br />
				Language.getLanguageObject(null,"French")			<%= Language.getLanguageObject(null,"French") %><br /><br /><br />			


				
				candidate.getCandidateId()        			<%=candidate.getCandidateId()%><br /><br/>
				candidate.getDateOfBirth()        			<%=candidate.getDateOfBirth()%><br /><br/>
				candidate.getGender()             			<%=candidate.getGender()%><br /><br/>
				candidate.getHobbies()            			<%=candidate.getHobbies()%><br /><br/>
				candidate.getUserId()             			<%=candidate.getUserId()%><br /><br/>
				candidate.getMaritalStatus()      			<%=candidate.getMaritalStatus()%><br /><br/>
				candidate.getCountry()            			<%=candidate.getCountry()%><br /><br/>
				candidate.getIntrest()            			<%=candidate.getIntrest()%><br /><br/>
				candidate.getProfilePicPath()     			<%=candidate.getProfilePicPath()%><br /><br/>
				candidate.getResumeFilePath()     			<%=candidate.getResumeFilePath()%><br /><br/>
				candidate.getResumeUploadDate()   			<%=candidate.getResumeUploadDate()%><br /><br/>
				candidate.getStrengths()          			<%=candidate.getStrengths()%><br /><br/>
				candidate.getWeaknesses()         			<%=candidate.getWeaknesses()%><br /><br/>
				candidate.getCurricularActivity() 			<%=candidate.getCurricularActivity()%><br /><br/>
				candidate.getFatherName()         			<%=candidate.getFatherName()%><br /><br/>
				candidate.getMotherName()         			<%=candidate.getMotherName()%><br /><br/>

				//successful
				LanguageCapability.getLanguageCapabilityObject(16,null)------------<%= LanguageCapability.getLanguageCapabilityObject(16,null) %>
				LanguageCapability.getLanguageCapabilityObject(null,"read")------------<%= LanguageCapability.getLanguageCapabilityObject(null,"read") %>
				LanguageCapability.getLanguageCapabilityObject(16,"read")------------<%= LanguageCapability.getLanguageCapabilityObject(16,"read") %>
				LanguageCapability.getLanguageCapabilityObject(null,null)------------<%= LanguageCapability.getLanguageCapabilityObject(null,null) %>

				${contacts[0]}			
				${contacts[1]}
				${contacts[2]}
				${contacts[3]}			
				${contacts[4]}			
				${contacts[5]}			
				${contacts[6]}

				//regress testing of getUserAddressObject() 8 combinations considering contactType always null
				//successful
				Integer userAddressId , User user , ContactType contactType , PinCode pinCode , String address){<br /><br /><br />
				<% String address = ""; %>
				UserAddress.getUserAddressObject(null,null,null,null,null)-------------<%= UserAddress.getUserAddressObject(null,null,null,null,null) %><br /><br /><br />
				UserAddress.getUserAddressObject(1,null,null,null,null)-------------<%= UserAddress.getUserAddressObject(1,null,null,null,null) %><br /><br /><br />
				UserAddress.getUserAddressObject(null,user,null,null,null)-------------<%= UserAddress.getUserAddressObject(null,user,null,null,null) %><br /><br /><br />
				UserAddress.getUserAddressObject(1,null,null,null,"hello")-------------<%= UserAddress.getUserAddressObject(1,null,null,null,"hello") %><br /><br /><br />
				UserAddress.getUserAddressObject(null,user,null,null,"hello")-------------<%= UserAddress.getUserAddressObject(null,user,null,null,"hello") %><br /><br /><br />
				UserAddress.getUserAddressObject(1,user,null,null,null)-------------<%= UserAddress.getUserAddressObject(1,user,null,null,null) %><br /><br /><br />
				UserAddress.getUserAddressObject(1,user,null,null,"hello")-------------<%= UserAddress.getUserAddressObject(1,user,null,null,"hello") %><br /><br /><br />

				//regress testing of getContactObject() 8 combinations considering contactType always null
				//successful
				Contact.getContactObject(1,null,null,null)--------------<%= Contact.getContactObject(1,null,null,null) %><br /><br /><br />
				Contact.getContactObject(null,null,(User)session.getAttribute("user"),null)---------------<%= Contact.getContactObject(null,null,(User)session.getAttribute("user"),null) %><br /><br /><br />
				Contact.getContactObject(null,null,null,"8871370467")-------------------<%= Contact.getContactObject(null,null,null,"8871370467") %><br /><br /><br />


				Contact.getContactObject(null,null,(User)session.getAttribute("user"),"8871370467")---------------<%= Contact.getContactObject(null,null,(User)session.getAttribute("user"),"8871370467") %><br /><br /><br />
				Contact.getContactObject(2,null,(User)session.getAttribute("user"),null)---------------<%= Contact.getContactObject(2,null,(User)session.getAttribute("user"),null) %><br /><br /><br />
				Contact.getContactObject(2,null,null,"8871370467")---------------<%= Contact.getContactObject(2,null,null,"8871370467") %><br /><br /><br />
				Contact.getContactObject(2,null,(User)session.getAttribute("user"),"8871370467")---------------<%= Contact.getContactObject(2,null,(User)session.getAttribute("user"),"8871370467") %><br /><br /><br />

				
				ContactType.collectAllContactTypes()---------------------<%= ContactType.collectAllContactTypes()%>			

				//regress testing of getPinCodeObject()
				//successful
				PinCode.getPinCodeObject(null,null,null)--------------------------<%=PinCode.getPinCodeObject(null,null,null)%><br /><br />
				PinCode.getPinCodeObject(61,null,null)--------------------------<%=PinCode.getPinCodeObject(61,null,null)%><br /><br />
				PinCode.getPinCodeObject(null,"482001",null)--------------------------<%=PinCode.getPinCodeObject(null,"482001",null)%><br /><br />
				PinCode.getPinCodeObject(null,null,new City(1,null,null))--------------------------<%=PinCode.getPinCodeObject(null,null,new City(1,null,null))%><br /><br />
				PinCode.getPinCodeObject(62,"483775",null)--------------------------<%=PinCode.getPinCodeObject(62,"483775",null)%><br /><br />
				PinCode.getPinCodeObject(63,null,new City(2,null,null))--------------------------<%=PinCode.getPinCodeObject(63,null,new City(2,null,null))%><br /><br />
				PinCode.getPinCodeObject(null,"583773",new City(6,null,null))--------------------------<%=PinCode.getPinCodeObject(null,"583773",new City(6,null,null))%><br /><br />
				PinCode.getPinCodeObject(86,"583005",new City(7,null,null))--------------------------<%=PinCode.getPinCodeObject(86,"583005",new City(7,null,null))%><br /><br />

				//regress testing of getCityObject()
				//successful
				City.getCityObject(null,null,null)--------------------------<%=City.getCityObject(null,null,null)%><br /><br />
				City.getCityObject(1,null,null)--------------------------<%=City.getCityObject(1,null,null)%><br /><br />
				City.getCityObject(null,"Nellore",null)--------------------------<%=City.getCityObject(null,"Nellore",null)%><br /><br />
				City.getCityObject(null,null,new State(1,null,null))--------------------------<%=City.getCityObject(null,null,new State(1,null,null))%><br /><br />
				City.getCityObject(2,"indore",null)--------------------------<%=City.getCityObject(2,"indore",null)%><br /><br />
				City.getCityObject(3,null,new State(1,null,null))--------------------------<%=City.getCityObject(3,null,new State(1,null,null))%><br /><br />
				City.getCityObject(null,"gwalior",new State(1,null,null))--------------------------<%=City.getCityObject(null,"gwalior",new State(1,null,null))%><br /><br />
				City.getCityObject(6,"messa",new State(5,null,null))--------------------------<%=City.getCityObject(6,"messa",new State(5,null,null))%><br /><br />


				//regress testing of getCountryObject()
				//successful
				(Country.getCountryObject(1,null))------------------------<%=(Country.getCountryObject(1,null))%><br /><br />
				(Country.getCountryObject(null,"usa"))------------------------<%=(Country.getCountryObject(null,"usa"))%><br /><br />
				(Country.getCountryObject(3,"pakistan"))------------------------<%=(Country.getCountryObject(3,"pakistan"))%><br /><br />
				(Country.getCountryObject(null,null))------------------------<%=(Country.getCountryObject(null,null))%><br /><br />


				//regress testing of getStateObject()
				//successful
				State.getStateObject(null,null,null)--------------------------<%=State.getStateObject(null,null,null)%><br /><br />
				State.getStateObject(1,null,null)--------------------------<%=State.getStateObject(1,null,null)%><br /><br />
				State.getStateObject(null,"mp",null)--------------------------<%=State.getStateObject(null,"mp",null)%><br /><br />
				State.getStateObject(null,null,new Country(1,null))--------------------------<%=State.getStateObject(null,null,new Country(1,null))%><br /><br />
				State.getStateObject(2,"up",null)--------------------------<%=State.getStateObject(2,"up",null)%><br /><br />
				State.getStateObject(3,null,new Country(1,null))--------------------------<%=State.getStateObject(3,null,new Country(1,null))%><br /><br />
				State.getStateObject(null,"jk",new Country(1,null))--------------------------<%=State.getStateObject(null,"jk",new Country(1,null))%><br /><br />
				State.getStateObject(6,"texas",new Country(2,null))--------------------------<%=State.getStateObject(6,"texas",new Country(2,null))%><br /><br />

				<%==(PinCode.getPincodeObject(pincode,null))%><br /><br />

				ContactType.getContactTypeObject(1,null)------------------------<%=ContactType.getContactTypeObject(1,null)%><br /><br />
				ContactType.getContactTypeObject(null,"Alternate Landline Number")------------------------<%=ContactType.getContactTypeObject(null,"Alternate Landline Number")%><br /><br />
				ContactType.getContactTypeObject( 4 ,"Alternate Mobile Number")------------------------<%=ContactType.getContactTypeObject( 4 ,"Alternate Mobile Number")%><br /><br />
				ContactType.getContactTypeObject(null,null)------------------------<%=ContactType.getContactTypeObject(null,null)%>	<br /><br />
							

				OrganizationType.getOrganizationTypeObject(1 , null)------------------------<%=OrganizationType.getOrganizationTypeObject(1 , null)%><br /><br />
				OrganizationType.getOrganizationTypeObject(null , "BOARD")------------------------<%=OrganizationType.getOrganizationTypeObject(null , "BOARD")%>	<br /><br />
				OrganizationType.getOrganizationTypeObject(1 , "BOARD")------------------------<%=OrganizationType.getOrganizationTypeObject(1 , "BOARD")%><br /><br />
				OrganizationType.getOrganizationTypeObject(null , null)------------------------<%=OrganizationType.getOrganizationTypeObject(null , null)%><br /><br />

				CandidateSkill.updateCandidateSkill(1,'version','duration',candidate)		<%= CandidateSkill.updateCandidateSkill(Skill.getSkillObject(1,null),"version","duration",candidate)%>
			--%>

		</div>
		<%
			String rt = "inside REGRESS_TESTING.JSP-->";
			
			
			/*
			//regress testing of getCountryObject() 4 combinations
			//successful
			System.out.println(rt + (Country.getCountryObject(1,null)));
			System.out.println(rt + (Country.getCountryObject(null,"usa")));
			System.out.println(rt + (Country.getCountryObject(3,"pakistan")));
			System.out.println(rt + (Country.getCountryObject(null,null)));


			//regress testing of getStateObject() 8 combinations
			//successful
			System.out.println(rt + (State.getStateObject(null,null,null)));
			System.out.println(rt + (State.getStateObject(1,null,null)));
			System.out.println(rt + (State.getStateObject(null,"mp",null)));
			System.out.println(rt + (State.getStateObject(null,null,new Country(1,null))));
			System.out.println(rt + (State.getStateObject(2,"up",null)));
			System.out.println(rt + (State.getStateObject(3,null,new Country(1,null))));
			System.out.println(rt + (State.getStateObject(null,"jk",new Country(1,null))));
			System.out.println(rt + (State.getStateObject(6,"texas",new Country(2,null))));
			*/
//			System.out.println(rt + (PinCode.getPincodeObject(pincode,null)));

			/*
			System.out.println(ContactType.getContactTypeObject(1,null));
			System.out.println(ContactType.getContactTypeObject(null,"Alternate Landline Number"));
			System.out.println(ContactType.getContactTypeObject( 4 ,"Alternate Mobile Number"));
			System.out.println(ContactType.getContactTypeObject(null,null));

			System.out.println(OrganizationType.getOrganizationTypeObject(1 , null));
			System.out.println(OrganizationType.getOrganizationTypeObject(null , "BOARD"));
			System.out.println(OrganizationType.getOrganizationTypeObject(1 , "BOARD"));
			System.out.println(OrganizationType.getOrganizationTypeObject(null , null));
			*/
		%>
		<br /><br />






















































<%--		
((((((((((((((((((((((((TESTING REGARDING POST NEW JOB PAGE))))))))))))))))))))))))	
<%		
		String spnjs = "inside ShowPostNewJobServlet--->";
		String nextPage = "showlogin.do";

		
		ArrayList<Job> jobs = new ArrayList<Job>() , jobsInThisBranch = null;
		ArrayList<Branch> branches = null;
		ArrayList<JobShift> jobShifts = new ArrayList<JobShift>();
		ArrayList<JobSkill> jobSkills = new ArrayList<JobSkill>();
		ArrayList<JobScoreCriteria> jobScoreCriterias = new ArrayList<JobScoreCriteria>();
		ArrayList<JobQualification> jobQualifications = new ArrayList<JobQualification>();
		//ArrayList<ArrayList<Job>> jobsInAllBranches = null;

		if(company != null){
			nextPage = "post_new_job.jsp";
			
			session.setAttribute("branches",branches = Branch.collectBranches(company));
			for(Branch branch : branches){
				System.out.println(spnjs + "JobsInThisBranch : " + (jobsInThisBranch = Job.collectJobs(null,branch,null,null,null,null,null,null,null,null,null,null)));

				for(Job job : jobsInThisBranch){
					jobs.add(job);
				}
			}
			/*
			for(ArrayList<Job> job : jobsInAllBranches){
				jobs.add(job);
			}*/

			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

			for(Job job : jobs){
				jobScoreCriterias.add(JobScoreCriteria.getJobScoreCriteriaObject(null,job,null,null,null));//Integer jobScoreCriteriaId,Job job,Integer sscScore,Integer hscDiplomaScore,Integer graduation
				jobShifts.add(JobShift.getJobShiftObject(null,job,null,null));//(Integer jobShiftId,Job job,Time startHour,Time endHour)
				jobQualifications.add(JobQualification.getJobQualificationObject(null,job,null));//Integer jobQualificationId,Job job,Course course
				jobSkills.add(JobSkill.getJobSkillObject(null,job,null));//Integer jobSkillId,Job job,Skill skill
			}
			session.setAttribute("jobs",jobs);
			session.setAttribute("jobscorecriterias",jobScoreCriterias);
			session.setAttribute("jobshifts",jobShifts);
			session.setAttribute("jobqualfications",jobQualifications);
			session.setAttribute("jobskills",jobSkills);
			
			out.println("jobs"+jobs+"<br /><br /><br /><br /><br /><br />");
			out.println("jobscorecriterias"+jobScoreCriterias+"<br /><br /><br /><br /><br /><br />");
			out.println("jobshifts"+jobShifts+"<br /><br /><br /><br /><br /><br />");
			out.println("jobqualfications"+jobQualifications+"<br /><br /><br /><br /><br /><br />");
			out.println("jobskills"+jobSkills+"<br /><br /><br /><br /><br /><br />");
			

		}else{	
			System.out.println(spnjs + "Session Expired");
		}
%>
--%>



		<%--
			Branch.collectBranches(company)-----><%= Branch.collectBranches(company) %>
		--%>
	</body>
</html>