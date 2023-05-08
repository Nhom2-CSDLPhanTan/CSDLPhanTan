<%@page import="org.apache.commons.collections4.splitmap.AbstractIterableGetMapDecorator"%>
<%@page import="model.bean.order_inf"%>
<%@page import="model.bean.OrderDetail"%>
<%@page import="model.dao.OrderInfoDAO"%>
<%@page import="model.dao.OrderDetailDAO"%>
<%@page import="model.bean.Products"%>
<%@page import="model.dao.ProductsDAO"%>
<%@page import="model.bean.Cart"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/public/inc/header.jsp"%>
<div class="editor"
	style="margin: 95px auto; width: 815px; margin-bottom: 80px;">
	<!-- menu content -->
	<form action="<%=request.getContextPath()%>/client1/public/dathang"
		method="post"
		style="margin: 0px auto; margin-bottom: 20px; text-align: center;">
		<h1>Giỏ hàng</h1>
		<table
			style="border: 1px solid #ccc; text-align: center; width: 900px;">
			<tr>
				<th style="text-align: center;">Stt</th>
				<th style="text-align: center;">Tên sản phẩm</th>
				<th style="text-align: center;">Số lượng</th>
				<th style="text-align: center;">Chọn</th>
			</tr>
		<%
			ProductsDAO productsDAO = new ProductsDAO();
			ArrayList<Cart> listCart = new ArrayList<>();
			if(request.getAttribute("listCart")!=null){
				listCart = (ArrayList<Cart>) request.getAttribute("listCart");
			}
			
			if(listCart != null && listCart.size() > 0){
				int i = 0;
				for(Cart item : listCart){
					i++;
					Products product = productsDAO.getProductsById(item.getIdPro());
		%>
			<tr>
				<td><%=i %></td>
				<td><%=product.getName() %></td>
				<td><input name="sl" type="text"
					style="border: none; text-align: center" value="<%=item.getNumber() %>"
					readonly="readonly"></td>
				<td><input name="idsp" type="checkbox" value="<%=item.getId()%>"></td>
			</tr>
			<%}} %>
		</table>
		<input
			style="margin-top: 10px; color: white; background: red; width: 50px"
			value="Mua" type="submit">
	</form>

	<h3 style="margin-top: 60px; font-weight: bold;">Đang chờ xử lý</h3>
	<%
		OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
		OrderInfoDAO orderInfoDAO = new OrderInfoDAO();
		ProductsDAO productsDAO2 = new ProductsDAO();
		HttpSession session3 = request.getSession();
		Person userLogin3 = (Person)session3.getAttribute("userLogin");
		ArrayList<OrderDetail> listOderDetail = orderDetailDAO.getListOrderDetail();
		ArrayList<order_inf> listOderinfo = new ArrayList<>();
		
	%>
	<table border="1px solid black" style="text-align: center;">
		<tr>
			<th width="200px" style="text-align: center; font-size: 16px">Tên
				sản phẩm</th>
			<th width="200px" style="text-align: center; font-size: 16px">Tên
				khách hàng</th>
			<th width="100px" style="text-align: center; font-size: 16px">Số
				lượng</th>
			<th width="200px" style="text-align: center; font-size: 16px">Tổng
				tiền</th>
			<th width="150px" style="text-align: center; font-size: 16px">Tình
				trạng</th>
		</tr>
		<%for (OrderDetail iemDetail : listOderDetail){
			order_inf itemInf = orderInfoDAO.getItemById(iemDetail.getIdInfo());
			if(itemInf != null){
			if((itemInf.getId_person() == userLogin.getUser_ID()) || (userLogin.getUser_Role()==1)){
				Products itemPro = productsDAO2.getProductsById(iemDetail.getIdPro());
			%>
		<tr>
			<td><%=itemPro.getName() %></td>
			<td><%=userLogin3.getUser_Name() %></td>
			<td><%=iemDetail.getNumber() %></td>
			<td><%=iemDetail.getPrice()*iemDetail.getNumber() %>VNĐ</td>
			<td style="background-color: #f0e7da;">
				
					<%
						if(userLogin.getUser_Role() == 1 && itemInf.getStatus() == 0){
					%>
					<a href="<%=request.getContextPath() %>/client1/public/update-status?choose=1&id=<%=itemInf.getId() %>" style="text-decoration: none">
						<p style="color: blue; font-style: italic;">Xác nhận đơn hàng</p>
					</a>
					<%} %>
					<%
						if(userLogin.getUser_Role() == 4 && itemInf.getStatus() == 0){
					%>
					<a href="#" style="text-decoration: none">
						<p style="color: red;cursor: context-menu;">Đang chờ xác nhận</p>
					</a>
					<%} %>
					<%
						if(userLogin.getUser_Role() == 1 && itemInf.getStatus() == 1){
					%>
					<a href="<%=request.getContextPath() %>/client1/public/update-status?choose=2&id=<%=itemInf.getId() %>" style="text-decoration: none">
						<p style="color: #b26704; font-style: italic;">Giao hàng</p>
					</a>
					<%} %>
					<%
						if(userLogin.getUser_Role() == 4 && itemInf.getStatus() == 1){
					%>
					<a href="#" style="text-decoration: none">
						<p style="color: #b26704;cursor: context-menu;">Đang thực hiện</p>
					</a>
					<%} %>
					<%
						if(userLogin.getUser_Role() == 1 && itemInf.getStatus() == 2){
					%>
					<a href="#" style="text-decoration: none">
						<p style="color: green; font-weight: bold;">Đã nhận hàng</p>
					</a>
					<%} %>
					<%
						if(userLogin.getUser_Role() == 4 && itemInf.getStatus() == 2){
					%>
					<a href="#" style="text-decoration: none">
						<p style="color: green; font-weight: bold;">Đã nhận hàng</p>
					</a>
					<%} %>
				
			</td>
		</tr>
		<%}}} %>
	</table>

</div>


<%@include file="/public/inc/footer.jsp"%>