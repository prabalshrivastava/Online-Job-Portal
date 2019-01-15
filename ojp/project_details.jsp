<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "models.Project,utilities.Db,java.util.ArrayList" %>
<html>
	<head>
		<title>Project Details</title>
		<jsp:include page="page.jsp" />
		<link	type="text/css"	rel="stylesheet" href="css/projectdetails.css" />
		<script type="text/javascript" src="js/table_report.js"></script>
		<script type="text/javascript" src="js/projectdetails.js"></script>
	</head>

		<%
			ArrayList<Project> projects;
			System.out.println(projects = (ArrayList<Project>)session.getAttribute("projects"));
			Project project = null;
			if(projects != null && !projects.isEmpty())
				project = (Project)projects.get(0);
			if(project == null)
				project = new Project();
		%>

<%--
		<%=
			project
		%>
--%>


	<body>
		<form action = "projectdetails.do">
			<table id="table_form">


				<tr>
					<% Db.assignDisplay(project.getTitle());  %>
					<td class="td_label">Project Name</td>
					<td class="td_report" id="_title" name="_title" <%= Db.report %>>
						${projects[0].title}
					</td>
					<td class="td_form" <%= Db.form %>>
						<input class="form_data" type="text" name="title" id="title" />
					</td>
				</tr>



				<tr>
					<% Db.assignDisplay(project.getMembers());  %>
					<td class="td_label">No of Members</td>
					<td class="td_report" id="_members" name="_members" <%= Db.report %>>
						${projects[0].members}
					</td>
					<td class="td_form" <%= Db.form %>>
						<input class="form_data" type="number" name="members" id="members" />
					</td>
				</tr>
				

				
				<tr>
					<% Db.assignDisplay(project.getDuration());  %>
					<td class="td_label">Duration (in months)</td>
					<td class="td_report" id="_duration" name="_duration" <%= Db.report %>>
						${projects[0].duration}
					</td>
					<td class="td_form" <%= Db.form %>>
						<input class="form_data"  type="text" name="duration" id="duration" />
					</td>
				</tr>
				

				
				<tr>
					<% Db.assignDisplay(project.getDetails());  %>
					<td class="td_label">Project Details</td>
					<td class="td_report"  id="_details" name="_details" <%= Db.report %>>
						${projects[0].details}
					</td>
					<td class="td_form" <%= Db.form %>>
						<textarea class="form_data" id="details" name = "details" >$
							${projects[0].details}
						</textarea>
					</td>
				</tr>
				

				
				<tr>
					<% Db.assignDisplay(project.getOtherDetails());  %>
					<td class="td_label">Other Details</td>
					<td class="td_report" id="_otherdetails" name="_otherdetails" <%= Db.report %>>
						${projects[0].otherDetails}
					</td>
					<td class="td_form" <%= Db.form %>>
						<textarea class="form_data" id="otherdetails" name = "otherdetails">
							${projects[0].otherDetails}
						</textarea>
					</td>
				</tr>
				

				
				<tr>
					<td class="td_form" <%= Db.form %>>
						<input class="form_data"  type="submit" name="update" id="update" value="update" />
					</td>
					<td class="td_skip"> 
						<a href="showlogin.do" class="skip_button">Skip</a>
					</td>
				</tr>

			

			</table>
		</form>

<!--		
	+------------+-------+--------------+---------+----------+---------+---------------+
	| project_id | title | candidate_id | members | duration | details | other_details |
	+------------+-------+--------------+---------+----------+---------+---------------+
-->
			<table id="table_report">
				<tr>
					<td>ProjectId</td>
					<td>Title</td>
					<td>Candidate Name</td>
					<td>Nuber Of Members</td>
					<td>Duration</td>
					<td>Details</td>
					<td>OtherDetails</td>
				</tr>
				<c:forEach var="project" items="${projects}">
					<tr class="report_tr tr_${project.projectId}" id="report_tr_${project.projectId}">
						<td id="projectid_.${project.projectId}">${project.projectId}</td>
						<td id="title_.${project.projectId}">${project.title}</td>
						<td id="username_.${project.projectId}">${project.candidate.userName}</td>
						<td id="members_.${project.projectId}">${project.members}</td>
						<td id="duration_.${project.projectId}">${project.duration}</td>
						<td id="details_.${project.projectId}">${project.details}</td>
						<td id="otherdetails_.${project.projectId}">${project.otherDetails}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="7" class="button" id="addmoredata">Add More Projects</td>
				</tr>
				<tr id="tr_report_message">
					<td colspan="7" id="td_report_message">Click to edit and View the form</td>
				</tr>
			</table>
	</body>
</html>