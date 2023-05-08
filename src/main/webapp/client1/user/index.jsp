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
						style="margin-left: 150px; width: 1000px;">
						<h6 class="mb-4" style="font-size: 50px;">Quản lý tài khoản</h6>
						<div style="margin-bottom: 40px;">
							<form class="d-none d-md-flex ms-4">
								<input
									style="margin-top: 4px; width: 340px; margin-left: 100px;"
									class="form-control bg-dark border-0" type="search"
									placeholder="Search">
								<button type="button" class="btn btn-outline-primary m-2"
									style="margin-top: 3px;">Tìm kiếm</button>
							</form>
						</div>
						<%
						int msg = 0;
						if (request.getParameter("msg") != null) {
							msg = Integer.parseInt(request.getParameter("msg"));
						}else{
							if(request.getAttribute("msg") != null){
								msg = Integer.parseInt((String)request.getAttribute("msg"));
							}
						}
						
						switch (msg) {
							case 1 :
							%>
							<p style="color: green">
								Cập nhật thành công
							</p>
							<%
								break;
							case 2:
								%>
								<p style="color: green">
									Sửa thành công
								</p>
								<%
								break;
							case 3:
								%>
								<p style="color: green">
									Xóa thành công
								</p>
								<%
								break;
							default:
								%>
								
								<%
						}
						%>
						<%
						Person personLogin = (Person) httpSession.getAttribute("userLogin");
						ArrayList<Person> listUser = (ArrayList<Person>) request.getAttribute("listUser");
						if (personLogin.getUser_Role() == 0) {
						%>
						<a href="<%=request.getContextPath()%>/client1/admin/user/update">
							<button type="button" class="btn btn-info m-2"
								style="background-color: green; border-color: green; color: white; font-weight: bold">Cập
								nhật nhân viên</button>
						</a>
						<%
						}
						%>

						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Nickname</th>
									<th scope="col">Email</th>
									<th scope="col">Password</th>
									<th style="width: 200px" scope="col">Chức năng</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (Person item : listUser) {
								%>
								<tr>
									<th scope="row"></th>
									<td><%=item.getUser_Name()%></td>
									<td><%=item.getUser_Email()%></td>
									<%
									if (personLogin.getUser_ID() == item.getUser_ID()) {
									%>
									<td><%=item.getUser_Password()%></td>
									<%
									} else {
									%>
									<td></td>
									<%
									}
									%>
									<td>
										<%
										if (item.getUser_ID() == personLogin.getUser_ID()) {
										%> <a
										href="<%=request.getContextPath()%>/client1/admin/user/edit?id=<%=item.getUser_ID()%>"><button
												type="button" class="btn btn-info m-2">Sửa</button></a> <%
 }
 if (personLogin.getUser_Role() == 1 || personLogin.getUser_Role() == 2
 		|| personLogin.getUser_Role() == 3) {
 %> <a href="<%=request.getContextPath()%>/client1/admin/user/del?iduser=<%=item.getUser_ID()%>"
										onclick="return confirm('Bạn có muốn xóa không!')"><button
												type="button" class="btn btn-primary m-2">Xóa</button></a> <%
 }
 %>
									</td>
								</tr>
								<%
								}
								%>
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
			document.getElementById("nguoidung").classList.add('active');
		</script>
</body>

</html>