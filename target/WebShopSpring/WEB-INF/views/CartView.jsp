<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/Header.jsp"%>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Page</title>
</head>

<h1>Cart Page</h1>
<div style="color: white">
	<c:choose>
		<c:when test="${cart != null}">
			<form action="./cart" method="post">
				<table border='1' width="400">

					<c:forEach var="product" items="${cart.entrySet()}">
						<tr align='center'>
							<td width="30"><img
								src="resources/productImage/${product.getKey().id}.jpg" height="25"></td>
							<td width="80"><input type="hidden" name="productToBuy"
								value="${product.getKey().id}" /> ${product.getKey().name}</td>
							<td width="20">${product.getKey().price}</td>
							<td width="20"><input type="hidden" name="productToBuy"
								value="" />
								<div id="numberProduct${product.getKey().id}">
									${product.getValue()}</div></td>
							<c:set var="Summ"
								value="${Summ + (product.getValue() * product.getKey().price)}" />
							<td width="30">${product.getValue() * product.getKey().price}</td>
							<td width="20"><input type="submit" value="-"
								onclick="minus(${product.getKey().id})"></td>
							<td width="20"><input type="submit" value="+"
								onclick="plus(${product.getKey().id})"></td>
							<td width="20"><input type="submit" value="remove"
								onclick="remove(${product.getKey().id})" /></td>
					</c:forEach>
				</table>
				<table border='1' width="400">
					<tr>
						<td align="center"><input type="submit" value="ClearCart"
							onclick="clearCart()" /></td>
						<td width="75" align="center">Final Summ:</td>
						<td width="45" align="center">${Summ}</td>
					</tr>
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<h3 align = "center">Your cart is empty</h3>
		</c:otherwise>
	</c:choose>
</div>

</html>


<%@ include file="/WEB-INF/include/Footer.jsp"%>

<script>
function minus(id) {
	var number = document.getElementById("numberProduct" + id).innerHTML;	
	number--;
	document.getElementById("numberProduct" + id).innerHTML = number;
	$.ajax({
	        url:     "./cart", 
	        type:     "POST", 
	        dataType: "html", 
			data: "recountProduct=" + id + "&qnt=" + number,
	        success: function(response) { 
	 		
	        },
	        error: function(response) { // Данные не отправлены
	            document.getElementById(result_form).innerHTML = "Ошибка. Данные не отправленны.";
	        }
	    });
	}

	function plus(id){
		var number = document.getElementById("numberProduct"+id).innerHTML;
		number ++;
		document.getElementById("numberProduct" + id).innerHTML = number;
		$.ajax({
	        url:     "./cart", 
	        type:     "POST", 
	        dataType: "html", 
			data: "recountProduct=" + id + "&qnt=" + number,
	        success: function(response) { 
	 		
	        },
	        error: function(response) { // Данные не отправлены
	            document.getElementById(result_form).innerHTML = "Ошибка. Данные не отправленны.";
	        }
	    });
		
	}

	function remove(id) {
		var number = document.getElementById("numberProduct"+id).innerHTML;
	$.ajax({
	        url:     "./cart", 
	        type:     "POST", 
	        dataType: "html", 
			data: "productToRemove=" + id + "&qnt=" + number,
	        success: function(response) { 
	 		
	        },
	        error: function(response) { // Данные не отправлены
	            document.getElementById(result_form).innerHTML = "Ошибка. Данные не отправленны.";
	        }
	    });
	}
	
	function clearCart(id) {
		$.ajax({
	        url:     "./cart", 
	        type:     "POST", 
	        dataType: "html", 
			data: "ClearCart=Ok",
	        success: function(response) { 
	 		
	        },
	        error: function(response) { // Данные не отправлены
	            document.getElementById(result_form).innerHTML = "Ошибка. Данные не отправленны.";
	        }
	    });
	}
</script>
