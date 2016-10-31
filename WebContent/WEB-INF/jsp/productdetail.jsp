<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBazaar - Product Details</title>
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
                Product Details for ${product.name }
            </h3>
        </div>
        <form method="post" action="${pageContext.servletContext.contextPath}/addToCart">
        <div id="no-more-tables">
            <table width="600px" class="col-md-12 table-bordered table-striped table-condensed cf">
            	<tr>
					<td><b>Quantity</b></td>
					<td><input type = "text" name = "quantity" >
					<label style="color: red">${exp.getMessage()}</label>
					</td>
				</tr>
				<tr>
					<td><b>Item</b></td>
					<td>${product.name }</td>
				</tr>
				<tr>
					<td><b>Price</b></td>
					<td>${product.unitPrice }</td>
				</tr>
				<tr>
					<td><b>Quantity</b></td>
					<td>${product.quantityAvail }</td>
				</tr>
				<tr>
					<td><b>Review</b></td>
					<td>${product.description }</td>
				</tr>
			</table>
		</div>
		<br>
		<table>
			<tr>
				<td>
					
						<p class="buttonRow">
							<input type="submit" value="Add to Cart">
							<input type="hidden" name="prodName" value="${product.name }"> 
							<input type="hidden" name="unitPrice" value="${product.unitPrice }">
						</p>
					
				</td>
				<td>
				<p class="buttonRow">
					<button onclick="goBack()">Back</button>
					<input type="hidden" > 
					<input type="hidden">
				</p>
				</td>
			</tr>
		</table>
		</form>
	</div></div>
	<script>
		function goBack() {
			window.history.back();
		}
	</script>
</body>

</html>