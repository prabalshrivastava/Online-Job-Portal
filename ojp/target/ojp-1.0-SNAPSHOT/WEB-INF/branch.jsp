<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="a" uri="getDisplay" %>

<html>
	<head>
		<title>Branch</title>
		<%--<%@ include file="page.jsp" %>--%>
		<c:import url="page.jsp" />
		<link rel="stylesheet" href="css/branch.css" type="text/css" />
		<script type="text/javascript" src="js/branch.js"></script>
		<script type="text/javascript" src="js/table_report.js"></script>
	</head>
	<body>
		<form action="branch.do">

			<table id="table_form">
				<tr>
					<a:formreport field="${branches[0].branchName}" fieldName="branchname"  />
					<td class="td_label">Branch Name : </td>
					<td class="td_report" ${report} id="_branchname">${branches[0].branchName}</td>
					<td class="td_form" ${form}>
						<input type="text" class="form_data" id="branchname" name="branchname" />
					</td>
				</tr>



				<tr>
					<a:formreport field="${branches[0].pinCode.city.state.country.country}" fieldName="country" />
					<td class="td_label">Country : </td>
					<td class="td_report" ${report} id="_country">${branches[0].pinCode.city.state.country.country}</td>
					<td class="td_form" ${form}>
						<select id="country" name="country" class="form_data">
							${countries}
						</select>
					</td>
				</tr>


			
				<tr>
					<a:formreport field="${branches[0].pinCode.city.state.state}" fieldName="state" />
					<td class="td_label">State : </td>
					<td class="td_report" ${report} id="_state">${branches[0].pinCode.city.state.state}</td>
					<td class="td_form" ${form}>
						<select type="text" class="form_data" id="state" name="state" >
							${states}
						</select>
					</td>
				</tr>




				<tr>
					<a:formreport field="${branches[0].pinCode.city.city}" fieldName="city" />
					<td class="td_label">City : </td>
					<td class="td_report" ${report} id="_city">${branches[0].pinCode.city.city}</td>
					<td class="td_form" ${form}>
						<select type="text" class="form_data" id="city" name="city" >
							${applicationScope.cities}
						</select>
					</td>
				</tr>



				<tr>
					<a:formreport field="${branches[0].pinCode.pinCode}" fieldName="pincode" />
					<td class="td_label">pincode : </td>
					<td class="td_report" ${report} id="_pincode">${branches[0].pinCode.pinCode}</td>
					<td class="td_form" ${form}>
						<select class="form_data" id="pincode" name="pincode">
							${pincodes}
						</select>
					</td>
				</tr>




				<tr>
					<a:formreport field="${branches[0].address}" fieldName="address" />
					<td class="td_label">Address : </td>
					<td class="td_report" ${report} id="_address">${branches[0].address}</td>
					<td class="td_form" ${form}>
						<input type="text" class="form_data" id="address" name="address" />
					</td>
				</tr>


				
				<tr>
					<a:formreport field="${branches[0].email}" fieldName="contactemail" />
					<td class="td_label">Email : </td>
					<td class="td_report" ${report} id="_contactemail">${branches[0].email}</td>
					<td class="td_form" ${form}>
						<input type="email" class="form_data" id="contactemail" name="contactemail" />
					</td>
				</tr>



				<tr>
					<a:formreport field="${branches[0].contactNumber}" fieldName="contactnumber" />
					<td class="td_label">Contact Number : </td>
					<td class="td_report" ${report} id="_contactnumber">${branches[0].contactNumber}</td>
					<td class="td_form" ${form}>
						<input type="tel" class="form_data" id="contactnumber" name="contactnumber" />
					</td>
				</tr>



				<tr>
					<a:formreport field="${branches[0].contactPerson}" fieldName="contactperson" />
					<td class="td_label">Contact Person : </td>
					<td class="td_report" ${report} id="_contactperson">${branches[0].contactPerson}</td>
					<td class="td_form" ${form}>
						<input type="text" class="form_data" id="contactperson" name="contactperson" />
					</td>
				</tr>


				
				<tr>
					<td>
						<input id="update" class="form_data" type="submit" value="update" />
					</td>
					<td class="td_skip">
						<a href="showpostnewjob.do" class="skip_button" id="skip">skip</a>
					</td>
				</tr>
			</table>




			<table id="table_report">
				<tr>
					<td>Branch Id</td>
					<td>Branch Name</td>
					<td>Adddress</td>
					<td>PinCode</td>
					<td>Contact Person</td>
					<td>Contact Number</td>
					<td>Email</td>
					<td>Company Name</td>
				</tr>

				<c:forEach var="branch" items="${branches}">
					<tr class="report_tr">
						<td id="branchid_.${branch.branchId}">${branch.branchId}</td>
						<td id="branchname_.${branch.branchId}">${branch.branchName}</td>
						<td id="address_.${branch.branchId}">${branch.address}</td>
						<td id="pincode_.${branch.branchId}" value="${branch.pinCode.pinCodeId}">${branch.pinCode.pinCode}</td>
						<td id="contactperson_.${branch.branchId}">${branch.contactPerson}</td>
						<td id="contactnumber_.${branch.branchId}">${branch.contactNumber}</td>
						<td id="contactemail_.${branch.branchId}">${branch.email}</td>
						<td id="companyname_.${branch.branchId}">${branch.company.userName}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="1000" id="addmoredata" class="button">Add More Records</td>
				</tr>
	
				<tr id="tr_report_message">
					<td colspan="8" id="td_report_message">Click to View & Edit the Details </td>
				</tr>
			</table>
		</form>
	</body>
</html>