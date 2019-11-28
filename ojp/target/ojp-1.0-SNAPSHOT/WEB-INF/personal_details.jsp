<%@ page import = "models.Candidate,models.Country,models.City,java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>	
<html>
	<head>
		<title>Personal Details</title>
		<%--<c:import url="page.jsp" />	 --%>
		<%@include file="page.jsp" %>
		<link rel="stylesheet" type="text/css" href="css/personaldetails.css" />
		<script type="text/javascript" src="js/personaldetails.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
	</head>
	<body>
		<form action = "personaldetails.do" method = "post" enctype="multipart/form-data" accept-charset="utf-8">
			<% 
				Candidate candidate = (Candidate)session.getAttribute("user"); 
			%>
			<%! String m = "" ,u = "" ,checked = "checked='checked'"; %>
			${user}
			<table>
				<!-- here underscore convention is used for editable (data or report from user) fields -->
				<!-- here uderscore used two times is for the message to be displayed about the 
					 form field and the report field -->
						
				<tr>
					<td class="td_label">Name :</td>
					<td class="td_form" id="td_username">
						<input type="text" id="_username" name="_username" class="form_data user_data" value=${user.userName} />
					</td>
					<td class="td_report" id="rep_username">
						<span id="username" name="username" class="report_data">
							${user.userName}
						</span>
					</td>
					<td class="td_update_reset_cancel" id="update_username">Update Name</td>
					<td class="td_update_reset_cancel" id="reset_username">Reset Name</td>
					<td class="td_update_reset_cancel" id="cancel_username">Cancel</td>
					<td class="message" id="__username" name="__username"></td>
				</tr>

				<tr>
					<td class="td_label">Email :</td>
					<td class="td_form" id="td_email">
						<input type="email" id="_email" name="_email" class="form_data user_data" value=${user.email} />
					</td>
					<td class =  "td_report">
						<span id="email" name="email" class="report_data">${user.email}</span>
					</td>
					<td class="td_update_reset_cancel" id="update_email">Update Email</td>
					<td class="td_update_reset_cancel" id="reset_email">Reset Email</td>
					<td class="td_update_reset_cancel" id="cancel_email">Cancel</td>
					<td class="message" id="__email" name="__email"></td>
				</tr>

				<tr>
					<td class="td_label">Password :</td>
					<td class="td_form" id="td_password">
						Password :<input type="password" id="_password" name="_password" class="form_data user_data" value=${user.password} />
						Confirm Password :<input type="password" id="_confirmpassword" name="_confirmpassword" class="form_data user_data" />
					</td>

					<td class="td_report">
						<span id="password" name="password" class="report_data">
							change your password ${user.password}
						</span>
					</td>
					<td class="td_update_reset_cancel" id="update_password">Update Password</td>
					<td class="td_update_reset_cancel" id="reset_password">Reset Password</td>
					<td class="td_update_reset_cancel" id="cancel_password">Cancel</td>
					<td class="message" id="__password" name="__user"></td>
				</tr>

				<tr>
					<td class="td_label">UserType :</td>
					<td class="td_report">
						<span id="usertype" name="usertype" class="report_data">
							${user.userType.userType}
						</span>
						<span id="_usertype" name="_usertype" class="form_data">
					</td>
					<td class ="message" id="__usertype"></td>
				</td>

				<tr>
					<td class="td_label">Date Of Birth :</td>
					<%
						String form,report;
						String show = "style = 'display:block'",hidden = "style = 'display:none'"; 
						if(candidate.getDateOfBirth()==null){
							form = show;
							report = hidden;
						}else{
							form = hidden;
							report = show;
						}
					%>
					<td class="td_form" id="td_dob" <%= form %>>
						<input type="date" id="_dob" name="_dob" class="form_data" value=${user.dateOfBirth} />
					</td>
	
					<td class="td_report" <%= report %>>
						<span id="dob" name="dob" class="report_data">  
							${user.dateOfBirth}
						</span>
					</td>
					<td class="td_update_reset_cancel" id="update_dob">Update Date of Birth</td>
					<td class="td_update_reset_cancel" id="reset_dob">Reset Date of Birth</td>
					<td class="td_update_reset_cancel" id="cancel_dob">cancel</td>
					<td class="message" id="__dob" name="__dob"></td>
				</tr>

				<tr>
					<td class="td_label">Gender :</td>
					<% 
						String male = null,female = null;
						if(candidate.getGender() == null){
							System.out.println(candidate.getGender());
							form = show;
							report = hidden;
							male = hidden;
							female = hidden;
						}else if(candidate.getGender()){
							//candidate is a male
							form = hidden;
							report = show;

							male = show;
							female = hidden;

							m = checked;
							u = "";

						}else{
							//candidate is a female
							form = hidden;
							report = show;
							
							male = hidden;
							female = show;

							m = "";
							u = checked;
						}
					%>
					<!--
						gender :${user.gender}	<br />
						form :<%= form %>		<br />
						report :<%= report %>	<br />
						male :<%= male%>		<br />
						female :<%= female%>	<br />
					-->
					<td class="td_form" id="td_gender"  <%=form%>>
						<span class="form_data_parent">
							Male :<input type="radio" name="_gender"	id="_male" value="male" class ="form_data" <%= m %> />
							Female :<input type="radio" name="_gender"	id="_female" value="female" class="form_data" <%= u %> />
						</span>
					</td>

					<td class="td_report" <%=report%> >
						<span id="gender" name="gender" class="report_data" <%=male%>>Male</span>
						<span id="gender" name="gender" class="report_data" <%=female%>> Female</span>
					</td>
					<td class="td_update_reset_cancel" id="update_gender">Update Gender</td>
					<td class="td_update_reset_cancel" id="reset_gender">Reset Gender</td>
					<td class="td_update_reset_cancel" id="cancel_gender">cancel</td>
					<td class="message" id="__gender" name="__gender"></td>
				</tr>

				<tr>	
					<td class="td_label">Father's Name :</td>
					<% 
						if(candidate.getFatherName() == null) {
							form = show;
							report = hidden;
						}else{
							report = show;
							form = hidden;
						}
					%>

					<td class="td_form" id="td_fname" <%= form%> >
						<input type="text" id="_fname" name="_fname" class="form_data" value=${user.fatherName} />
					</td>

					<td class="td_report" <%= report %> >
						<span id="fname" name="fname" class="report_data">${user.fatherName}</span>
					</td>
					<td class="td_update_reset_cancel" id="update_fname">Update Father's Name</td>
					<td class="td_update_reset_cancel" id="reset_fname">Reset Father's Name</td>
					<td class="td_update_reset_cancel" id="cancel_fname">cancel</td>
					<td class="message" id="__fname" name="__fname"></td>
				</tr>

				<tr>
					<td class="td_label">Mother's Name :</td>
					<%
						if(candidate.getMotherName() == null){
							form = show;
							report = hidden;
						}else{
							form = hidden;
							report = show;
						}
					%>
					
					<td class="td_form" id="td_mname" <%= form %>>
						<input type="text" id="_mname" name="_mname" class="form_data" value=${user.motherName} />
					</td>

					<td class ="td_report" <%= report %>>
						<span id="mname" name="mname" class="report_data">${user.motherName}</span>
					</td>
					<td class="td_update_reset_cancel" id="update_mname">Update Mother's Name</td>
					<td class="td_update_reset_cancel" id="reset_mname">Reset Mother's Name</td>
					<td class="td_update_reset_cancel" id="cancel_mname">cancel</td>
					<td class="message" id="__mname" name="__mname"></td>
				</tr>
				
				<tr>
					<td class="td_label">profile Pic :</td>
					<%
						if(candidate.getProfilePicPath() == null || candidate.getProfilePicPath().equals("")){
							report = hidden;
							form = show ;
						}else{
							report = show ;
							form = hidden;
						}
					%>
					<td class="td_form" id="td_profilepicpath" <%= form %> >
						Upload Your Photo :
						<input type="file" id="_profilepicpath" name="_profilepicpath" class="form_data" value=${user.profilePicPath} />
					</td>
					<td class="td_report" <%= report %>>
						<!--
							<iframe src="downloadcandidatefile.do" frameborder = "0" height = "300">
							</iframe>
						-->
						<img src = "downloadcandidatefile.do?resource=profilepicpath" width ="100%" height ="30%"/>
						<br />
						<span id="profilepicpath" name ="profilepicpath" class ="report_data" >
							${user.profilePicPath}
						</span>
					</td>
					<td class="td_update_reset_cancel" id="update_profilepicpath">Update Profile Photo</td>
					<td class="td_update_reset_cancel" id="reset_profilepicpath">Reset Profile Photo</td>
					<td class="td_update_reset_cancel" id="cancel_profilepicpath">cancel</td>
					<td class="message" id="__profilepicpath" name="__profilepicpath"></td>
				</tr>
				
				<tr>
					<td class="td_label">MaritalStatus :</td>

					<%	String married = null,unmarried = null ;
						if(candidate.getMaritalStatus() == null){
							form = show;
							report = hidden;

							married = hidden;
							unmarried = hidden;
						}else if(candidate.getMaritalStatus()){
							report = show;

							married = show;
							unmarried = hidden;

							m = checked;
							u = "";
						}else{
							report = show;

							married = hidden;
							unmarried = show;

							m = "";
							u = checked;
						}
					%>
					<!--
						maritalStatus :${user.maritalStatus}<br />
						form :<%= form %>					<br />
						report :<%= report %>				<br />
						married :<%= married %>				<br />
						unmarried :<%= unmarried %>			<br />
					-->
					<td class="td_form" id="td_maritalstatus" <%= form %>>
						<span class="form_data_parent">
							<span>Married :</span>
							<input type="radio" id="married" name="_maritalstatus" class="form_data" value="married"  <%= m %> />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span>Unmarried :</span>
							<input type="radio" id="unmarried" name="_maritalstatus" class="form_data" value="Ummarried" <%= u %> />
						</span>
					</td>
					
					<td class="td_report" <%= report %>>
						<span id="married" name="maritalstatus" class="report_data" <%= married %>>
							Married
						</span>
						<span id="unmarried" name="maritalstatus" class="report_data" <%= unmarried %>>
							Unmarried
						</span>
					</td>
					<td class="td_update_reset_cancel" id="update_maritalstatus">Update Marital Status</td>
					<td class="td_update_reset_cancel" id="reset_maritalstatus">Reset Marital status</td>
					<td class="td_update_reset_cancel" id="cancel_maritalstatus">cancel</td>
					<td class="message" id="__maritalstatus" name="__maritalstatus"></td>
				</tr>

				<tr>
					<td class="td_label">Nationality :</td>
					<% 
						ArrayList countries = (ArrayList)application.getAttribute("countries");
						if(candidate.getCountry() == null && candidate.getCountry().getCountry() == null){
							form = show;
							report = hidden;
						}else{
							form = hidden;
							report = show;
						}
					%>
					<td class="td_form" id="td_country" <%= form %>>
						<select	id="_country"	name="_country" class="form_data"/>
						<%		
							int i = 0,size = countries.size();
							String countryName = null;
							while(i!=size){
								countryName = (String)((Country)countries.get(i++)).getCountry();
						%>	
							<option>
								<%= countryName %>
							</option>
						<%
							}
						%>
					</td>
					<!--
						<%--
							<td class ="td_form" id="td_country">
								<select	id="_country"	name="_country" class="form_data"/>
								<% 
									for(Object country : countries){
								%>
									<option>
										<%= (String)country %>
									</option>
								<%
									}
								%>
							</td>
						--%>
					-->

					<td class="td_report" <%= report %>>
						<span id="country" name="country" class="report_data">
							${user.country.country}
						</span>
					</td>
					<td class="td_update_reset_cancel" id="update_country">Update Country</td>
					<td class="td_update_reset_cancel" id="reset_country">Reset Country</td>
					<td class="td_update_reset_cancel" id="cancel_country">cancel</td>
					<td class="message" id="__country" name="__country"></td>
				</tr>

				<tr>
					<td id ="td_update">
						<input type="submit" value="update" id ="update" name="update" />
					</td>
					<td class="td_skip">
						<input type="button" value = "Skip" onclick = "window.location = 'showexperiencedetails.do'" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>