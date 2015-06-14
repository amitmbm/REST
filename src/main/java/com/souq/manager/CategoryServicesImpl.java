package com.souq.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.souq.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.souq.dao.GenericDao;
import com.souq.entity.ProductCategory;

import javax.inject.Inject;

@Service
public class CategoryServicesImpl implements CategoryServices {

	@Inject
	GenericDao genericDao;

	// Adding a Category
	@Transactional
	@Override
	public ProductCategory addCategory(ProductCategory productCategory) throws Exception {
			productCategory.setItemId(UUID.randomUUID().toString());
			return genericDao.addEntity(productCategory);
		}

	// updating a Category
	@Transactional
	@Override
	public ProductCategory updateCategory(ProductCategory productCategory, String itemId) throws Exception {
		try{
			ProductCategory newProductCategory = getCategoryById(itemId);
			if (productCategory.getItemDesc() != null)
				newProductCategory.setItemDesc(productCategory.getItemDesc());

			if(productCategory.getItemName() != null)
				newProductCategory.setItemName(productCategory.getItemName());
            
			return genericDao.updateEntity(newProductCategory);
		}catch(ResourceNotFoundException re){
			throw re;
		}	
	}

	// Get a Category by Id
	@Transactional
	@Override
	public ProductCategory getCategoryById(String id) throws Exception {
		String query = "from ProductCategory where itemId = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(id);
		ProductCategory productCategory = genericDao.getEntity(query, list);
		if (productCategory == null)
			throw new ResourceNotFoundException("Cat-id :"+ id+ " not exist");
		return productCategory;
	}
	
	// Get a Category by Name
	@Transactional
	@Override
	public ProductCategory getCategoryByName(String name) throws Exception {
		String query = "from ProductCategory where itemName = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(name);
		ProductCategory productCategory = genericDao.getEntity(query, list);
		if (productCategory == null)
			throw new ResourceNotFoundException("Cat Name :"+ name+ " not exist");
		return productCategory;
	}
	

	// get Category List	
	@Transactional
	@Override
	public List<ProductCategory> getCategoryList() throws Exception {
		String query = "from ProductCategory";
		return genericDao.getEntities(query, null);
	}


	// delete Category by Id
	@Transactional
	@Override
	public boolean deleteCategory(String id) throws Exception {
		return genericDao.deleteEntity(getCategoryById(id));
	}

}

