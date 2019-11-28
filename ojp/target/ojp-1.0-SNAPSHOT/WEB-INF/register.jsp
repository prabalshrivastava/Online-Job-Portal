<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="models.UserType" %>
<html>
	<head>
		<title>
			Register
		</title>
		<link rel="stylesheet"	type="text/css"	href="css/register.css" />
		<script type="text/javascript" src="js/register.js" ></script>
		<c:import url="page.jsp" />
	</head>
	<body>
		<div id="msg">
			<% String msg = (String)request.getAttribute("msg"); %>
			<%=
				msg==null ? /*print nothing*/ "" : msg /*print the error message*/
			%>

		</div>
		<div id ="body">
			<h1 id="registerhead">Register</h1>
			<div>
			<form action="register.do" id="registerform">
				<table>
					<tr>
						<td colspan="2">
							Register As
						</td>
						<td colspan="1">
							Candidate	
							<input type="radio"	name="usertypeid" value="<%= UserType.CANDIDATE %>" id="candidate" />
						</td>
						<td colspan="1">
							company
							<input type="radio"	name="usertypeid" value="<%=UserType.COMPANY %>" id="company" />
						</td>
					</tr>
					<tr>
						<td colspan="2"> User Name	</td>
						<td colspan="2"> 
							<input type="text"	name="username"	placeholder="enter name" id="username" />
							<span id="_username"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2"> Email id </td>
						<td colspan="2"> 
							<input type="email" name="email" placeholder="Enter Email" class="textbox" id="email"/>
							<span id="_email"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							Password		
						</td>
						<td colspan="2">
							<input type="password"	name="password" placeholder="Enter Password" class="textbox" id="password"/>
							<span id="_password"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							retype Password	
						</td>
						<td colspan="2">
							<input type="password" name="retypepassword" placeholder="Retype password" class="textbox" id="retypepassword"/>
							<span id="_retypepassword"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" id="register" value="register" />
						</td>
					</tr>
				</table>
			</form>
			<a href="showlogin.do">already registered?(login)</a>
		</div>
	</body>
</html>