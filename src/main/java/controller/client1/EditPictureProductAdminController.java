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
public class EditPictureProductAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductsDAO productsDAO;
	
    public EditPictureProductAdminController() {
        super();
        productsDAO = new ProductsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idha =Integer.parseInt(request.getParameter("idha"));
		
		Picture itemEdit = productsDAO.getPictureById(idha);
		
		request.setAttribute("itemEdit",itemEdit);
		RequestDispatcher rd = request.getRequestDispatcher("/client1/sanpham/edithinhanh.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idha =Integer.parseInt(request.getParameter("idha"));
		
		String idsp = request.getParameter("idsp");
		
		Picture itemEdit = productsDAO.getPictureById(idha);
		
		Part filePart = request.getPart("ha");
		
		final String dirPathName = request.getServletContext().getRealPath("/file");
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		//Láº¥y tÃªn file tá»« path
		String filename =FileUtil.getName(filePart);
		//Ä‘á»•i tÃªn file
		String picture = "";
		if(filename.isEmpty()) {
			picture = itemEdit.getUrl();
		} else {
			picture = FileUtil.rename(filename);
		}
		 
		//dÆ°á»�ng dáº«n file
		String filePathName = dirPathName + File.separator + picture;
		
		itemEdit.setUrl(picture);
		
		if(productsDAO.editPicture(itemEdit) > 0) {	
			if(!filename.isEmpty()) {
				//xÃ³a file cÅ©
				String oldfilePathName = dirPathName  + File.separator + itemEdit.getUrl();
				File oldFile = new File(oldfilePathName);
				if(oldFile.exists()) {
					oldFile.delete();
				}
				//ghi file
				filePart.write(filePathName);		
			}
			response.sendRedirect(request.getContextPath()+"/client1/admin/sanpham/hinhanhsp?msg=3&idsp="+idsp);
			return;
		}else {
			System.out.println("loi");
			return;
		}
	}

}
