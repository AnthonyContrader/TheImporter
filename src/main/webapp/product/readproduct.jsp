<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.ProductDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a class="active" href="homeuser.jsp">Home</a>
  <a  href="ProductServlet?mode=productlist">Products</a>
  <a  href="ExcelServlet?mode=mode">Import Excel</a>
  <a  href="HistoryServlet?mode=mode">history</a>				<!-- metti collegamento -->
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%ProductDTO u = (ProductDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Product Name</th>
		<th>Price</th>
		<th>Description</th>
		<th>Brand</th>
	</tr>
	<tr>
		<td><%=u.getProductName()%></td>
		<td> <%=u.getPrice()%></td>
		<td> <%=u.getDescription()%></td>
		<td> <%=u.getProductBrand()%></td>
	</tr>	
</table>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>