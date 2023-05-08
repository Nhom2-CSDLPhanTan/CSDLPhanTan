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
import utils.AuthUtil;

public class EditProductAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;

	public EditProductAdminController() {
		super();
		productsDAO = new ProductsDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		request.setCharacterEncoding("utf-8");

		String idsp = request.getParameter("idsp");

		Products itemEdit = productsDAO.getProductsById(idsp);

		request.setAttribute("itemEdit", itemEdit);

		RequestDispatcher rd = request.getRequestDispatcher("/client1/sanpham/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String idsp = request.getParameter("idsp");

		Products itemEdit = productsDAO.getProductsById(idsp);

		String tensp = request.getParameter("tensp");
		String chitiet = request.getParameter("chitiet");
		int giaban = Integer.parseInt(request.getParameter("giaban"));

		itemEdit.setName(tensp);
		itemEdit.setDetail(chitiet);
		itemEdit.setPrice(giaban);
		
		if(productsDAO.editItem(itemEdit) > 0) {
			response.sendRedirect(request.getContextPath() + "/client1/admin/products?msg=2");
			return;
		}
	}

}
