package mws.pojo;

import java.util.List;

public class Category {
	private Integer id;
	private String name;
	private List<Product> products;
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", products=" + products + "]";
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Integer id, String name, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
	}
	
	
	

}
