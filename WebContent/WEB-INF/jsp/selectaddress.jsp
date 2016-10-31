<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<h1>${title}</h1>
	<table border="1" id='selectAddTable'>
		<tr>
		    <th>Name</th>
		    <th>Street</th> 
		    <th>City</th>
		    <th>State</th> 
		    <th>ZipCode</th>
		    <th>Select Address</th>
		    <th>Delete Address</th>
		  </tr>
		  <c:forEach var="address" items="${addressList}">
		  <tr>
		  	<td><input name = 'getStreet' value = '${address.street}' /></td>
			<td><input name = 'getCity' value = '${address.city}' /></td>
			<td><input name = 'getState' value = '${address.state}' /></td>
			<td><input name = 'getZip' value = '${address.zip}' /></td>
		
			<td><a href="${pageContext.servletContext.contextPath}/shippingbilling/${address.id }/${title}">Select</a></td>
			<td><a href="${pageContext.servletContext.contextPath}/shippingbilling/${address.id }/${title}">Delete</a></td>
            
		</tr>
		  </c:forEach>
	</table>
	<input type="button" value="Back" onclick="goBack()"/>
	<script>
		function goBack() {
			window.history.back();
			
		}
		$('.select').on('click', function(e){
		    alert($("#table tr.selected td:first").html());
		});
		
		
	</script>
</body>
</html>