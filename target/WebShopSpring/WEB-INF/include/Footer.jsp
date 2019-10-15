<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

</div>

<div id="sidebar">
	<table border=1>
		<tr>
			<td width="252" align="left"><font color=white> Вы
					авторизировались как ${user.name} <br /> В вашей корзине
					${numberGeneralProducts} товаров.
			</font></td>
		</tr>
	</table>
	<h2>Боковое меню</h2>
	<ul>
		<li><a href="./products?category=1">Категория 1</a></li>
		<li><a href="./products?category=2">Категория 2</a></li>
		<li><a href="./products?category=3">Категория 3</a></li>
		<c:choose>
			<c:when test="${user.name == null}">
				<li><a href="./register">Регистрация</a></li>
				<li><a href="./auth">Вход</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="./correct">Редактирование</a></li>
				<li><a href="./auth?logOut=ok">Выход</a></li>
			</c:otherwise>
		</c:choose>
		<li><a href="./cart">Корзина</a></li>
	</ul>
</div>
</div>
</div>
</div>
</div>
<div id="footer">
	<p>Copyright (c) by</p>
</div>
<!-- end #footer -->
</body>
</html>
