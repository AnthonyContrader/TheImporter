<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.ProductDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Product</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active" href="ProductServlet?mode=productlist">Products</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%ProductDTO u = (ProductDTO) request.getAttribute("dto");%>


<form id="floatleft" action="ProductServlet?mode=update&id=<%=u.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="product">Productname</label>
    </div>
    <div class="col-75">
      <input type="text" id="product" name="productname" value=<%=u.getProductName()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="price">Price</label>
    </div>
    <div class="col-75">
      <input
			type="number" id="price" name="price" value=<%=u.getPrice()%>> 
    </div>
  </div>
   <div class="row">
    <div class="col-25">
      <label for="description">Description</label>
    </div>
    <div class="col-75">
      <input type="text" id="description" name="description" value=<%=u.getDescription()%>>
    </div>
  </div>
   <div class="row">
    <div class="col-25">
      <label for="brand">brand</label>
    </div>
    <div class="col-75">
      <input type="text" id="brand" name="brand" value=<%=u.getProductBrand()%>>
    </div>
  </div>
  
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>