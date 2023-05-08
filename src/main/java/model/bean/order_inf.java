package model.bean;

import java.sql.Date;

public class order_inf {
	
	private int id;
	private String code;
	private Date date;
	private int id_person;
	private String address;
	private int status;
	private String phone;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId_person() {
		return id_person;
	}
	public void setId_person(int id_person) {
		this.id_person = id_person;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public order_inf() {
		super();
		// TODO Auto-generated constructor stub
	}
	public order_inf(int id, String code, Date date, int id_person, String address, int status, String phone) {
		super();
		this.id = id;
		this.code = code;
		this.date = date;
		this.id_person = id_person;
		this.address = address;
		this.status = status;
		this.phone = phone;
	}
	
	

}
