package controller.client1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Person;
import model.bean.Products;
import model.dao.ProductsDAO;
import utils.AuthUtil;

public class IndexProductsAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;
       
    public IndexProductsAdminController() {
        super();
        productsDAO = new ProductsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		HttpSession session = request.getSession();
		Person userLogin = (Person) session.getAttribute("userLogin");
		
		ArrayList<Products> listProduct = new ArrayList<>();
		
		if(userLogin.getUser_Role() == 0) {
			listProduct = productsDAO.getListProducts();
		}else {
			if(userLogin.getUser_Role() == 1 || userLogin.getUser_Role() == 4) {
				listProduct = productsDAO.getListProductsClient1();
			}
		}
		
		request.setAttribute("listProduct", listProduct);
		
		RequestDispatcher rd = request.getRequestDispatcher("/client1/sanpham/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
