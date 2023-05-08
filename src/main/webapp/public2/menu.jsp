<%@page import="model.bean.Products"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="javax.print.attribute.standard.PrinterMoreInfoManufacturer"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/public2/inc/header.jsp"%>
<div class="tm-main-section light-gray-bg">
	<div class="container" id="main">
		<section class="tm-section row">
			<div class="col-lg-9 col-md-9 col-sm-8">
				<h2 class="tm-section-header gold-text tm-handwriting-font">Variety
					of Menus</h2>
				<h2>Cafe House</h2>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-4 tm-welcome-img-container">
				<div class="inline-block shadow-img">
					<img src="<%=request.getContextPath()%>/public/img/1.jpg"
						alt="Image" class="img-circle img-thumbnail">
				</div>
			</div>
		</section>
		<section class="tm-section row">
			<div class="col-lg-12 tm-section-header-container margin-bottom-30">
				<h2 class="tm-section-header gold-text tm-handwriting-font">
					<img src="<%=request.getContextPath()%>/public/img/logo.png"
						alt="Logo" class="tm-site-logo"> Our Menus
				</h2>
				<div class="tm-hr-container">
					<hr class="tm-hr">
				</div>
			</div>
			<div>
				<div class="col-lg-3 col-md-3">
					<div class="tm-position-relative margin-bottom-30"
						style="background-color: white;">

						<nav class="tm-side-menu" style="margin-left: -10px">

							<ul>

								<li><a href="<%=request.getContextPath()%>/menu?cid="
									style="color: #d26e07;">Coffe</a></li>

							</ul>
						</nav>
					</div>
				</div>
				<div class="tm-menu-product-content col-lg-9 col-md-9">
					<!-- menu content -->
					<div class="tm-product">
						<form action="<%=request.getContextPath()%>/menu" method="post">
							<label style="margin-left: 10px; margin-right: 10px">Search:</label>
							<input type="text" name="menuname" placeholder=""
								style="width: 200px"> <input type="submit"
								value="Search">
						</form>
					</div>

					<%
						ArrayList<Products> listProducts = new ArrayList<>();
						if(request.getAttribute("listProducts") != null){
							listProducts = (ArrayList)request.getAttribute("listProducts");
						}
						for(Products item : listProducts){
					%>
					<div class="tm-product">
						<img src="<%=request.getContextPath()%>/public/img/special-1.jpg"
							style="width: 200px; height: 150px" alt="Product">
						<div class="tm-product-text" style="margin: 10px;position: absolute; margin-left: 225px;">
							<h3 class="tm-product-title">
								<a href="<%=request.getContextPath()%>/client2/public/products/detail?id=<%=item.getId() %>" title="">Name: <%=item.getName() %></a>
							</h3>
							<p class="tm-product-description" title="">Detail: <%=item.getDetail() %></p>
							<p style="margin-top: 5px">Price: <%=item.getPrice() %></p>
						</div>
					</div>
					<% }%>
				</div>
		</section>
	</div>
</div>
<script>
	document.getElementById("menu").classList.add('active');
</script>
<%@include file="/public2/inc/footer.jsp"%>