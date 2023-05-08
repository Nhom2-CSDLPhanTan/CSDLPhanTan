package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Cart;
import utils.DBConnectUtil;

public class CartDAO {
	
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public ArrayList<Cart> listCarts(int idUsser) {
		ArrayList<Cart> listCart = new ArrayList<>();
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Select * From Cart where cart_IdPerson = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUsser);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				Cart item = new Cart(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				listCart.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		
		return listCart;
	}
	
	public int add_Cart(Cart item) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();

		String sql = "insert into Cart values (?,?,?,default)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getIdPro());
			pst.setInt(2, item.getNumber());
			pst.setInt(3, item.getIdPerson());
			
			result = pst.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		
		return result;
	}
	
	public int updateNumber(Cart item) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();

		String sql = "update Cart set cart_Number = ? where cart_IdProduct = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, item.getNumber());
			pst.setString(2, item.getIdPro());
			
			result = pst.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		
		return result;
	}

	public Cart getListCart(int s, int user_ID) {
		Cart item = null;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "select * from Cart where cart_ID = ? and cart_IdPerson = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, s);
			pst.setInt(2, user_ID);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				item = new Cart(s, rs.getString(2), rs.getInt(3), user_ID);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		return item;
	}

	public int delItem(int id) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();

		String sql = "delete from Cart where cart_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			result = pst.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		
		return result;
	}

}
