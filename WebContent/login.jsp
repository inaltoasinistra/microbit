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
<%

if(request!=null && request.getParameter("id")!=null){
	int id_user = Integer.parseInt(request.getParameter("id"));
	/*
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

%>
<body>
<a href=".">Home</a><br/>
<%= request.getParameter("id") %>
<%= request.getParameter("password") %>
</body>
</html>