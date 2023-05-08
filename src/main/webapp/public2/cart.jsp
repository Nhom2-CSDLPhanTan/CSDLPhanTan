<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/public2/inc/header.jsp"%>
<div class="editor"
	style="margin: 95px auto; width: 815px; margin-bottom: 80px;">
	<!-- menu content -->
	<form action="<%=request.getContextPath()%>/public2/datnhieuhang"
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

			<tr>
				<td>1</td>
				<td>Coffe sữa</td>
				<td><input name="sl" type="text"
					style="border: none; text-align: center" value="2"
					readonly="readonly"></td>
				<td><input name="idsp" type="checkbox" value="1"></td>
			</tr>
		</table>
		<input
			style="margin-top: 10px; color: white; background: red; width: 50px"
			value="Mua" type="submit">
	</form>

	<h3 style="margin-top: 60px; font-weight: bold;">Đang chờ xử lý</h3>
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
		<tr>
			<td>Coffe đen</td>
			<td>Phú</td>
			<td>2</td>
			<td>40000</td>
			<td style="background-color: #f0e7da;"><p
					style="color: red; font-style: italic; font-weight: bold;">Đang
					chờ xác nhận</p></td>
		</tr>
		<tr>
			<td>Coffe phin</td>
			<td>Võ Văn A</td>
			<td>1</td>
			<td>25000</td>
			<td style="background-color: #f0e7da;"><p
					style="color: blue; font-style: italic;">Chờ nhận hàng</p></td>
			
		</tr>

	</table>

</div>


<%@include file="/public2/inc/footer.jsp"%>