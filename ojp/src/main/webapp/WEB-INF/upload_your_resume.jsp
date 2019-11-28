<%@ taglib prefix="a" uri="getDisplay" %>
<html>
	<head>
		<title>Upload Your Resume</title>
		<script type="text/javascript" src="js/upload_resume.js"></script>
		<%@include file="page.jsp" %>
	</head>
	<body>
		<form action="uploadresume.do" method="post" enctype="multipart/form-data">
			<table>
				<a:formreport field="${user.resumeFilePath}" fieldName="resume" />
				<tr>
					<td class="td_report" ${report} id="_resume">
						${user.resumeFilePath}
					</td>
					<td ${report}>
						<a href="downloadcandidatefile.do?resource=resumefilepath">Download Resume</a>
					</td>

					<td class="td_form" ${form}>
						<input type="file" id="resume" name="resume" class="form_data" value="${user.resumeFilePath}" />
					<td>
<!--					
					<td class="hidden_element">
						<input type="hidden" name="uploadtype" id="uploadtype" />
					</td>
-->
				</tr>
				<tr>
					<td>
						<input type="submit" id="upload" value="upload" />
					</td>
					<td class="td_skip">
						<a id="skip" href="showlogin.do" class="skip_button">Skip</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>