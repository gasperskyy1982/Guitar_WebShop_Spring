<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Photoshoot 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20110926

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Guitar shop by Alex</title>
<link href="resources/styles/style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="resources/javascripts/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="resources/javascripts/jquery.poptrox-0.1.js"></script>
</head>
<body>
<div id="header" class="container">
	<div id="logo">
		<h1><a href="#"> My Guitar </a></h1>
		<p>интернет-магазин гитар <a href="http://www.freecsstemplates.org"></a></p>
	</div>
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="./home">Главная</a></li>
			<li><a href="./products">Товары</a></li>
			<c:choose>
			<c:when test = "${user.name == null}">
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
<!-- end #header -->
<div id="poptrox">
	<!-- start -->
	<ul id="gallery" align = 'center'>
		<li><a href="resources/images/guitars_2.jpg"><img src="resources/images/guitars_2.jpg" title="Acustic Guitars" alt="" /></a></li>
		<li><a href="resources/images/guitars_3.jpg"><img src="resources/images/guitars_3.jpg" title="Electric Guitars" alt="" /></a></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<br class="clear" />
	<script type="text/javascript">
		$('#gallery').poptrox();
		</script>
	<!-- end -->
</div>
<div id="page">
	<div id="bg1">
		<div id="bg2">
			<div id="bg3">
				<div id="content">