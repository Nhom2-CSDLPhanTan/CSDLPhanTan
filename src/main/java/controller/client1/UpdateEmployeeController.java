package controller.client1;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Person;
import model.dao.PersonDAO;
import utils.ReadExcelNhanVien;

public class UpdateEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String excelFilePath = "C:/demo/Nhanvien.xlsx";
	private PersonDAO personDAO;
	
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static Random rand = new Random();
       
    public UpdateEmployeeController() {
        super();
        personDAO = new PersonDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Person> listEmployee = ReadExcelNhanVien.readExcel(excelFilePath);
		
		List<Person> listUser = personDAO.getListUser();
		
		//add when no item in db
		for(int i = 0; i<listEmployee.size();i++) {
			boolean ktr = false;
			for(int j = 0;j<listUser.size();j++) {
				if(!listEmployee.get(i).getUser_Email().equals(listUser.get(j).getUser_Email())) {
					ktr = true;
				}else {
					ktr = false;
					break;
				}
			}
			System.out.println(ktr);
			if(ktr) {
				listEmployee.get(i).setUser_Forget(UpdateEmployeeController.randomAlphaNumeric(8));
				listEmployee.get(i).setUser_Password("123456");
				listEmployee.get(i).setUser_Active(1);
				if(listEmployee.get(i).getUser_Role() == 1) {
					personDAO.addUser(listEmployee.get(i));
				}else {
					if(listEmployee.get(i).getUser_Role() == 2) {
						personDAO.addUserClient2(listEmployee.get(i));
					}
				}
			}
		}
		
		//del item when no item in excel
		for(int i = 0; i<listUser.size();i++) {
			boolean ktr = false;
			for(int j = 0;j<listEmployee.size();j++) {
				if((!listUser.get(i).getUser_Email().equals(listEmployee.get(j).getUser_Email()))
						&& (listUser.get(i).getUser_Role() == 1)) {
					//
					ktr = true;
				}else {
					ktr = false;
					break;
				}
			}
			if(ktr) {
				personDAO.dellUser(listUser.get(i));
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/client1/admin/user/index?msg=1");
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
