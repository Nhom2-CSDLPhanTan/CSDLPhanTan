package controller.client2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import utils.AuthUtil;

public class DelCatAdminClient2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
       
    public DelCatAdminClient2Controller() {
        super();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc2");
			return;
		}
		String id = request.getParameter("id");
		
		Category itemDel = categoryDAO.getItemDel(id);
		
		if(categoryDAO.delItem(itemDel) > 0) {
			response.sendRedirect(request.getContextPath() + "/client2/admin/cat/index?msg=3");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
