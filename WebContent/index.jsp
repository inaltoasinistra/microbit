<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Micro Bi(t). Home</title>
</head>
<%! String ip; %>
<body>
<%
ip = request.getRemoteAddr();
%>
<%-- Your IP address is <%= ip %> but... who cares?<br/><br/><br/> --%>

<div align="left">
	<a href="register.jsp">Register</a>
	
	<br />
	<form name="login" action="login.jsp" method="POST">
		<input type="text" name="id" value="id or deposit address" onclick="this.form.elements[0].value = ''" />
		<input type="password" name="password" value="***" onclick="this.form.elements[1].value = ''" />
		<input type="submit" value="Go" style="visibility:hidden" />
	</form>
</div>

</body>
</html>