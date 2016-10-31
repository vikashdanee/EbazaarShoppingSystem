<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBazaar - Catalog</title>
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
                Catalog List
            </h3>
        </div>
        <div id="no-more-tables">
            <table width="300px" class="col-md-12 table-bordered table-striped table-condensed cf">
        		<thead class="cf">
        			<tr>
        			<th>Catalog Name</th>
        			<th>Action</th>
        			</tr>
        		</thead>
				<c:forEach var="catalogPres" items="${catalogs}">
					<tr>
						<td>${catalogPres.name }</td>
						<td align="center"><a href="${pageContext.servletContext.contextPath}/viewProductList/catalogId/${catalogPres.id }/catalogName/${catalogPres.name }">Select</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		<br>
		<a href="${pageContext.servletContext.contextPath}/login/callback/login">Login</a>
		<a href="${pageContext.servletContext.contextPath}/orderhistory">View Order History</a>
	</div>
	</div>
	<br>

</body>
</html>