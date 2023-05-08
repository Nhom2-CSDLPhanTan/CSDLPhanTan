<%@page import="model.dao.ProductsDAO"%>
<%@page import="model.bean.Cart"%>
<%@page import="model.bean.Products"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/public/inc/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="editor" style="margin-top: 100px; margin-left: 500px;">
	<!-- menu content -->
	<h3 style="font-weight: bold;">Order</h3>

	<%
		ProductsDAO productsDAO = new ProductsDAO();
		ArrayList<Cart> listPro  = new ArrayList<>();
		if(request.getAttribute("listspMua") != null){
			listPro = (ArrayList<Cart>)request.getAttribute("listspMua");
		}
	%>
	<form action="<%=request.getContextPath()%>/client1/public/order" id="form" method="post"
		style="width: 350px">
		
		<%
			if(listPro != null && listPro.size() > 0){
				for(Cart item : listPro){
					
					Products itemPro = productsDAO.getProductsById(item.getIdPro());
		%>
		<div style="border-bottom: 1px dotted black; margin-bottom: 10px; padding 5px ">
			<div style="height: 25px; margin-bottom: 10px">
				<label style="float: left;">Mã sản phẩm:</label> <input type="text"
					name="masp" value="<%=itemPro.getId() %>" class="username"
					style="width: 200px; float: right" readonly>
			</div>
			<div style="height: 25px; margin-bottom: 10px">
				<label style="float: left;">Tên sản phẩm:</label> <input type="text"
					name="name" value="<%=itemPro.getName() %>" class="username"
					style="width: 200px; float: right" readonly>
			</div>
			<div style="height: 25px; margin-bottom: 10px">
				<label style="float: left;">Giá</label> <input type="text"
					value="<%=itemPro.getPrice() %>" name="chiphi" class="username"
					style="width: 200px; float: right" dreadonlyisabled="disabled" readonly="readonly">
			</div>
			<div style="height: 25px; margin-bottom: 10px;">
				<label style="float: left;">Số lượng</label> <input type="number"
					name="soluong" required="required" value="<%=item.getNumber() %>" class="username" id="soluong"
					style="width: 200px; float: right">
			</div>
		</div>
			<%}} %>
		
		
		<div>
				<label>Tên khách hàng:</label>
				<input type="text" name="tenkh" value="Trần Phú" size="40" style="margin-bottom: 5px" class="wpcf7-form-control wpcf7-text wpcf7-email wpcf7-validates-as-required wpcf7-validates-as-email" aria-required="true" aria-invalid="false">
				<label>Địa chỉ:</label>
				<input type="text" required="required" name="dc" id="dc" value="" size="40" style="margin-bottom: 5px" class="wpcf7-form-control wpcf7-text wpcf7-email wpcf7-validates-as-required wpcf7-validates-as-email" aria-required="true" aria-invalid="false" placeholder="Địa chỉ">
				<br>
				<label>Số điện thoại:</label>
				<input type="text" name="sdt" value="" size="40" style="margin-bottom: 5px" class="wpcf7-form-control wpcf7-text wpcf7-email wpcf7-validates-as-required wpcf7-validates-as-email" aria-required="true" aria-invalid="false" placeholder="Số điện thoại">
				<label>Email:</label>
				<input readonly type="text" name="email" value="tranphunguyenle30@gmail.com" size="40" style="margin-bottom: 5px" class="wpcf7-form-control wpcf7-text wpcf7-email wpcf7-validates-as-required wpcf7-validates-as-email" aria-required="true" aria-invalid="false" placeholder="Email">
						
			</div>

		<button class="tm-more-button tm-more-button-welcome" type="submit"
			style="height: 45px;color: white; font-weight: bold">Order</button>
	</form>

</div>

<script>
		$().ready(
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
													soluong : {
														required : "(Số lượng không được để trống)",
													},
													dc : {
														required : "(Địa chỉ không được để trống)",
													}

												}
											});
						});
	</script>

<script type="text/javascript">
	document.getElementById("info").classList.add('active');
</script>
<%@include file="/public/inc/footer.jsp"%>