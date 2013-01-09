<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="0; index.jsp">
<title>Micro Bi(t).</title>
</head>
<body>

<jsp:useBean id="login" scope="session" class="org.silix.the9ull.microbit.controlinterface.LoginJB" />

<jsp:setProperty name="login" property="idOrAddress" param="id" />
<jsp:setProperty name="login" property="password" param="password" />

<%-- Id <jsp:getProperty name="login" property="id" />
Addr <jsp:getProperty name="login" property="address" />
--%>
<jsp:setProperty name="login" property="logged" value="true" />

</body>
</html>