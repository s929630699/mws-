package mws.service;

import java.util.List;

import mws.pojo.Product;

public interface ProductService {
	public List<Product> getProductListWhere(Product product);
	
	public List<Product> allProduct(Product product);
	
	public Product getProductById(int id);
	
	public List<Product> getProductByName(String name);
	
	public List<Product> getProductByCategoryId(int categoryId);
	
	public int insertProduct(Product product);
	
	public int updateProduct(Product product);
	
	public int deleteProductById(int id);
	
	

}
