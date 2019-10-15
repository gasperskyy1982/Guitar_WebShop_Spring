<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/Header.jsp"%>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<h1>Product page</h1>
<div style="color: white">
	<c:forEach var="product" items="${requestScope.productList}">
		<table>
			<tr>
				<td width="200">${product.name}</td>
				<td width="400"></td>
			</tr>
			<tr>
				<td><img src="resources/productImage/${product.id}.jpg"
					height="200" weight="200"></td>
				<td>${product.description}</td>
			</tr>
			<tr>
				<td>price: ${product.price} grn.</td>
				<td>
					<table>
						<tr>

							<td><img src="resources/images/-.png" height="20" id="-"
								onclick="minus(${product.id})"></td>
							<td><div id="numberProduct${product.id}">0</div></td>
							<td><img src="resources/images/+.png" height="20" id="+"
								onclick="plus(${product.id})"></td>
							<td><input type="submit" value="buy"
								onclick="submit(${product.id})" /></td>
					</table>
				</td>
			</tr>
		</table>
		<br>
		<br>
	</c:forEach>
</div>

<script>
function minus(id) {
	
var number = document.getElementById("numberProduct" + id).innerHTML;	
	if (number >= 2) {
	number--;
	document.getElementById("numberProduct" + id).innerHTML = number;	
	}
}

function plus(id){
	var number = document.getElementById("numberProduct"+id).innerHTML;
	number ++;
	document.getElementById("numberProduct" + id).innerHTML = number;
}

function submit(id) {
	var number = document.getElementById("numberProduct"+id).innerHTML;
	if (number>=1) {
$.ajax({
        url:     "./cart", 
        type:     "POST", 
        dataType: "html", 
		data: "productToBuy=" + id + "&qnt=" + number,
        success: function(response) { 
        },
        error: function(response) { // Данные не отправлены
            document.getElementById(result_form).innerHTML = "Ошибка. Данные не отправленны.";
        }
    });
	}
}
</script>

<%@ include file="/WEB-INF/include/Footer.jsp"%>