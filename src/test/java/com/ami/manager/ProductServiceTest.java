package com.ami.manager;

import com.ami.dao.GenericDaoImpl;
import com.ami.entity.Product;
import com.ami.exceptions.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by amit.khandelwal on 24/08/16.
 *
 * <p>This is a test written using the mockito style</p>
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
    private Product mockedProduct = new Product("1", "Item 1", "This is item 1");
    String query = "from Product where itemId = ?";


    @Test
    public void getItemNameUpperCase() {
        // Given
        Product mockedProduct = new Product("1", "Item 1", "This is item 1");
        String query = "from Product where itemId = ?";
        List<Object> list = new ArrayList<Object>();
        list.add("1");
        try{
            when(genericDao.getEntity(query,list)).thenReturn(mockedProduct);
        }catch (Exception e){
         fail();
        }

        // When
        Product product = null;
        try {
            product = productServices.getProduct("1");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        //then
        try {
            verify(genericDao, times(1)).getEntity(query,list);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("Item 1",product.getItemName());
    }
}
