<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order History Detail</title>
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
                View Order History Detail
            </h3>
        </div>
        <div id="no-more-tables">
            <table id="orderdetailtable" width="550px" class="col-md-12 table-bordered table-striped table-condensed cf">
        		<thead class="cf">
        			<tr>
	<th>Product Name</th>
	<th>Quantity</th>
	<th>Unit Price</th>
	<th>Total Price</th>
	
	</tr>
        		</thead>
	
 <tbody>
		<c:forEach var="orderItemPres" items="${orderItemlist}">
			<tr>
			<td>${orderItemPres.getOrderItem().getProductName()}</td>
			<td>${orderItemPres.getOrderItem().getQuantity()}</td>
			<td>${orderItemPres.getOrderItem().getUnitPrice()}</td>
			<td>${orderItemPres.getOrderItem().getTotalPrice()}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<script>
$('#orderdetailtable').DataTable();
</script>
</div>
</div>
</body>
</html>