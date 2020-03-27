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
<style type="text/css">
      
div.pager {
    text-align: left; 
}

div.pager span {
    display: inline-block;
    width: 1.8em;
    height: 1.8em;
    line-height: 1.8;
    text-align: center;
    cursor: pointer;
    background: #191919;
    color: #ccc;
    margin-right: 0.5em;
}

div.pager span.active {
    background: #fff;
    color:#191919;
    font-weight:bold;
    text-align:center;
}

</style>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</head>
<body>
	<%@ include file="../css/header.jsp"%>

	<div class="navbar">
<div class="navbar">
  <a href="homeuser.jsp">Home</a>
  <a  href="ProductServlet?mode=productlist">Products</a>
  <a  href="ExcelServlet?mode=mode">Import Excel</a>
  <a class="active" href="HistoryServlet?mode=mode">history</a>				<!-- metti collegamento -->
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
	</div>


<% if(request.getSession().getAttribute("SEARCHBY").equals("user")){
%>
	<%
		List<ProductDTO> list = (List<ProductDTO>) request.getSession().getAttribute("productHistory"); //metti collegamento
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
	</tbody>
	</table>
	<script>


    $('table.paginated').each(function () {
        var currentPage = 0;
        var numPerPage = 5; // number of items 
        var $table = $(this);
        //var $tableBd = $(this).find("tbody");

        $table.bind('repaginate', function () {
            $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
        });
        $table.trigger('repaginate');
        var numRows = $table.find('tbody tr').length;
        var numPages = Math.ceil(numRows / numPerPage);
        var $pager = $('<div class="pager"></div>');
        for (var page = 0; page < numPages; page++) {
            $('<span class="page-number"></span>').text(page + 1).bind('click', {
                newPage: page
            }, function (event) {
                currentPage = event.data['newPage'];
                $table.trigger('repaginate');
                $(this).addClass('active').siblings().removeClass('active');
            }).appendTo($pager).addClass('clickable');
        }
        if (numRows > numPerPage) {
            $pager.insertAfter($table).find('span.page-number:first').addClass('active');
        }
    });
</script>
<%
 	}
%>

<% if(request.getSession().getAttribute("SEARCHBY").equals("product")){
%>
	<%
		List<UserDTO> list = (List<UserDTO>) request.getSession().getAttribute("userHistory");
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
			for (UserDTO u : list) {
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