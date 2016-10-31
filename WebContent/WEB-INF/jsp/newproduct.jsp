<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBazzar Product</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="//select2.github.io/select2/select2-3.4.1/select2.js"></script>
    <link rel="stylesheet" type="text/css" href="//select2.github.io/select2/select2-3.4.1/select2.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
</head>
<body>
<form action = "${pageContext.servletContext.contextPath}/saveproduct" method = 'post'>	
  
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
					<td><b>Cat Id: <b></b></td>
					<td><input type="text" value = "${catId}" name="catId"></td>
				</tr>
				<tr>
					<td><b>Catalog:</b></td>
					<td><input type="text" value = "${catName}" name="catName"></td>
				</tr>
				<tr>
					<td><b>Product Name:</b></td>
					<td><input type="text" name="pName"></td>
				</tr>
				<tr>
					<td><b> Manufacturer Date</b></td>
					<td><input type="text" name="mDate"></td>
				</tr>
				<tr>
					<td><b>  Number in stock :</b></td>
					<td><input type="text" name="num"></td>
				</tr>
				<tr>
					<td><b>Unit Price :</b></td>
					<td><input type="text" name="price"></td>
				</tr>
				<tr>
					<td><b>Unit Price :</b></td>
					<td><input type="text" name="price"></td>
				</tr>
				<tr>
					<td><b> Description</b></td>
					<td><input type="text" name="des"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						 <input type="submit" value="Save" name = "Save">
  
					</td>
					<td>
					<input type="submit" value="Clear" name = "Clear">
  
					</td>
				</tr>
			</table>
		</div>
	</div></div>
  
  
  
  
  
  
</form>
</body>
</html>