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
	<%
		ProductDTO u = (ProductDTO) request.getSession().getAttribute("dto");
	%>


	<form id="floatleft" action="/product/update" method="post">
		<div class="col-25">
			<label for="product">ProductName</label>
		</div>
		<div class="col-75">
			<input type="text" id="user" name="productName"
				value=<%=u.getProductName()%>>
		</div>
		<div class="row">
			<div class="col-25">
				<label for="product">price</label>
			</div>
			<div class="col-75">
				<input type="text" id="pass" name="price"
					value=<%=u.getPrice()%>>
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label for="product">brand</label>
			</div>
			<div class="col-75">
				<input type="text" id="pass" name="brand"
					value=<%=u.getBrand()%>>
			</div>
		</div>
		<div class="row">
			<div class="col-25">
				<label for="product">description</label>
			</div>
			<div class="col-75">
				<input type="text" id="pass" name="description"
					value=<%=u.getDescription()%>>
			</div>
			<input type="hidden" name="id" value=<%=u.getId()%>>
		</div>
		<button type="submit">Edit</button>


	</form>



	<%@ include file="./css/footer.jsp"%>
</body>
</html>