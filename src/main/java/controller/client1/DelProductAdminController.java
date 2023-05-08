package controller.client1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Products;
import model.dao.ProductsDAO;
import utils.AuthUtil;

public class DelProductAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;
       
    public DelProductAdminController() {
        super();
        productsDAO = new ProductsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		request.setCharacterEncoding("utf-8");

		String idsp = request.getParameter("idsp");

		Products itemDel = productsDAO.getProductsById(idsp);
		
		if(productsDAO.delItem(itemDel) > 0) {
			response.sendRedirect(request.getContextPath() + "/client1/admin/products?msg=3");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
