<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.model.Product" import="it.contrader.model.User"%>
<!-- metti collegamento -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Excel Manager</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</head>
<body>
	<%@ include file="../css/header.jsp"%>

<div class="navbar">
  <a  href="/homeuser.jsp">Home</a>
  <a  href="/product/getall">Products</a>
  <a  href="/excelmanager.jsp">Import Excel</a>
  <a  class="active" href="/history/getall">history</a>			<!-- metti collegamento -->
  <a href="/user/logout" id="logout">Logout</a>
</div>
<%
			if (request.getSession().getAttribute("SEARCHBY").equals("user")) {
		%>
		<%
			List<Product> list = (List<Product>) request.getSession().getAttribute("productHistory"); //metti collegamento
		%>

		<br>

		<table class="paginated">
			<thead>
				<tr>
					<th>ProductName</th>
					<th>Price</th>
					<th>Brand</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Product u : list) {
				%>
				<tr>
					<td><%=u.getProductName()%></td>
					<td><%=u.getPrice()%></td>
					<td><%=u.getBrand()%></td>
					<td><%=u.getDescription()%></td>

				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<br>

		<script>
			$('table.paginated')
					.each(
							function() {
								var currentPage = 0;
								var numPerPage = 5; // number of items 
								var $table = $(this);
								//var $tableBd = $(this).find("tbody");

								$table.bind('repaginate', function() {
									$table.find('tbody tr').hide().slice(
											currentPage * numPerPage,
											(currentPage + 1) * numPerPage)
											.show();
								});
								$table.trigger('repaginate');
								var numRows = $table.find('tbody tr').length;
								var numPages = Math.ceil(numRows / numPerPage);
								var $pager = $('<div class="pager"></div>');
								for (var page = 0; page < numPages; page++) {
									$(
											'<span class="page-number"></span>')
											.text(page + 1)
											.bind(
													'click',
													{
														newPage : page
													},
													function(event) {
														currentPage = event.data['newPage'];
														$table
																.trigger('repaginate');
														$(this)
																.addClass(
																		'active')
																.siblings()
																.removeClass(
																		'active');
													}).appendTo($pager)
											.addClass('clickable');
								}
								if (numRows > numPerPage) {
									$pager.insertAfter($table).find(
											'span.page-number:first').addClass(
											'active');
								}
							});
		</script>
		<%
			}
		%>


		<%
			if (request.getSession().getAttribute("SEARCHBY").equals("product")) {
		%>
		<%
			List<User> list = (List<User>) request.getSession().getAttribute("userHistory");
		%>

		<br>

		<table class="paginated">
			<thead>
				<tr>
					<th>Username</th>
					<th>Usertype</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (User u : list) {
				%>
				<tr>
					<td><%=u.getUsername()%></td>
					<td><%=u.getUsertype()%></td>

				</tr>
				<%
					}
				%>
			</tbody>
		</table>


		<%
			}
		%>
	<%@ include file="../css/footer.jsp"%>
</body>
</html>