package model.bean;

public class Cart {
	
	private int id;
	private String idPro;
	private int number;
	private int idPerson;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(int id, String idPro, int number, int idPerson) {
		super();
		this.id = id;
		this.idPro = idPro;
		this.number = number;
		this.idPerson = idPerson;
	}
	
}
