<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBazzar login</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="//select2.github.io/select2/select2-3.4.1/select2.js"></script>
    <link rel="stylesheet" type="text/css" href="//select2.github.io/select2/select2-3.4.1/select2.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
</head>
<body>
<form method="post"	action="${pageContext.servletContext.contextPath}/login/callback/${callback}">
	<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 class="text-left">
                ${message}
            </h3>
        </div>
        <div id="no-more-tables">
            <table class="col-md-12 table-bordered table-striped table-condensed cf">
				<tr>
					<td>User Name :</td>
					<td><input type="text" name="custId"/></td>
				</tr>
				<tr>
					<td><b>Password:</b></td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="Sign In"/>
					</td>
				</tr>
			</table>
		</div>
	</div></div>
</form>
</body>
</html>