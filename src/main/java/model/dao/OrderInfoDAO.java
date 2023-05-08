package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.order_inf;
import utils.DBConnectUtil;

public class OrderInfoDAO {
	
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public int add_OrderInfo(order_inf item) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();

		String sql = "insert into Order_Information values (?,?,?,?,?,?,default)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getCode());
			pst.setDate(2, item.getDate());
			pst.setInt(3, item.getId_person());
			pst.setString(4, item.getAddress());
			pst.setInt(5, item.getStatus());
			pst.setString(6, item.getPhone());
			
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

	public order_inf getItemNew() {
		order_inf item = null;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "SELECT * FROM Order_Information WHERE od_ID = (SELECT MAX(od_ID) from Order_Information) ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				int id = rs.getInt(1);
				String code = rs.getString(2);
				Date ngaymua = rs.getDate(3);
				int id_nguoimua = rs.getInt(4);
				String diachi = rs.getString(5);
				int trangthai = rs.getInt(6);
				String sdt = rs.getString(7);
				
				item = new order_inf(id, code, ngaymua, id_nguoimua, diachi,trangthai,sdt);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs != null && st != null && conn != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return item;
	}
	
	public order_inf getItemById (int id){
		order_inf listOrderInf = null;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "SELECT * FROM Order_Information WHERE od_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				listOrderInf = new order_inf(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getNString(5), rs.getInt(6), rs.getString(7));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs!=null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return listOrderInf;
	}

	public int updateStatus(int i,int id) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "update Order_Information set od_Status = ? where od_ID =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setInt(2, id);
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		return result;
	}


}
