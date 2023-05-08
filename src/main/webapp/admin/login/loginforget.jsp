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
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet"> 
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
	<script src="<%=request.getContextPath() %>/templates/public/js/jquery-3.2.1.js"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/ckeditor/ckeditor.js"></script>
    <!-- Libraries Stylesheet -->
    <link href="<%=request.getContextPath() %>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>

<body>
    <div class="container-fluid position-relative d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Sign Up Start -->
        <div class="container-fluid">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                    <div class="bg-secondary rounded p-4 p-sm-5 my-4 mx-3">
                    
                        <div class="d-flex align-items-center justify-content-between mb-3">
                        	
                            <a href="index.html" class="">
                                <h3 class="text-primary"><i class="fa fa-user-edit me-2"></i></h3>
                            </a>
                            <h3>Sign In</h3>
                        </div>
                        <form id="form" action="" method="post">
	                        <div class="form-floating mb-3">
	                            <input type="email" class="form-control" required="required" name="email" id="floatingInput" placeholder="name@example.com">
	                            <label for="floatingInput">Email address</label>
	                        </div>
	                        <div class="form-floating mb-4">
	                            <input type="text" class="form-control" required="required" name="code" id="floatingPassword" placeholder="Password">
	                            <label for="floatingPassword">Code</label>
	                        </div>
	                        <div class="d-flex align-items-center justify-content-between mb-4">
	                            <div class="form-check">
	                            </div>
	                        </div>
	                        <button type="submit" class="btn btn-primary py-3 w-100 mb-4">Login</button>
	                        <p class="text-center mb-0">
	                        	<a style="margin-right: 20px" href="<%=request.getContextPath()%>/signup">Sign Up</a>
	                        	<a href="<%=request.getContextPath()%>/login">Sign In</a>
	                        </p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Sign Up End -->
    </div>

    <!-- JavaScript Libraries -->
    <script>
    $().ready(function(){
    	//validate dữ liệu khi nhấn submit
    	var vaditor = $("#form").validate({
    		errPlacement: function(error, element){
    			$(element).closest("form").find("label[for='" + element.attr("id") + "']").append(error)
    		},
    		errorElement:"span",
    		messages:{
    			email:{
    				required:"(Email không được để trống)",
    			},
    			code:{
    				required:"(Mã Code không được để trống)",
    			}
    		}
    	});
    });
</script>

    <!-- Template Javascript -->
    <script src="<%=request.getContextPath() %>/js/main.js"></script>
</body>

</html>