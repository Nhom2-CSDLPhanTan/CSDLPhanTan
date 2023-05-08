package controller.client2;

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

public class IndexProductsAdminClient2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;
       
    public IndexProductsAdminClient2Controller() {
        super();
        productsDAO = new ProductsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc2");
			return;
		}
		HttpSession session = request.getSession();
		Person userLogin = (Person) session.getAttribute("userLogin");
		
		ArrayList<Products> listProduct = new ArrayList<>();
		
		if(userLogin.getUser_Role() == 0) {
			listProduct = productsDAO.getListProducts();
		}else {
			if(userLogin.getUser_Role() == 2 || userLogin.getUser_Role() == 5) {
				listProduct = productsDAO.getListProductsClient2();
			}
		}
		
		request.setAttribute("listProduct", listProduct);
		
		RequestDispatcher rd = request.getRequestDispatcher("/client2/sanpham/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
