package com.ami.manager;

import com.ami.entity.Product;
import com.ami.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author: Amit Khandelwal
 * Date: 03/09/16
 * This Unit tests has very less coverage and explanation of why can be found at
 * href :- http://stackoverflow.com/questions/3690033/mockito-passes-but-code-coverage-still-low comments section of
 * jon skeet (thodu stackoverflow moderator).
 *
 */

@Ignore
public class ProductSvcTest {
    private static ProductServices mockedProductServices;
    private static Product product1;
    private static Product product2;

    @BeforeClass
    public static void setUp() throws Exception {
        mockedProductServices = mock(ProductServices.class);
        product1 = new Product("1","first","first-desc");
        product2 = new Product();
        product2.setItemId("2");
        product2.setItemName("second");
        product2.setItemDesc("second-desc");
        when(mockedProductServices.getProducts()).thenReturn(Arrays.asList(product1,product2));
        when(mockedProductServices.getProduct("1")).thenReturn(product1);
        when(mockedProductServices.add(product2)).thenReturn(product2);
        when(mockedProductServices.delete("1")).thenReturn(true);
        when(mockedProductServices.getProduct("2")).thenThrow(new ResourceNotFoundException());
    }

    @Test
    public void addProductTest() throws Exception {
        Product product = mockedProductServices.add(product2);
        Assert.assertNotNull(product);
        Assert.assertEquals("Object creation is failed",product2,product);
    }

    @Test
    public void getProductTest() throws Exception {
        Product product = mockedProductServices.getProduct("1");
        Assert.assertNotNull(product);
        Assert.assertEquals(product.getItemId(),"1");
    }

    @Test
    public void getProducts() throws Exception{
        List<Product> products = mockedProductServices.getProducts();
        Assert.assertNotNull(products);
        Assert.assertEquals(products.size(),2);
        Assert.assertEquals(products.get(0).getItemId(),"1");
        Assert.assertEquals(products.get(1).getItemId(),"2");
    }

    @Test
    public void deleteProductTest() throws Exception {
        boolean isDelete = mockedProductServices.delete("1");
        Assert.assertEquals(true,isDelete);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testResourceNotFoundException() throws Exception {
        mockedProductServices.getProduct("2");
    }

}
