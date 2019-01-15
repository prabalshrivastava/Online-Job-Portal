<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="a" uri="getDisplay" %>
<html>
	<head>
		<title>Skills</title>

		<link rel="stylesheet" type="text/css" href="css/skills.css" />
		<script type="text/javascript" src="js/table_report.js"></script>
		<script type="text/javascript" src="js/skills.js"></script>

		<jsp:include page="page.jsp" />
	</head>

	<body>
		<form action="skills.do">
			<table>
				<tr>
					<a:formreport field="${candidateskills[0].skill.skillId}" fieldName="skill" />
					<td class="td_label">skill : </td>
					<td class="td_report" ${report} id="_skill">${candidateskills[0].skill.skill}</td>
					<td class="td_form" ${form}>
						<select type="text" name="skill" id="skill" class="form_data">
							${skills}
						</select>
					</td>
				</tr>
				<tr>
					<a:formreport field="${candidateskills[0].version}" fieldName="version" />
					<td class="td_label">version : </td>
					<td class="td_report" ${report} id="_version">${candidateskills[0].version}</td>
					<td class="td_form" ${form}>
						<input type="number" name="version" id="version" class="form_data" />
					</td>
				</tr>
				<tr>
					<s:formreport field="${candidateskills[0].duration}" fieldName="duration" />
					<td class="td_label">duration : </td>
					<td class="td_report" ${report} id="_duration">${candidateskills[0].duration}</td>
					<td class="td_form" ${form}>
						<input type="text" name="duration" id="duration" class="form_data" />
					</td>
				</tr>
				<tr>
					<a:formreport field="${candidateskills[0].certificate}" fieldName="certification" />
					<td class="td_label">certification : </td>
					<td class="td_report" ${report} id="_certificate">
						${candidateskills[0].certificate}
					</td>
					<!--
					<td class="td_form hidden_element">
						<input type="hidden" class="form_data" id="certificate" name="certificate" />
					</td>
					-->
					<td class="td_form" ${form}>
						<iframe src="showuploadcertificate.do" id="certificate" name="certificate" class="form_data">
						</iframe>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="submit" />
					</td>
					<td class="td_skip">
						<a href="showlogin.do" class="skip_button" id="skip">skip</a>
					</td>
				</tr>
			</table>
		</form>

		<table id="table_report">
			<tr>
				<td>Candidate Skill ID</td>
				<td>Skill</td>
				<td>Version</td>
				<td>Duration</td>
				<td>Certificate</td>
			</tr>
			<c:forEach var="cs" items="${candidateskills}">
				<c:set var="c" value="${cs.candidateSkillId}"/>
				<tr class="report_tr">
					<td id="candidateskills_.${c}">${c}</td>
					<td id="skill_.${c}" value="${cs.skill.skillId}">${cs.skill.skill}</td>
					<td id="version_.${c}">${cs.version}</td>
					<td id="duration_.${c}">${cs.duration}</td>
					<td id="certificate_.${c}">${cs.certificate}</td>
				</tr>
			</c:forEach>
			<tr id="tr_report_message">
				<td id="td_report_message" colspan="100">Click To Edit</td>
			</tr>
			<tr>
				<td class=" button" id="addmoredata" colspan="100">Add More Data</td>
			</tr>
		</table>
	</body>
</html>