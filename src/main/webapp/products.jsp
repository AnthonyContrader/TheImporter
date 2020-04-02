<%@ include file="./css/header.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
	

<div class="navbar">
  <a  href="/homeuser.jsp">Home</a>
  <a  class="active" href="/product/getall">Products</a>
  <a  href="/excelmanager.jsp">Import Excel</a>
  <a  href="/history/getall">history</a>			<!-- metti collegamento -->
  <a href="/user/logout" id="logout">Logout</a>
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
		List<ProductDTO> list = (List<ProductDTO>) request.getSession().getAttribute("list"); //metti collegamento
		if(list!=null){
			request.getSession().setAttribute("listLoaded", list);
		}
		else list = (List<ProductDTO>) request.getSession().getAttribute("listLoaded"); //metti collegamento
		
		if(list == null){ //se non ho ancora nulla ricarico
			
		}
		Double pages = list.size()/(double)20;
		pages = Math.ceil(pages);
		%>
	
		<p>prodotti totali:<%=list.size() %></p>
		pagine:
		<%for(count=1; count<=pages; count++) {%>
		<%= myMethod("<a href='/product/pagination?page="+count+"'>"+count+"</a>  ") %>
		<%} %>
		
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
			int pageToShow = 1;
			List<ProductDTO>list1;
			if(request.getSession().getAttribute("page")!=null){
				pageToShow = Integer.parseInt(request.getSession().getAttribute("page").toString());
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