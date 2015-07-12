package com.souq.resources;

import com.souq.constants.RequestConstants;
import com.souq.entity.ProductCategory;
import com.souq.manager.CategoryServices;
import com.souq.request.KentroRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Created by ami on 6/13/2015.
 */

@Path("/api/v1/manage/")
public class CategoryController {

    @Context private HttpServletRequest httpServletRequest;

    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Inject
    CategoryServices categoryServicesImpl;

    @PostConstruct
    public void printServiceBeans()
    {
        System.out.println("normal autowitred bean"+categoryServicesImpl);
        System.out.println("normal autowitred bean"+categoryServicesImpl); System.out.println("normal autowitred bean"+categoryServicesImpl);
        System.out.println("normal autowitred bean"+categoryServicesImpl); System.out.println("normal autowitred bean"+categoryServicesImpl);
        System.out.println("normal autowitred bean"+categoryServicesImpl); System.out.println("normal autowitred bean"+categoryServicesImpl);
        System.out.println("normal autowitred bean"+categoryServicesImpl);
    }


    // post a category
    @Path("categories")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(ProductCategory productCategory , @Context UriInfo uriInfo) {
        Response response = null;
        KentroRequestContext kentroRequestContext = (KentroRequestContext)httpServletRequest.getAttribute(RequestConstants.KENTRO_REQUEST_CONTEXT);
        logger.info("inside create item request with"+ kentroRequestContext);
        try {
            // categoryServices.validateCategory(ProductCategory);

            response = Response.status(Response.Status.CREATED)
                    .entity(categoryServicesImpl
                            .addCategory(productCategory)).build();
        }
        catch (Exception e) {
           e.printStackTrace();
            logger.error("exception occured in createCategory",e);
        }
        logger.info("Time taken by createCategory request::"+ (System.currentTimeMillis()-kentroRequestContext.getInTime()));
        return response;
    }

    // update a category
    @PUT
    @Path("categories/{catguid}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(ProductCategory ProductCategory,
                                   @PathParam("catguid") String itemId, @Context UriInfo uriInfo) {
        Response response = null;
        try {
            response = Response.status(Response.Status.OK)
                    .entity(categoryServicesImpl.updateCategory(
                            ProductCategory, itemId)).build();
        } catch (Exception e) {
         e.printStackTrace();
        }
        return response;
    }

    // Get Category
    // TODO need to remove exception handling part
    // as this is just added for the poc of spring exception handling
    @GET
    @Path("categories/{itemId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCategory(@PathParam("itemId") String itemId) throws Exception {
        Response response = null;
        try {
            response = Response.status(Response.Status.OK)
                    .entity(categoryServicesImpl.getCategoryById(itemId)).build();
        } /*catch (ResourceNotFoundException re) {
			response = Response.status(Response.Status.NOT_FOUND)
					.entity(" RESOURCE_NOT_FOUND Exception").build();
			logger.logException(LogLevel.ERROR, "Get Category failed ::", re);
		}*/ catch (Exception e) {
			/*ErrorsDTO errorsDTO = Utility.createError(ErrorConstants.INTERNAL_SYSTEM_ERROR,e.getMessage());
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(errorsDTO).build();
			logger.logException(LogLevel.ERROR,
					"exception occured while getting a category", e);*/
            System.out.println("coming inside the ex");
            e.printStackTrace();
            throw e;
        }
        return response;
    }

    /*// Get List of All the Category
    @GET
    @Path("categories")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCategories() {
        List<ProductCategory> categoryList = null;
        Response response = null;
        try {
            categoryList = categoryServicesImpl.getCategoryList();
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
