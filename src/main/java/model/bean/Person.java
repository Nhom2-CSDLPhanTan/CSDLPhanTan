package model.bean;

public class Person {
	private int user_ID;
	private String user_Name;
	private String user_Password;
	private String user_Avartar;
	private String user_Forget;
	private int user_Active;
	private int user_Role;
	private String user_Email;
	
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getUser_Password() {
		return user_Password;
	}
	public void setUser_Password(String user_Password) {
		this.user_Password = user_Password;
	}
	public String getUser_Avartar() {
		return user_Avartar;
	}
	public void setUser_Avartar(String user_Avartar) {
		this.user_Avartar = user_Avartar;
	}
	public String getUser_Forget() {
		return user_Forget;
	}
	public void setUser_Forget(String user_Forget) {
		this.user_Forget = user_Forget;
	}
	public int getUser_Active() {
		return user_Active;
	}
	public void setUser_Active(int user_Active) {
		this.user_Active = user_Active;
	}
	public int getUser_Role() {
		return user_Role;
	}
	public void setUser_Role(int user_Role) {
		this.user_Role = user_Role;
	}
	public String getUser_Email() {
		return user_Email;
	}
	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}
	public Person(int user_ID, String user_Name, String user_Password, String user_Avartar, String user_Forget,
			int user_Active, int user_Role, String user_Email) {
		super();
		this.user_ID = user_ID;
		this.user_Name = user_Name;
		this.user_Password = user_Password;
		this.user_Avartar = user_Avartar;
		this.user_Forget = user_Forget;
		this.user_Active = user_Active;
		this.user_Role = user_Role;
		this.user_Email = user_Email;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
