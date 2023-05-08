package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import utils.DBConnectUtil;

public class CategoryDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	//server
		public ArrayList<Category> getListCat() {
			ArrayList<Category> list = new ArrayList<Category>();
			conn = DBConnectUtil.getConnection();
			String sql = "Select * From Categories";
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Category item = new Category(rs.getString(1), rs.getString(2), rs.getNString(3));
					list.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (rs != null && st != null && conn != null) {
					try {
						rs.close();
						st.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}
			return list;
		}

	//client1
	public ArrayList<Category> getListCatClient1() {
		ArrayList<Category> list = new ArrayList<Category>();
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Select * From Categories";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category item = new Category(rs.getString(1), rs.getString(2), rs.getNString(3));
				list.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		return list;
	}
	
	//client2
		public ArrayList<Category> getListCatClient2() {
			ArrayList<Category> list = new ArrayList<Category>();
			conn = DBConnectUtil.getConnectionClient2();
			String sql = "Select * From Categories";
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Category item = new Category(rs.getString(1), rs.getString(2), rs.getNString(3));
					list.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (rs != null && st != null && conn != null) {
					try {
						rs.close();
						st.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}
			return list;
		}

	public Category getItemEdit(String id) {
		Category itemEdit = null;
		conn = DBConnectUtil.getConnection();
		String sql = "Select * From Categories where cat_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				itemEdit = new Category(rs.getString(1), rs.getString(2), rs.getNString(3));
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
		return itemEdit;
	}
	
	public Category getItemDel(String id) {
		Category itemEdit = null;
		conn = DBConnectUtil.getConnection();
		String sql = "Select * From Categories where cat_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				itemEdit = new Category(rs.getString(1), rs.getString(2), rs.getNString(3));
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
		return itemEdit;
	}

	public int editItem(Category itemEdit) {
		int result = 0;
		conn = DBConnectUtil.getConnection();

		String sql = "update Categories set cat_Name = ?, cat_Detail = ? where cat_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemEdit.getCat_Name());
			pst.setString(2, itemEdit.getCat_Detail());
			pst.setString(3, itemEdit.getCat_Id());
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

	public int addItem(Category item) {
		int result = 0;
		conn = DBConnectUtil.getConnection();

		String sql = "insert into Categories values (?,?,?,default)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getCat_Id());
			pst.setNString(2, item.getCat_Name());
			pst.setNString(3, item.getCat_Detail());
			
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

	public int delItem(Category itemDel) {
		int result = 0;
		conn = DBConnectUtil.getConnection();

		String sql = "delete from Categories where cat_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemDel.getCat_Id());
			
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
