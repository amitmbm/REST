package com.ami.manager;

import com.ami.dao.GenericDao;
import com.ami.dao.GenericDaoImpl;
import com.ami.entity.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author: Amit Khandelwal
 * Date: 03/09/16
 */

@RunWith(MockitoJUnitRunner.class)
public class GenericDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Query query;

    @InjectMocks
    private GenericDaoImpl genericDao;

    @Before
    public void setup() {
        when(session.save(new Product())).thenReturn(Product.class);
        when(session.save(null)).thenReturn(Exception.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);

    }

    @Test
    public void testAddEntity() throws Exception {
        Product product = genericDao.addEntity(new Product());
        Assert.assertNotNull(product);
    }

    @Test
    public void testAddEntityException() throws Exception {
        Assert.assertEquals(null, genericDao.addEntity(null));
    }

    @Test
    public void testGetEntities() throws Exception {
        String hql = "from Product";
        List list = new ArrayList<>();
        list.add("1");
        when(session.createQuery(hql)).thenReturn(query);
        Product mockedProduct = new Product("1","first","desc");
        Product mockedProduct2 = new Product("2","first","desc");
        Product mockedProduct3 = new Product("3","first","desc");
        List<Product> products = Arrays.asList(mockedProduct,mockedProduct2,mockedProduct3);
        when(query.list()).thenReturn(products);
        List<Product> actualProducts = genericDao.getEntities(hql,list);
        Assert.assertEquals(3,actualProducts.size());
        Assert.assertEquals(products,actualProducts);
    }

}
