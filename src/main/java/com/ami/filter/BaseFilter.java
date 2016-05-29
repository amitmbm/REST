package com.ami.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import com.ami.constants.RequestConstants;
import com.ami.request.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/*@Provider
public class BaseFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static Logger logger = LoggerFactory.getLogger(BaseFilter.class);
    @Context private HttpServletRequest httpServletRequest;


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        logger.info("inside the ContainerRequestContext filter");
        MDC.remove(RequestConstants.REQUEST_ID);
        MDC.clear();
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        logger.info("Inside filter method of BaseFilter");

        KentroRequestContext kentroRequestContext = new KentroRequestContext(httpServletRequest);

        logger.info("printing the kentrorequestcontext"+kentroRequestContext);

        MDC.put(RequestConstants.REQUEST_ID, kentroRequestContext.getRequestId());

        httpServletRequest.setAttribute(RequestConstants.REQUEST_CONTEXT, kentroRequestContext);
    }
}*/

public class BaseFilter implements Filter{
    private static Logger logger = LoggerFactory.getLogger(BaseFilter.class);
    private RequestContext requestContext;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        requestContext = new RequestContext(httpServletRequest);
        MDC.put(RequestConstants.REQUEST_ID, requestContext.getRequestId());

        logger.info("Enter inside filter");

        httpServletRequest.setAttribute(RequestConstants.REQUEST_CONTEXT, requestContext);

        filterChain.doFilter(servletRequest, servletResponse);
        //Clear context
        MDC.remove(RequestConstants.REQUEST_ID);
        MDC.clear();
        logger.info("cleared requestid in filter");
    }

    @Override
    public void destroy() {
        logger.info("inside destroy method");
    }
}
