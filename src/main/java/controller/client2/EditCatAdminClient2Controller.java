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

public class EditCatAdminClient2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;

	public EditCatAdminClient2Controller() {
		super();
		categoryDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		Category itemEdit = categoryDAO.getItemEdit(id);

		request.setAttribute("itemEdit", itemEdit);

		RequestDispatcher rd = request.getRequestDispatcher("/client2/danhmuc/edit.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");

		Category itemEdit = categoryDAO.getItemEdit(id);

		String cat_name = request.getParameter("tendm");
		String mota = request.getParameter("mota");
		
		itemEdit.setCat_Name(cat_name);
		itemEdit.setCat_Detail(mota);
		
		if(categoryDAO.editItem(itemEdit) > 0) {
			response.sendRedirect(request.getContextPath() + "/client2/admin/cat/index?msg=1");
			return;
		}

	}

}
