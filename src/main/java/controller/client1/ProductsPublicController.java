package controller.client1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Products;
import model.dao.ProductsDAO;
import utils.AuthUtil;

public class ProductsPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;
       
    public ProductsPublicController() {
        super();
        productsDAO = new ProductsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		
		ArrayList<Products> listProducts = productsDAO.getListProductsClient1();
		request.setAttribute("listProducts", listProducts);
		RequestDispatcher rd = request.getRequestDispatcher("/public/menu.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
