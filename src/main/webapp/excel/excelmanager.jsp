<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.ProductDTO"%>
<!-- metti collegamento -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Excel Manager</title>
</head>
<body>
	<%@ include file="../css/header.jsp"%>

	<div class="navbar">
		<a href="homeuser.jsp">Home</a> <a class="active"
			href="ProductServlet?mode=productlist">Products</a>
		<!-- metti collegamento -->
		<a href="LogoutServlet" id="logout">Logout</a>
	</div>
<%
	int counter=1;
	String u="a";
%>
	<p id="demo"></p>

	<form action="ExcelServlet?mode=insert" method="post">

		<label for="user">Scegliere il percorso del file .xlsx: </label> <input
			type="file" id="directory" name="directory"
			placeholder="Insert file excel">

		<button type="submit" value="Import" name="pulsante">Login</button>
       <%="["+counter+"]"+ u%>
	</form>






	<%@ include file="../css/footer.jsp"%>
</body>
</html>