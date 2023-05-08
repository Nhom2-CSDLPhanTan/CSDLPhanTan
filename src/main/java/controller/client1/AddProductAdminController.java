package controller.client1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Products;
import model.dao.ProductsDAO;
import utils.AuthUtil;

public class AddProductAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;

	public AddProductAdminController() {
		super();
		productsDAO = new ProductsDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/client1/sanpham/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		request.setCharacterEncoding("utf-8");

		String masp = request.getParameter("masp");
		String tensp = request.getParameter("tensp");
		String chitiet = request.getParameter("chitiet");
		int giaban = Integer.parseInt(request.getParameter("giaban"));
		String c_id = request.getParameter("dm");
		
		ArrayList<Products> listProducts = productsDAO.getListProducts();
		for(Products item : listProducts) {
			//kiểm tra masp có tồn tại hay chưa
			if(masp.equals(item.getId())) {
				response.sendRedirect(request.getContextPath() + "/client1/admin/sanpham/add?err=1");
				return;
			}
		}

		Products item = new Products(masp, tensp, chitiet, giaban, c_id);
		
		if(productsDAO.addProduct(item) > 0) {
			response.sendRedirect(request.getContextPath() + "/client1/admin/products?msg=1");
		}
	}

}
