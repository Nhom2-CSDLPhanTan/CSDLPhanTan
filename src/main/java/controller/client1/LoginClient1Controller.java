package controller.client1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.OrderDetail;
import model.bean.Person;
import model.bean.order_inf;
import model.dao.OrderDetailDAO;
import model.dao.OrderInfoDAO;
import model.dao.PersonDAO;

public class LoginClient1Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PersonDAO personDAO;
	private OrderDetailDAO orderDetailDAO;
	private OrderInfoDAO orderInfoDAO;
	private int active = 0;
       
    public LoginClient1Controller() {
        super();
        personDAO = new PersonDAO();
        orderDetailDAO = new OrderDetailDAO();
        orderInfoDAO = new OrderInfoDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/client1/login/login.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		//kiểm tra tài khoản bị khóa hay không
		if (personDAO.getUserByEmailActive(email) != null) {
			response.sendRedirect(request.getContextPath() + "/loginc1?err=3");
			return;
		}

		ArrayList<Person> listUser = personDAO.getListUserClient1();

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
			response.sendRedirect(request.getContextPath() + "/loginc1?err=1");
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
					response.sendRedirect(request.getContextPath() + "/loginc1?err=3");
					return;
				}
			}
			response.sendRedirect(request.getContextPath() + "/loginc1?err=2");
			return;
		}
		
		//đúng email và password thì đăng nhập thành công
		Person userLogin = personDAO.getItemLoginClient1(email, pass);

		if (userLogin != null) {
			ArrayList<OrderDetail> listOderDetail = orderDetailDAO.getListOrderDetail();
			
			for(OrderDetail it : listOderDetail) {
				order_inf itemInf = orderInfoDAO.getItemById(it.getIdInfo());
			}
			session.setAttribute("userLogin", userLogin);
			response.sendRedirect(request.getContextPath() + "/client1/public/index");
			return;
		}
	}

}
