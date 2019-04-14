package com.ami.manager;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.ami.dao.GenericDaoImpl;
import com.ami.entity.Product;

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

    private static String hql;

    @Before
    public void setup() {
        hql = "from Product";
        when(session.save("foo")).thenReturn("foo");
        when(session.save(null)).thenReturn(NullPointerException.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery(hql)).thenReturn(query);
    }

    @Test
    public void testAddEntity() throws Exception {
        Assert.assertEquals("foo",genericDao.addEntity("foo"));
        verify(session).save("foo");
    }

    @Test
    public void testGetEntities() throws Exception {
        List list = new ArrayList<>();
        list.add("1");
        Product mockedProduct = new Product("1","first","desc");
        Product mockedProduct2 = new Product("2","first","desc");
        Product mockedProduct3 = new Product("3","first","desc");
        List<Product> products = Arrays.asList(mockedProduct,mockedProduct2,mockedProduct3);
        when(query.list()).thenReturn(products);
        List<Product> actualProducts = genericDao.getEntities(hql,list);
        Assert.assertEquals(3,actualProducts.size());
        Assert.assertEquals(products,actualProducts);
    }

    @Test
    public void testGetEntitiesWithNull() throws Exception {
        String hql = "from Product";
        List list = null;
        when(query.list()).thenReturn(list);
        List<Product> actualProducts = genericDao.getEntities(hql,list);
        Assert.assertNull(actualProducts);
    }

    @Test
    public void testGetEntitiesWithZeroSize() throws Exception {
        String hql = "from Product";
        List list = new ArrayList();
        when(query.list()).thenReturn(list);
        List<Product> actualProducts = genericDao.getEntities(hql,list);
        Assert.assertNotNull(actualProducts);
    }

    @Test
    public void testDelete() throws Exception{
      Assert.assertTrue(genericDao.deleteEntity("foo"));
        verify(session).delete("foo");
    }

    @Test
    public void testGet() throws Exception{
        Product product = new Product();
        String q = "from Product where itemId = ?";
        List<Object> list = new ArrayList<Object>();
        list.add("200");
        when(session.createQuery(q)).thenReturn(query);
        when(query.uniqueResult()).thenReturn(product);
        Assert.assertEquals(product,genericDao.getEntity(q,list));
        verify(query).uniqueResult();
    }



}
