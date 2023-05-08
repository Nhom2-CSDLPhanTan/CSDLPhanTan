package model.bean;

public class Category {
	private String cat_Id;
	private String cat_Name;
	private String cat_Detail;
	public String getCat_Id() {
		return cat_Id;
	}
	public void setCat_Id(String cat_Id) {
		this.cat_Id = cat_Id;
	}
	public String getCat_Name() {
		return cat_Name;
	}
	public void setCat_Name(String cat_Name) {
		this.cat_Name = cat_Name;
	}
	public String getCat_Detail() {
		return cat_Detail;
	}
	public void setCat_Detail(String cat_Detail) {
		this.cat_Detail = cat_Detail;
	}
	public Category(String cat_Id, String cat_Name, String cat_Detail) {
		super();
		this.cat_Id = cat_Id;
		this.cat_Name = cat_Name;
		this.cat_Detail = cat_Detail;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
