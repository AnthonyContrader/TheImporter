<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.HistoryDTO"
	import="it.contrader.dto.UserDTO"%>
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
  <a  href="/excelmanager.jsp">Import Excel</a>
  <a  class="active"  href="/history/getall">history</a>				<!-- metti collegamento -->
  <a href="/user/logout" id="logout">Logout</a>
</div>

<%
		List<HistoryDTO> productlist = (List<HistoryDTO>) request.getSession().getAttribute("historyDTOlist"); //metti collegamento
		List<UserDTO> idUser= (List<UserDTO>) request.getSession().getAttribute("list");
	%>


	<table>
		<tr>
			<td>

				<form action="/history/searchbyuser" method="post">

					<label for="user">Cerca prodotti per ID cliente: </label> 
					<select id="iduser" name="iduser">

						<%
							for (HistoryDTO u : productlist) {
								
								
								if(!idUser.contains(u.getUserDTO())){
									idUser.add(u.getUserDTO());
								}
								
							}	
								
						for (UserDTO u : idUser) {
								
						%>
							<option value=<%=u.getId()%>><%=u.getUsername()%></option>
						<%
							}
						%>
						
					</select>
					
					<button type="submit" value="Import" name="pulsante">Cerca</button>

				</form>

			</td>
			<td>

				<form action="/history/searchbyproduct" method="post">

					<label for="user">Cerca Utenti per ID prodotto: </label> 
					<select id="idproduct" name="idproduct">

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