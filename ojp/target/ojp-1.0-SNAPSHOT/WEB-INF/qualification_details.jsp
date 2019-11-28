<html>
	<head>
		<title>Qualification Details</title>
		<jsp:include page="page.jsp" />
		<link rel="stylesheet" type="text/css" href="css/qualificationdetails.css" />
		<script type="text/javascript" src="js/qualificationdetails.js"></script>
	</head>
	<body>
		<form action="qualificationdetails.do">
			<table>		
				<tbody id="appendcontenthere">
					<tr>
						<td class="td_label" colspan="7">Course Type</td>
						<td class="td_label" colspan="7">Board/University</td>
						<td class="td_label" colspan="7" >Courses</td>
						<td class="td_label" colspan="7" >Passing/Year</td>
						<td class="td_label" colspan="7" >Score (in percentage)</td>
						<td class="td_label" colspan="7" >Upload Certificates</td>
						<td class="td_label" colspan="7" >Save Data</td>
					</tr>
				</tbody>




				<tr>
					<td class="button" id="addmorebutton" >THE BIGGG BUTTON</td>
					<input type="hidden" class="hidden" id="rowcount" name="rowcount" value="0" />
				</tr>
				<tr>	
					<td class="button" id="update" >
						<input type="submit" value="update" />
					</td>
					<td  class="td_skip">
						<a href="showlogin.do" class="skip_button">Skip</a>
					</td>
				</tr>



				<tbody class="wrapper" id="wrapper">
					<tr>


						<td  class="td_report" id="_coursetype....0"></td>
						<td  class="td_form" colspan="7">
							<select id="coursetype....0" name="coursetype" class="form_data">
								${coursetypes}
							</select>
						</td>


						<td  class="td_report" id="_organization....0"></td>
						<td  class="td_form" colspan="7">
							<select class="form_data" name="organization" id="organization....0">
								${organizations}
							</select>
						</td>
						
						
						<td class="td_report" id="_course....0" ></td>
						<td class="td_form" colspan="7" >
							<select id="course....0" name="course" class="form_data">
								${courses}
							</select>
						</td>


						<td  class="td_report" id="passingyear....0"></td>
						<td  class="td_form" colspan="7">
							<input type="date" class="form_data" name="passingyear" id="passingyear....0"/>
						</td>


						<td  class="td_report" id="_score....0"></td>
						<td  class="td_form" colspan="7">
							<input type="number" class="form_data" name="score" id="score....0" />
						</td>

						<td  class="td_report" id="_certificatepath....0"></td>
						<td  class="td_form iframetd">
							<!--
							<iframe src="showuploadresume.do" name="certificatepath" id="certificatepath....0" class="form_data" />
							-->
							<iframe src="showuploadcoursecertificate.do" name="certificatepath" id="certificatepath....0" class="form_data" />
						</td>
						<td id="save....0" name="save" class="qbutton button" >
							SAVE DATA
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>