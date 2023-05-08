package controller.client2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Person;
import model.dao.PersonDAO;
import utils.FileUtil;
@MultipartConfig
public class SignUpC2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonDAO personDAO;
	
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	
	private static Random rand;

	public SignUpC2Controller() {
		super();
		personDAO = new PersonDAO();
		rand = new Random();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/client2/login/signup.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		ArrayList<Person> listUser = personDAO.getListUserClient1();
		for(Person item : listUser) {
			if(email.equals(item.getUser_Email())) {
				response.sendRedirect(request.getContextPath() + "/signupc2?err=1");
				return;
			}
		}
		
		String password = request.getParameter("pass");

		Part filePart = request.getPart("avartar");

		// tạo thư mục lưu ảnh
		final String dirPathName = request.getServletContext().getRealPath("/file");
		File dirFile = new File(dirPathName);
		System.out.println(dirFile);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		String filename = FileUtil.getName(filePart);

		String picture = FileUtil.rename(filename);
		
		String forgetPass = SignUpC2Controller.randomAlphaNumeric(8);

		String filePathName = dirPathName + File.separator + picture;

		Person item = new Person(0, username, password, picture, forgetPass, 1, 5, email);

		if (personDAO.addUserClient2(item) > 0) {
			if (!filename.isEmpty()) {
				filePart.write(filePathName);
			}
			response.sendRedirect(request.getContextPath() + "/loginc2?msg=1");
			return;
		}else {
			System.out.println("loi");
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
