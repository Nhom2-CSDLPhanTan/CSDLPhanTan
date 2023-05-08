package controller.client1;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Person;
import model.dao.PersonDAO;
import utils.AuthUtil;
import utils.FileUtil;

@MultipartConfig
public class EditUserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonDAO personDAO;

	public EditUserAdminController() {
		super();
		personDAO = new PersonDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));

		Person itemEdit = personDAO.getItemById(id);

		request.setAttribute("itemEdit", itemEdit);

		RequestDispatcher rd = request.getRequestDispatcher("/client1/user/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		
		request.setCharacterEncoding("utf-8");

		int id = Integer.parseInt(request.getParameter("id"));

		Person itemEdit = personDAO.getItemById(id);

		String username = request.getParameter("username");
		String pass = request.getParameter("pass");

		Part filePart = request.getPart("avatar");

		final String dirPathName = request.getServletContext().getRealPath("/file");
		File dirFile = new File(dirPathName);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		// Lấy tên file 
		String filename = FileUtil.getName(filePart);
		// Đổi tên file
		String picture = "";
		if (filename.isEmpty()) {
			picture = itemEdit.getUser_Avartar();
		} else {
			picture = FileUtil.rename(filename);
		}

		// dÆ°á»�ng dáº«n file
		String filePathName = dirPathName + File.separator + picture;
		
		itemEdit.setUser_Name(username);
		itemEdit.setUser_Password(pass);
		itemEdit.setUser_Avartar(picture);
		
		if(personDAO.editItem(itemEdit) > 0) {	
			if(!filename.isEmpty()) {
				//xÃ³a file cÅ©
				String oldfilePathName = dirPathName  + File.separator + itemEdit.getUser_Avartar();
				File oldFile = new File(oldfilePathName);
				if(oldFile.exists()) {
					oldFile.delete();
				}
				//ghi file
				filePart.write(filePathName);		
			}
			response.sendRedirect(request.getContextPath() + "/client1/admin/user/index?msg=2");
			return;
		}

	}

}
