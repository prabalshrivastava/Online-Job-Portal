<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
	<head>
		<title>login</title>
		<link rel="stylesheet" type="text/css" href="css/login.css" />
		<script type="text/javascript" src="js/login.js" ></script>
		<c:import url="page.jsp" />
	</head>
	<body>
		<div id="msg">
			<% String msg = (String)request.getAttribute("msg"); %>
			<%= 
				msg==null ? "" :msg
			%>
		</div>
		<div id ="body">
			<h1 id ="login">Login</h1>
			<form action="login.do" id="loginform">
			<table>
				<tr>
					<td> Login as </td>
				</tr>

			<!--
				<tr>
					<td> candidate	<input type="radio" name="usertype" value="candidate" /></td>
					<td> company	<input type="radio" name="usertype" value="company" /> </td>
					<td> <span></span> </td>
				</tr>
			-->
				<tr>
					<td> Email id </td>
					<td> 
						<input type="text" name="email" placeholder="Enter Email" class="textbox" id="email"/>
					</td>
					<td class="hidden_element">
						<span id= "_email"></span>
					</td>
				</tr>

				<tr>
					<td> Password </td>
					<td> 
						<input type="password" name="password" placeholder="Enter Password" class="textbox" id="password"/>
					</td>
					<td class="hidden_element">
						<span id = "_password"></span>
					</td>
				</tr>

				<tr>
					<td>
						<input type= "submit" value="login" id="login"/>
					</td>
					<td>
						<a href="showregister.do">New User? Register here</a>
					</td>	
				</tr>
			</table>
			</form>
		</div>
	</body>
</html>