package mws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mws.service.ProductService;
import mws.dao.ProductMapper;
import mws.pojo.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<Product> getProductListWhere(Product product) {
		return this.productMapper.getProductList(product);
	}
	
	@Override
	public Product getProductById(int id){
		return this.productMapper.getProductById(id);
	}
	
	@Override
	public List<Product> getProductByName(String name){
		return this.productMapper.getProductByName(name);
	}
	
	@Override
	public List<Product> getProductByCategoryId(int categoryId){
		return this.productMapper.getProductByCategoryId(categoryId);
	}

	@Override
	public List<Product> allProduct(Product product) {
		return this.productMapper.allProduct(product);
	}
	

	@Override
	public int insertProduct(Product product){
		return this.productMapper.insertProduct(product);
	}
	
	@Override
	public int updateProduct(Product product){
		return this.productMapper.updateProduct(product);
	}
	
	@Override
	public int deleteProductById(int id) {
		return this.productMapper.deleteProductById(id);
	}
	
	
}
