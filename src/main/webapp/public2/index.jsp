<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/public2/inc/header.jsp" %>
    <div class="tm-main-section light-gray-bg">
      <div class="container" id="main">
        <section class="tm-section row">
          <div class="col-lg-9 col-md-9 col-sm-8">
            <h2 class="tm-section-header gold-text tm-handwriting-font">The Best MilkTea for you</h2>
            <h2>Cafe House</h2>
            <p class="tm-welcome-description">Hân hạnh phục vụ đến quý khách những ly trà sữa thượng hạng nhất</p>
            <a href="<%=request.getContextPath() %>/public/menu.jsp" style="color: yellow" class="tm-more-button margin-top-30">Menu</a>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-4 tm-welcome-img-container">
            <div class="inline-block shadow-img">
              <img src="img/1.jpg" alt="Image" class="img-circle img-thumbnail">  
            </div>              
          </div>            
        </section>          
        <section class="tm-section tm-section-margin-bottom-0 row">
          
          <div class="col-lg-12 tm-popular-items-container">
      
            
          </div>      
          
        </section>
        
        
      </div>
    </div> 
    <script type="text/javascript">
    	document.getElementById("home").classList.add('active');
	</script>
    <%@include file="/public2/inc/footer.jsp"%>