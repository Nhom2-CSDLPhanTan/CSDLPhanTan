package model.bean;

public class OrderDetail {
	
	private int id;
	private int idInfo;
	private String idPro;
	private int number;
	private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdInfo() {
		return idInfo;
	}
	public void setIdInfo(int idInfo) {
		this.idInfo = idInfo;
	}
	public String getIdPro() {
		return idPro;
	}
	public void setIdPro(String idPro) {
		this.idPro = idPro;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetail(int id, int idInfo, String idPro, int number, int price) {
		super();
		this.id = id;
		this.idInfo = idInfo;
		this.idPro = idPro;
		this.number = number;
		this.price = price;
	}
	
	
	
}
