package controller.client1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.Person;
import model.dao.CategoryDAO;
import utils.AuthUtil;

public class IndexCatAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
       
    public IndexCatAdminController() {
        super();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		
		HttpSession session = request.getSession();
		Person userLogin = (Person) session.getAttribute("userLogin");
		
		ArrayList<Category> listCat = new ArrayList<>();
		
		if(userLogin.getUser_Role() == 0) {
			listCat = categoryDAO.getListCat();
		}else {
			if(userLogin.getUser_Role() == 1) {
				listCat = categoryDAO.getListCatClient1();
			}
		}
		
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/client1/danhmuc/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
