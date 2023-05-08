package model.bean;

public class Picture {
	private int id;
	private String url;
	private String id_Pro;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId_Pro() {
		return id_Pro;
	}
	public void setId_Pro(String id_Pro) {
		this.id_Pro = id_Pro;
	}
	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Picture(int id, String url, String id_Pro) {
		super();
		this.id = id;
		this.url = url;
		this.id_Pro = id_Pro;
	}
	
	
}
