package controller.client1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Picture;
import model.dao.ProductsDAO;
import utils.AuthUtil;

public class PictureProductAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;
       
    public PictureProductAdminController() {
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
		
		ArrayList<Picture> listPicture = productsDAO.getListPicture(idsp);
		
		request.setAttribute("idsp", idsp);
		request.setAttribute("listPicture", listPicture);
		
		RequestDispatcher rd = request.getRequestDispatcher("/client1/sanpham/hinhanhsp.jsp");
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
