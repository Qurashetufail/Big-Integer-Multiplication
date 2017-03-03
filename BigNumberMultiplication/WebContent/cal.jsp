<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="BigInteger.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Style.css">
<title>Result</title>
</head>
<body style="background-color:#B7D671;">
<%
	String num1=request.getParameter("num1");
	String num2=request.getParameter("num2");
	Test calculate=new Test();
	%>
	<div> 
	<textarea rows="100" cols="500" id=styleid readonly>
	<%out.println(calculate.multiply(num1,num2)); %>
	</textarea>
	</div>
	<form action="Welcome.jsp">
	<br>
	<input type="submit" value=Back>
	</form>
</body>
</html>