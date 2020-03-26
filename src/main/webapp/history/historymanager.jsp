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
<div class="navbar">
  <a  href="homeuser.jsp">Home</a>
  <a  href="ProductServlet?mode=productlist">Products</a>
  <a  href="ExcelServlet?mode=mode">Import Excel</a>
  <a  class="active" href="HistoryServlet?mode=mode">history</a>				<!-- metti collegamento -->
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
	</div>




	<table>
		<tr>
			<td>
			
				<form action="HistoryServlet?mode=searchbyuser" method="post">

					<label for="user">Cerca prodotti per ID cliente: </label>
					<input	type="text" id="idUser" name="idUser" placeholder="2">
					<button type="submit" value="Import" name="pulsante">Cerca</button>
					
				</form>
				
			</td>
			<td>

				<form action="HistoryServlet?mode=searchbyproduct" method="post">

					<label for="user">Cerca Utenti per ID prodotto: </label> 
					<input	type="text" id="idProduct" name="idProduct" placeholder="1">
					<button type="submit" value="Import" name="pulsante">Cerca</button>
					
					
				</form>

			</td>
		</tr>
	</table>


	<%@ include file="../css/footer.jsp"%>
</body>
</html>