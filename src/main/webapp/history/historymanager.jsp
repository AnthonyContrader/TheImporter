<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.HistoryDTO"
	import="java.util.List"%>
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
			<a href="homeuser.jsp">Home</a> <a
				href="ProductServlet?mode=productlist">Products</a> <a
				href="ExcelServlet?mode=mode">Import Excel</a> <a class="active"
				href="HistoryServlet?mode=mode">history</a>
			<!-- metti collegamento -->
			<a href="LogoutServlet" id="logout">Logout</a>
		</div>
	</div>

	<%
		List<HistoryDTO> productlist = (List<HistoryDTO>) request.getSession().getAttribute("historyDTOlist"); //metti collegamento
		List<Integer> idUser = (List<Integer>) request.getSession().getAttribute("idUsers");
	%>


	<table>
		<tr>
			<td>

				<form action="HistoryServlet?mode=searchbyuser" method="post">

					<label for="user">Cerca prodotti per ID cliente: </label> 
					<select id="idUser" name="idUser">

						<%
							for (HistoryDTO u : productlist) {
								
								
								if(!idUser.contains(u.getIdUser())){
									idUser.add(u.getIdUser());
								}
								
							}	
								
						for (Integer u : idUser) {
								
						%>
							<option value=<%=u%>><%=u%></option>
						<%
							}
						%>
						
					</select>
					
					<button type="submit" value="Import" name="pulsante">Cerca</button>

				</form>

			</td>
			<td>

				<form action="HistoryServlet?mode=searchbyproduct" method="post">

					<label for="user">Cerca Utenti per ID prodotto: </label> 
					<select id="idProduct" name="idProduct">

						<%
							for (HistoryDTO u : productlist) {
						%>
							<option value=<%=u.getIdProduct() %>><%=u.getIdProduct() %></option>
						<%
							}
						%>
						
					</select>
					<button type="submit" value="Import" name="pulsante">Cerca</button>


				</form>

			</td>
		</tr>
	</table>


	<%@ include file="../css/footer.jsp"%>
</body>
</html>