
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="utils.DBConnectUtil"%>
<%@page import="java.util.List"%>
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
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet"> 
    <script src="<%=request.getContextPath() %>/templates/public/js/jquery-3.2.1.js"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/ckeditor/ckeditor.js"></script>
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

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


        <%@include file="/template/admin/inc/leftbar.jsp" %>
        <!-- Sidebar End -->


        <!-- Content Start -->
        <div class="content">
            <!-- Navbar Start -->
            <%@include file="/template/admin/inc/header.jsp" %>
            <!-- Navbar End -->


            <!-- Sale & Revenue Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="col-sm-12 col-xl-6">
                    <div class="bg-secondary rounded h-100 p-4" style="margin-left: 150px; width: 850px;">
                                        	<%
                    		int msg = 0;
                    		if(request.getParameter("msg") != null){
                    			msg = Integer.parseInt(request.getParameter("msg"));
                    		}
                    		int err = 0;
                    		if(request.getParameter("err") != null){
                    			err = Integer.parseInt(request.getParameter("err"));
                    		}
                    		switch(msg){
                    			case 1:
                    				%>
                    				<p style="color: green;font-style: italic">Thêm sản phẩm thành công</p>
                    				<%
                    				break;
                    			case 2:
                    				%>
                    				<p style="color: green;font-style: italic">Sửa sản phẩm thành công</p>
                    				<%
                    				break;
                    			case 3:
                    				%>
                    				<p style="color: green;font-style: italic">Xóa sản phẩm thành công</p>
                    				<%
                    				break;
                    		}
                    		
                    		switch(err){
	                			case 1:
	                				%>
	                				<p style="color: red;font-style: italic">Mã sản phẩm đã tồn tại</p>
	                				<%
	                				break;
	                			case 2:
	                				%>
	                				<p style="color: red;font-style: italic">Sửa sản phẩm thành công</p>
	                				<%
	                				break;
	                			case 3:
	                				%>
	                				<p style="color: red;font-style: italic">Xóa sản phẩm thành công</p>
	                				<%
	                				break;
                		}
                    	%>
                    
                        <h6 class="mb-4" style="font-size: 40px;">Thêm sản phẩm</h6>
                        <form id="form" action="" method="post">
                            <div class="row mb-3">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Mã sản phẩm</label>
                                <div class="col-sm-10">
                                    <input type="text" required="required" name="masp" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            
                            
                            <div class="row mb-3">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Tên sản phẩm</label>
                                <div class="col-sm-10">
                                    <input type="text" required="required" name="tensp" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Chi tiết</label>
                                <div class="col-sm-10">
                                    <input type="text" required="required" name="chitiet" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Giá bán</label>
                                <div class="col-sm-10">
                                    <input type="text" required="required" name="giaban" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Danh mục</label>
                                <div class="col-sm-10">
                                    <select name="dm">
                                   		<%
                                   			CategoryDAO categoryDAO = new CategoryDAO();
                                   			ArrayList<Category> listCat = new ArrayList<>();
                                			listCat = categoryDAO.getListCat();
                                			if(listCat!=null && listCat.size()>0){
                                   			for(Category item : listCat){
                                   		%>
                                   			<option value="<%=item.getCat_Id()%>"><%=item.getCat_Name() %></option>
                                   		<%} }%>
                                    </select>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Thêm</button>
                        </form>
                    </div>
                </div>
            </div>
            
    </div>
<script>
    $().ready(function(){
    	//validate dữ liệu khi nhấn submit
    	var vaditor = $("#form").validate({
    		errPlacement: function(error, element){
    			$(element).closest("form").find("label[for='" + element.attr("id") + "']").append(error)
    		},
    		errorElement:"span",
    		messages:{
    			tensp:{
    				required:"(tên sản phẩm không được để trống)",
    			},
    			mota:{
    				required:"(Mô tả không được để trống)",
    			},
    			giagoc:{
    				required:"(Giá gốc không được để trống)",
    			}
    		}
    	});
    });
</script>

	
    <!-- JavaScript Libraries -->
    

    <!-- Template Javascript -->
    <script src="<%=request.getContextPath() %>/js/main.js"></script>
</body>

</html>