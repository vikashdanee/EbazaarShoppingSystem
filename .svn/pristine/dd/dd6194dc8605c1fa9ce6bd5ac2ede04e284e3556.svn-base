<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBazaar - Cart Items</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="//select2.github.io/select2/select2-3.4.1/select2.js"></script>
    <link rel="stylesheet" type="text/css" href="//select2.github.io/select2/select2-3.4.1/select2.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 class="text-left">
                Cart Items
            </h3>
        </div>
        <div id="no-more-tables">
            <table width="550px" class="col-md-12 table-bordered table-striped table-condensed cf">
            	<thead class="cf">
        			<tr>
        				<th>Item Name</th>
		    			<th>Quantity</th> 
		    			<th>Price</th>
		    			<th>Total Price</th>
        			</tr>
        		</thead>
				<c:forEach var="cartItemPres" items="${cartItems}">
					<tr>
						<td>${cartItemPres.itemName }</td>
						<td>${cartItemPres.quantity }</td>
						<td>${cartItemPres.price }</td>
						<td>${cartItemPres.totalPrice }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		<p class="buttonRow">
			<input type="submit" value="Proceed to Checkout"  onclick="window.location='${pageContext.servletContext.contextPath}/shippingbilling'">
			<input type="submit" value="Continue Shopping" onclick="window.location='${pageContext.servletContext.contextPath}'">
			<input type="submit" value="Save Cart" onclick="window.location='${pageContext.servletContext.contextPath}/savecart'">
			<input type="submit" value="Delete Selected">
		</p>
	</div></div>

</body>
</html>