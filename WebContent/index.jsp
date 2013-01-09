<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Micro Bi(t). Home</title>
</head>
<body>
<jsp:include page="header.jsp" />

<jsp:useBean id="login" scope="session" class="org.silix.the9ull.microbit.controlinterface.LoginJB" />
<jsp:useBean id="info" scope="application" class="org.silix.the9ull.microbit.controlinterface.GetInfoJB" />

<div align="left">
	<%
	if(!login.isLogged()){
	%>
	<a href="register.jsp">Register</a>
	<% } %>
	1BTC = <jsp:getProperty name="info" property="usd" /> USD;
	1BTC = <jsp:getProperty name="info" property="eur" /> EUR;
	Users = <jsp:getProperty name="info" property="nusers" /> 
	
	<br />
	<%
	if(login.isLogged()){
	%>
	User id: <jsp:getProperty name="login" property="id" />. <a href="logout.jsp">logout</a> <br />
	<%
	}
	else {
	%>
	<form name="login" action="login.jsp" method="POST">
		<input type="text" name="id" value="id or deposit address" onclick="this.form.elements[0].value = ''" />
		<input type="password" name="password" value="***" onclick="this.form.elements[1].value = ''" />
		<input type="submit" value="Go" style="visibility:hidden" />
	</form>
	<% } %>
</div>

</body>
</html>