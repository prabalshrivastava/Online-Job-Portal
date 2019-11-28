<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>

	<head>
		<title>candidate profile page</title>
	</head>
	<body>
		<c:import url="page.jsp" />
		<h1>
			heelo
			<%--
				<%="welcome "+((models.User)session.getAttribute("user")).getUserName() %>
				<%= "welcome"+((models.Candidate)session.getAttribute("candidate")).getUserName() %>
			--%>
			
		</h1>
	</body>
</html>