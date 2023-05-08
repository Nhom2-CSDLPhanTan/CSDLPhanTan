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
<script
	src="<%=request.getContextPath()%>/templates/public/js/jquery-3.2.1.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/lib/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/lib/ckeditor/ckeditor.js"></script>
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


		<!-- Sign Up Start -->
		<div class="container-fluid">
			<div class="row h-100 align-items-center justify-content-center"
				style="min-height: 100vh;">
				<div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
					<div class="bg-secondary rounded p-4 p-sm-5 my-4 mx-3">
						<%
						int msg1 = 0;
						String err = "";
						if (request.getParameter("msg") != null) {
							msg1 = Integer.parseInt(request.getParameter("msg"));
						}
						if (request.getParameter("err") != null) {
							err = request.getParameter("err");
						}
						if (err.equalsIgnoreCase("1")) {
						%>
						<p style="color: red">Email đã tồn tại</p>
						<%
						}
						if (err.equalsIgnoreCase("2")) {
						%>
						<p style="color: red">
							Mật khẩu không hợp lệ <br> ( Nhập sai quá 3 lần tài khoản sẽ
							bị khóa )
						</p>
						<%
						}
						if (err.equalsIgnoreCase("3")) {
						%>
						<p style="color: red">Tài khoản đã bị khóa</p>
						<%
						}
						switch (msg1) {
						case 1 :
						%>
							<p style="color: green">
							Tạo tài khoản thành công! Vui lòng đăng nhập lại!
						</p>
						<%
						}
						%>

						<div
							class="d-flex align-items-center justify-content-between mb-3">

							<a href="index.html" class="">
								<h3 class="text-primary">
									<i class="fa fa-user-edit me-2"></i>DarkPan
								</h3>
							</a>
							<h3>Sign Up</h3>
						</div>
						<form id="form" enctype="multipart/form-data" action="<%=request.getContextPath()%>/signup"
							method="post">
							<div class="form-floating mb-3">
								<input type="text" class="form-control" required="required"
									name="username" id="floatingText" placeholder="jhondoe">
								<label for="floatingText">Username</label>
							</div>
							<div class="form-floating mb-3">
								<input type="email" class="form-control" required="required"
									name="email" id="floatingInput" placeholder="name@example.com">
								<label for="floatingInput">Email address</label>
							</div>
							<div class="form-floating mb-4">
								<input type="password" class="form-control" required="required"
									name="pass" id="floatingPassword" placeholder="Password">
								<label for="floatingPassword">Password</label>
							</div>
							<div class="form-floating mb-4" style="margin-top:65px">
								<label style=" margin-top:-50px">Avartar</label>
								<input style="height:35px; padding: 5px 0px 0px 5px;" type="file" required="required" name="avartar"
									class="form-control" id="inputEmail3">
							</div>
							<div
								class="d-flex align-items-center justify-content-between mb-4">
								<div class="form-check"></div>
								<a href="<%=request.getContextPath()%>/admin/forget/index">Forget
									Password</a>
							</div>
							<button type="submit" class="btn btn-primary py-3 w-100 mb-4">Sign
								Up</button>
							<p class="text-center mb-0">
								<a href="<%=request.getContextPath()%>/loginc1">Sign In</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Sign Up End -->
	</div>
	<script>
		$()
				.ready(
						function() {
							//validate dữ liệu khi nhấn submit
							var vaditor = $("#form")
									.validate(
											{
												errPlacement : function(error,
														element) {
													$(element)
															.closest("form")
															.find(
																	"label[for='"
																			+ element
																					.attr("id")
																			+ "']")
															.append(error)
												},
												errorElement : "span",
												messages : {
													nickname : {
														required : "(Nickname không được để trống)",
													},
													email : {
														required : "(Email không được để trống)",
													},
													pass : {
														required : "(Password không được để trống)",
													}

												}
											});
						});
	</script>
	<!-- JavaScript Libraries -->

	<!-- Template Javascript -->
	<script src="<%=request.getContextPath()%>/js/main.js"></script>
</body>

</html>