<%@page import="model.bean.Products"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="javax.print.attribute.standard.PrinterMoreInfoManufacturer"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/public2/inc/header.jsp"%>
<div style="margin: 200px 0 100px 100px;">
	<div
		style="float: left; border-right: 1px solid black; padding-right: 15px; margin-left: 220px;">
		<div style="margin: 0px auto; width: 300px">
			<img src="<%=request.getContextPath()%>/public2/img/special-1.jpg"
				style="width: 300px; height: 250px" alt="Product">
		</div>
		<div style="margin-top: 20px">
			<div style="float: left; margin-right: 5px">
				<img src="<%=request.getContextPath()%>/public2/img/special-1.jpg"
					style="width: 100px; height: 100px" alt="Product">
			</div>
			<div style="float: left; margin-right: 5px">
				<img src="<%=request.getContextPath()%>/public2/img/special-1.jpg"
					style="width: 100px; height: 100px" alt="Product">
			</div>

			<div style="float: right">
				<img src="<%=request.getContextPath()%>/public2/img/special-1.jpg"
					style="width: 100px; height: 100px" alt="Product">
			</div>
			<div style="clear: both"></div>
		</div>
	</div>
	<%
		Products item = null;
		if(request.getAttribute("item") != null){
			item = (Products)request.getAttribute("item");
		}
		
	%>
	<div style="float: right; margin-right: 200px">
		<div style="margin-left: 10px">
			<p style="font-size: 15px">Tên sản phẩm: <%=item.getName() %></p>
			<p style="font-size: 15px">Mô tả: <%=item.getDetail() %></p>
			<p style="font-size: 15px">Giá: <%=item.getPrice() %>VNĐ</p>
			<label>Số lượng</label> <input type="number" name="soluong">
		</div>
		<div style="margin-top: 120px; width:500px ">
			<div style="float:left;background-color: green;width: 200px;text-align:center; padding: 5px; border-radius:5px">
				<a style="cursor:pointer; text-decoration: none;">
					<p style="color: white; margin-top: 9px; font-weight: bold">Thêm vào giỏ hàng</p>
				</a>
			</div>
			<div style="float:right;background-color: #19b3de;width: 200px;text-align:center; padding: 5px; border-radius:5px">
				<a style="cursor:pointer; text-decoration: none;">
					<p style="color: white; margin-top: 9px; font-weight: bold">Mua ngay</p>
				</a>
			</div>
			<div style="clear:both"></div>
		</div>
	</div>
	<% %>
	<div style="clear: both"></div>
</div>
<%@include file="/public2/inc/footer.jsp"%>