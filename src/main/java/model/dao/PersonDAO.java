package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.Person;
import utils.DBConnectUtil;

public class PersonDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<Person> getListUser() {
		ArrayList<Person> list = new ArrayList<Person>();
		conn = DBConnectUtil.getConnection();
		String sql = "Select * From Person";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Person itemLogin = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
				list.add(itemLogin);
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

	// client1
	public ArrayList<Person> getListUserClient1() {
		ArrayList<Person> list = new ArrayList<Person>();
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Select * From Person";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Person itemLogin = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
				list.add(itemLogin);
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

	// client2
	// client1
	public ArrayList<Person> getListUserClient2() {
		ArrayList<Person> list = new ArrayList<Person>();
		conn = DBConnectUtil.getConnectionClient2();
		String sql = "Select * From Person";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Person itemLogin = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
				list.add(itemLogin);
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

	public Person getItemLogin(String email, String pass) {
		Person itemLogin = null;
		conn = DBConnectUtil.getConnection();
		String sql = "Select * FROM Person where user_Email = ? and user_Password =?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, pass);

			rs = pst.executeQuery();
			if (rs.next()) {
				itemLogin = new Person(rs.getInt(1), rs.getString(2), pass, rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), email);
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

		return itemLogin;
	}

	// client1
	public Person getItemLoginClient1(String email, String pass) {
		Person itemLogin = null;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Select * FROM Person where user_Email = ? and user_Password =?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, pass);

			rs = pst.executeQuery();
			if (rs.next()) {
				itemLogin = new Person(rs.getInt(1), rs.getString(2), pass, rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), email);
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

		return itemLogin;
	}

	// client2
	public Person getItemLoginClient2(String email, String pass) {
		Person itemLogin = null;
		conn = DBConnectUtil.getConnectionClient2();
		String sql = "Select * FROM Person where user_Email = ? and user_Password =?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, pass);

			rs = pst.executeQuery();
			if (rs.next()) {
				itemLogin = new Person(rs.getInt(1), rs.getString(2), pass, rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), email);
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

		return itemLogin;
	}

	public int updateActive(String email) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "Update Person set user_Active =  0 where user_Email = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);

			result = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Person getUserByEmailActive(String email) {
		Person itemLogin = null;
		conn = DBConnectUtil.getConnection();
		String sql = "SELECT * FROM Person WHERE user_Email = ? and user_Active = 0";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				itemLogin = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), email);
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
		return itemLogin;
	}

	public int addUser(Person item) {
		int result = 0;
		conn = DBConnectUtil.getConnection();
		String sql = "insert into Person values (?,?,?,?,?,?,?,default)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, item.getUser_Name());
			pst.setString(2, item.getUser_Password());
			pst.setString(3, item.getUser_Avartar());
			pst.setString(4, item.getUser_Forget());
			pst.setInt(5, item.getUser_Active());
			pst.setInt(6, item.getUser_Role());
			pst.setString(7, item.getUser_Email());

			result = pst.executeUpdate();

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

		return result;
	}

	// client 1
	public int addUserClient1(Person item) {
		int result = 0;
		conn = DBConnectUtil.getConnectionClient1();
		String sql = "insert into Person values (?,?,?,?,?,?,?,default)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, item.getUser_Name());
			pst.setString(2, item.getUser_Password());
			pst.setString(3, item.getUser_Avartar());
			pst.setString(4, item.getUser_Forget());
			pst.setInt(5, item.getUser_Active());
			pst.setInt(6, item.getUser_Role());
			pst.setString(7, item.getUser_Email());

			result = pst.executeUpdate();

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

		return result;
	}

	// client 2
	public int addUserClient2(Person item) {
		int result = 0;
		conn = DBConnectUtil.getConnection();
		String sql = "insert into Person values (?,?,?,?,?,?,?,default)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, item.getUser_Name());
			pst.setString(2, item.getUser_Password());
			pst.setString(3, item.getUser_Avartar());
			pst.setString(4, item.getUser_Forget());
			pst.setInt(5, item.getUser_Active());
			pst.setInt(6, item.getUser_Role());
			pst.setString(7, item.getUser_Email());

			result = pst.executeUpdate();

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

		return result;
	}

	public int dellUser(Person person) {
		int result = 0;
		conn = DBConnectUtil.getConnection();

		String sql = "DELETE FROM Person WHERE user_Email = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, person.getUser_Email());
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

	public Person getItemById(int id) {
		Person itemEdit = null;
		conn = DBConnectUtil.getConnection();

		String sql = "select * from Person where user_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				itemEdit = new Person(id, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8));
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
		return itemEdit;
	}

	//client1
	public int editItem(Person itemEdit) {
		int result = 0;
		conn = DBConnectUtil.getConnection();

		String sql = "update Person set user_Name = ?, user_Password=?,user_Avartar=? where user_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemEdit.getUser_Name());
			pst.setString(2, itemEdit.getUser_Password());
			pst.setString(3, itemEdit.getUser_Avartar());
			pst.setInt(4, itemEdit.getUser_ID());
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

	//client1
	public List<Person> getListUserClient1(Person userLogin) {
		ArrayList<Person> list = new ArrayList<Person>();
		conn = DBConnectUtil.getConnection();
		String sql = "Select * From Person where user_Email = ? or user_Role = 4";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, userLogin.getUser_Email());

			rs = pst.executeQuery();
			while (rs.next()) {
				Person itemLogin = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
				list.add(itemLogin);
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
		return list;
	}
	
	//client1
		public List<Person> getListUserClient2(Person userLogin) {
			ArrayList<Person> list = new ArrayList<Person>();
			conn = DBConnectUtil.getConnection();
			String sql = "Select * From Person where user_Email = ? or user_Role = 5";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, userLogin.getUser_Email());

				rs = pst.executeQuery();
				while (rs.next()) {
					Person itemLogin = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
					list.add(itemLogin);
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
			return list;
		}

	public int delItem(Person itemDel) {
		int result = 0;
		conn = DBConnectUtil.getConnection();

		String sql = "DELETE FROM Person WHERE user_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, itemDel.getUser_ID());
			result = pst.executeUpdate();

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
		return result;
	}

	public List<Person> getListUserEmployee() {
		ArrayList<Person> list = new ArrayList<Person>();
		conn = DBConnectUtil.getConnection();
		String sql = "Select * From Person where user_Role = 1 or user_Role = 2 or user_Role = 3";
		try {
			st = conn.createStatement();

			rs = st.executeQuery(sql);
			while (rs.next()) {
				Person itemLogin = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
				list.add(itemLogin);
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

	public Person getItemByEmail(String email) {
		Person item = null;
		conn = DBConnectUtil.getConnection();
		String sql = "SELECT * FROM Person WHERE user_Email = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), email);
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

	public Person checkLoginForget(String email, String code) {
		Person item = null;
		conn = DBConnectUtil.getConnection();
		String sql = "SELECT * FROM Person WHERE user_Email = ? and user_Forget = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, code);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), email);
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

	public int updateForget(Person item) {
		int result = 0;
		conn = DBConnectUtil.getConnection();

		String sql = "update Person set user_Forget = ? where user_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getUser_Forget());
			pst.setInt(2, item.getUser_ID());

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
