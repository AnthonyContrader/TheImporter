<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.ProductDTO"%>
<!-- metti collegamento -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Excel Manager</title>
</head>
<body>
	<%@ include file="../css/header.jsp"%>

<div class="navbar">
  <a  href="homeuser.jsp">Home</a>
  <a  href="ProductServlet?mode=productlist">Products</a>
  <a class="active" href="ExcelServlet?mode=mode">Import Excel</a>
  <a  href="HistoryServlet?mode=mode">history</a>				<!-- metti collegamento -->
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
	<%	
	List<List<String>> dataList = (List<List<String>>) request.getSession().getAttribute("titlesList");//J:\TheImporter\TheImporter.xlsx
	List<String> titles = dataList.get(1);
	if(titles != null){
		request.getSession().setAttribute("titlesList",titles);  //settiamo titles per andarlo a leggere nella seconda fase del controller 
	}
	List<String> preData = dataList.get(0);
	int counter = 0;
	%>
	
	<br>

	<table>
		<tr>
			<%
				for (String u : titles) {
				counter++;
			%>
			<th><%="[" + counter + "]" + u%></th>
			<%
				}
			%>
		</tr>
		<%
			counter = 0;
		for (int i = 0; i <= ((preData.size() / titles.size()) - 1); i++) {
        %><tr><%
			for (int j = 0; j <= titles.size() - 1; j++) {
			%>
			<td><%=preData.get(counter)%></td>
			<%
				counter++;
			}
			%>

		</tr>
		<%
			}
		%>
	</table>
	
			
	<br>


	
	

	<form id="floatright" action="ExcelServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="col1">Inserine N° della colonna nome prodotto: </label>  <!-- ho modificato i label -->
    </div>
    <div class="col-75">
      <input type="text" id="userPar1" name="userPar1" placeholder="inserisci colonna productName">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="col2">Inserine N° della colonna prezzo: </label>
    </div>
    <div class="col-75">
      <input type="number" id="userPar2" name="userPar2" placeholder="inserisci colonna prezzo"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="col3">Brand</label>  <!-- ho modificato i label -->
    </div>
    <div class="col-75">
      <input type="text" id="userPar3" name="userPar3" placeholder="inserisci colonna brand del prodotto">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="col4">Description</label>  <!-- ho modificato i label -->
    </div>
    <div class="col-75">
      <input type="text" id="userPar4" name="userPar4" placeholder="inserisci colonna descrizione prodotto">
    </div>
  </div>
      <button type="submit" >Insert</button>
</form>


		
	





	<%@ include file="../css/footer.jsp"%>
</body>
</html>