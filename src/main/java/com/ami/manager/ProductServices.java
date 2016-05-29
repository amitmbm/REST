package com.ami.manager;

import java.util.List;

import com.ami.entity.Product;

public interface ProductServices {
	
	// category related stubs
	public Product add(Product Product) throws Exception;
	public Product update(Product Product, String id) throws Exception;
	public Product getProduct(String id) throws Exception;
	public List<Product> getProducts() throws Exception;
	public boolean delete(String id) throws Exception;
}
