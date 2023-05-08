package controller.client1;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Picture;
import model.dao.ProductsDAO;

public class DelPictureProductAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsDAO productsDAO;
       
    public DelPictureProductAdminController() {
        super();
        productsDAO = new ProductsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idsp = request.getParameter("idsp");
		
		int idha = Integer.parseInt(request.getParameter("idha"));
		Picture itemDel = productsDAO.getPictureById(idha);
		
		if(productsDAO.delItem(idha) > 0) {
			final String dirPathName = request.getServletContext().getRealPath("/file");
			String filePathName = dirPathName + File.separator + itemDel.getUrl();
			//System.out.println(filePathName);
			File file = new File(filePathName);
			
			if(file.exists()) {
				file.delete();
			}
			
			response.sendRedirect(request.getContextPath()+"/client1/admin/sanpham/hinhanhsp?msg=2&idsp="+idsp);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
