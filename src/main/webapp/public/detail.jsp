<%@page import="model.bean.Picture"%>
<%@page import="model.dao.ProductsDAO"%>
<%@page import="model.bean.Products"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="javax.print.attribute.standard.PrinterMoreInfoManufacturer"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/public/inc/header.jsp"%>
<div style="margin: 200px 0 100px 100px;">
<%
		Products item = null;
		ProductsDAO productsDAO = new ProductsDAO();
		
		if(request.getAttribute("item") != null){
			item = (Products)request.getAttribute("item");
		}
		
		ArrayList<Picture> listPic = productsDAO.getListPicture(item.getId());
		
	%>
	
	<div
		style="float: left; border-right: 1px solid black; padding-right: 15px; margin-left: 220px;">
		<div style="margin: 0px auto; width: 300px">
			<img src="<%=request.getContextPath()%>/file/<%=listPic.get(0).getUrl() %>"
				style="width: 300px; height: 250px" alt="Product">
		</div>
		
			<div style="margin-top: 20px; text-align: center;">
			<%
			if(listPic.size() > 1){
				for(int i =1;  i<listPic.size(); i++){ %>
		
			<div style="margin-right: 5px; display: inline-block; ">
				<img src="<%=request.getContextPath()%>/file/<%=listPic.get(i).getUrl() %>"
					style="width: 100px; height: 100px" alt="">
					
			</div>

			
			<%}} %>
		</div>
		
	</div>
	
	<form action="<%=request.getContextPath() %>/client1/public/cart" method="post">
		<div style="float: right; margin-right: 200px">
		<div style="margin-left: 10px">
			<label>Mã sản phẩm:</label> <input type="text" style="color: #870404; border: none" name="masp" value="<%=item.getId() %>"  readonly="readonly">
			<p style="font-size: 15px">Tên sản phẩm: <%=item.getName() %></p>
			<p style="font-size: 15px">Mô tả: <%=item.getDetail() %></p>
			<p style="font-size: 15px">Giá: <%=item.getPrice() %>VNĐ</p>
			<label>Số lượng</label> <input type="number" value="1" name="soluong">
		</div>
		<div style="margin-top: 120px; width:500px ">
			<div style="float:left;background-color: green;width: 200px;text-align:center; padding: 5px; border-radius:5px">
				<a style="cursor:pointer; text-decoration: none;">
					<p style="color: white; margin-top: 9px; font-weight: bold">
						<input style="background: green; border: none" type="submit" value="Thêm vào giỏ hàng">
					</p>
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
	</form>
	<% %>
	<div style="clear: both"></div>
</div>
<%@include file="/public/inc/footer.jsp"%>