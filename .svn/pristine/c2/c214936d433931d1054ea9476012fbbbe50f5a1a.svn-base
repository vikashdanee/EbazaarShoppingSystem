<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="//select2.github.io/select2/select2-3.4.1/select2.js"></script>
    <link rel="stylesheet" type="text/css" href="//select2.github.io/select2/select2-3.4.1/select2.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/terms" method="post">
	<div class="container">
	    <div class="row">
	        <div class="col-md-12">
	            <h3 class="text-left">
					Credit Card Information
				</h3>
	        </div>
	        <div id="no-more-tables">
				<table width="400px" class="col-md-12 table-bordered table-striped table-condensed cf">
					<tr>
						<td>Name</td>
						<td>${cname }</td>
					</tr>
					<tr>
						<td>Card Number</td>
						<td>${cnumber }</td>
					</tr>
					<tr>
						<td>Card Type</td>
						<td>
							<select>
							  <option value="volvo">Master Card</option>
							  <option value="saab">${ctype}</option>
							  <option value="mercedes">Discover</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Expiration Date</td>
						<td>${cdate}</td>
					</tr>
				</table>
				<br>
				<input type="submit" value="Checkout"  >
			</div>
		</div>
	</div>
</form>
	<div class="container">
	    <div class="row">
	        <div id="no-more-tables">
			<div class="buttonRow">		
				<input type="submit" value="Back to Shipping Cart" onclick="window.location='${pageContext.servletContext.contextPath}/addToCart'">
				<input type="submit" value="Previous Page" onclick="goBack()">
			</div>
		</div>
		</div>
	</div>
	<script>
		function goBack() {
			window.history.back();
		}
	</script>
</body>
</html>