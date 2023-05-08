package controller.client2;

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

public class ProductsPublicClient2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;
       
    public ProductsPublicClient2Controller() {
        super();
        productsDAO = new ProductsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Products> listProducts = productsDAO.getListProductsClient2();
		request.setAttribute("listProducts", listProducts);
		RequestDispatcher rd = request.getRequestDispatcher("/public2/menu.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
