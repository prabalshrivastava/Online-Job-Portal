<%@taglib uri="getDisplay" prefix="a" %>
<html>
	<head>
		<title>company profile</title>
		<%@ include file="page.jsp" %>
		<script type="text/javascript" src="js/companyprofile.js"></script>
	</head>

	<body>
		<div>
			<%--
				<%= "welcome "+((models.User)session.getAttribute("user")).getUserName() %>
				<%= "welcome "+((models.Company)session.getAttribute("company")).getUserName() %>
				${user}
			--%>
		</div>
		${branch}
		<form action="companyprofile.do">
			<table>
				<tr>
					<a:formreport field="${user.logoPath}" fieldName="logopath" />
					<td class="td_label">Logo :  </td>
					<td class="td_report" ${report} id="_logopath" value="${user.logoPath}">
						<img style="height:10%;width:10%" src="downloadcompanyfile.do?resource=logopath" />
					</td>
					<td class="td_form" ${form}>
						<iframe class="form_data" src="showuploadcompanylogo.do" id="logopath" name="logopath">
						</iframe>
					</td>
				<tr>
				<tr>
					<a:formreport field="${user.userName}" fieldName="username" />
					<td class="td_label">Company Name : </td>
					<td class="td_report" ${report} id="_username">
						${user.userName}	
					</td>
					<td class="td_form" ${form}>
						<input type="text" class="form_data" id="username" name="username" />
					</td>
				</tr>
				<tr>
					<a:formreport field="${user.email}" fieldName="email" />
					<td class="td_label">Email : </td>
					<td class="td_report" ${report} id="_email">${user.email}</td>
					<td class="td_form" ${form}>
						<input type="email" class="form_data" id="email" name="email" />
					</td>
				</tr>
				<tr>
					<a:formreport field="${user.password}" fieldName="password" />
					<td class="td_label">Password : </td>
					<td class="td_report" ${report} id="_password">${user.password}</td>
					<td class="td_form" ${form}>
						<input type="password" class="form_data" id="password" name="password" />
					</td>
				</tr>


		


				<tr Style="visibility:hidden" ><td><br /><br /><br /><br /></td></tr>
				<tr>
					<td class="td_label">Head Office : </td>
					<td class="td_report" id="_branchname" value="Head Office">Head Office</td>
					<input type="hidden" id="branchname" name="branchname" value="Head Office" class="form_data" />
				</tr>
				<tr>
					<a:formreport field="${branch.address}" fieldName="address" />
					<td class="td_label">Address : </td>
					<td class="td_report" ${report} id="_address">${branch.address}</td>
					<td class="td_form" ${form}>
						<input type="text" id="address" name="address" class="form_data" />
					</td>
				</tr>

				<tr>
					<a:formreport field="${branch.pinCode.city.state.country.country}" fieldName="country" />
					<td class="td_label">Country :  </td>
					<td class="td_report" ${report} id="_country">${branch.pinCode.city.state.country.country}</td>
					<td class="td_form" ${form}>
						<select id="country" name="country" class="form_data" >${countries}</select>
					</td>
				</tr>

				<tr>
					<a:formreport field="${branch.pinCode.city.state.state}" fieldName="state" />
					<td class="td_label">State :  </td>
					<td class="td_report" ${report} id="_state">${branch.pinCode.city.state.state}</td>
					<td class="td_form" ${form}>
						<select id="state" name="state" class="form_data" >${states}</select>
					</td>
				</tr>

				<tr>
					<a:formreport field="${branch.pinCode.city.city}" fieldName="city" />
					<td class="td_label">City :  </td>
					<td class="td_report" ${report} id="_city">${branch.pinCode.city.city}</td>
					<td class="td_form" ${form}>
						 <select id="city" name="city" class="form_data" >${cities}</select>
					</td>
				</tr>

				<tr>
					<a:formreport field="${branch.pinCode.pinCode}" fieldName="pinCode" />
					<td class="td_label">Pin :  </td>
					<td class="td_report" ${report} id="_pincode">${branch.pinCode.pinCode}</td>
					<td class="td_form" ${form}>
						<select class="form_data" id="pincode" name="pincode">${pincodes}</select>
					</td>
				</tr>


				<tr style="visibility:hidden"><td><br /><br /><br /><br /></td></tr>
				<tr>
					<td class="td_label">Contact :  </td>
				</tr>
				<tr>
					<a:formreport field="${branch.contactNumber}" fieldName="contactNumber" />
					<td class="td_label">Contact Number :  </td>
					<td class="td_report" ${report} id="_contactnumber">${branch.contactNumber}</td>
					<td class="td_form" ${form}>
						<input type="tel" id="contactnumber" name="contactnumber" class="form_Data" />
					</td>
				</tr>
				<tr>
					<a:formreport field="${branch.contactPerson}" fieldName="contactPerson" />
					<td class="td_label">Contact Person :  </td>
					<td class="td_report" ${report} id="_contactperson">${branch.contactPerson}</td>
					<td class="td_form" ${form}>
						<input type="text" id="contactperson" name="contactperson" class="form_data" />
					</td>
				</tr>
				<!--
				<tr>
					<td class="td_label">Contact LandLine :  </td>
					<td class="td_report"></td>
					<td class="td_form">
						<input type="text" id="contactlandline" name="contactlandline" class="form_data" />
					</td>
				</tr>
				-->
				<tr>
					<a:formreport field="${branch.email}" fieldName="email" />
					<td class="td_label">Contact Email :  </td>
					<td class="td_report" ${report} id="_contactemail">${branch.email}</td>
					<td class="td_form" ${form}>
						<input type="tel" id="contactemail" name="contactemail" class="form_data" />
					</td>
				</tr>



				<tr style="visibility:hidden"><td></td></tr>
				<tr>
					<a:formreport field="${user.website}" fieldName="${website}" />
					<td class="td_label">Website :  </td>
					<td class="td_report" ${report} id="_website">${user.website}</td>
					<td class="td_form" ${form}>
						<input type="url" id="website" name="website" class="form_data" />
					</td>
				</tr>
				<tr>
					<a:formreport field="${user.industryType.industryTypeId}" fieldName="industrytype" />
					<td class="td_label">Industry Type :  </td>
					<td class="td_report" ${report} id="_industrytype">${user.industryType.industryType}</td>
					<td class="td_form" ${form}>
						<select id="industrytype" name="industrytype" class="form_data" >
							${industrytypes}
						</select>
					</td>
				</tr>
				<!--
				<tr>
					<td class="td_label">Certification Type :  </td>
					<td class="td_report" id="_certificatetype"></td>
					<td class="td_form">
						<input type="text" id="certificationtype" name="certificationtype" class="form_data" />
					</td>
				</tr>

				<tr>
					<td class="td_label">Gallery :  </td>
					<td class="td_report"></td>
					<td class="td_form">
						<iframe class="form_data"></iframe>
					</td>
				</tr>
				-->
				<tr>
					<a:formreport field="${user.mission}" fieldName="mission" />
					<td class="td_label">Mission :  </td>
					<td class="td_report" ${report} id="_mission">${user.mission}</td>
					<td class="td_form" ${form}>
						<input type="text" id="mission" name="mission" class="form_data" />
					</td>
				</tr>
				<tr>
					<a:formreport field="${user.vision}" fieldName="vision" />
					<td class="td_label">Vision :  </td>
					<td class="td_report" ${report} id="_vision">${user.vision}</td>
					<td class="td_form" ${form}>
						<input type="text" id="vision" name="vision" class="form_data" />
					</td>
				</tr>
				<tr>
					<a:formreport field="${user.history}" fieldName="history" />
					<td class="td_label">History :  </td>
					<td class="td_report" ${report} id="_history">${user.history}</td>
					<td class="td_form" ${form}>
						<input type="text" id="history" name="history" class="form_data" />
					</td>
				</tr>


				
				<tr>
					<a:formreport field="${user.awards}" fieldName="awards" />
					<td class="td_label">Awards : </td>
					<td class="td_report" ${report} id="_awards">${user.awards}</td>
					<td class="td_form" ${form}>
						<input type="text" class="form_data" id="awards" name="awards" />
					</td>
				</tr>


				<tr>
					<a:formreport field="${user.aboutUs}" fieldName="aboutUs" />
					<td class="td_label">About Us :  </td>
					<td class="td_report" ${report} id="_aboutus">${user.aboutUs}</td>
					<td class="td_form" ${form}>
						<input type="text" id="aboutus" name="aboutus" class="form_data" />
					</td>
				</tr>
				<tr>
					<td class="td_form">
						<input type="submit" class="form_data" id="update" name="update" value="update" />
					</td>
					<td class="td_skip">
						<a href="showbranch.do" class="skip_button">Skip</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>