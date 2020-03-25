<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.ProductDTO"
	import="it.contrader.dto.UserDTO"
	%>
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


<% if(request.getAttribute("SEARCHBY").equals("product")){
%>
	<%
		List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("list"); //metti collegamento
	%>

<br>

	<table>
		<tr>
			<th>ProductName</th>
			<th>Price</th>
			<th>Brand</th>
			<th>Description</th>
		</tr>
		<%
			for (ProductDTO u : list) {
		%>
		<tr>
			<td><%=u.getProductName()%></td>
			<td><%=u.getPrice()%></td>
			<td><%=u.getProductBrand()%></td>
			<td><%=u.getDescription()%></td>

		</tr>
		<%
			}
		%>
	</table>
<%
 	}
%>

<% if(request.getAttribute("SEARCHBY").equals("user")){
%>
	<%
		List<UserDTO> list = (List<UserDTO>) request.getAttribute("list");
	%>

<br>

	<table>
		<tr>
			<th>Username</th>
			<th>Usertype</th>
		</tr>
		<%
			for (UserDTO u : list) {
		%>
		<tr>
			<td><%=u.getUsername()%></td>
			<td><%=u.getUsertype()%></td>

		</tr>
		<%
			}
		%>
	</table>


<%
 	}
%>
	<%@ include file="../css/footer.jsp"%>
</body>
</html>