/*
 * Copyright 2017-2017 ami.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of ami, Inc. ("Confidential Information")
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license
 * agreement you entered into with ami.
 */

package com.ami.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ami.entity.Product;
import com.ami.manager.ProductServices;
import com.ami.manager.ProductServicesImpl;

/**
 * Test the product controller. // TODO :- this is not working.
 * @author: Amit Khandelwal
 * Date: 2/26/17
 */
public class ProductControllerTest extends JerseyTest {
	public static ProductServices productServicesMock = Mockito.mock(ProductServicesImpl.class);
	private static Product mockedProduct = new Product("1","first-product","desc");

	@Override
	protected Application configure(){
		return new ResourceConfig(ProductController.class);
	}

	@Before
	public void setup() throws Exception {
		when(productServicesMock.getProduct("1")).thenReturn(mockedProduct);
	}

	@Test
	public void test() {
		Response response  = target("/api/products/").request("1").get(Response.class);
		assertEquals(200, response.getStatus());
	}



}
