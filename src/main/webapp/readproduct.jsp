<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.ProductDTO"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read Product</title>
</head>
<body>
	<%@ include file="./css/header.jsp"%>

	<div class="navbar">
			<a href="/homeuser.jsp">Home</a> 
			<a class="active" href="/product/getall">Products</a> 
			<a href="excelmanager.jsp">Import Excel</a> 
			<a href="HistoryServlet?mode=mode">history</a>
			<a href="/user/logout" id="logout">Logout</a>
	</div>
	<br>

	<div class="main">
		<%
			ProductDTO u = (ProductDTO) request.getSession().getAttribute("dto");
		%>


		<table>
			<tr>
				<th>Product Name</th>
				<th>Price</th>
				<th>Description</th>
				<th>Brand</th>
			</tr>
			<tr>
				<td><%=u.getProductName()%></td>
				<td><%=u.getPrice()%></td>
				<td><%=u.getDescription()%></td>
				<td><%=u.getBrand()%></td>
			</tr>
			</table>
					<br> <br> <br> <br> <br> <br> <br>
					<br> <br> <br> <br> <br> <br> <br>
			
		</div>
		
			<%@ include file="./css/footer.jsp"%>
</body>
</html>