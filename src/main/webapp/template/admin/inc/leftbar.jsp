<%@page import="model.bean.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Sidebar Start -->
<div class="sidebar pe-4 pb-3">
	<nav class="navbar bg-secondary navbar-dark">
		<a href="<%=request.getContextPath()%>/client1/public/index"
			class="navbar-brand mx-4 mb-3">
			<h3 class="text-primary">
				<i class="fa fa-user-edit me-2"></i>Người dùng
			</h3>
		</a>
		<%
		HttpSession sessionHttpSession = request.getSession();
		Person user = (Person) sessionHttpSession.getAttribute("userLogin");
		%>
		<div class="d-flex align-items-center ms-4 mb-4" style="width: 150px">
			<div class="position-relative">
				<img class="rounded-circle"
					src="<%if (user != null)
	out.print(request.getContextPath() + "/file/" + user.getUser_Avartar());%>"
					alt="" style="width: 40px; height: 40px;">
				<div
					class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
			</div>
			<div class="ms-3">
				<h6 class="mb-0">
					<%
					if (user != null) {
						out.print(user.getUser_Name());
					}
					%>
				</h6>
			</div>
		</div>
		<div class="navbar-nav w-100">
			<a href="<%=request.getContextPath()%>/client1/admin/index"
				class="nav-item nav-link " id="trangchu"><i
				class="fa fa-tachometer-alt me-2"></i>Trang chủ</a> 
				<%
				HttpSession httpSession3 = request.getSession();
				Person itemLogin = (Person)httpSession3.getAttribute("userLogin");
				if(itemLogin.getUser_Role() == 0){
				%>
				<a href="<%=request.getContextPath()%>/client1/admin/cat/index" id="danhmuc" class="nav-item nav-link">
					<i class="fa fa-th me-2"></i>Quản lý danh mục</a> 
				<%} %>
				<a href="<%=request.getContextPath()%>/client1/admin/products"
				class="nav-item nav-link" id="category"><i
				class="fa fa-keyboard me-2"></i>Quản lý sản phẩm</a> 
				<a
				href="<%=request.getContextPath()%>/client1/admin/user/index"
				class="nav-item nav-link" id="nguoidung"><i
				class="fa fa-table me-2"></i>Quản lý người dùng</a> 
				<a
				href="<%=request.getContextPath()%>/admin/tintuc/index"
				class="nav-item nav-link" id="tintuc"><i
				class="fa fa-table me-2"></i>Quản lý tin tức</a>
		</div>
	</nav>
</div>