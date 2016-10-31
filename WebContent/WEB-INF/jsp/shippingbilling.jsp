<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBazaar - Shipping & Billing</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="//select2.github.io/select2/select2-3.4.1/select2.js"></script>
    <link rel="stylesheet" type="text/css" href="//select2.github.io/select2/select2-3.4.1/select2.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
</head>
<body>
<form action = "${pageContext.servletContext.contextPath}/payment" method = 'post'>
	<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 class="text-left">
                Shipping And Billing Addresses
            </h3>
        </div>
        <div id="no-more-tables">
            <table class="table table-striped table-hover">
            	<thead class="cf">
        			<tr>
        				<th>Address</th>
		    			<th>Shipping Address</th> 
		    			<th>Billing Address</th>
        			</tr>
        		</thead>
				<tr>
					<td><b>Street</b></td>
					<td><input type="text" name = 'shippingStreet' value = '${shippingAddress.street }' /></td>
					<td><input type="text" name = 'billingStreet' value = '${billingAddress.street }' /></td>
				</tr>
				<tr>
					<td><b>City</b></td>
					<td><input type="text" name = 'shippingCity' value = '${shippingAddress.city }' /></td>
					<td><input type="text" name = 'billingCity' value = '${billingAddress.city }' /></td>
				</tr>
				<tr>
					<td><b>State</b></td>
					<td><input type="text" name = 'shippingState' value = '${shippingAddress.state }' /></td>
					<td><input type="text" name = 'billingState' value = '${billingAddress.state }' /></td>
				</tr>
				<tr>
					<td><b>Zip</b></td>
					<td><input type="text" name = 'shippingZip' value = '${shippingAddress.zip }' /></td>
					<td><input type="text" name = 'billingZip' value = '${billingAddress.zip }' /></td>
				</tr>
			</table>
		</div>
	
		<div>
		<br>
		<input type="checkbox" name="add" value="sameShipBill"> Billing Same As Shipping?<br>
	  	<input type="checkbox" name="add" value="saveShippingAdd" checked>Save Shipping Address<br>
	  	<input type="checkbox" name="add" value="saveBillingAdd" checked>Save Billing Address<br>
		</div>
		<br>
		<div class="buttonRow">
			<input type="button" value="Select Ship Address"  onclick="window.location='${pageContext.servletContext.contextPath}/selectshipaddress'">
			<input type="button" value="Select Bill Address" onclick="window.location='${pageContext.servletContext.contextPath}/selectbilladdress'">
			<input type="submit" value="Checkout" onclick="window.location='${pageContext.servletContext.contextPath}/payment'">
			<input type="button" value="Back To Cart" onclick="goBack()">
		</div>
	</div>
	</div>
</form>
	<script>
		function goBack() {
			window.history.back();
		}
	</script>
</body>
</html>