package controller.client1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Cart;
import model.bean.OrderDetail;
import model.bean.Person;
import model.dao.CartDAO;
import model.dao.OrderDetailDAO;
import model.dao.OrderInfoDAO;
import utils.AuthUtil;
import utils.WriteExcelHoaDon;

public class UpdateStatusOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderInfoDAO orderInfoDAO;
    private OrderDetailDAO orderDetailDAO;
    private CartDAO cartDAO;
    
    public UpdateStatusOrderController() {
        super();
        orderInfoDAO = new OrderInfoDAO();
        cartDAO = new CartDAO();
        orderDetailDAO = new OrderDetailDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/loginc1");
			return;
		}
		int choose = Integer.parseInt(request.getParameter("choose"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		switch(choose) {
			case 1:
				if(orderInfoDAO.updateStatus(1,id) > 0) {
					
					HttpSession httpSession = request.getSession();
					Person userLogin = (Person) httpSession.getAttribute("userLogin");
					
					ArrayList<Cart> listCart = cartDAO.listCarts(userLogin.getUser_ID());
					
					request.setAttribute("listCart", listCart);
					
					RequestDispatcher rd = request.getRequestDispatcher("/public/cart.jsp");
					rd.forward(request, response);
				}
				break;
			case 2:
				if(orderInfoDAO.updateStatus(2,id) > 0) {
					
					HttpSession httpSession = request.getSession();
					Person userLogin = (Person) httpSession.getAttribute("userLogin");

					final List<OrderDetail> listdh = orderDetailDAO.getListOrderDetail();
			        final String excelFilePath = "C:/demo/dathang.xlsx";
			        WriteExcelHoaDon.writeExcel(listdh, excelFilePath);
					
					ArrayList<Cart> listCart = cartDAO.listCarts(userLogin.getUser_ID());
					
					request.setAttribute("listCart", listCart);
					
					RequestDispatcher rd = request.getRequestDispatcher("/public/cart.jsp");
					rd.forward(request, response);
				}
				break;
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
