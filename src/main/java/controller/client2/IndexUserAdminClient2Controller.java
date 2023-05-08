package controller.client2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Person;
import model.dao.PersonDAO;
import utils.AuthUtil;
import utils.ReadExcelNhanVien;

public class IndexUserAdminClient2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String excelFilePath = "C:/demo/Nhanvien.xlsx";
	private PersonDAO personDAO;
       
    public IndexUserAdminClient2Controller() {
        super();
        personDAO = new PersonDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc2");
			return;
		}
		
		HttpSession httpSession = request.getSession();
		Person userLogin = (Person) httpSession.getAttribute("userLogin");
		
		List<Person> listUser = new ArrayList<>();
		
		if(userLogin!=null) {
			if(userLogin.getUser_Role() == 0) {
				listUser = ReadExcelNhanVien.readExcel(excelFilePath);
				listUser.add(userLogin);
			}else {
				if(userLogin.getUser_Role() == 1) {
					listUser = personDAO.getListUserClient2(userLogin);
				}else {
					listUser.add(userLogin);
				}
			}
		}
		
		request.setAttribute("listUser", listUser);
		
		RequestDispatcher rd = request.getRequestDispatcher("/client2/user/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
