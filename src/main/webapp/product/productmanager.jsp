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
  <a href="/JspApp/homeuser.jsp">Home</a>
  <a   class="active" href="/JspApp/ProductServlet?mode=productlist">Products</a>
  <a  href=/JspApp/ExcelServlet?mode=mode">Import Excel</a>
  <a  href="/JspApp/HistoryServlet?mode=mode">history</a>				<!-- metti collegamento -->
  <a href="/JspApp/LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">

	<%!
	String myMethod(String input) {
    return input;
	}
	%>
	<%!
	int count=0;
	%>

	<%
		List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("list"); //metti collegamento
		if(list!=null){
			request.getSession().setAttribute("listLoaded", list);
		}
		else list = (List<ProductDTO>) request.getSession().getAttribute("listLoaded"); //metti collegamento
		Double pages = list.size()/(double)20;
		pages = Math.ceil(pages);
		
	%>
	
	<p>prodotti totali:<%=list.size() %></p>
	pagine:
	<%for(count=1; count<=pages; count++) {%>
	<%= myMethod("<a href='/JspApp/product/productmanager.jsp?page="+count+"'>"+count+"</a>  ") %>
	<%} %>
	
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
		int pageToShow = 1;
		List<ProductDTO>list1;
		if(request.getParameter("page")!=null){
			pageToShow = Integer.parseInt(request.getParameter("page"));
		}
			
			if(list.size()<(pageToShow-1)*20+20){
				list1 = list.subList((pageToShow-1)*20, list.size()); //pageToShow*20+20
			}
			else {
				list1 = list.subList((pageToShow-1)*20, (pageToShow-1)*20+20); //pageToShow*20+20
			}
			for (ProductDTO u : list1) {
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