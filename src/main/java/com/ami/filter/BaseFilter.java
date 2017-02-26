package com.ami.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.ami.constants.RequestConstants;
import com.ami.request.RequestContext;

/**
 * All the http request will pass through this filter.
 * It will log the time taken by http call.
 */
public class BaseFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(BaseFilter.class);
    private RequestContext requestContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
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
