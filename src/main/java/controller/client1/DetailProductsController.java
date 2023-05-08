package controller.client1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Products;
import model.dao.ProductsDAO;

public class DetailProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;
       
    public DetailProductsController() {
        super();
        productsDAO = new ProductsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Products item = productsDAO.getProductsById(id);
		request.setAttribute("item", item);
		RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
