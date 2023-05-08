package controller.client1;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Person;
import model.dao.PersonDAO;

public class LoginForgetPassAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;

	private static Random rand = new Random();
	
	private PersonDAO personDAO;

	public LoginForgetPassAdminController() {
		super();
		personDAO = new PersonDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/client1/login/loginforget.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String email = request.getParameter("email");
		String code = request.getParameter("code");

		Person item = personDAO.checkLoginForget(email, code);

		if (item != null) {
			String forgetPass = LoginForgetPassAdminController.randomAlphaNumeric(8);
			item.setUser_Forget(forgetPass);
			if (personDAO.updateForget(item) > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("userLogin", item);
				response.sendRedirect(request.getContextPath() + "/client1/public/index");
			}
		}
	}

	public static String randomAlphaNumeric(int numberOfCharactor) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numberOfCharactor; i++) {
			int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
			char ch = ALPHA_NUMERIC.charAt(number);
			sb.append(ch);
		}
		return sb.toString();
	}

	public static int randomNumber(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}

}
