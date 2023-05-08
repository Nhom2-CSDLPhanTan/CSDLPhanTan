package controller.client1;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Cart;
import model.bean.OrderDetail;
import model.bean.Person;
import model.bean.order_inf;
import model.dao.CartDAO;
import model.dao.OrderDetailDAO;
import model.dao.OrderInfoDAO;

public class PublicOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	
	private static Random rand;
	
	private OrderInfoDAO orderInfoDAO;
	private OrderDetailDAO orderDetailDAO;
	private CartDAO cartDAO;
    public PublicOrderController() {
        super();
        rand = new Random();
        orderInfoDAO = new OrderInfoDAO();
        orderDetailDAO = new OrderDetailDAO();
        cartDAO = new CartDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession httpSession = request.getSession();
		Person userLogin = (Person)httpSession.getAttribute("userLogin");
		
		String[] masp = request.getParameterValues("masp");		
		String[] soluong = request.getParameterValues("soluong");
		String[] chiphi = request.getParameterValues("chiphi");
		
		ArrayList<Cart> listMua =(ArrayList<Cart>) httpSession.getAttribute("listMua");
		int i = 0;
		String address = request.getParameter("dc");
		String phone = request.getParameter("sdt");
		for(Cart itemCart : listMua) {
			String code = PublicOrderController.randomAlphaNumeric(8);
			Date date = Date.valueOf(LocalDate.now());
			
			String dc = request.getParameter("dc");
			String sdt = request.getParameter("sdt");
			
			order_inf itemOrder = new order_inf(0, code, date, userLogin.getUser_ID(), address, 0, phone);
			
			String idPro = masp[i];
			int sl = Integer.parseInt(soluong[i]);
			int cp = Integer.parseInt(chiphi[i]);
			int dongia = sl*cp;
			
			if(orderInfoDAO.add_OrderInfo(itemOrder) > 0) {
				order_inf itemOrderInfoNew = orderInfoDAO.getItemNew();
				OrderDetail detail = new OrderDetail(0, itemOrderInfoNew.getId(), idPro, sl, cp);
				if(orderDetailDAO.add_OrderInfo(detail) > 0) {
					if(cartDAO.delItem(itemCart.getId()) > 0) {
						i++;
					}else {
						System.out.println("lôi");
						return;
					}
					
				}else {
					System.out.println("loi");
					return;
				}
				
			}else {
				System.out.println("loi");
				return;
			}
		}
		
		
		
		response.sendRedirect(request.getContextPath() + "/client1/public/cart");
		return;
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
