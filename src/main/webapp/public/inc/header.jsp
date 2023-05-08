<%@page import="model.bean.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cafe House</title>
<!-- 
Cafe House Template
http://www.templatemo.com/tm-466-cafe-house
-->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,600,700'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Damion'
	rel='stylesheet' type='text/css'>
<link href="<%=request.getContextPath()%>/public/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/public/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/public/css/templatemo-style.css"
	rel="stylesheet">

<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/public/img/favicon.ico"
	type="image/x-icon" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/public/js/jquery-1.11.2.min.js"></script>
<!-- jQuery -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/public/js/templatemo-script.js"></script>
<!-- Templatemo Script -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/public/lib/jquery.validate.min.js"></script>

</head>
<body>
	<!-- Preloader -->
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<!-- End Preloader -->
	<div class="tm-top-header">
		<div class="container">
			<div class="row">
				<div class="tm-top-header-inner">
					<div class="tm-logo-container">
						<img src="<%=request.getContextPath()%>/public/img/logo.png"
							alt="Logo" class="tm-site-logo">
						<h1 class="tm-site-name tm-handwriting-font">Cafe House</h1>
					</div>
					<div class="mobile-menu-icon">
						<i class="fa fa-bars"></i>
					</div>
					<nav class="tm-nav">
						<ul>
							<li><a href="<%=request.getContextPath()%>/client1/public/index"
								id="home">Home</a></li>

							<li><a href="<%=request.getContextPath()%>/client1/public/products"
								id="menu">Menu</a></li>
							<li><a href="<%=request.getContextPath()%>/client1/public/contact"
								id="contact">Contact</a></li>
							<li><a href="<%=request.getContextPath()%>/client1/public/cart"
								id="info">Cart</a></li>

							<%
							HttpSession session2 = request.getSession();
							Person userLogin = (Person) session2.getAttribute("userLogin");
							if (userLogin != null) {
							%>
							<li><a href="<%=request.getContextPath()%>/client1/admin/index" id="login"><%=userLogin.getUser_Name()%></a></li>
							<li><a href="<%=request.getContextPath()%>/logoutc1" id="logout">Logout</a></li>
							<%
							} else {
							%>
							<li><a href="<%=request.getContextPath()%>/loginc1" id="login">Login</a></li>
							<%
							}
							%>

						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<section class="tm-welcome-section">
		<div class="container tm-position-relative">
			<div class="tm-lights-container">
				<img src="<%=request.getContextPath()%>/public/img/light.png"
					alt="Light" class="light light-1"> <img
					src="<%=request.getContextPath()%>/public/img/light.png"
					alt="Light" class="light light-2"> <img
					src="<%=request.getContextPath()%>/public/img/light.png"
					alt="Light" class="light light-3">
			</div>
			<div class="row tm-welcome-content">
				<h2 class="white-text tm-handwriting-font tm-welcome-header">
					<img src="<%=request.getContextPath()%>/public/img/header-line.png"
						alt="Line" class="tm-header-line">&nbsp;Welcome
					To&nbsp;&nbsp;<img
						src="<%=request.getContextPath()%>/public/img/header-line.png"
						alt="Line" class="tm-header-line">
				</h2>
				<h2 class="gold-text tm-welcome-header-2">Cafe House</h2>
			</div>
			<img src="<%=request.getContextPath()%>/public/img/table-set.png"
				alt="Table Set" class="tm-table-set img-responsive">
		</div>
	</section>