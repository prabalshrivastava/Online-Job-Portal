<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="getDisplay" %>
<%@ page import = "models.Country,java.util.ArrayList,models.State,models.City,models.ContactType,models.Contact,models.UserAddress,utilities.Db" %>
<html>
	<head>
		<title>Contact Details</title>
		<jsp:include page="page.jsp" />
		<link type="text/css" rel="stylesheet" href="css/contactdetails.css" />
		<script type="text/javascript" src="js/contactdetails.js"></script>

	</head>
	<body>
		<form action="contactdetails.do">
			<table>
							
				
				
				<%--
					${sessionScope}
					${m:display(sessionScope.contacts[sessionScope.PERMANENT_ADDRESS].address)}
				--%>
				<tr>
					<m:formreport field="${contacts[sessionScope.PERMANENT_ADDRESS].address}" />
					<td class="td_label">Permanent Address</td>
					<td class="td_report" id="_permanentaddress" ${report}>
						${contacts[sessionScope.PERMANENT_ADDRESS].address}
					</td>
					<td class="td_form" ${form}>
						<textarea name="permanentaddress" id="permanentaddress" class="form_data">
						</textarea>
					</td>
					<!--<td class="td_update_reset_cancel"></td>-->
				</tr>
		
				

				<tr>
					<m:formreport field="${contacts[PERMANENT_ADDRESS].pinCode.city.state.country.countryId}" />
					<td class="td_label">Country</td>
					<td class="td_report" id="_country" value="${contacts[sessionScope.PERMANENT_ADDRESS].pinCode.city.state.country.countryId}" ${report}>
						${contacts[sessionScope.PERMANENT_ADDRESS].pinCode.city.state.country.country}
					</td>
					<td class="td_form" ${form}>
						<select name="country" id="country" class="form_data" >

							<%	ArrayList<Country> countries = (ArrayList<Country>)application.getAttribute("countries");
								String countryName = null;
								Integer countryId = null;
								Integer firstTimeCountry = null;
								for(Country country : countries){
									countryName = (String)country.getCountry();

									if(firstTimeCountry == null)
										firstTimeCountry = countryId;

									countryId = (Integer)country.getCountryId();
							%>
									<option	value=<%= countryId %>><%= countryName %></option>
							<%
								}
							%>

						</select>
					</td>
				</tr>
		

				<tr>
					<m:formreport field="${contacts[PERMANENT_ADDRESS].pinCode.city.state.state}" />
					<td class="td_label">State</td>
					<td class="td_report" id="_state" value="${contacts[sessionScope.PERMANENT_ADDRESS].pinCode.city.state.stateId}" ${report}>
						${contacts[sessionScope.PERMANENT_ADDRESS].pinCode.city.state.state}
					</td>
					<td class="td_form" ${form}>
						<select name="state" id="state" class="form_data" >
						
							<%
								ArrayList<State> states = (ArrayList<State>)application.getAttribute("states");
								Integer firstTimeState = null;
								for(State state : states){

									if(firstTimeState == null)
										firstTimeState = state.getStateId();

									if(firstTimeCountry.equals(state.getCountry().getCountryId())){
							%>
									<option value=<%= state.getStateId() %>>
										<%= state.getState() %>
									</option>
							<%
									}
								}
							%>
						</select>
					</td>
				</tr>


				<tr>
					<m:formreport field="${contacts[PERMANENT_ADDRESS].pinCode.city.city}" />
					<td class="td_label">City</td>
					<td class="td_report" id="_city" value="${contact[sesssionScope.PERMANENT_ADDRESS].pinCode.city.cityId}" ${report}>	
						${contacts[sessionScope.PERMANENT_ADDRESS].pinCode.city.city}
					</td>
					<td class="td_form" ${form}}>
						<select name="city" id="city" class="form_data" >
							<%	
								ArrayList<City> cities = (ArrayList<City>)application.getAttribute("cities");
								for(City city : cities){
									if(city.getState().getStateId().equals(firstTimeState)){
							%>
									<option value=<%= city.getCityId() %>>
										<%= city.getCity() %>
									</option>
							<%

									}
								}
							%>
						</select>
					</td>
				</tr>



				<tr>
					<m:formreport field="${contacts[PERMANENT_ADDRESS].pinCode.pinCode}" />
					<td class="td_label">Pin Code</td>
					<td class="td_report" id="_pincode" ${report}>
						${contacts[sessionScope.PERMANENT_ADDRESS].pinCode.pinCode}
					</td>

					<td class="td_form" id="td_pincode" ${form}>
						<input type="number" name="pincode" id="pincode" class="form_data" />
						<div id="pincode_div"></div>
					</td>
				</tr>




				<tr style="visibility:hidden"><td></td></tr>





				<tr class="lp">
					<m:formreport field="${contacts[LOCAL_ADDRESS].address}" />
					<td class="td_label local">Local Address</td>
					<td class="td_report local" id="_localaddress" ${report}>
						${contacts[sessionScope.LOCAL_ADDRESS].address}
					</td>
					<td class="td_form local" ${form}>
						<textarea name="localaddress" id="localaddress" class="form_data local" >
						</textarea>
					</td>




					<td class="td_label trans">Same As Permanent Address</td>
					<!--
						<td class="td_report local" id="_localpermanent" >
						</td>
					-->
					<td class="td_form trans" id="checkboxparent">
						<input type="checkbox" id="localpermanent" name="localpermanent" value="1" class="form_data" />
					</td>
				</tr>




				<tr class="local">
					<m:formreport field="${contacts[LOCAL_ADDRESS].pinCode.city.state.country.country}" />
					<td class="td_label">Country : </td>
					<td class="td_report" id="_lcountry" value="${contacts[sessionScope.LOCAL_ADDDRESS].pinCode.city.state.country.countryId}" ${report}>
						${contacts[sessionScope.LOCAL_ADDRESS].pinCode.city.state.country.country}
					</td>
					<td class="td_form" ${form}>
						<select  id="lcountry" name="lcountry" class="form_data">
							${countries}
						</select>
					</td>
				</tr>



				<tr class="local">
					<m:formreport field="${contacts[LOCAL_ADDRESS].pinCode.city.state.stateId}" />
					<td class="td_label">State : </td>
					<td class="td_report" id="_lstate" value="${contacts[sessionScope.LOCAL_ADDRESS].pinCode.city.state.stateId}" ${report}>
						${contacts[LOCAL_ADDRESS].pinCode.city.state.state}
					</td>
					<td class="td_form" ${form}>
						<select id="lstate" name="lstate" class="form_data">
							${states}
						</select>
					</td>
				</tr>





				<tr class="local">
					<m:formreport field="${contacts[LOCAL_ADDRESS].pinCode.city.city}" />
					<td class="td_label">City : </td>
					<td class="td_report" id="_lcity" value="${contacts[sessionScope.LOCAL_ADDRESS].pinCode.city.city}" ${report}>
						${contacts[LOCAL_ADDRESS].pinCode.city.city}
					</td>
					<td class="td_form" ${form}>
						 <select id="lcity" name="lcity" class="form_data">
							${cities}
						 </select>
					</td>
				</tr>




				<tr class="local">
					<m:formreport field="${contacts[LOCAL_ADDRESS].pinCode.pinCodeId}" />
					<td class="td_label">Pin Code : </td>
					<td class="td_report" id="_lpincode" value="${contacts[sessionScope.LOCAL_ADDRESS].pinCode.pinCodeId}" ${report}>
						${contacts[sessionScope.LOCAL_ADDRESS].pinCode.pinCode}
					</td>
					<td class="td_form" ${form}>
						<select id="lpincode" name="lpincode" class="form_data">
							${pincodes}
						</select>
					</td>
				</tr>



				<tr style="visibility:hidden"><td></td></tr>
				

					
				<tr>
					<m:formreport field="${contacts[MOBILE_NUMBER].contact}" />
					<td class="td_label">Primary Mobile no</td>
					<td class="td_report" id="_mobile" ${report}>
						${contacts[sessionScope.MOBILE_NUMBER].contact}
					</td>
					<td class="td_form" ${form}>
						<input type="tel" name="mobile" id="mobile" class="form_data" />
					</td>


					<m:formreport field="${contacts[ALTERNATE_MOBILE_NUMBER].contact}" />
					<td class="td_label">Alternate Mobile no</td>
					<td class="td_report" id="_amobile" ${report}>
						${contacts[sessionScope.ALTERNATE_MOBILE_NUMBER].contact}
					</td>
					<td class="td_form" ${form}>
						<input type="tel" name="amobile" id="amobile" class="form_data" />
					</td>
				</tr>



				<tr>
					<m:formreport field="${contacts[LANDLINE_NUMBER].contact}" />
					<td class="td_label">Primary Landline</td>
					<td class="td_report" id="_landline" ${report}>
						${contacts[sessionScope.LANDLINE_NUMBER].contact}
					</td>
					<td class="td_form" ${form}>
						<input type="tel" name="landline"	id="landline" class="form_data" />
					</td>
					

					<m:formreport field="${contacts[ALTERNATE_LANDLINE_NUMBER].contact}" />
					<td class="td_label">Alternate Landline</td>
					<td class="td_report" id="_alandline" ${report}>
						${contacts[sessionScope.ALTERNATE_LANDLINE_NUMBER].contact}
					</td>
					<td class="td_form" ${form}>
						<input type="tel" id="alandline" name="alandline" class="form_data" />
					</td>
				</tr>




				<tr>
					<m:formreport field="${contacts[EMAIL].contact}" />
					<td class="td_label">Primary Email</td>
					<td class="td_report" id="_email" ${report}>
						${contacts[sessionScope.EMAIL].contact}
					</td>
					<td class="td_form" ${form}>
						<input type="email"	name="email" id="email" class="form_data" />
					</td>


					<m:formreport field="${contacts[ALTERNATE_EMAIL].contact}" />
					<td class="td_label">Alternate Email</td>
					<td class="td_report" id="_aemail" ${report}>
						${contacts[sessionScope.ALTERNATE_EMAIL].contact}
					</td>
					<td class="td_form" ${form}>
						<input type="email"	name="aemail" id="aemail" class="form_data" />
					</td>
				</tr>




				<tr>
					<td>
						<input type="submit" value="update" id="update" class="form_data" />
					</td>
					<td class="td_skip">
						<a href="showlogin.do" class="skip_button">Skip</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>