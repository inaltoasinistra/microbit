<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Micro Bi(t).</title>
</head>

<jsp:useBean id="register" class="org.silix.the9ull.microbit.controlinterface.Register" />


<body>
	<jsp:include page="header.jsp" />
<%
	if(request!=null && request.getParameter("email")!=null) {
		%>
		<jsp:setProperty name="register" property="email" param="email" />
		<jsp:setProperty name="register" property="password" param="password" />
		<jsp:setProperty name="register" property="confirm" param="confirm" />
		
		<jsp:getProperty name="register" property="registered" />
		<%
		
		/*if(registered){
			%> REGISTRATO!! <%
		}*/
	}
	else {
%>
	<jsp:include page="register-form.jsp" />
<%
	}
%>
</body>
</html>