<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="Style.css">
<script>
function submitForm() {
	   // Get the first form with the name
	   // Hopefully there is only one, but there are more, select the correct index
	   var frm = document.getElementsByName('fs')[0];
	   frm.submit(); // Submit
	   frm.reset();  // Reset
	   //return false; // Prevent page refresh
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Input</title>
</head>
<body onload="document.getElementById('myid').value=''" style="background-color:#B7D671;">

	<div class=mydiv>
		<h1>Big Number Multiplication</h1>
		<form name=fs method=post action=cal.jsp>
			<input type=text name=num1 class=myclass id=myid > <br>
			<br> <input type=text name=num2 class=myclass id=myid1> <br> <br>
			<input type="button" value="Calculate" id="btnsubmit" onclick="submitForm()">
		</form>
	</div>
</body>
</html>