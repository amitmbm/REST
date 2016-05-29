package com.ami.manager;

import com.ami.dao.GenericDao;
import com.ami.entity.Product;
import com.ami.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named
public class ProductServicesImpl implements ProductServices {

    @Inject
    GenericDao genericDao;

    @Transactional
    @Override
    public Product add(Product product) throws Exception {
        product.setItemId(UUID.randomUUID().toString());
        return genericDao.addEntity(product);
    }

    @Transactional
    @Override
    public Product update(Product product, String itemId) throws Exception {
        try {
            Product newProduct = getProduct(itemId);
            if (product.getItemDesc() != null)
                newProduct.setItemDesc(product.getItemDesc());

            if (product.getItemName() != null)
                newProduct.setItemName(product.getItemName());

            return genericDao.updateEntity(newProduct);
        } catch (ResourceNotFoundException re) {
            throw re;
        }
    }

    @Transactional
    @Override
    public Product getProduct(String id) throws Exception {
        String query = "from Product where itemId = ?";
        List<Object> list = new ArrayList<Object>();
        list.add(id);
        Product product = genericDao.getEntity(query, list);
        if (product == null)
            throw new ResourceNotFoundException("Cat-id :" + id + " not exist");
        return product;
    }

    @Transactional
    @Override
    public List<Product> getProducts() throws Exception {
        String query = "from Product";
        return genericDao.getEntities(query, null);
    }


    @Transactional
    @Override
    public boolean delete(String id) throws Exception {
        return genericDao.deleteEntity(getProduct(id));
    }

}

