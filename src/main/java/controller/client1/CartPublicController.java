package controller.client1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Cart;
import model.bean.Person;
import model.dao.CartDAO;
import model.dao.CategoryDAO;
import utils.AuthUtil;

public class CartPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartDAO cartDAO;
    public CartPublicController() {
        super();
        cartDAO = new CartDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		
		HttpSession httpSession = request.getSession();
		Person userLogin = (Person) httpSession.getAttribute("userLogin");
		
		ArrayList<Cart> listCart = cartDAO.listCarts(userLogin.getUser_ID());
		
		request.setAttribute("listCart", listCart);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/cart.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession httpSession = request.getSession();
		Person userLogin = (Person) httpSession.getAttribute("userLogin");
		
		 String masp = request.getParameter("masp");
		 int soluong = Integer.parseInt(request.getParameter("soluong"));
		 
		 Cart item = new Cart(0, masp, soluong, userLogin.getUser_ID());
		 
		 ArrayList<Cart> listCart = cartDAO.listCarts(userLogin.getUser_ID());
		 
		 
		 for(Cart it : listCart) {
			 if(item.getIdPro().equals(it.getIdPro())) {
				 item.setNumber(it.getNumber() + soluong);
				 if(cartDAO.updateNumber(item) > 0) {
					 response.sendRedirect(request.getContextPath() + "/client1/public/cart");
					 return;
				 }
			 }
		 }
		 
		 if(cartDAO.add_Cart(item) > 0) {
			 response.sendRedirect(request.getContextPath() + "/client1/public/cart");
			 return;
		 }
	}

}
