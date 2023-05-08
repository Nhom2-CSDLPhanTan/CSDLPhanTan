package controller.client1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Person;
import model.dao.PersonDAO;
import utils.AuthUtil;

public class DelUserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonDAO personDAO;
       
    public DelUserAdminController() {
        super();
        personDAO = new PersonDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		
		int iduser = Integer.parseInt(request.getParameter("iduser"));
		
		Person itemDel = personDAO.getItemById(iduser);
		
		if(personDAO.delItem(itemDel) > 0) {
			response.sendRedirect(request.getContextPath() + "/client1/admin/user/index?msg=3");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
