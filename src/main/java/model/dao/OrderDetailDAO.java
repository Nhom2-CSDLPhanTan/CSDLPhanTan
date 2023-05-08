package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.OrderDetail;
import model.bean.order_inf;
import utils.DBConnectUtil;

public class OrderDetailDAO {
	
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public int add_OrderInfo(OrderDetail item) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();

		String sql = "insert into order_detail values (?,?,?,?,default)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, item.getIdInfo());
			pst.setString(2, item.getIdPro());
			pst.setInt(3, item.getNumber());
			pst.setInt(4, item.getPrice());
			
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
	
	public ArrayList<OrderDetail> getListOrderDetail(){
		ArrayList<OrderDetail> listOrderdetail = new ArrayList<>();
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "select * from order_detail";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				OrderDetail item = new OrderDetail(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				listOrderdetail.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (rs!=null && st != null && conn != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		
		return listOrderdetail;
	}
	
}
