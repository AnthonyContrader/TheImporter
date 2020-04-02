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
  <a  href="/homeuser.jsp">Home</a>
  <a  href="/product/getall">Products</a>
  <a  class="active" href="/excelmanager.jsp">Import Excel</a>
  <a  href="/history/getall">history</a>			<!-- metti collegamento -->
  <a href="/user/logout" id="logout">Logout</a>
</div>
<%
	int counter=1;
	String u="a";
%>
	<p id="demo"></p>
	
	<%if(System.getProperty("os.name").toLowerCase().contains("mac")){ %>
		<form action="/excel/preinsert" method="post">

		<label for="directory">Scegliere il percorso del file .xlsx: </label> <input
			type="text" id="directory" name="directory"
			placeholder="Insert directory of the file:">

		<button type="submit" value="Import" name="pulsante">submit</button>
	</form>
			
	<%} %>
	<%if(!System.getProperty("os.name").toLowerCase().contains("mac")){ %>
		<form action="/excel/preinsert" method="post">
	
			<label for="user">Scegliere il percorso del file .xlsx: </label> <input
				type="file" id="directory" name="directory"
				placeholder="Insert file excel">
	
			<button type="submit" value="Import" name="pulsante">submit</button>
		</form>
	<%} %>





	<%@ include file="../css/footer.jsp"%>
</body>
</html>