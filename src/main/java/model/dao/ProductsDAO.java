package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Picture;
import model.bean.Products;
import utils.DBConnectUtil;

public class ProductsDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	//server
		public ArrayList<Products> getListProducts() {
			ArrayList<Products> listProducts = new ArrayList<>();
			conn = DBConnectUtil.getConnection();
			String sql = "Select * FROM Products";
			
			try {
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()) {
					String id = rs.getString(1);
					String name = rs.getString(2);
					String detail = rs.getString(3);
					int price = rs.getInt(4);
					String c_id = rs.getString(5);
					Products item = new Products(id, name, detail, price, c_id);
					listProducts.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(rs!=null && pst!=null && conn!=null) {
					try {
						rs.close();
						pst.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}
			return listProducts;
		}
	
	//client1
	public ArrayList<Products> getListProductsClient1() {
		ArrayList<Products> listProducts = new ArrayList<>();
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Select * FROM Products where pro_Cid = 'CF'";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String detail = rs.getString(3);
				int price = rs.getInt(4);
				String c_id = rs.getString(5);
				Products item = new Products(id, name, detail, price, c_id);
				listProducts.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return listProducts;
	}
	
	//client2
		public ArrayList<Products> getListProductsClient2() {
			ArrayList<Products> listProducts = new ArrayList<>();
			conn = DBConnectUtil.getConnectionClient2();
			String sql = "Select * FROM Products where pro_Cid = 'MT'";
			
			try {
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()) {
					String id = rs.getString(1);
					String name = rs.getString(2);
					String detail = rs.getString(3);
					int price = rs.getInt(4);
					String c_id = rs.getString(5);
					Products item = new Products(id, name, detail, price, c_id);
					listProducts.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(rs!=null && pst!=null && conn!=null) {
					try {
						rs.close();
						pst.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}
			return listProducts;
		}

	public Products getProductsById(String id) {
		Products item = null;
		conn = DBConnectUtil.getConnection();
		String sql = "Select * FROM Products where pro_ID = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String name = rs.getString(2);
				String detail = rs.getString(3);
				int price = rs.getInt(4);
				String c_id = rs.getString(5);
				item = new Products(id, name, detail, price, c_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
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

	public int addProduct(Products item) {
		int result = 0;
		conn = DBConnectUtil.getConnection();
		String sql = "Insert into Products values (?,?,?,?,?,default)";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, item.getId());
			pst.setNString(2, item.getName());
			pst.setNString(3, item.getDetail());
			pst.setInt(4, item.getPrice());
			pst.setString(5, item.getC_id());
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pst!=null && conn!=null) {
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

	public int editItem(Products itemEdit) {
		int result = 0;
		conn = DBConnectUtil.getConnection();

		String sql = "update Products set pro_Name =?, pro_Detail =?,pro_Price=? where pro_ID =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, itemEdit.getName());
			pst.setNString(2, itemEdit.getDetail());
			pst.setInt(3, itemEdit.getPrice());
			pst.setNString(4, itemEdit.getId());
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

	public int delItem(Products itemDel) {
		int result = 0;
		conn = DBConnectUtil.getConnection();

		String sql = "delete from Products where pro_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemDel.getId());
			
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

	public ArrayList<Picture> getListPicture(String idsp) {
		ArrayList<Picture> listPicture = new ArrayList<>();
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Select * FROM Picture where pic_IdPro = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idsp);
			rs = pst.executeQuery();
			while(rs.next()) {
				Picture item = new Picture(rs.getInt(1), rs.getString(2), rs.getString(3));
				listPicture.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return listPicture;
	}

	public int addPictureProduct(Picture item) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Insert into Picture values (?,?,default)";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, item.getUrl());
			pst.setNString(2, item.getId_Pro());
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pst!=null && conn!=null) {
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

	public Picture getPictureById(int idha) {
		Picture item = null;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Select * FROM Picture where pic_ID = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idha);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Picture(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
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
	
	public Picture getPictureByIdPro(String idha) {
		Picture item = null;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Select * FROM Picture where pic_IdPro = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idha);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Picture(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
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
	
	public ArrayList<Picture> getListPictureByIdPro(String idha) {
		ArrayList<Picture> listPicture = new ArrayList<>();
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Select * FROM Picture where pic_IdPro = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idha);
			rs = pst.executeQuery();
			while(rs.next()) {
				Picture item = new Picture(rs.getInt(1), rs.getString(2), rs.getString(3));
				listPicture.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return listPicture;
	}

	public int delItem(int idha) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();

		String sql = "delete from Picture where pic_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idha);
			
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

	public int editPicture(Picture itemEdit) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();

		String sql = "update Picture set pic_Url = ? where pic_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemEdit.getUrl());
			pst.setInt(2, itemEdit.getId());
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
