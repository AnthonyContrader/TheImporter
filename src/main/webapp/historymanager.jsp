<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.HistoryDTO"%>
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
  <a  href="/history/getall">history</a>				<!-- metti collegamento -->
  <a href="/user/logout" id="logout">Logout</a>
</div>

<%
		List<HistoryDTO> productlist = (List<HistoryDTO>) request.getSession().getAttribute("historyDTOlist"); //metti collegamento
		List<HistoryDTO> idUser= (List<HistoryDTO>) request.getSession().getAttribute("list");
	%>


	<table>
		<tr>
			<td>

				<form action="HistoryServlet?mode=searchbyuser" method="post">

					<label for="user">Cerca prodotti per ID cliente: </label> 
					<select id="idUser" name="idUser">

						<%
							for (HistoryDTO u : productlist) {
								
								
								if(!idUser.contains(u.getUserDTO().getId())){
									idUser.add(u);
								}
								
							}	
								
						for (HistoryDTO u : idUser) {
								
						%>
							<option value=<%=u.getUserDTO().getId()%>><%=u.getUserDTO().getUsername()%></option>
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
							<option value=<%=u.getProductDTO().getId()%>><%=u.getProductDTO().getProductName() %></option>
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