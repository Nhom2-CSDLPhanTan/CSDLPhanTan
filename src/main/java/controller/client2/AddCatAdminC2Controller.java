package controller.client2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import utils.AuthUtil;

public class AddCatAdminC2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
       
    public AddCatAdminC2Controller() {
        super();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc2");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/client2/danhmuc/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String madm = request.getParameter("madm");
		String tendm = request.getParameter("tendm");
		String mota = request.getParameter("mota");
		
		Category item = new Category(madm, tendm, mota);
		
		if(categoryDAO.addItem(item) > 0) {
			response.sendRedirect(request.getContextPath() + "/client2/admin/cat/index?msg=2");
			return;
		}
		
	}

}
