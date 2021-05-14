<%@page import= "com.cart" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/cart.js"></script>
</head>
<body>

<h1>shopping cart Management </h1>
<form id="formcart" name="formcart" method="post" action="cart.jsp">
 product id: 
<input id="prodid" name="prodid" type="text" 
 class="form-control form-control-sm">
<br> product name: 
<input id="prodname" name="prodname" type="text" 
 class="form-control form-control-sm">
<br> product quantity: 
<input id="prodqty" name="prodqty" type="text" 
 class="form-control form-control-sm">
<br> product price: 
<input id="prodprice" name="prodprice" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidprodnumSave" name="hidprodnumSave" value="">
</form>

<%
 out.print(session.getAttribute("statusMsg")); 
%> 
<br>
<%
cart cartObj = new cart(); 
 out.print(cartObj.readcart()); 
%>


</body>
</html>