package controller.client2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Person;
import model.dao.PersonDAO;

public class LoginClient2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PersonDAO personDAO;
	private int active = 0;
       
    public LoginClient2Controller() {
        super();
        personDAO = new PersonDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/client2/login/login.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		//kiểm tra tài khoản bị khóa hay không
		if (personDAO.getUserByEmailActive(email) != null) {
			response.sendRedirect(request.getContextPath() + "/loginc2?err=3");
			return;
		}

		ArrayList<Person> listUser = personDAO.getListUserClient2();
		
		//kiểm tra tồn tại email hay không
		boolean checkEmail = true;
		for (Person item : listUser) {
			if (!email.equals(item.getUser_Email())) {
				checkEmail = false;
			} else {
				checkEmail = true;
				break;
			}

		}
		if (!checkEmail) {
			response.sendRedirect(request.getContextPath() + "/loginc2?err=1");
			return;
		}

		//kiểm tra đúng password hay ko
		boolean checkPass = true;
		for (Person item : listUser) {
			if (email.equals(item.getUser_Email()) && !pass.equals(item.getUser_Password())) {
				checkPass = false;
				break;
			}
		}
		
		//khóa tài khoản
		if (!checkPass) {
			active++;
			if (active == 3) {
				if (personDAO.updateActive(email) > 0) {
					response.sendRedirect(request.getContextPath() + "/loginc2?err=3");
					return;
				}
			}
			response.sendRedirect(request.getContextPath() + "/loginc2?err=2");
			return;
		}
		
		//đúng email và password thì đăng nhập thành công
		Person userLogin = personDAO.getItemLoginClient2(email, pass);

		if (userLogin != null) {
			session.setAttribute("userLogin", userLogin);
			response.sendRedirect(request.getContextPath() + "/client2/public/index");
			return;
		}
	}

}
