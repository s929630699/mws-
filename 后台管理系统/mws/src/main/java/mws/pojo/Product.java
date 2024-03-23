package mws.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
	private Integer id;
	private String name;
	private Double price;
	private Integer categoryId;
	private Integer pnum;
	private String imgurl;
	private String description;
	private int shelfLife;
	private Category category;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", categoryId=" + categoryId + ", pnum="
				+ pnum + ", imgurl=" + imgurl + ", description=" + description + ", manufactureDate=" + ", shelfLife=" + shelfLife + ", category=" + category + "]";
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Integer id, String name, Double price, Integer categoryId, Integer pnum, String imgurl,
			String description, Date manufactureDate, int shelfLife, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
		this.pnum = pnum;
		this.imgurl = imgurl;
		this.description = description;
		this.shelfLife = shelfLife;
		this.category = category;
	}
	
	
	
	

}
