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

import model.bean.Picture;
import model.dao.ProductsDAO;
import utils.FileUtil;

@MultipartConfig
public class AddPictureProductAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;
       
    public AddPictureProductAdminController() {
        super();
        productsDAO = new ProductsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String idsp = request.getParameter("idsp");
		
		RequestDispatcher rd = request.getRequestDispatcher("/client1/sanpham/addhinhanh.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String idsp = request.getParameter("idsp");
		
		Part filePart = request.getPart("ha");
		
		//tạo thư mục lưu hình ảnh
		final String dirPathName = request.getServletContext().getRealPath("/file");
		File dirFile = new File(dirPathName);
		System.out.println(dirFile);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		String filename = FileUtil.getName(filePart);
		
		String picture = FileUtil.rename(filename);
		
		String filePathName = dirPathName + File.separator + picture;

		Picture item = new Picture(0, picture, idsp);
	
		if(productsDAO.addPictureProduct(item) > 0) {
			if(!filename.isEmpty()) {
				filePart.write(filePathName);
			}	
			response.sendRedirect(request.getContextPath()+"/client1/admin/sanpham/hinhanhsp?msg=1&idsp="+idsp);
			return;
		}
	}

}
