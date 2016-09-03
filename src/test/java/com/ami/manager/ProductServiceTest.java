package com.ami.manager;

import com.ami.dao.GenericDaoImpl;
import com.ami.entity.Product;
import com.ami.exceptions.ResourceNotFoundException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by amit.khandelwal on 24/08/16.
 *
 * <p>This is a test written using the mockito style and uses given-when-then style </p>
 * These tests cover 97% code of productService.java
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private GenericDaoImpl genericDao;

    @InjectMocks
    private ProductServicesImpl productServices;


    // Either below code or @RunWith annotation on top of the class is required to run the tests.
   /* @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }*/

    private static Product mockedProduct;
    private static Product updatedMockedProduct;
    private static String query;
    private static String getAllProductsQuery;
    private static List<Object> list;
    private static List products = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        mockedProduct = new Product("1", "Item 1", "This is item 1");
        query = "from Product where itemId = ?";
        list = new ArrayList<>();
        list.add("1");
        getAllProductsQuery = "from Product";
        // given
        when(genericDao.getEntity(query, list)).thenReturn(mockedProduct);
        when(genericDao.addEntity(mockedProduct)).thenReturn(mockedProduct);
        when(genericDao.updateEntity(mockedProduct)).thenReturn(mockedProduct);
        when(genericDao.deleteEntity(mockedProduct)).thenReturn(true);

    }


    @Test
    public void testGetProduct() throws Exception {

        //when
        Product product = productServices.getProduct("1");

        // then or verify
        Assert.assertNotNull(product);
        Assert.assertEquals(product.getItemId(), mockedProduct.getItemId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testRNFException() throws Exception {
        productServices.getProduct("2");
    }

    @Test
    public void testAddProduct() throws Exception {
        Product product = productServices.add(mockedProduct);
        Assert.assertEquals("Item 1",product.getItemName());
    }

    @Test
    public void testUpdateProduct() throws Exception{
        updatedMockedProduct = new Product();
        updatedMockedProduct.setItemId("1");
        updatedMockedProduct.setItemName("name updated");
        // given
        updatedMockedProduct = productServices.update(updatedMockedProduct,"1");
        Assert.assertEquals("name updated",updatedMockedProduct.getItemName());
    }

    @Test
    public void testDelete() throws Exception{
        productServices.delete("1");
    }

    @Test
    public void testGetProducts() throws Exception{
        products.add(new Product());
        products.add(new Product());
        when(genericDao.getEntities(getAllProductsQuery,null)).thenReturn(products);
        products=productServices.getProducts();
        Assert.assertEquals(2,products.size());
    }
}
