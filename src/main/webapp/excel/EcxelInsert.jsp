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
		List<List<String>> dataList = (List<List<String>>) request.getAttribute("titlesList");//J:\TheImporter\TheImporter.xlsx
	List<String> titles = dataList.get(1);
	List<String> preData = dataList.get(0);
	int counter = 0;
	%>


	<table>
		<tr>
			<%
				for (String u : titles) {
				counter++;
			%>
			<th><%="[" + counter + "]" + u%></th>
			<%
				}
			%>
		</tr>
		<%
			counter = 0;
		for (int i = 0; i <= ((preData.size() / titles.size()) - 1); i++) {

			for (int j = 0; j <= titles.size() - 1; j++) {
		%>
		<tr>
			<td><%=preData.get(counter)%></td>
			<%
				counter++;
			}
			%>

		</tr>
		<%
			}
		%>
	</table>



	<form action="ExcelServlet?mode=insert" method="post">

		<label for="user">Inserine N° della prima colonna: </label>
		<input	type="text" id="directory" name="directory" placeholder="1">
		
		<label for="user">Inserine N° della seconda colonna: </label> 
		<input	type="text" id="directory" name="directory" placeholder="2">
		
		<label for="user">Inserine N° della quarta colonna: </label>
		<input	type="text" id="directory" name="directory" placeholder="3">
		
		<label for="user">Inserine N° della quinta colonna: </label> 
		<input	type="text" id="directory" name="directory" placeholder="4">

		<button type="submit" value="Import" name="pulsante">Login</button>
	</form>





	<%@ include file="../css/footer.jsp"%>
</body>
</html>