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
import model.bean.Products;
import model.dao.CartDAO;
import model.dao.ProductsDAO;


public class PublicDatHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductsDAO productsDAO;
    private CartDAO cartDAO;
    public PublicDatHangController() {
        super();
        productsDAO = new ProductsDAO();
        cartDAO = new CartDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		Person userLogin = (Person) httpSession.getAttribute("userLogin");
		
		String[] id =(request.getParameterValues("idsp"));//chon mau là tên của ô checkbox
		
		ArrayList<Cart> listspMua = new ArrayList<Cart>();
        for (String s : id) {
        	int idsp = Integer.parseInt(s);
            Cart item = cartDAO.getListCart(idsp,userLogin.getUser_ID());
            listspMua.add(item);
        }
        
        httpSession.setAttribute("listMua", listspMua);
              
        request.setAttribute("listspMua", listspMua);
        RequestDispatcher rd = request.getRequestDispatcher("/public/order.jsp");
		rd.forward(request, response);
	}

}
