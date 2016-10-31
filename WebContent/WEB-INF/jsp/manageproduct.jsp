<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBazaar - Manage Product</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="//select2.github.io/select2/select2-3.4.1/select2.js"></script>
    <link rel="stylesheet" type="text/css" href="//select2.github.io/select2/select2-3.4.1/select2.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
</head>
<script>

function getDetails()
 {
    var userSelection = document.getElementById("catid").value;
    var text = document.getElementById("catid").options[document.getElementById('catid').selectedIndex].text;
     //alert(userSelection);
   	window.location = 'productlist?catId='+userSelection+'&name='+text;
}
</script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 class="text-left">
              Product List
            </h3>
        </div>
       
   
	<select id="catid" name="catid" onChange="getDetails()">
	    <c:forEach var="catalogPres" items="${catalogs}">
	     <option value= "${catalogPres.id}">${catalogPres.name}</option>
	    </c:forEach>
	</select>

	 <div id="no-more-tables">
	  <table class="col-md-12 table-bordered table-striped table-condensed cf">
	  
	  <thead class="cf">
        			<tr>
	<th>Name</th>
	<th>Quantity</th>
	<th>MDate</th>
	<th>UnitPrice</th>
	</tr>
        		</thead>
	
		<c:forEach var="productPres" items="${products}">
		<tr>
			
			<td>${productPres.getName() }</td>
			
			<td>${productPres.getQuantityAvail() }</td>
		
			
			<td>${productPres.getMfg() }</td>
		
			
			<td>${productPres.getUnitPrice() }</td>
		</tr>
    	</c:forEach>
    	<tr>
    	<td>
    	<input type="submit" value="Add Product" onclick="window.location='${pageContext.servletContext.contextPath}/addnewproduct?id=${selectedId}&value=${name}'"></td>
  <td>	<input type="submit" value="clear" name = "Clear"></td>
  	</tr>
    	
	</table>
	<br>
	
  	</div>
  	</div>
  
  	</div>
</body>
</html>
<script>
document.getElementById("catid").value = "${selectedId}";
//document.getElementById('catid').selectedIndex = 0;
</script>