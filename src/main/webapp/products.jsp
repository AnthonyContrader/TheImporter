<%@ page import="it.contrader.dto.ProductDTO" import="java.util.*"%>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="User Management">
		<meta name="author" content="Vittorio Valent">
		<link href="/css/vittoriostyle.css" rel="stylesheet">
		<title>Product Manager</title>
		
	</head>
<body>
	<%@ include file="./css/header.jsp"%>

	<div class="navbar">
			<a class="active" href="homeuser.jsp">Home</a> 
			<a href="/product/getall">Products</a> 
			<a href="ExcelServlet?mode=mode">Import Excel</a> 
			<a href="HistoryServlet?mode=mode">history</a>
			<a href="/user/logout" id="logout">Logout</a>
	</div>
	
	<div class="main">
		<%
			List<ProductDTO> list = (List<ProductDTO>) request.getSession().getAttribute("list");
		%>

		<br>

		<table>
			<tr>
				<th>Product Name</th>
				<th>price</th>
				<th>Brand</th>
				<th>Description</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (ProductDTO u : list) {
			%>
			<tr>
				<td><a href="/product/read?id=<%=u.getId()%>"> <%=u.getProductName()%></a></td>
				<td><%=u.getPrice()%></td>
				<td><%=u.getBrand()%></td>
				<td><%=u.getDescription()%></td>
				<td><a href="/product/preupdate?id=<%=u.getId()%>">Edit</a></td>
				<td><a href="/product/delete?id=<%=u.getId()%>">Delete</a></td>
			</tr>
			<%
				}
			%>
		</table>



		<form id="floatright" action="/product/insert" method="post">
			<div class="row">
				<div class="col-25">
					<label for="productName">Product Name</label>
				</div>
				<div class="col-75">
					<input type="text" id="productName" name="productName"
						placeholder="inserisci nome prodotto">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="price">Price</label>
				</div>
				<div class="col-75">
					<input type="number" id="price" name="price"
						placeholder="inserisci prezzo">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="brand">Brand</label>
				</div>
				<div class="col-75">
					<input type="text" id="brand" name="brand"
						placeholder="inserisci brand prodotto">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="description">description</label>
				</div>
				<div class="col-75">
					<input type="text" id="description" name="description"
						placeholder="inserisci descrizione prodotto">
				</div>
			</div>
			<button type="submit">Insert</button>
		</form>

	</div>
	<br>
	<%@ include file="./css/footer.jsp"%>
</body>
</html>