<%@page import="model.bean.Category"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>DarkPan - Bootstrap 5 Admin Template</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link
	href="<%=request.getContextPath()%>/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css"
	rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Template Stylesheet -->
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet">
</head>

<body>
	<div class="container-fluid position-relative d-flex p-0">
		<!-- Spinner Start -->
		<div id="spinner"
			class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
			<div class="spinner-border text-primary"
				style="width: 3rem; height: 3rem;" role="status">
				<span class="sr-only">Loading...</span>
			</div>
		</div>
		<!-- Spinner End -->


		<%@include file="/template/admin/inc/leftbar.jsp"%>
		<!-- Sidebar End -->


		<!-- Content Start -->
		<div class="content">
			<!-- Navbar Start -->
			<%@include file="/template/admin/inc/header.jsp"%>
			<!-- Navbar End -->


			<!-- Sale & Revenue Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="col-sm-12 col-xl-6">
					<div class="bg-secondary rounded h-100 p-4"
						style="margin-left: 150px; width: 800px;">
						<%
						int msg = 0;
						if((request.getParameter("msg")) != null){
							msg = Integer.parseInt(request.getParameter("msg"));
						}
						switch(msg){
						case 1:
							%>
							<p style="color:green">Sửa thành công</p>
							<%
							break;
						case 2:
							%>
							<p style="color:green">Thêm thành công</p>
							<%
							break;
						case 3:
							%>
							<p style="color:green">Xóa thành công</p>
							<%
							break;
						}
						%>
						<h6 class="mb-4" style="font-size: 50px;">Quản lý danh mục</h6>
						<div style="margin-bottom: 40px;">

							<button type="button" class="btn btn-success m-2"
								style="float: left;">
								<a href="<%=request.getContextPath()%>/admin/cat/add" style="color: white;">Thêm danh mục</a>
								
							</button>

							<form action="" method="post" class="d-none d-md-flex ms-4">
								<input
									style="margin-top: 4px; width: 340px; margin-left: 100px;"
									class="form-control bg-dark border-0" type="search"
									name="tendm" placeholder="Search">
								<button type="submit" class="btn btn-outline-primary m-2"
									style="margin-top: 3px;">Tìm kiếm</button>
							</form>
						</div>

						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Tên danh mục</th>
									<th scope="col">Mô tả</th>
									<th style="width: 200px" scope="col">Chức năng</th>
								</tr>
							</thead>
							<tbody>
								<%
								ArrayList<Category> listCat = new ArrayList<>();
								if(request.getAttribute("listCat") != null){
									listCat = (ArrayList<Category>) request.getAttribute("listCat");
								}
								for(Category item : listCat){
								%>
								<tr>
									<th scope="row"></th>
									<td><%=item.getCat_Name() %></td>
									<td><%=item.getCat_Detail() %></td>
									<td>
									<a
										href="<%=request.getContextPath()%>/admin/cat/edit?id=<%=item.getCat_Id()%>"><button
												type="button" class="btn btn-info m-2">Sửa</button></a> 
												<a
										href="<%=request.getContextPath()%>/admin/cat/del?id=<%=item.getCat_Id() %>"
										onclick="return confirm('Bạn có muốn xóa không!')"><button
												type="button" class="btn btn-primary m-2">Xóa</button></a>
									</td>
								</tr>
								<%}%>
							</tbody>
						</table>


					</div>
				</div>
			</div>

		</div>

		<!-- JavaScript Libraries -->
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
		<script src="<%=request.getContextPath()%>/lib/chart/chart.min.js"></script>
		<script src="<%=request.getContextPath()%>/lib/easing/easing.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/lib/waypoints/waypoints.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/lib/owlcarousel/owl.carousel.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/lib/tempusdominus/js/moment.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/lib/tempusdominus/js/moment-timezone.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

		<!-- Template Javascript -->
		<script src="<%=request.getContextPath()%>/js/main.js"></script>
		<script>
			document.getElementById("danhmuc").classList.add('active');
		</script>
</body>

</html>