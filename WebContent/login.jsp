<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<%@page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Micro Bit.</title>
</head>
<%--

if(request!=null && request.getParameter("id")!=null){
	
	/*
	int id_user = Integer.parseInt(request.getParameter("id"));
	Enumeration<String> names = request.getHeaderNames();
	while(names.hasMoreElements()){
		String name = names.nextElement();
		out.println(name+" "+request.getHeader(name)+"<br/>");
	}*/
	
	HttpSession bsession = request.getSession();
	//bsession.getAttribute("key");
	
	 
	bsession.setAttribute("user",104);
	out.println(bsession.getId());
}

--%>
<body>
<jsp:include page="header.jsp" />

<jsp:useBean id="login" class="org.silix.the9ull.microbit.control.Login" />

<jsp:setProperty name="login" property="idOrAddress" param="id" />
<jsp:setProperty name="login" property="password" param="password" />

<%-- Id <jsp:getProperty name="login" property="id" />
Addr <jsp:getProperty name="login" property="address" />
--%>
Logged: <jsp:getProperty name="login" property="logged" />
</body>
</html>