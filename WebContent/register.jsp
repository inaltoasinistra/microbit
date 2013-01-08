<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Micro Bi(t).</title>
</head>

<jsp:useBean id="register" class="org.silix.the9ull.microbit.controlinterface.RegisterJB" />

<body>
	<jsp:include page="header.jsp" />
<%
	if(request!=null && request.getParameter("email")!=null) {
		%>
		<jsp:setProperty name="register" property="*" />
		<%
		
		if(register.isRegistered()){
			%><b>User registered.</b><br />  
			Deposit address: <jsp:getProperty name="register" property="new_deposit_address" /><br />
			User id: <jsp:getProperty name="register" property="new_id" /><br />
			Email: <jsp:getProperty name="register" property="email" /><br />
			<%
		}
		if(register.getError()==1){
			//password non confermata
			%>Password not confirmed. Try again: <br />
			<jsp:include page="register-form.jsp" /><%
		}
	}
	else {
%>
	<jsp:include page="register-form.jsp" />
<%
	}
%>
</body>
</html>