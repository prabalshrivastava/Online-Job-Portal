<%@ page import="models.IndustryType,models.Department,models.Designation,models.Experience" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Experience Details</title>
		<link rel="stylesheet" type="text/css" href="css/experiencedetails.css" />
		<script type="text/javascript" src="js/experiencedetails.js"></script>
		<script type="text/javascript" src="js/table_report.js"></script>
		
		<c:import url="page.jsp" />



	</head>
	<body>
		<table>
			<form action="experiencedetails.do">
				
				<%--<% Candidate candidate = (Candidate)session.getAttribute();--%>
				<%--${experience}--%>
				
				<% 
					Experience experience = (Experience)session.getAttribute("experience"); 
					String show = "style = 'display : block'" , hide = "style = 'display : none'";
					String form = "" , report = "";
				%>




				<%
					try{						
						form = hide;
						report = show;
						if(experience == null || experience.getOrganizationName() == null){
							form = show;
							report = hide;
						}
					}catch(NullPointerException e){
						System.out.println("experience object is null");
					}
				%>
				<tr>
					<td class="td_label">Company Name :</td>
					<td class="td_report" id="_company" <%= report %>>${experience.organizationName}</td>
					<td class="td_form" <%= form %>>
						<input type="text" name="company" id="company" class="form_data"/>
					</td>
				</tr>




				<%	
					form = hide;
					report = show;
					try{
						if(experience == null || experience.getIndustryType().getIndustryType() == null){
							form = show;
							report = hide;
						}
					}catch(NullPointerException e){
						System.out.println("IndustryType is null");
					}
				%>
				<tr>
					<td class="td_label">Industry :</td>
					<td class="td_report" id="_industrytype" value='${experience.industryType.industryTypeId}' <%= report %>>
						${experience.industryType.industryType}
					</td>
					<td class="td_form" <%= form %>>
						<select name="industrytype" id="industrytype" class="form_data">
							${applicationScope.industrytypes}
						</select>
						<input type="hidden" value=${applicationScope.industrytypes} id="industry" />
					</td>
				</tr>



				<%					
					form = hide;
					report = show;
					try{
						if(experience == null || experience.getDepartment().getDepartment() == null){
							form = show;
							report = hide;
						}
					}catch(NullPointerException e){
						System.out.println("Department is null");
					}
				%>
				<tr>
					<td class="td_label">Department :</td>
					<td class="td_report" id="_department" value='${experience.department.departmentId}' <%= report %>>${experience.department.department}</td>
					<td class="td_form" <%= form %>>
						<select name="department" id="department" class="form_data">
							${applicationScope.departments}
						</select>

					</td>
				</tr>



				<%
					form = hide;
					report = show;
					if(experience == null || experience.getJoiningDate() == null){
						form = show;
						report = hide;
					}
				%>
				<tr>
					<td class="td_label">Joining Date :</td>
					<td class="td_report" id="_joining" <%= report %>>${experience.joiningDate}</td>
					<td class="td_form" <%= form %>>
						<input type="date" name="joining" id="joining" class="form_data" />
					</td>
				</tr>



				<%
					form = hide;
					report = show;
					if(experience == null || experience.getLeavingDate() == null){
						form = show;
						report = hide;
					}
				%>
				<tr>
					<td class="td_label">Leaving Date :</td>
					<td class="td_report" id="_leaving" <%= report %>>${experience.leavingDate}</td>
					<td class="td_form" <%= form %>>
						<input type="date" name="leaving" id="leaving" class="form_data"/>
					</td>
				</tr>



				<%
					form = hide;
					report = show;
					if(experience == null || experience.getDesignation().getDesignation() == null){
						form = show;
						report = hide;
					}
				%>
				<tr>
					<td class="td_label">Designation :</td>
					<td class ="td_report" id="_designation" value='${experience.designation.designationId}' <%= report %>>
						${experience.designation.designation}
					</td>
					<td class="td_form" <%= form %>>
						<select name="designation" id="designation" class="form_data" >
							${applicationScope.designations}
						</select>
					</td>
				</tr>



				<% 
					form = hide;
					report = show;
					if(experience == null || experience.getCtc() == null){
						form = show;
						report = hide;
					}
				%>
				<tr>	
					<td class="td_label">CTC (Package) :</td>
					<td class="td_report" id="_ctc" <%= report %>>${experience.ctc}</td>
					<td class="td_form" <%= form %>>
						<input type="number" name="ctc" id="ctc" class="form_data" />
					</td>
				</tr>
				<tr>
					<td class="td_form">
						<input type="submit" id="update" value="update data" class="form_data"/>
					</td>
					<td class="td_skip">
						<a href="showlogin.do" class="skip_button">Skip</a>
					</td>
				</tr>
			</form>
		</table>



		<table id="table_report">
			<tr>
				<td>Experience Id</td>
				<td>Company Name</td>
				<td>Industry</td>
				<td>Department</td>
				<td>Joining Date</td>
				<td>Leaving Date</td>
				<td>Designation</td>
				<td>CTC</td>
			</tr>
			<c:forEach var="exp" items="${experiences}">
				<tr class="report_tr">
					<td id="experineceid_.${exp.experienceId}">${exp.experienceId}</td>
					<td id="company_.${exp.experienceId}">${exp.organizationName}</td>
					<td id="industrytype_.${exp.experienceId}" value="${exp.industryType.industryTypeId}">${exp.industryType.industryType}</td>
					<td id="department_.${exp.experienceId}" value="${exp.department.departmentId}">${exp.department.department}</td>
					<td id="joining_.${experienceId}">${exp.joiningDate}</td>
					<td id="leaving_.${exp.experienceId}">${exp.leavingDate}</td>
					<td id="designation_.${exp.experienceId}" value="${exp.designation.designationId}">${exp.designation.designation}</td>
					<td id="ctc_.${exp.experienceId}">${exp.ctc}</td>
				</tr>
			</c:forEach>

			<tr>
				<td colspan="1000" id="addmoredata" class="button">Add More Records</td>
			</tr>

			<tr id="tr_report_message">
				<td id="td_report_message" colspan="1000">Click to View & Edit the Details</td>
			</tr>
		</table>
	</body>
</html>