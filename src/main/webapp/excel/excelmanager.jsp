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
  <a class="active" href="homeuser.jsp">Home</a>
  <a  href="ProductServlet?mode=productlist">Products</a>
  <a  href="ExcelServlet?mode=mode">Import Excel</a>
  <a  href="HistoryServlet?mode=mode">history</a>				<!-- metti collegamento -->
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<%
	int counter=1;
	String u="a";
%>
	<p id="demo"></p>

	<form action="ExcelServlet?mode=getchoice" method="post">

		<label for="user">Scegliere il percorso del file .xlsx: </label> <input
			type="file" id="directory" name="directory"
			placeholder="Insert file excel">

		<button type="submit" value="Import" name="pulsante">submit</button>
	</form>






	<%@ include file="../css/footer.jsp"%>
</body>
</html>