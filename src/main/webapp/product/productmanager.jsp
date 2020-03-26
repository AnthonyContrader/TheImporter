<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.ProductDTO"%>  <!-- metti collegamento -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Product Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a href="homeuser.jsp">Home</a>
  <a   class="active" href="ProductServlet?mode=productlist">Products</a>
  <a  href="ExcelServlet?mode=mode">Import Excel</a>
  <a  href="HistoryServlet?mode=mode">history</a>				<!-- metti collegamento -->
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
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
			<th></th>
			<th></th>
		</tr>
		<%
			for (ProductDTO u : list) {
		%>
		<tr>
			<td><a href=ProductServlet?mode=read&id=<%=u.getId()%>>
					<%=u.getProductName()%>
			</a></td>
			<td><%=u.getPrice()%></td>
			<td><%=u.getProductBrand()%></td>
			<td><%=u.getDescription()%></td>
			<td><a href=ProductServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=ProductServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="ProductServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="product">ProductName</label>  <!-- ho modificato i label -->
    </div>
    <div class="col-75">
      <input type="text" id="productname" name="productname" placeholder="inserisci un nome prodotto">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="price">Price</label>
    </div>
    <div class="col-75">
      <input type="number" id="price" name="price" placeholder="inserisci prezzo"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="brand">Brand</label>  <!-- ho modificato i label -->
    </div>
    <div class="col-75">
      <input type="text" id="brand" name="brand" placeholder="inserisci il brand del prodotto">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="description">Description</label>  <!-- ho modificato i label -->
    </div>
    <div class="col-75">
      <input type="text" id="description" name="description" placeholder="inserisci una descrizione prodotto">
    </div>
  </div>
      <button type="submit" >Insert</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>