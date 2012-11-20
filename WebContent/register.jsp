<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Micro Bit.</title>
</head>
<body>
	<jsp:include page="header.jsp" />
<%
	if(request!=null && request.getParameter("email")!=null) {
		out.println("<br /> "+request.getParameter("email"));
		out.println(request.getParameter("password").equals(request.getParameter("confirm")));
	}
	else {
%>

	<form name="register" action="register.jsp" method="POST">
		<input type="email" name="email" value="you@some.domain" onclick="this.form.elements[0].value = ''" />
		<input type="password" name="password" value="***" onclick="this.form.elements[1].value = ''" />
		<input type="password" name="confirm" value="***" onclick="this.form.elements[2].value = ''" />
		<input type="submit" value="Register" style="visibility:hidden" />
	</form>
<%
	}
%>
</body>
</html>