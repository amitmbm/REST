package com.ami.resources;

import com.ami.constants.RequestConstants;
import com.ami.entity.Product;
import com.ami.manager.ProductServices;
import com.ami.request.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by ami on 6/13/2015.
 */

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Context
    private HttpServletRequest httpServletRequest;

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Inject
    ProductServices productServicesImpl;

    // post a product
    @POST
    public Response addProduct(Product product, @Context UriInfo uriInfo) {
        Response response = null;
        RequestContext requestContext = (RequestContext) httpServletRequest.getAttribute(RequestConstants.REQUEST_CONTEXT);
        logger.info("inside create item request with" + requestContext);
        try {
            response = Response.status(Response.Status.CREATED)
                    .entity(productServicesImpl
                            .add(product)).build();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception occurred in addProduct", e);
        }
        logger.info("Time taken by addProduct request::" + (System.currentTimeMillis() - requestContext.getInTime()));
        return response;
    }

    //update a product
    @PUT
    @Path("{id}")
    public Response updateProduct(Product Product,
                                  @PathParam("id") String itemId, @Context UriInfo uriInfo) {
        Response response = null;
        try {
            response = Response.status(Response.Status.OK)
                    .entity(productServicesImpl.update(
                            Product, itemId)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    // Get product
    @GET
    @Path("{id}")
    public Response getProduct(@PathParam("id") String id) throws Exception {
        Response response = null;
        try {
            response = Response.status(Response.Status.OK)
                    .entity(productServicesImpl.getProduct(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return response;
    }

    /*// Get List of All the products
    @GET
    @Path("categories")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCategories() {
        List<ProductCategory> categoryList = null;
        Response response = null;
        try {
            categoryList = categoryServicesImpl.getProducts();
            // list of category DTO
            List<ProductCategory> listProductCategory = new ArrayList<ProductCategory>();
            for(int i=0;i<categoryList.size();i++)
            {
                ProductCategory ProductCategory = new ProductCategory(categoryList.get(i));
                listProductCategory.add(ProductCategory);

            }
            GenericEntity<List<ProductCategory>> genericEntity = new GenericEntity<List<ProductCategory>>(
                    listProductCategory) {
            };
            response = Response.status(Response.Status.OK).entity(genericEntity)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
*/
}
