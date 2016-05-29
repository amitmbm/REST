package com.ami.manager;

import java.util.List;

import com.ami.entity.ProductCategory;

public interface CategoryServices {
	
	// category related stubs
	public ProductCategory addCategory(ProductCategory ProductCategory) throws Exception;
	public ProductCategory updateCategory(ProductCategory ProductCategory, String catGuid) throws Exception;
	public ProductCategory getCategoryById(String guid) throws Exception;
	public ProductCategory getCategoryByName(String name) throws Exception;
	public List<ProductCategory> getCategoryList() throws Exception;
	public boolean deleteCategory(String guid) throws Exception;
}
