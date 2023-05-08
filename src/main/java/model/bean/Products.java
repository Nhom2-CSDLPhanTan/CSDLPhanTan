package model.bean;

public class Products {
	private String id;
	private String name;
	private String detail;
	private int price;
	private String c_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public Products(String id, String name, String detail, int price, String c_id) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.price = price;
		this.c_id = c_id;
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
